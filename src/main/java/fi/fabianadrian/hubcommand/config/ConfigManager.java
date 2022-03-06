package fi.fabianadrian.hubcommand.config;

import fi.fabianadrian.hubcommand.HubCommand;
import org.spongepowered.configurate.ConfigurateException;

public final class ConfigManager {
    private final ConfigLoader<HubCommandConfig> mainConfigLoader;
    private HubCommandConfig mainConfig;

    public ConfigManager(HubCommand plugin) {
        this.mainConfigLoader = new ConfigLoader<>(
                HubCommandConfig.class,
                plugin.dataDirectory().resolve("main.conf"),
                options -> options.header("HubCommand Main Configuration")
        );
    }

    public void loadConfigs() {
        try {
            this.mainConfig = this.mainConfigLoader.load();
            this.mainConfigLoader.save(this.mainConfig);
        } catch (ConfigurateException e) {
            throw new IllegalStateException("Failed to load config", e);
        }
    }

    public HubCommandConfig mainConfig() {
        if (this.mainConfig == null) {
            throw new IllegalStateException("Config has not yet been loaded");
        }
        return this.mainConfig;
    }
}
