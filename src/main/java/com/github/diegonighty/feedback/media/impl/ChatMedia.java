package com.github.diegonighty.feedback.media.impl;

import com.github.diegonighty.feedback.ComponentUtils;
import com.github.diegonighty.feedback.media.FeedbackMedia;
import com.github.diegonighty.feedback.media.MediaType;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;
import java.util.Objects;

public record ChatMedia(String message) implements FeedbackMedia {

    @Override
    public void sendMedia(final Audience audience, final TagResolver... resolvers) {
        audience.sendMessage(ComponentUtils.miniMessage(message, resolvers));
    }

    @Override
    public MediaType type() {
        return MediaType.CHAT;
    }

    public static class ChatMediaSerializer implements TypeSerializer<FeedbackMedia> {
        @Override
        public FeedbackMedia deserialize(Type type, ConfigurationNode node) throws SerializationException {
            return new ChatMedia(Objects.requireNonNull(node.node("message").getString()));
        }

        @Override
        public void serialize(Type type, @Nullable FeedbackMedia obj, ConfigurationNode node) throws SerializationException {
            if (!(obj instanceof ChatMedia media)) {
                throw new SerializationException("Invalid media type");
            }

            node.node("type").set(MediaType.CHAT.name().toLowerCase());
            node.node("message").set(media.message());
        }
    }
}
