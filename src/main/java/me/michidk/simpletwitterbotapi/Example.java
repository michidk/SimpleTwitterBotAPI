package me.michidk.simpletwitterbotapi;

import me.michidk.simpletwitterbotapi.behaviour.Behaviour;
import me.michidk.simpletwitterbotapi.behaviour.BehaviourBuilder;
import me.michidk.simpletwitterbotapi.events.HashtagEvent;
import me.michidk.simpletwitterbotapi.events.KeywordEvent;
import me.michidk.simpletwitterbotapi.events.MentionEvent;
import me.michidk.simpletwitterbotapi.filters.RTFilter;
import me.michidk.simpletwitterbotapi.reactions.LikeReaction;
import me.michidk.simpletwitterbotapi.reactions.QueuedReplyReaction;


/**
 * Created by Michael Lohr on 20.09.2016.
 */
public class Example {

    public static void main(String[] args) {

        TwitterBot bot = new TwitterBot();                          // create a new Twitter bot instance

        Behaviour behaviour = BehaviourBuilder.create()
            .listen(
                new KeywordEvent("lookingForThisKeyword"),          // looking for "lookingForThisKeyword" and "#mySuperCoolHashtag" on Twitter
                new HashtagEvent("mySuperCoolHashtag")              // can also be achieved by calling listen() on the builder multiple times
            ).filter(
                new RTFilter()                                      // exclude tweets that starts with "RT, a lot of bots do this
            ).react(
                new QueuedReplyReaction("Test!", "Test 123") // replyOrTweet "Test!" then "Test 123" and start fromt he beginning
        ).build();

        bot.addBehaviour(behaviour);                                // add the behaviour to the bot instance

        // or do everything in one statement
        bot.addBehaviour(
            BehaviourBuilder.create().listen(
                    new MentionEvent("schuwi4")
            ).react(
                    new LikeReaction()
            ).build()
        );

        // ... add as many behaviours as you want

    }

}
