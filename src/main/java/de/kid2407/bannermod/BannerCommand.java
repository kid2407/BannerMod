package de.kid2407.bannermod;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class BannerCommand extends CommandBase {
    private HashMap<String, ArrayList<HashMap<String, String>>> characterPatterns;

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
        if (args.length == 0) {
            sender.sendMessage(new TextComponentTranslation("command.bannermod.error.missingText"));
        } else {
            if (!(sender instanceof EntityPlayer)) {
                sender.sendMessage(new TextComponentTranslation("command.bannermod.error.isconsole"));
                return;
            }

            EntityPlayer player = (EntityPlayer) sender;

            String word = args[0];
            int textColor = args[1] != null ? Integer.parseInt(args[1]) : 15;
            int baseColor = args[2] != null ? Integer.parseInt(args[2]) : 0;
            ItemStack banner;

            String[] uniqueCharacters = Arrays.stream(word.split("")).distinct().toArray(String[]::new);
            for (String character : uniqueCharacters) {
                banner = new ItemStack(Items.BANNER, 1, baseColor);
                banner.setTagCompound(generateBannerCompound(character, textColor, baseColor));
                player.addItemStackToInventory(banner);
            }


//            patternNBT.setInteger("Color", textColor);
//            patternNBT.setString("Pattern", "HIER PATTERN JE BUCHSTABE");
//            list.appendTag(patternNBT);
//            blockDataNBT.setTag("Patterns", list);
//            compound.setTag("BlockEntityTag", blockDataNBT);

//            banner.setTagCompound(compound);

//            player.addItemStackToInventory(banner);
//                ItemStack holding = player.getHeldItem(EnumHand.MAIN_HAND);
//                String    itemNBT;
//                if (holding.getTagCompound() != null) {
//                    itemNBT = "Die NBT-Daten des Items sind: " + holding.getTagCompound().toString();
//                } else {
//                    itemNBT = "kein Tag-compound";
//                }
//                player.sendMessage(new TextComponentString(itemNBT));
        }
    }

    private NBTTagCompound generateBannerCompound(String character, int textColor, int baseColor) {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagCompound blockDataNBT = new NBTTagCompound();

        blockDataNBT.setTag("Patterns", generatePatternCompound(character, textColor, baseColor));
        compound.setTag("BlockEntityTag", blockDataNBT);

        return compound;
    }

    private NBTTagList generatePatternCompound(String character, int textColor, int baseColor) {
        NBTTagList layers = new NBTTagList();
        NBTTagCompound singlePattern;

        for (HashMap<String, String> patternDescription : characterPatterns.get(character)) {
            singlePattern = new NBTTagCompound();
            singlePattern.setString("Pattern", patternDescription.get("Pattern"));
            singlePattern.setInteger("Color", patternDescription.get("primaryColor").equals("yes") ? textColor : baseColor);
            layers.appendTag(singlePattern);
        }

        return layers;
    }
}
