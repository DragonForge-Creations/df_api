package me.quickscythe.dragonforge.api.tests.config;

import me.quickscythe.dragonforge.api.DragonForgeIntegration;
import me.quickscythe.dragonforge.api.config.Config;
import me.quickscythe.dragonforge.api.config.ConfigTemplate;
import me.quickscythe.dragonforge.api.config.ConfigValue;
import me.quickscythe.dragonforge.api.config.NestedConfig;

@ConfigTemplate(name = "nested")
public class TestNestedConfig<T extends Config> extends NestedConfig<T> {

    @ConfigValue
    public String nestedValue = "nestedValue";

    @ConfigValue
    public int nestedInt = 1;

    /**
     * Creates a new config file
     *
     * @param parent      The Config instance that holds this nested config.
     * @param name        The name of the config
     * @param integration The plugin that owns this config
     */
    public TestNestedConfig(T parent, String name, DragonForgeIntegration integration) {
        super(parent, name, integration);
    }
}
