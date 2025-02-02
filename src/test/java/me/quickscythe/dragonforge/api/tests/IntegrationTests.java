package me.quickscythe.dragonforge.api.tests;

import me.quickscythe.dragonforge.api.DragonForgeIntegration;
import me.quickscythe.dragonforge.api.config.Config;
import me.quickscythe.dragonforge.api.config.ConfigManager;
import me.quickscythe.dragonforge.api.tests.config.TestConfig;
import me.quickscythe.dragonforge.api.tests.factory.ObjectFactory;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegrationTests {
    Logger logger = LoggerFactory.getLogger("IntegrationTests");


    void destroyIntegration(DragonForgeIntegration integration, String test) {
        try {
            integration.destroy();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void launchIntegrationTwiceClearWhenDone() throws InterruptedException {
        long started = System.currentTimeMillis();

        DragonForgeIntegration integration = ObjectFactory.createIntegration();
        launchIntegration(integration, "launchIntegrationTwiceClearWhenDone");
        TestConfig config = ConfigManager.getConfig(integration, TestConfig.class);
        config.testConfig.nestedValue = "Test";
        config.save();

        logger.info("launchIntegrationTwiceClearWhenDone: simulating shut-down");
        ConfigManager.reset();

        logger.info("launchIntegrationTwiceClearWhenDone: creating second integration");
        DragonForgeIntegration integration2 = ObjectFactory.createIntegration();
        launchIntegration(integration2, "launchIntegrationTwiceClearWhenDone");

        destroyIntegration(integration2, "launchIntegrationTwiceClearWhenDone");

        integration2.log("TestIntegration", "Tests Complete! Time took: " + (System.currentTimeMillis() - started) + "ms");

        assertFalse(integration2.dataFolder().exists(), "Data folder was not deleted");
    }

    @Test
    void launchIntegrationClearWhenDone() {
        logger.info("Starting test: launchIntegrationClearWhenDone");
        long started = System.currentTimeMillis();

        DragonForgeIntegration integration = ObjectFactory.createIntegration();
        launchIntegration(integration, "launchIntegrationClearWhenDone");

        destroyIntegration(integration, "launchIntegrationClearWhenDone");
        integration.log("TestIntegration", "Tests Complete! Time took: " + (System.currentTimeMillis() - started) + "ms");

        assertFalse(integration.dataFolder().exists());
    }


    void launchIntegration(DragonForgeIntegration integration, String test) {
        integration.enable();
        TestConfig config = ConfigManager.getConfig(integration, TestConfig.class);
        logger.info("{}: Verifying config files exist", test);
        checkConfigFiles(config);

        logger.info("{}: Verifying config contents", test);
        checkConfigContents(config);
    }

    void checkConfigContents(Config config) {
        assertFalse(config.json().isEmpty(), "Config is empty");
    }

    void checkConfigFiles(Config config) {
        assertTrue(config.file().exists(), "Config file does not exist");
    }

}
