/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.quickscythe.dragonforge.api;

import me.quickscythe.dragonforge.api.events.EventHandler;
import me.quickscythe.dragonforge.api.logger.DragonForgeLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

/**
 * The main interface for the plugin
 */
public abstract class DragonForgeIntegration {

    private final EventHandler handler;
    private final DragonForgeLogger logger;

    public DragonForgeIntegration(){
        logger = new DragonForgeLogger(this);
        handler = new EventHandler(this);
    }

    public final EventHandler events(){
        return handler;
    }


    public abstract void enable();

    /**
     * Log a message to the console
     *
     * @param tag           The config that the message is from
     * @param message       The message to log
     */
    public void log(String tag, String message) {
        logger.log("[%s] %s".formatted(tag, message));
    }

    /**
     * Get the data folder for the plugin
     *
     * @return The data folder
     */
    public abstract File dataFolder();

    /**
     * Get the name of the plugin
     *
     * @return The name of the plugin
     */
    public abstract String name();

    public void destroy() throws IOException {
        if(dataFolder() != null && dataFolder().exists()){
            Files.walk(dataFolder().toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            log(name(), "Folder deleted");
        }
    }

    public DragonForgeLogger logger() {
        return logger;
    }

}
