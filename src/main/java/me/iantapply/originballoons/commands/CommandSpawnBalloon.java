package me.iantapply.originballoons.commands;

import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import me.iantapply.originballoons.builders.Balloon;
import me.iantapply.originballoons.builders.BalloonBuilder;
import me.iantapply.originballoons.commands.manager.Command;
import me.iantapply.originballoons.commands.manager.enums.CommandPermission;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandSpawnBalloon extends Command {

    public CommandSpawnBalloon(JavaPlugin m) {
        super(m);
        this.addCommandAlias("sb");
        this.addCommandAlias("spawnballoon");
        this.setCommandDescription("Spawns a balloon");
        this.setCommandSyntax("/spawnballoon <balloon>");
        this.setRequiredPermission(CommandPermission.ADMINISTRATOR);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(NamedTextColor.RED + "You must be a player to use this command!");
            return;
        }

        Player player = (Player) sender;

        // Check for the correct amount of arguments
        if (args.length == 1) {
            // Check if the balloon exists
            BalloonType balloonType = OriginBalloons.balloonTypeMap.get(args[0].toLowerCase());

            if (balloonType != null) {
                // Check if the player already has a balloon
                if (OriginBalloons.getPlayerBalloon(player.getUniqueId()) != null) {
                    sender.sendMessage(NamedTextColor.RED + "You already have a balloon spawned! Please destroy it first before using this command.");
                    return;
                }

                // Spawn the balloon
                BalloonBuilder builder = new BalloonBuilder(balloonType, player);
                Balloon balloon = builder.build();
                balloon.initialize();
                balloon.run();

                // Set the player's balloon
                OriginBalloons.setPlayerBalloon(player.getUniqueId(), balloon);
            } else {
                sender.sendMessage(NamedTextColor.RED + "Invalid argument. Cannot find a balloon with that name.");
            }
        } else {
            sender.sendMessage(NamedTextColor.RED + "Invalid argument. Please provide a balloon name.");
        }
    }
}
