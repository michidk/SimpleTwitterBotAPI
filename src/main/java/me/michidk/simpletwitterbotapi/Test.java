package me.michidk.simpletwitterbotapi;

import me.michidk.simpletwitterbotapi.behaviour.Behaviour;
import me.michidk.simpletwitterbotapi.behaviour.BehaviourBuilder;
import me.michidk.simpletwitterbotapi.events.HashtagEvent;
import me.michidk.simpletwitterbotapi.events.KeywordEvent;
import me.michidk.simpletwitterbotapi.filters.RTFilter;
import me.michidk.simpletwitterbotapi.reactions.QueuedReplyReaction;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Michael Lohr on 20.09.2016.
 */
public class Test {

    public static void main(String[] args) {

        Handler[] handlers = Logger.getLogger( "" ).getHandlers();
        for ( int index = 0; index < handlers.length; index++ ) {
            handlers[index].setLevel( Level.SEVERE);
        }

        TwitterBot bot = new TwitterBot();  // create a new Twitter bot instance

        Behaviour behaviour = BehaviourBuilder.create()
            .listen(
                new KeywordEvent("lookingForThisKeyword"), new HashtagEvent("mySuperCoolHashtag") // looking for "lookingForThisKeyword" and "#mySuperCoolHashtag" on Twitter
            ).filter(
                new RTFilter() // exclude tweets that starts with "RT"
            ).react(
                new QueuedReplyReaction("Test!", "Test 123")    // reply "Test!" then "Test 123" and start fromt he beginning
        ).build();

        bot.addBehaviour(behaviour); // add the behaviour to the bot instance
    }

}
