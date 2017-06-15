package me.michidk.simpletwitterbotapi.reactions;

import me.michidk.simpletwitterbotapi.TwitterBot;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class ReplyReaction extends Reaction {

    private String reply;


    public ReplyReaction(TwitterBot bot) {
        this.reply = reply;
    }

    @Override
    public void react(TwitterBot bot, Status tweet) throws TwitterException {
        bot.replyOrTweet(tweet, reply);
    }

}
