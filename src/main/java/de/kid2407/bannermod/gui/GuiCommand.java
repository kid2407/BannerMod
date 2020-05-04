package de.kid2407.bannermod.gui;

import de.kid2407.bannermod.BannerMod;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * User: Tobias Franz
 * Date: 28.04.2020
 * Time: 17:49
 */
@MethodsReturnNonnullByDefault
public class GuiCommand extends CommandBase {

    @Override
    public String getName() {
        return "gui";
    }

    @Override
    @ParametersAreNonnullByDefault
    public String getUsage(ICommandSender sender) {
        return "/gui";
    }

    @Override
    @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;
            BlockPos position = player.getPosition();
            BannerMod.logger.info("Kommando ausgeführt!");
            player.openGui(BannerMod.MOD_ID, GuiHandler.GUIID, player.getEntityWorld(), position.getX(), position.getY(), position.getZ());
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }
}
