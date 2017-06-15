package me.michidk.simpletwitterbotapi.reactions;

import me.michidk.simpletwitterbotapi.TwitterBot;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.Random;

/**
 * Created by Michael Lohr on 18.09.2016.
 *
 * Please keep in mind, that posting the same message twice results in an error.
 * I recommend to use QueuedReplyReaction instead of this one.
 */
public class RandomReplyReaction extends Reaction {

    private String[] replies;

    private Random random = new Random();


    @Override
    public void react(TwitterBot bot, Status tweet) throws TwitterException {
        bot.reply(tweet, getRandomReply());
    }

    private String getRandomReply() {
        return replies[random.nextInt(replies.length)];
    }

}
