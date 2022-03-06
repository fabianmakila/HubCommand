package fi.fabianadrian.hubcommand.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.HashSet;
import java.util.Set;

@ConfigSerializable
public final class HubCommandConfig {
    private String hubServer = "hub";

    private Set<String> disabledServers = new HashSet<>();

    private MessagesSection messagesSection = new MessagesSection();

    public String hubServer() {
        return this.hubServer;
    }

    public Set<String> disabledServers() {
        return this.disabledServers;
    }

    public MessagesSection messagesSection() {
        return this.messagesSection;
    }

    public static final class MessagesSection {
        private String alreadyConnected = "<red>You are already connected to this server!</red>";
        private String playerOnlyCommand = "<red>Only players can use this command!</red>";
        private String serverDisabled = "<red>You can't use that command here!</red>";

        public Component alreadyConnected() {
            return MiniMessage.miniMessage().deserialize(this.alreadyConnected);
        }

        public Component playerOnlyCommand() {
            return MiniMessage.miniMessage().deserialize(this.playerOnlyCommand);
        }

        public Component serverDisabled() {
            return MiniMessage.miniMessage().deserialize(this.serverDisabled);
        }
    }
}
