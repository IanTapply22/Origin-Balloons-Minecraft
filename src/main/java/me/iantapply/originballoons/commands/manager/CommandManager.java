package me.iantapply.originballoons.commands.manager;

import lombok.Getter;
import me.iantapply.originballoons.commands.CommandBalloonGUI;
import me.iantapply.originballoons.commands.CommandDestroyBalloon;
import me.iantapply.originballoons.commands.CommandSpawnBalloon;
import me.iantapply.originballoons.commands.manager.enums.CommandAccess;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class CommandManager implements CommandExecutor {
    @Getter
    private final ArrayList<Command> commands;
    private final JavaPlugin plugin;

    public CommandManager(JavaPlugin providedPlugin) {
        plugin = providedPlugin;
        commands = new ArrayList<>();
        addCommand(new CommandBalloonGUI(plugin));
        addCommand(new CommandDestroyBalloon(plugin));
        addCommand(new CommandSpawnBalloon(plugin));
        registerCommands();
    }

    /**
     * Registers all commands in the commands list
     */
    public void registerCommands() {
        for (Command c : this.getCommands()) {
            for (String l : c.getCommandAliases()) {
                Objects.requireNonNull(plugin.getCommand(l)).setExecutor(this);
            }
        }
    }

    /**
     * Adds a command to the commands list
     * @param c The command to add
     */
    public void addCommand(Command c) {
        commands.add(c);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, org.bukkit.command.@NotNull Command command, @NotNull String label, String[] args) {
        for (Command c : getCommands()) {
            if (c.getCommandAliases().contains(label.toLowerCase())) {
                if (!meetsRequirements(c, sender)) {
                    sender.sendMessage(NamedTextColor.RED + "Unknown command! Please use a valid command");
                    return false;
                }

                if (c.getRequiredAccess() == CommandAccess.DISABLED) {
                    sender.sendMessage(NamedTextColor.RED + "This command is currently disabled.");
                    return false;
                }

                try {
                    c.execute(sender, args);
                } catch (Exception e) {
                    sender.sendMessage(NamedTextColor.RED + "Incorrect usage. &7(&f" + c.getCommandSyntax() + "&7)");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the player sending the command meets the requirements to execute the command
     * @param c The command to check
     * @param s The sender of the command
     * @return Whether or not the user meets the requirements
     */
    public boolean meetsRequirements(Command c, CommandSender s) {
        return c.hasRequirement(s, c.getRequiredPermission());
    }
}
