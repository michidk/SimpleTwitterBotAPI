package me.michidk.simpletwitterbotapi.reactions;

import me.michidk.simpletwitterbotapi.TwitterBot;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Created by Michael Lohr on 19.06.2017.
 */
public class PrintToConsoleReaction extends Reaction {

    private String prefix = "";


    public PrintToConsoleReaction() {
    }

    public PrintToConsoleReaction(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void react(TwitterBot bot, Status tweet) throws TwitterException {
        System.out.println("[" + prefix + "] " + tweet.getUser().getName() + ": " + tweet.getText());
    }

}
