package me.iantapply.originballoons.commands;

import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.builders.Balloon;
import me.iantapply.originballoons.commands.manager.Command;
import me.iantapply.originballoons.commands.manager.enums.CommandPermission;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandDestroyBalloon extends Command {

    public CommandDestroyBalloon(JavaPlugin m) {
        super(m);
        this.addCommandAlias("db");
        this.addCommandAlias("destroyballoon");
        this.setCommandDescription("Destroys the current balloon on a player if they have one");
        this.setCommandSyntax("/destroyballoon");
        this.setRequiredPermission(CommandPermission.PLAYER);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(NamedTextColor.RED + "You must be a player to use this command!");
            return;
        }

        Player player = (Player) sender;
        Balloon playerBalloon = OriginBalloons.getPlayerBalloon(player.getUniqueId());

        if (playerBalloon != null) {
            playerBalloon.destroy();
            OriginBalloons.removePlayerBalloon(player.getUniqueId());
            sender.sendMessage(NamedTextColor.GREEN + "Your balloon has been destroyed!");
        } else {
            sender.sendMessage(NamedTextColor.RED + "You do not have a balloon to destroy!");
        }
    }
}
