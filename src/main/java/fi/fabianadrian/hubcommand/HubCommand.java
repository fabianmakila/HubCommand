package fi.fabianadrian.hubcommand;

import fi.fabianadrian.hubcommand.command.CommandHub;
import fi.fabianadrian.hubcommand.config.ConfigManager;
import net.kyori.adventure.platform.bungeecord.BungeeAudiences;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public final class HubCommand extends Plugin {
    private BungeeAudiences adventure;
    private ConfigManager configManager;

    public ConfigManager configManager() {
        return this.configManager;
    }

    public @NotNull BungeeAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Cannot retrieve audience provider while plugin is not enabled");
        }
        return this.adventure;
    }

    @Override
    public void onEnable() {
        this.adventure = BungeeAudiences.create(this);

        this.configManager = new ConfigManager(this);
        this.configManager.loadConfigs();

        getProxy().getPluginManager().registerCommand(this, new CommandHub(this));
    }

    @Override
    public void onDisable() {
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    public Path dataDirectory() {
        return this.getDataFolder().toPath();
    }
}
