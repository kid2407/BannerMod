package de.kid2407.bannermod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class BannerCommand extends CommandBase {
    @Override
    public String getName() {
        return "banner";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "command.banner.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        TextComponentString text = new TextComponentString("Der Mod lebt!");
        sender.sendMessage(text);
    }
}
