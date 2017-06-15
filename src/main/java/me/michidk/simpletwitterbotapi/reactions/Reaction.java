package me.michidk.simpletwitterbotapi.reactions;

import me.michidk.simpletwitterbotapi.TwitterBot;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public abstract class Reaction {

    public Reaction() {
    }

    public abstract void react(TwitterBot bot, Status tweet) throws TwitterException;

}
