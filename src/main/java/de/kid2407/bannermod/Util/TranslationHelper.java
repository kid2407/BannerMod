package de.kid2407.bannermod.util;

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
        } else if (translations.get("en_us").containsKey(translationKey)) {
            return String.format(translations.get("en_us").get(translationKey), args);
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
        en_us.put("command.bannermod.error.unknownColor", "Unknown color \"%s\"");
        en_us.put("command.bannermod.textColor", "[Text color]");
        en_us.put("command.bannermod.baseColor", "[Base color]");
        en_us.put("command.bannermod.error.unsupportedCharacter", "Unsupported character \"%s\"");
        en_us.put("command.bannermod.error.unknowSpecial", "Unknown special banner \"%s\"");
        en_us.put("bannermod.permission", "Determines who can use the /banner command");

        en_us.put("bannermod.gui.changeTextColor", "Change text color");
        en_us.put("bannermod.gui.changeBaseColor", "Change base color");
        en_us.put("bannermod.gui.name.letter", "Letter \"%s\"");
        en_us.put("bannermod.gui.name.special", "Special: \"%s\"");
        en_us.put("bannermod.gui.name.number", "Number: \"%s\"");

        en_us.put("bannermod.color.white", "white");
        en_us.put("bannermod.color.orange", "orange");
        en_us.put("bannermod.color.magenta", "magenta");
        en_us.put("bannermod.color.light_blue", "light_blue");
        en_us.put("bannermod.color.yellow", "yellow");
        en_us.put("bannermod.color.lime", "lime");
        en_us.put("bannermod.color.pink", "pink");
        en_us.put("bannermod.color.gray", "gray");
        en_us.put("bannermod.color.silver", "silver");
        en_us.put("bannermod.color.cyan", "cyan");
        en_us.put("bannermod.color.purple", "purple");
        en_us.put("bannermod.color.blue", "blue");
        en_us.put("bannermod.color.brown", "brown");
        en_us.put("bannermod.color.green", "green");
        en_us.put("bannermod.color.red", "red");
        en_us.put("bannermod.color.black", "black");

        translations.put("en_us", en_us);


        HashMap<String, String> de_de = new HashMap<>();

        de_de.put("command.bannermod.help", "Syntax: /banner help [word|special]");
        de_de.put("command.bannermod.help.first", "Dieser Mod dient zum Erzeugen verschiedenster Banner. F\u00FCr mehr Details verwende ");
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
        de_de.put("command.bannermod.error.unknownColor", "Unbekannte Farbe \"%s\"");
        de_de.put("command.bannermod.textColor", "[Textfarbe]");
        de_de.put("command.bannermod.baseColor", "[Grundfarbe]");
        de_de.put("command.bannermod.error.unsupportedCharacter", "Nicht unterst\u00FCtzter Buchstabe \"%s\"");
        de_de.put("command.bannermod.error.unknowSpecial", "Unbekannter Special-Banner \"%s\"");
        de_de.put("bannermod.permission", "Legt fest, wer /banner benutzen kann");

        de_de.put("bannermod.gui.changeTextColor", "Textfarbe \u00E4ndern");
        de_de.put("bannermod.gui.changeBaseColor", "Grundfarbe \u00E4ndern");
        de_de.put("bannermod.gui.name.letter", "Buchstabe \"%s\"");
        de_de.put("bannermod.gui.name.special", "Spezial: \"%s\"");
        de_de.put("bannermod.gui.name.number", "Zahl: \"%s\"");

        de_de.put("bannermod.color.white", "Wei\u00DF");
        de_de.put("bannermod.color.orange", "Orange");
        de_de.put("bannermod.color.magenta", "Magenta");
        de_de.put("bannermod.color.light_blue", "Hellblau");
        de_de.put("bannermod.color.yellow", "Gelb");
        de_de.put("bannermod.color.lime", "Hellgr\u00FCn");
        de_de.put("bannermod.color.pink", "Rosa");
        de_de.put("bannermod.color.gray", "Grau");
        de_de.put("bannermod.color.silver", "Hellgrau");
        de_de.put("bannermod.color.cyan", "T\u00FCrkis");
        de_de.put("bannermod.color.purple", "Violett");
        de_de.put("bannermod.color.blue", "Blau");
        de_de.put("bannermod.color.brown", "Braun");
        de_de.put("bannermod.color.green", "Gr\u00FCn");
        de_de.put("bannermod.color.red", "Rot");
        de_de.put("bannermod.color.black", "Schwarz");

        translations.put("de_de", de_de);


        HashMap<String, String> es_es = new HashMap<>();

        es_es.put("command.bannermod.help", "Uso: /banner help [word|special]");
        es_es.put("command.bannermod.help.first", "Este mod es capaz de generar distintos tipos de banners. Para m\u00E1s informaci\u00F3n, ve a ");
        es_es.put("command.bannermod.help.middle", " y ");
        es_es.put("command.bannermod.help.word", "Crea banners para la palabra o caracteres dados. Los colores del texto y base son opcionales.");
        es_es.put("command.bannermod.help.special", "Crea banners para la palabra especial dada. Los colores del texto y base son opcionales");
        es_es.put("command.bannermod.usage", "/banner <m\u00E9todo>");
        es_es.put("command.bannermod.word", "Uso: /banner word <palabra> [color de texto] [color base]");
        es_es.put("command.bannermod.special", "Uso: /banner special <nombre> [color de texto] [color base]");
        es_es.put("command.bannermod.error.isconsole", "/banner solo puede ser utilizado por un jugador");
        es_es.put("command.bannermod.error.missingText", "Necesitas especificar el texto a generar.");
        es_es.put("command.bannermod.error.invalidNumberTextColor", "El color de texto tiene que ser un n\u00FAmero de 0 a 15.");
        es_es.put("command.bannermod.error.invalidNumberBaseColor", "El color base tiene que ser un n\u00FAmero de 0 a 15.");
        es_es.put("command.bannermod.textColor", "[Color de texto]");
        es_es.put("command.bannermod.baseColor", "[Color base]");
        es_es.put("command.bannermod.error.unsupportedCharacter", "Caracter no soportado: \"%s\"");
        es_es.put("command.bannermod.error.unknowSpecial", "Banner especial desconocido: \"%s\"");
        es_es.put("bannermod.permission", "Determina qui\u00E9n puede utilizar el comando /banner");

        translations.put("es_es", es_es);
    }
}
