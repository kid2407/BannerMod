package de.kid2407.bannermod.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

/**
 * User: Tobias Franz
 * Date: 28.04.2020
 * Time: 17:31
 */
public class GuiHandler implements IGuiHandler {

    public final static int GUIID = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUIID) {
            return new GuiContainerMod(world, x, y, z, player);
        }

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUIID) {
            return new GuiWindow(world, x, y, z, player, new GuiContainerMod(world, x, y, z, player));
        }

        return null;
    }
}
