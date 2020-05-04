package de.kid2407.bannermod.gui;

import de.kid2407.bannermod.BannerCommand;
import de.kid2407.bannermod.util.TranslationHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.UUID;

/**
 * User: Tobias Franz
 * Date: 28.04.2020
 * Time: 17:38
 */
@MethodsReturnNonnullByDefault
public class GuiContainerMod extends Container {

    private final IInventory internalInventory;
    private final EntityPlayer entityPlayer;

    public static final int textColorSlot = 48;
    public static final int baseColorSlot = 50;

    private static final HashMap<UUID, Integer> textColorPerPlayer = new HashMap<>();
    private static final HashMap<UUID, Integer> baseColorPerPlayer = new HashMap<>();

    private BannerModInventoryTypes currentInventoryType = BannerModInventoryTypes.DEFAULT;

    public GuiContainerMod(World world, int x, int y, int z, EntityPlayer player) {
        this.entityPlayer = player;
        this.internalInventory = new InventoryBasic("BannerGui", true, 54);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(this.internalInventory, j + i * 9, 8 + j * 18, 22 + i * 18) {
                    @Override
                    @ParametersAreNonnullByDefault
                    public boolean canTakeStack(EntityPlayer playerIn) {
                        return false;
                    }
                });
            }
        }

        textColorPerPlayer.putIfAbsent(entityPlayer.getUniqueID(), 0);
        baseColorPerPlayer.putIfAbsent(entityPlayer.getUniqueID(), 15);

        generateDefaultInventory();
    }

    private ItemStack getColorSelector(String name, int color) {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagCompound nameTag = new NBTTagCompound();

        nameTag.setTag("Name", new NBTTagString(name));
        compound.setTag("display", nameTag);

        ItemStack itemStack = new ItemStack(Blocks.WOOL, 1, color);
        itemStack.setTagCompound(compound);

        return itemStack;
    }

    public void setTextColorForPlayer(int color) {
        textColorPerPlayer.put(entityPlayer.getUniqueID(), color);
    }

    public void setBaseColorForPlayer(int color) {
        baseColorPerPlayer.put(entityPlayer.getUniqueID(), color);
    }

    public BannerModInventoryTypes getCurrentInventoryType() {
        return currentInventoryType;
    }

    public void loadInventory(BannerModInventoryTypes type) {
        switch (type) {
            case DEFAULT:
                generateDefaultInventory();
                break;
            case TEXT_COLOR:
                generateTextColorInventory();
                break;
            case BASE_COLOR:
                generateBaseColorInventory();
                break;
        }
    }

    private void generateDefaultInventory() {
//        internalInventory.clear();
        UUID uuid = entityPlayer.getUniqueID();
        int textColor = textColorPerPlayer.get(uuid);
        int baseColor = baseColorPerPlayer.get(uuid);
        int tmpTextColor;
        int tmpBaseColor;

        String[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".split("");
        for (int i = 0; i < characters.length; i++) {
            String character = characters[i];
            if (BannerCommand.flippedCharacters.contains(character)) {
                tmpTextColor = baseColor;
                tmpBaseColor = textColor;
            } else {
                tmpTextColor = textColor;
                tmpBaseColor = baseColor;
            }
            ItemStack banner = new ItemStack(Items.BANNER, 1, tmpBaseColor);
            banner.setTagCompound(BannerCommand.generateBannerCompoundForCharacter(character, tmpTextColor, tmpBaseColor));
            internalInventory.setInventorySlotContents(i, banner);
        }

        ItemStack textColorStack = getColorSelector(TranslationHelper.translate("bannermod.gui.changeTextColor"), textColor);
        internalInventory.setInventorySlotContents(textColorSlot, textColorStack);

        ItemStack baseColorStack = getColorSelector(TranslationHelper.translate("bannermod.gui.changeBaseColor"), baseColor);
        internalInventory.setInventorySlotContents(baseColorSlot, baseColorStack);

        internalInventory.markDirty();

        currentInventoryType = BannerModInventoryTypes.DEFAULT;
    }

    private void generateTextColorInventory() {
//        internalInventory.clear();

        for (int i = 0; i < 16; i++) {
            internalInventory.setInventorySlotContents(i, getColorSelector(BannerCommand.getColorName(EnumDyeColor.byDyeDamage(i)), i));
        }

        currentInventoryType = BannerModInventoryTypes.TEXT_COLOR;
    }

    private void generateBaseColorInventory() {
//        internalInventory.clear();

        for (int i = 0; i < 16; i++) {
            internalInventory.setInventorySlotContents(i, getColorSelector(BannerCommand.getColorName(EnumDyeColor.byDyeDamage(i)), i));
        }

        currentInventoryType = BannerModInventoryTypes.BASE_COLOR;
    }

    public void giveBannerToPlayer(int slotId) {
        entityPlayer.addItemStackToInventory(internalInventory.getStackInSlot(slotId).copy());
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canInteractWith(EntityPlayer player) {
        return internalInventory.isUsableByPlayer(player);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onContainerClosed(EntityPlayer playerIn) {
        this.internalInventory.clear();
    }
}
