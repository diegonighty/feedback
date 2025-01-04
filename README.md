# [Feedback API](https://github.com/diegonighty/feedback)
Feedback API is a simple API that allows you to send multiple types of feedback medias
to an Audience

For use this API you need to use the following libraries:
* [Configurate](https://github.com/SpongePowered/Configurate)
* [Adventure](https://github.com/KyoriPowered/adventure)
* [MiniMessage](https://docs.advntr.dev/minimessage/api.html)

## How to use
To use this API you will need to create a new [Configurate](https://github.com/SpongePowered/Configurate) Configuration object and
use the [Feedback](https://github.com/diegonighty/feedback/blob/master/src/main/java/com/github/diegonighty/feedback/Feedback.java) object

```java
@ConfigSerializable
public record Configuration(
        Feedback playerJoinMessage
) {}
```

### NOTE: for Configuration object serialization you need to register [Feedback Serializer](https://github.com/diegonighty/feedback/blob/master/src/main/java/com/github/diegonighty/feedback/serializer/FeedbackSpongeSerializer.java) and [Feedback Media Serializer](https://github.com/diegonighty/feedback/blob/master/src/main/java/com/github/diegonighty/feedback/serializer/FeedbackMediaSpongeSerializer.java)
```java
TypeSerializerCollection.defaults()
    .childBuilder()
    .register(Feedback.class, new FeedbackSpongeSerializer())
    .register(FeedbackMedia.class, new FeedbackMediaSpongeSerializer())
```

To send feedback to an Audience you will need to use the [Feedback#sendFeedback(Audience, TagResolver)](https://github.com/diegonighty/feedback/blob/master/src/main/java/com/github/diegonighty/feedback/Feedback.java#L9) method
```java
playerJoinMessage.sendFeedback(audience);
```

with TagResolver:
```java
playerJoinMessage.sendFeedback(audience, TagResolver.resolver("placeholder", Tag.inserting(Component.text("hi!"))));
```

## YAML Feedback Format
```yaml
message-feedback: "<green>Hi <name>!" # default media type is CHAT

title-feedback:
  type: "TITLE"
  title: "<red>Feedback Title"
  sub-title: "<yellow>Feedback Subtitle" # Optional
  fade-in: 1 # Optional (in seconds) default 1
  stay: 3 # Optional (in seconds) default 1
  fade-out: 1 # Optional (in seconds) default 1

sound-feedback:
  type: "SOUND"
  name: "minecraft:entity.experience_orb.pickup"
  source: "AMBIENT" # Optional default MASTER
  pitch: 1.0 # Optional default 1.0
  volume: 1.0 # Optional default 1.0

actionbar-feedback:
  type: "ACTIONBAR"
  message: "<green>Feedback Actionbar"

boss-bar-feedback:
  type: "BOSSBAR"
  message: "<green>Feedback Bossbar"
  color: "GREEN" # Optional default PURPLE
  style: "SOLID" # Optional default PROGRESS
  progress: 1.0 # Optional default 1.0

multiple-feedback: # You can send multiple feedbacks at once
  - type: "CHAT"
    message: "<green>Feedback Chat"
  - type: "TITLE"
    title: "<red>Feedback Title"
  - type: "SOUND"
    name: "minecraft:entity.experience_orb.pickup"
```
