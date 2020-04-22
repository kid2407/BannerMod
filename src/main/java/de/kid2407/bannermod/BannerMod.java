package de.kid2407.bannermod;

import net.minecraft.command.server.CommandBanPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = BannerMod.MOD_ID, name = BannerMod.MOD_NAME, version = "@VERSION@")
public class BannerMod {

    public static final String MOD_ID = "bannermod";
    public static final String MOD_NAME = "Banner mod";

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent preInitializationEvent) {
        logger = preInitializationEvent.getModLog();
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent serverStartingEvent) {
        serverStartingEvent.registerServerCommand(new BannerCommand());
    }
}
