package fi.fabianadrian.hubcommand.command;

import fi.fabianadrian.hubcommand.HubCommand;
import fi.fabianadrian.hubcommand.config.HubCommandConfig;
import net.kyori.adventure.audience.Audience;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandHub extends Command {
    private final HubCommand plugin;
    private final HubCommandConfig mainConfig;

    public CommandHub(HubCommand plugin) {
        super("hub");
        this.plugin = plugin;
        this.mainConfig = plugin.configManager().mainConfig();
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Audience audience = plugin.adventure().sender(sender);
        if (!(sender instanceof ProxiedPlayer)) {
            audience.sendMessage(mainConfig.messagesSection().playerOnlyCommand());
            return;
        }

        ProxiedPlayer player = (ProxiedPlayer) sender;
        String serverName = playerServer(player);

        if (isHubServer(serverName)) {
            audience.sendMessage(mainConfig.messagesSection().alreadyConnected());
            return;
        }

        if (isDisabledServer(serverName)) {
            audience.sendMessage(mainConfig.messagesSection().serverDisabled());
            return;
        }

        player.connect(plugin.getProxy().getServerInfo(mainConfig.hubServer()));
    }

    private String playerServer(ProxiedPlayer player) {
        return player.getServer().getInfo().getName();
    }

    private boolean isHubServer(String serverName) {
        return serverName.equals(mainConfig.hubServer());
    }

    private boolean isDisabledServer(String serverName) {
        return mainConfig.disabledServers().contains(serverName);
    }
}
