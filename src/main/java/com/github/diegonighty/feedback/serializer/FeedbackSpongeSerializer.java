package com.github.diegonighty.feedback.serializer;

import com.github.diegonighty.feedback.Feedback;
import com.github.diegonighty.feedback.FeedbackImpl;
import com.github.diegonighty.feedback.FeedbackMediaContainer;
import com.github.diegonighty.feedback.media.FeedbackMedia;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public class FeedbackSpongeSerializer implements TypeSerializer<Feedback> {

    @Override
    public Feedback deserialize(Type type, ConfigurationNode node) throws SerializationException {
        return new FeedbackImpl(node.getList(FeedbackMedia.class));
    }

    @Override
    public void serialize(Type type, Feedback obj, ConfigurationNode node) throws SerializationException {
        if (obj instanceof FeedbackMediaContainer container) {
            node.setList(FeedbackMedia.class, container.medias());
        } else {
            throw new SerializationException("Invalid feedback type");
        }
    }
}
