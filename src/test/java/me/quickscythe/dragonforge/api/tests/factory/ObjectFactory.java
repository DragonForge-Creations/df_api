package me.quickscythe.dragonforge.api.tests.factory;

import me.quickscythe.dragonforge.api.DragonForgeIntegration;
import me.quickscythe.dragonforge.api.config.ConfigManager;
import me.quickscythe.dragonforge.api.tests.config.TestConfig;
import me.quickscythe.dragonforge.api.tests.config.TestNestedConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ObjectFactory {


    public static DragonForgeIntegration createIntegration(){
        return createIntegration("TestIntegration", "test_data");
    }

    public static DragonForgeIntegration createIntegration(String name, String folderPath){
        return new DragonForgeIntegration() {

            final Logger logger = LoggerFactory.getLogger(this.getClass());
            File file = new File(folderPath);


            @Override
            public void enable() {

                if (!file.exists())
                    log("TestIntegration", "Data folder does not exist. Creating... " + (file.mkdirs() ? "Success" : "Failed"));

                TestConfig config = ConfigManager.registerConfig(this, TestConfig.class);
                config.testConfig = ConfigManager.getNestedConfig(config, TestNestedConfig.class, "testConfig");
                config.save();
                log("TestIntegration", "Enabled");

            }

            @Override
            public void log(String tag, String message) {
                logger.info("[" + tag + "] " + message);
            }


            @Override
            public File dataFolder() {
                return file;
            }

            @Override
            public String name() {
                return name;
            }

        };
    }
}
