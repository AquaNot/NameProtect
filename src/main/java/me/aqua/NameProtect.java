package me.aqua;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NameProtect
        implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("NameProtect");

    public void onInitialize() {
        Init.INSTANCE.init();
        LOGGER.info("Initializing NameProtect");
    }
}
