package de.kid2407.bannermod.Util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import de.kid2407.bannermod.BannerMod;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * User: Tobias Franz
 * Date: 22.04.2020
 * Time: 21:30
 */
@Mod.EventBusSubscriber(modid = BannerMod.MOD_ID)
@Config(modid = BannerMod.MOD_ID)
public class ConfigHelper {

    private static final HashMap<String, String> initialJson = new HashMap<String, String>() {
        {
            put(CONFIG_TYPES.CHARACTERS.getFilename(), "{\"A\":[{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"B\":[{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"cbo\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"C\":[{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"D\":[{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"cbo\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"E\":[{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"F\":[{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":false},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"G\":[{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"hh\",\"primaryColor\":false},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"H\":[{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":false},{\"pattern\":\"rs\",\"primaryColor\":false},{\"pattern\":\"bo\",\"primaryColor\":true}],\"I\":[{\"pattern\":\"cs\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"J\":[{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"hh\",\"primaryColor\":false},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"K\":[{\"pattern\":\"drs\",\"primaryColor\":true},{\"pattern\":\"hh\",\"primaryColor\":false},{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"L\":[{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"M\":[{\"pattern\":\"tt\",\"primaryColor\":true},{\"pattern\":\"tts\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"N\":[{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"tt\",\"primaryColor\":false},{\"pattern\":\"drs\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"O\":[{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"P\":[{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"hhb\",\"primaryColor\":false},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"Q\":[{\"pattern\":\"mr\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":false},{\"pattern\":\"br\",\"primaryColor\":false},{\"pattern\":\"bo\",\"primaryColor\":true}],\"R\":[{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"hhb\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"drs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"S\":[{\"pattern\":\"mr\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"drs\",\"primaryColor\":false},{\"pattern\":\"bo\",\"primaryColor\":true}],\"T\":[{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"cs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"U\":[{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"V\":[{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bt\",\"primaryColor\":false},{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"W\":[{\"pattern\":\"bt\",\"primaryColor\":true},{\"pattern\":\"bts\",\"primaryColor\":false},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"X\":[{\"pattern\":\"cr\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"Y\":[{\"pattern\":\"drs\",\"primaryColor\":true},{\"pattern\":\"hhb\",\"primaryColor\":false},{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"Z\":[{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"1\":[{\"pattern\":\"cs\",\"primaryColor\":true},{\"pattern\":\"tl\",\"primaryColor\":true},{\"pattern\":\"cbo\",\"primaryColor\":false},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"2\":[{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"mr\",\"primaryColor\":false},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"3\":[{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"cbo\",\"primaryColor\":false},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"4\":[{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"hhb\",\"primaryColor\":false},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"5\":[{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"mr\",\"primaryColor\":false},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"drs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"6\":[{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"hh\",\"primaryColor\":false},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"7\":[{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"8\":[{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"9\":[{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"hhb\",\"primaryColor\":false},{\"pattern\":\"ms\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}],\"0\":[{\"pattern\":\"bs\",\"primaryColor\":true},{\"pattern\":\"ls\",\"primaryColor\":true},{\"pattern\":\"ts\",\"primaryColor\":true},{\"pattern\":\"rs\",\"primaryColor\":true},{\"pattern\":\"dls\",\"primaryColor\":true},{\"pattern\":\"bo\",\"primaryColor\":false}]}");
            put(CONFIG_TYPES.SPECIAL.getFilename(), "{\"picture_frame\":[{\"pattern\":\"bo\",\"primaryColor\":true}]}");
        }
    };

    @Config.Comment("Set the active Language for the mod. Possible values are en_us, de_de and es_es.")
    @Config.Name("language")
    public static String activeLanguage = "en_us";

    public static HashMap<String, ArrayList<HashMap<String, String>>> getConfig(CONFIG_TYPES type) {
        createConfigFile(type);

        Gson gson = new GsonBuilder().setLenient().create();
        try {
            JsonReader reader = new JsonReader(new FileReader(BannerMod.CONFIG_DIR + File.separator + type.getFilename()));
            return gson.fromJson(reader, new TypeToken<HashMap<String, ArrayList<HashMap<String, String>>>>() {}.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void createConfigFile(CONFIG_TYPES type) {
        File configFile = new File(BannerMod.CONFIG_DIR.getAbsolutePath() + File.separator + type.getFilename());
        if (!configFile.exists() && !configFile.isDirectory()) {
            Gson gson = new GsonBuilder().setLenient().setPrettyPrinting().create();
            try {
                FileWriter fileWriter = new FileWriter(configFile);
                JsonObject jsonObject = new JsonParser().parse(initialJson.get(type.getFilename())).getAsJsonObject();
                gson.toJson(jsonObject, fileWriter);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public enum CONFIG_TYPES {
        CHARACTERS(BannerMod.charactersFilename),
        SPECIAL(BannerMod.specialsFilename);

        private final String filename;

        CONFIG_TYPES(String filename) {
            this.filename = filename;
        }

        public String getFilename() {
            return filename;
        }
    }

}
