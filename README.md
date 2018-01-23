# SimpleTwitterBotAPI
A very simple API/Twitter4J wrapper for Twitter bots.

## Example
```java
        TwitterBot bot = new TwitterBot();                          // create a new Twitter bot instance

        Behaviour behaviour = BehaviourBuilder.create()
            .listen(
                new KeywordEvent("lookingForThisKeyword"),          // looking for "lookingForThisKeyword" and "#mySuperCoolHashtag" on Twitter
                new HashtagEvent("mySuperCoolHashtag")              // can also be achieved by calling listen() on the builder multiple times
            ).filter(
                new RTFilter()                                      // exclude tweets that starts with "RT, a lot of bots do this
            ).react(
                new QueuedReplyReaction("Test!", "Test 123")
        ).build();

        bot.addBehaviour(behaviour);                                // add the behaviour to the bot instance

        // or do everything in one statement
        bot.addBehaviour(
            BehaviourBuilder.create().listen(
                new MentionEvent("miichidk")
            ).react(
                new LikeReaction()
            ).build()
        );

        // ... add as many behaviours as you want
        
        bot.start();
```

## Setup
To create a new bot/bot behaviour just orientate on the example code.
Authentication works via Twitter4j. Follow the instructions [here](http://twitter4j.org/en/configuration.html). To authenticate via ConfigurationBuilder, just pass the built configuration object to the constructor of TwitterBot().

## Dependencies
- Java 8
- [Guava](https://github.com/google/guava)
- [Twitter4j](http://twitter4j.org/)
