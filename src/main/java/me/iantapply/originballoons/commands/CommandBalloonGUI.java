package me.iantapply.originballoons.commands;

import me.iantapply.originballoons.commands.manager.Command;
import me.iantapply.originballoons.commands.manager.enums.CommandAccess;
import me.iantapply.originballoons.commands.manager.enums.CommandPermission;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandBalloonGUI extends Command {

    public CommandBalloonGUI(JavaPlugin m) {
        super(m);
        this.addCommandAlias("balloons");
        this.addCommandAlias("balloongui");
        this.setCommandDescription("Opens up a catalog of balloons to spawn");
        this.setCommandSyntax("/balloons");
        this.setRequiredPermission(CommandPermission.PLAYER);
        this.setRequiredAccess(CommandAccess.DISABLED);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage("This command is not currently available.");
    }
}
