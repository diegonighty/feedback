package com.github.diegonighty.feedback;

import com.github.diegonighty.feedback.media.FeedbackMedia;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.List;

public record FeedbackImpl(List<FeedbackMedia> medias) implements Feedback, FeedbackMediaContainer {

    @Override
    public void sendFeedback(Audience audience, TagResolver... resolver) {
        medias.forEach(media -> media.sendMedia(audience, resolver));
    }
}
