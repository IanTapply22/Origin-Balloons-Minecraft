package me.iantapply.originballoons.commands;

import me.iantapply.originballoons.OriginBalloons;
import me.iantapply.originballoons.balloonTypes.BalloonType;
import me.iantapply.originballoons.builders.BalloonBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("destroy") && OriginBalloons.playerBalloon != null) {
                    OriginBalloons.playerBalloon.destroy();
                }

                if (OriginBalloons.playerBalloon != null) {
                    OriginBalloons.playerBalloon.destroy();
                }

                BalloonType balloon = OriginBalloons.balloonTypeMap.getOrDefault(args[0].toLowerCase(), null);

                // Spawn a new balloon if "destroy" argument is not provided
                OriginBalloons.playerBalloon = new BalloonBuilder(balloon, player).build();
                OriginBalloons.playerBalloon.initialize();
                OriginBalloons.playerBalloon.run();
            } else {
                player.sendMessage("Invalid argument. Cannot find a balloon with that name.");
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (args.length == 1){

            List<String> arguments = new ArrayList<>(OriginBalloons.balloonTypeMap.keySet());

            arguments.add("destroy");

            return arguments;
        }

        return null;
    }
}
