package de.kid2407.bannermod.gui;

import de.kid2407.bannermod.BannerCommand;
import de.kid2407.bannermod.util.TranslationHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * User: Tobias Franz
 * Date: 28.04.2020
 * Time: 17:38
 */
@MethodsReturnNonnullByDefault
public class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {

    private IInventory internalInventory;
    private final World world;
    private final EntityPlayer entityPlayer;
    private final int x, y, z;
    private final Map<Integer, Slot> customSlots = new HashMap<>();

    public GuiContainerMod(World world, int x, int y, int z, EntityPlayer player) {
        this.world = world;
        this.entityPlayer = player;
        this.x = x;
        this.y = y;
        this.z = z;
        this.internalInventory = new InventoryBasic("BannerGui", true, 54 + 9);
        TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
        if (entity instanceof IInventory) {
            this.internalInventory = (IInventory) entity;
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(this.internalInventory, j + i * 9 + 9, 8 + j * 18, 22 + i * 18) {
                    @Override
                    @ParametersAreNonnullByDefault
                    public boolean canTakeStack(EntityPlayer playerIn) {
                        return false;
                    }
                });
            }
        }

        loadDefaultInventory();
    }

    private void loadDefaultInventory() {
        internalInventory.clear();
        int baseColor = 15;
        int textColor = 0;
        int tmpBaseColor;
        int tmpTextColor;

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
            internalInventory.setInventorySlotContents(i + 9, banner);
        }

        ItemStack textColorStack = getColorSelector(TranslationHelper.translate("bannermod.gui.changeTextColor"), textColor);
        internalInventory.setInventorySlotContents(57, textColorStack);

        ItemStack baseColorStack = getColorSelector(TranslationHelper.translate("bannermod.gui.changeBaseColor"), baseColor);
        internalInventory.setInventorySlotContents(59, baseColorStack);
    }

    private ItemStack getColorSelector(String name, int color) {
        NBTTagCompound ntc = new NBTTagCompound();
        NBTTagCompound bet = new NBTTagCompound();

        bet.setTag("Name", new NBTTagString(name));
        ntc.setTag("display", bet);

        ItemStack itemStack = new ItemStack(Blocks.WOOL, 1, color);
        itemStack.setTagCompound(ntc);

        return itemStack;
    }

    public Map<Integer, Slot> get() {
        return customSlots;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean canInteractWith(EntityPlayer player) {
        return internalInventory.isUsableByPlayer(player);
    }

    @Override
    @ParametersAreNonnullByDefault
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (!this.mergeItemStack(itemstack1, 0, 0, false)) {
                if (index < 27) {
                    if (!this.mergeItemStack(itemstack1, 27, this.inventorySlots.size(), true)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    if (!this.mergeItemStack(itemstack1, 0, 27, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                return ItemStack.EMPTY;
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }

    /**
     * Merges provided ItemStack with the first avaliable one in the
     * container/player inventor between minIndex (included) and maxIndex
     * (excluded). Args : stack, minIndex, maxIndex, negativDirection. /!\ the
     * Container implementation do not check if the item is valid for the slot
     */
    @Override
    @ParametersAreNonnullByDefault
    protected boolean mergeItemStack(ItemStack stack, int startIndex, int endIndex, boolean reverseDirection) {
        boolean flag = false;
        int i = startIndex;
        if (reverseDirection) {
            i = endIndex - 1;
        }
        if (stack.isStackable()) {
            while (!stack.isEmpty()) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }
                Slot slot = this.inventorySlots.get(i);
                ItemStack itemstack = slot.getStack();
                if (slot.isItemValid(itemstack) && !itemstack.isEmpty() && itemstack.getItem() == stack.getItem()
                        && (!stack.getHasSubtypes() || stack.getMetadata() == itemstack.getMetadata())
                        && ItemStack.areItemStackTagsEqual(stack, itemstack)) {
                    int j = itemstack.getCount() + stack.getCount();
                    int maxSize = Math.min(slot.getSlotStackLimit(), stack.getMaxStackSize());
                    if (j <= maxSize) {
                        stack.setCount(0);
                        itemstack.setCount(j);
                        slot.putStack(itemstack);
                        flag = true;
                    } else if (itemstack.getCount() < maxSize) {
                        stack.shrink(maxSize - itemstack.getCount());
                        itemstack.setCount(maxSize);
                        slot.putStack(itemstack);
                        flag = true;
                    }
                }
                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        if (!stack.isEmpty()) {
            if (reverseDirection) {
                i = endIndex - 1;
            } else {
                i = startIndex;
            }
            while (true) {
                if (reverseDirection) {
                    if (i < startIndex) {
                        break;
                    }
                } else if (i >= endIndex) {
                    break;
                }
                Slot slot1 = this.inventorySlots.get(i);
                ItemStack itemstack1 = slot1.getStack();
                if (itemstack1.isEmpty() && slot1.isItemValid(stack)) {
                    if (stack.getCount() > slot1.getSlotStackLimit()) {
                        slot1.putStack(stack.splitStack(slot1.getSlotStackLimit()));
                    } else {
                        slot1.putStack(stack.splitStack(stack.getCount()));
                    }
                    slot1.onSlotChanged();
                    flag = true;
                    break;
                }
                if (reverseDirection) {
                    --i;
                } else {
                    ++i;
                }
            }
        }
        return flag;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onContainerClosed(EntityPlayer playerIn) {
        this.internalInventory.clear();
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        return super.slotClick(slotId, dragType, clickTypeIn, player);
    }
}
