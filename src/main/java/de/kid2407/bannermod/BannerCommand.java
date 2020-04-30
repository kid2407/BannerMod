package de.kid2407.bannermod;

import de.kid2407.bannermod.util.CONFIG_TYPES;
import de.kid2407.bannermod.util.CommandHelper;
import de.kid2407.bannermod.util.ConfigHelper;
import de.kid2407.bannermod.util.TranslationHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@MethodsReturnNonnullByDefault
public class BannerCommand extends CommandBase {
    public static final ArrayList<String> flippedCharacters = new ArrayList<>(Arrays.asList("H", "Q", "S"));
    private static HashMap<String, ArrayList<HashMap<String, String>>> characterPatterns = new HashMap<>();
    private static HashMap<String, ArrayList<HashMap<String, String>>> specialPatterns = new HashMap<>();
    private static final ArrayList<String> subcommands = new ArrayList<>(Arrays.asList("help", "word", "special"));

    private static HashMap<String, Integer> colors;

    public static void initColors() {
        colors = new HashMap<>();

        colors.put(TranslationHelper.translate("bannermod.color.white"), EnumDyeColor.WHITE.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.orange"), EnumDyeColor.ORANGE.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.magenta"), EnumDyeColor.MAGENTA.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.light_blue"), EnumDyeColor.LIGHT_BLUE.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.yellow"), EnumDyeColor.YELLOW.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.lime"), EnumDyeColor.LIME.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.pink"), EnumDyeColor.PINK.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.gray"), EnumDyeColor.GRAY.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.silver"), EnumDyeColor.SILVER.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.cyan"), EnumDyeColor.CYAN.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.purple"), EnumDyeColor.PURPLE.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.blue"), EnumDyeColor.BLUE.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.brown"), EnumDyeColor.BROWN.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.green"), EnumDyeColor.GREEN.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.red"), EnumDyeColor.RED.getDyeDamage());
        colors.put(TranslationHelper.translate("bannermod.color.black"), EnumDyeColor.BLACK.getDyeDamage());
    }

    @Override
    public String getName() {
        return "banner";
    }

    @Override
    @ParametersAreNonnullByDefault
    public String getUsage(ICommandSender sender) {
        return TranslationHelper.translate("command.bannermod.usage");
    }

    @Override
    @ParametersAreNonnullByDefault
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 0) {
            return subcommands;
        } else if (args.length == 1) {
            return subcommands.stream().filter(element -> element.startsWith(args[0])).collect(Collectors.toList());
        } else if (args.length == 2) {
            if (args[0].equals("special")) {
                HashMap<String, ArrayList<HashMap<String, String>>> specialWords = ConfigHelper.getConfig(CONFIG_TYPES.SPECIAL);
                return specialWords.keySet().stream().filter(element -> element.startsWith(args[1])).collect(Collectors.toList());
            } else if (args[0].equals("help")) {
                return Stream.of("word", "special").filter(element -> element.startsWith(args[1])).collect(Collectors.toList());
            }
        } else {
            if (args[0].equals("special") || args[0].equals("word")) {
                if (args.length == 3) {
                    return colors.keySet().stream().filter(element -> element.toLowerCase().startsWith(args[2])).collect(Collectors.toList());
                } else if (args.length == 4) {
                    return colors.keySet().stream().filter(element -> element.toLowerCase().startsWith(args[3])).collect(Collectors.toList());
                }
            }
        }

        return new ArrayList<>();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof EntityPlayer)) {
            CommandHelper.sendMessageToCommandSender(sender, TranslationHelper.translate("command.bannermod.error.isconsole"), TextFormatting.RED);
            return;
        }

        EntityPlayer player = (EntityPlayer) sender;

        if (args.length == 0) {
            throw new WrongUsageException(TranslationHelper.translate("command.bannermod.usage"));
        } else {
            String subcommand = args[0];
            CONFIG_TYPES bannerType;

            switch (subcommand) {
                case "help":
                    if (args.length == 1) {
                        ClickEvent clickEventWord = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banner help word");
                        ITextComponent textWordClickableComponent = new TextComponentString("/banner help word").setStyle(new Style().setColor(TextFormatting.BLUE).setClickEvent(clickEventWord));
                        ClickEvent clickEventSpecial = new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/banner help special");
                        ITextComponent textSpecialClickableComponent = new TextComponentString("/banner help special").setStyle(new Style().setColor(TextFormatting.BLUE).setClickEvent(clickEventSpecial));

                        TextComponentString firstComponent = new TextComponentString(TranslationHelper.translate("command.bannermod.help.first"));
                        TextComponentString middleComponent = new TextComponentString(TranslationHelper.translate("command.bannermod.help.middle"));

                        player.sendMessage(CommandHelper.getPrefix().appendSibling(firstComponent).appendSibling(textWordClickableComponent).appendSibling(middleComponent).appendSibling(textSpecialClickableComponent));
                    } else {
                        TextFormatting formatting = null;
                        String translationKey;
                        switch (args[1]) {
                            case "word":
                                translationKey = "command.bannermod.help.word";
                                break;
                            case "special":
                                translationKey = "command.bannermod.help.special";
                                break;
                            default:
                                translationKey = "command.bannermod.help";
                                formatting = TextFormatting.YELLOW;
                                break;
                        }

                        if (formatting != null) {
                            CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate(translationKey), formatting);
                            return;
                        }
                        CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate(translationKey));
                        return;
                    }
                    return;
                case "word":
                    bannerType = CONFIG_TYPES.CHARACTERS;
                    break;
                case "special":
                    bannerType = CONFIG_TYPES.SPECIAL;
                    break;
                default:
                    CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.usage"), TextFormatting.RED);
                    return;
            }

            if (args.length == 1) {
                if (subcommand.equals("word")) {
                    CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.word"), TextFormatting.RED);
                } else {
                    CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.special"), TextFormatting.RED);
                }
            } else {
                String word = args[1];
                int textColor = 0;
                int baseColor = 15;
                ItemStack banner;

                if (args.length > 2) {
                    try {
                        if (colors.containsKey(args[2])) {
                            textColor = colors.get(args[2]);
                        } else if (!args[2].matches("^\\d+$")) {
                            CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.error.unknownColor", args[2]), TextFormatting.YELLOW);
                            return;
                        } else {
                            textColor = Integer.parseInt(args[2]);
                            if (textColor < 0 || textColor > 15) {
                                throw new NumberFormatException();
                            }
                            textColor = 15 - textColor;
                        }
                    } catch (NumberFormatException numberFormatException) {
                        CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.error.invalidNumberTextColor"), TextFormatting.YELLOW);
                        return;
                    }
                }

                if (args.length > 3) {
                    try {
                        if (colors.containsKey(args[3])) {
                            baseColor = colors.get(args[3]);
                        } else if (!args[3].matches("^\\d+$")) {
                            CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.error.unknownColor", args[3]), TextFormatting.YELLOW);
                            return;
                        } else {
                            baseColor = Integer.parseInt(args[3]);
                            if (baseColor < 0 || baseColor > 15) {
                                throw new NumberFormatException();
                            }
                            baseColor = 15 - baseColor;
                        }
                    } catch (NumberFormatException numberFormatException) {
                        CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.error.invalidNumberBaseColor"), TextFormatting.YELLOW);
                        return;
                    }
                }

                if (bannerType == CONFIG_TYPES.CHARACTERS) {
                    String[] uniqueCharacters = Arrays.stream(word.toUpperCase().split("")).distinct().toArray(String[]::new);
                    int tmpTextColor;
                    int tmpBaseColor;
                    for (String character : uniqueCharacters) {
                        if (characterPatterns.containsKey(character)) {
                            if (flippedCharacters.contains(character)) {
                                tmpTextColor = baseColor;
                                tmpBaseColor = textColor;
                            } else {
                                tmpTextColor = textColor;
                                tmpBaseColor = baseColor;
                            }

                            banner = new ItemStack(Items.BANNER, 1, tmpBaseColor);
                            banner.setTagCompound(generateBannerCompoundForCharacter(character, tmpTextColor, tmpBaseColor));
                            player.addItemStackToInventory(banner);
                        } else {
                            CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.error.unsupportedCharacter", character), TextFormatting.YELLOW);
                        }
                    }
                }

                if (bannerType == CONFIG_TYPES.SPECIAL) {
                    if (specialPatterns.containsKey(word)) {
                        banner = new ItemStack(Items.BANNER, 1, baseColor);
                        banner.setTagCompound(generateBannerCompoundForSpecial(word, textColor, baseColor));
                        player.addItemStackToInventory(banner);
                    } else {
                        CommandHelper.sendMessageToCommandSender(player, TranslationHelper.translate("command.bannermod.error.unknowSpecial", word), TextFormatting.YELLOW);
                    }
                }
            }
        }
    }

    public static NBTTagCompound generateBannerCompoundForCharacter(String character, int textColor, int baseColor) {
        if (characterPatterns.isEmpty()) {
            initCharacterBanners();
        }
        return generateBannerCompound(character, CONFIG_TYPES.CHARACTERS, textColor, baseColor);
    }

    private static NBTTagCompound generateBannerCompoundForSpecial(String character, int textColor, int baseColor) {
        if (specialPatterns.isEmpty()) {
            initSpecialBanners();
        }
        return generateBannerCompound(character, CONFIG_TYPES.SPECIAL, textColor, baseColor);
    }

    private static NBTTagCompound generateBannerCompound(String element, CONFIG_TYPES bannerType, int textColor, int baseColor) {
        NBTTagCompound compound = new NBTTagCompound();
        NBTTagCompound blockDataNBT = new NBTTagCompound();
        NBTTagCompound nameNBT = new NBTTagCompound();
        String itemName;

        if (bannerType == CONFIG_TYPES.CHARACTERS) {
            blockDataNBT.setTag("Patterns", generatePatternCompoundForCharacter(element, textColor, baseColor));
            if (element.matches("\\d")) {
                itemName = TranslationHelper.translate("bannermod.gui.name.number", element);
            } else {
                itemName = TranslationHelper.translate("bannermod.gui.name.letter", element);
            }
        } else {
            blockDataNBT.setTag("Patterns", generatePatternCompoundForSpecial(element, textColor, baseColor));
            itemName = TranslationHelper.translate("bannermod.gui.name.special", element);
        }

        blockDataNBT.setTag("Base", new NBTTagInt(textColor));
        compound.setTag("BlockEntityTag", blockDataNBT);

        nameNBT.setTag("Name", new NBTTagString(itemName));
        compound.setTag("display", nameNBT);

        return compound;
    }

    private static NBTTagList generatePatternCompoundForCharacter(String character, int textColor, int baseColor) {
        return generatePatternCompound(characterPatterns.get(character), textColor, baseColor);
    }

    private static NBTTagList generatePatternCompoundForSpecial(String word, int textColor, int baseColor) {
        return generatePatternCompound(specialPatterns.get(word), textColor, baseColor);
    }

    private static NBTTagList generatePatternCompound(ArrayList<HashMap<String, String>> element, int textColor, int baseColor) {
        NBTTagList layers = new NBTTagList();
        NBTTagCompound singlePattern;

        for (HashMap<String, String> patternDescription : element) {
            singlePattern = new NBTTagCompound();
            singlePattern.setString("Pattern", patternDescription.get("pattern"));
            singlePattern.setInteger("Color", patternDescription.get("primaryColor").equals("true") ? textColor : baseColor);
            layers.appendTag(singlePattern);
        }

        return layers;
    }

    public static void initCharacterBanners() {
        HashMap<String, ArrayList<HashMap<String, String>>> characters = ConfigHelper.getConfig(CONFIG_TYPES.CHARACTERS);
        if (!characters.isEmpty()) {
            characterPatterns = characters;
            BannerMod.logger.info("Characters erfolreich geladen.");
        } else {
            BannerMod.logger.info("Keine Inhalte gefunden.");
        }
    }

    public static void initSpecialBanners() {
        HashMap<String, ArrayList<HashMap<String, String>>> specialWords = ConfigHelper.getConfig(CONFIG_TYPES.SPECIAL);
        if (!specialWords.isEmpty()) {
            specialPatterns = specialWords;
            BannerMod.logger.info("Specials erfolreich geladen.");
        } else {
            BannerMod.logger.info("Keine Inhalte gefunden.");
        }
    }
}
