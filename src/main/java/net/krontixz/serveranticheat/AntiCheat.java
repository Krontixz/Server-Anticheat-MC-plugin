package net.krontixz.serveranticheat;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntiCheat implements ModInitializer {
    public static final String MOD_ID = "server-anticheat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing ServerAntiCheat for Fabric 26.1.2");
        
        setupDataFolders();
    }

    private void setupDataFolders() {
        java.io.File logDir = new java.io.File("logs/anticheat");
        if (!logDir.exists()) {
            logDir.mkdirs();
        }
    }
}
