package de.kid2407.bannermod;

import de.kid2407.bannermod.gui.GuiCommand;
import de.kid2407.bannermod.gui.GuiHandler;
import de.kid2407.bannermod.util.TranslationHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.server.permission.DefaultPermissionLevel;
import net.minecraftforge.server.permission.PermissionAPI;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = BannerMod.MOD_ID, name = BannerMod.MOD_NAME, version = BannerMod.MOD_VERSION, acceptableRemoteVersions = BannerMod.MOD_VERSION)
public class BannerMod {

    public static final String MOD_ID = "bannermod";
    public static final String MOD_NAME = "Banner mod";
    public static final String MOD_VERSION = "1.1.0";

    public static Logger logger;

    public static File CONFIG_DIR;
    public static String charactersFilename = "bannermod_characters.json";
    public static String specialsFilename = "bannermod_special.json";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preInitializationEvent) {
        logger = preInitializationEvent.getModLog();
        logger.info("Enabling bannermod version " + MOD_VERSION);
        CONFIG_DIR = preInitializationEvent.getModConfigurationDirectory();
        NetworkRegistry.INSTANCE.registerGuiHandler(MOD_ID, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent initializationEvent) {
        logger.info("Registering permissions");
        PermissionAPI.registerNode("bannermod.use", DefaultPermissionLevel.OP, TranslationHelper.translate("bannermod.permission"));
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent serverStartingEvent) {
        logger.info("Registering commands");
        serverStartingEvent.registerServerCommand(new BannerCommand());
        serverStartingEvent.registerServerCommand(new GuiCommand());
        BannerCommand.initColors();
        logger.info("Enabled bannermod");
    }
}
