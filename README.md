# SimpleTwitterBotAPI
A very simple API/Twitter4J wrapper for Twitter bots.

## Example
```java
        // create a new Twitter bot instance
        TwitterBot bot = new TwitterBot();

        Behaviour behaviour = BehaviourBuilder.create()
            .listen(
                // looking for "lookingForThisKeyword" and "#mySuperCoolHashtag" on Twitter
                new KeywordEvent("lookingForThisKeyword"), new HashtagEvent("mySuperCoolHashtag")
            ).filter(
                // exclude tweets which starts with "RT"
                new RTFilter()
            ).react(
                // reply "Test!" then "Test 123" and start fromt he beginning
                new QueuedReplyReaction("Test!", "Test 123")
        ).build();

        bot.addBehaviour(behaviour); // add the behaviour to the bot instance
```

## Setup
To create a new bot/bot behaviour just orientate on the example code.
Authentication works via Twitter4j. Follow the instructions [here](http://twitter4j.org/en/configuration.html). To authenticate via ConfigurationBuilder, just pass the built configuration object to the constructor of TwitterBot().

## Dependencies
- Java 8
- [Guava](https://github.com/google/guava)
- [Twitter4j](http://twitter4j.org/)
