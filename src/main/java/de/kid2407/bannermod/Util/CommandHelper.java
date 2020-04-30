package de.kid2407.bannermod.util;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

/**
 * User: Tobias Franz
 * Date: 24.04.2020
 * Time: 17:20
 */
public class CommandHelper {

    private static final TextComponentString prefix = (TextComponentString) new TextComponentString("[Banner mod] ").setStyle(new Style().setColor(TextFormatting.DARK_GREEN));

    public static void sendMessageToCommandSender(ICommandSender player, String message) {
        player.sendMessage(prefix.createCopy().appendSibling(new TextComponentString(message).setStyle(new Style().setColor(TextFormatting.WHITE))));
    }

    public static void sendMessageToCommandSender(ICommandSender player, String message, TextFormatting textColor) {
        player.sendMessage(prefix.createCopy().appendSibling(new TextComponentString(message).setStyle(new Style().setColor(textColor))));
    }

    public static TextComponentString getPrefix() {
        return prefix.createCopy();
    }
}
