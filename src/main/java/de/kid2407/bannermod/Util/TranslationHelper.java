package de.kid2407.bannermod.Util;

import java.util.HashMap;

/**
 * User: Tobias Franz
 * Date: 22.04.2020
 * Time: 21:01
 */
public class TranslationHelper {

    private static final HashMap<String, HashMap<String, String>> translations = new HashMap<>();

    /**
     * @param translationKey the translation key
     * @param args data for replacing the placeholders
     * @return String
     */
    public static String translate(String translationKey, Object... args) {
        if (translations.isEmpty()) {
            init();
        }
        if (translations.get(ConfigHelper.activeLanguage).containsKey(translationKey)) {
            return String.format(translations.get(ConfigHelper.activeLanguage).get(translationKey), args);
        }

        return "";
    }

    private static void init() {
        HashMap<String, String> en_us = new HashMap<>();
        en_us.put("command.bannermod.help", "Usage: /banner help [word|special]");
        en_us.put("command.bannermod.help.first", "This mod provides generation for different types of banners. For more information, see ");
        en_us.put("command.bannermod.help.middle", " and ");
        en_us.put("command.bannermod.help.word", "Creates banners for the given word or character(s). Text and base color are optional.");
        en_us.put("command.bannermod.help.special", "Creates a banner for the given special name. Text and base color are optional.");
        en_us.put("command.bannermod.usage", "/banner <method>");
        en_us.put("command.bannermod.word", "Usage: /banner word <word> [text color] [base color]");
        en_us.put("command.bannermod.special", "Usage: /banner special <name> [text color] [base color]");
        en_us.put("command.bannermod.error.isconsole", "/banner can only be used by a player");
        en_us.put("command.bannermod.error.missingText", "No text provided");
        en_us.put("command.bannermod.error.invalidNumberTextColor", "Text color must be a number from 0 to 15");
        en_us.put("command.bannermod.error.invalidNumberBaseColor", "Base color must be a number from 0 to 15");
        en_us.put("command.bannermod.textColor", "[Text color]");
        en_us.put("command.bannermod.baseColor", "[Base color]");
        en_us.put("command.bannermod.error.missingPermission", "You do not have permission to use this command!");
        en_us.put("command.bannermod.error.unsupportedCharacter", "Unsupported character \"%s\"");
        en_us.put("command.bannermod.error.unknowSpecial", "Unknown special banner \"%s\"");
        en_us.put("bannermod.permission", "Determines who can use the /banner command");
        translations.put("en_us", en_us);

        HashMap<String, String> de_de = new HashMap<>();
        de_de.put("command.bannermod.help", "Syntax: /banner help [word|special]");
        de_de.put("command.bannermod.help.first", "Dieser Mod dient zum Erzeugen verschiedenster Banner. Für mehr Details verwende ");
        de_de.put("command.bannermod.help.middle", " und ");
        de_de.put("command.bannermod.help.word", "Erzeugt Banner für das angegebene Wort oder Zeichen. Text- und Grundfarbe sind optional.");
        de_de.put("command.bannermod.help.special", "Erzeugt einen Banner für den angegebenen Sondernamen. Text- und Grundfarbe sind optional.");
        de_de.put("command.bannermod.usage", "/banner <Methode>");
        de_de.put("command.bannermod.word", "Syntax: /banner word <Text> [Textfarbe] [Grundfarbe]");
        de_de.put("command.bannermod.special", "Syntax: /banner special <Name> [Textfarbe] [Grundfarbe]");
        de_de.put("command.bannermod.error.isconsole", "/banner kann nur von Spieler verwendet werden");
        de_de.put("command.bannermod.error.missingText", "Kein Text angegeben");
        de_de.put("command.bannermod.error.invalidNumberTextColor", "Textfarbe muss eine Zahl von 0 bis 15 sein");
        de_de.put("command.bannermod.error.invalidNumberBaseColor", "Grundfarbe muss eine Zahl von 0 bis 15 sein");
        de_de.put("command.bannermod.textColor", "[Textfarbe]");
        de_de.put("command.bannermod.baseColor", "[Grundfarbe]");
        de_de.put("command.bannermod.error.missingPermission", "Du hast nicht die ben\u00F6tige Berechtigung, diesen Befehl auszuf\u00FChren");
        de_de.put("command.bannermod.error.unsupportedCharacter", "Nicht unterst\u00FCtzter Buchstabe \"%s\"");
        de_de.put("command.bannermod.error.unknowSpecial", "Unbekannter Special-Banner \"%s\"");
        de_de.put("bannermod.permission", "Legt fest, wer /banner benutzen kann");

        translations.put("de_de", de_de);
    }
}
