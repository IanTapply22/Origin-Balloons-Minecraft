package me.iantapply.originballoons.commands.manager;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import me.iantapply.originballoons.commands.manager.enums.CommandAccess;
import me.iantapply.originballoons.commands.manager.enums.CommandPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class Command {

    protected JavaPlugin plugin;

    @Getter @Setter
    private CommandPermission requiredPermission = CommandPermission.PLAYER;
    @Getter @Setter
    private CommandAccess requiredAccess = CommandAccess.ENABLED;
    @Getter @Setter
    private String commandSyntax;
    @Getter @Setter
    private String commandDescription;
    @Getter
    private final ArrayList<String> commandAliases = new ArrayList<>();

    public Command(JavaPlugin providedPlugin) {
        plugin = providedPlugin;
    }

    /**
     * Adds alias commands to the current command
     * Note: All aliases added here must be added to the plugin.yml file
     * @param alias The alias to add to the command
     */
    public void addCommandAlias(String alias) {
        commandAliases.add(alias);
    }

    public abstract void execute(CommandSender sender, String[] args) throws Exception;

    /**
     * Checks if the command meets the requirements to be executed
     * @param s The sender of the command
     * @param perm The permission required to execute the command
     * @return Whether or not the command meets the requirements
     */
    public boolean hasRequirement(CommandSender s, CommandPermission perm) {
        switch (perm) {
            case PLAYER:
                if (!(s instanceof Player)) {
                    return false;
                }
                break;
            case ADMINISTRATOR:
                if (s instanceof Player) {
                    if (!s.hasPermission("originballoons.administrator")) {
                        return false;
                    }
                }
                break;
        }
        return true;
    }
}