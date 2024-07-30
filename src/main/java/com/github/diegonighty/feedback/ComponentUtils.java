package com.github.diegonighty.feedback;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class ComponentUtils {
    private final static MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();

    public static Component miniMessage(final String message, final TagResolver... tagResolvers) {
        return MINI_MESSAGE.deserialize(message, tagResolvers);
    }

    public static String miniMessage(final Component component) {
        return MINI_MESSAGE.serialize(component);
    }
}
