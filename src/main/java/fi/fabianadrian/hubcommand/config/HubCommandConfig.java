package fi.fabianadrian.hubcommand.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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

    @ConfigSerializable
    public static final class MessagesSection {
        private String alreadyConnected = "You are already connected to this server!";
        private String playerOnlyCommand = "Only players can use this command!";
        private String serverDisabled = "You can't use that command here!";
        private String invalidPlayer = "Could not find that player!";
        private String sendingPlayer = "Sending target player to Hub";

        public Component alreadyConnected() {
            return Component.text(this.alreadyConnected, NamedTextColor.RED);
        }

        public Component playerOnlyCommand() {
            return Component.text(this.playerOnlyCommand, NamedTextColor.RED);
        }

        public Component serverDisabled() {
            return Component.text(this.serverDisabled, NamedTextColor.RED);
        }

        public Component invalidPlayer() {
            return Component.text(this.invalidPlayer, NamedTextColor.RED);
        }

        public Component sendingPlayer() {
            return Component.text(this.sendingPlayer, NamedTextColor.GREEN);
        }
    }
}
