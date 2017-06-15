package me.michidk.simpletwitterbotapi.reactions;

import me.michidk.simpletwitterbotapi.TwitterBot;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class QueuedReplyReaction extends Reaction {

    private final int maxIndex;
    private int currentIndex = 0;

    private String[] replies;


    public QueuedReplyReaction(String... replies) {
        this.replies = replies;
        this.maxIndex = replies.length - 1;
    }

    @Override
    public void react(TwitterBot bot, Status tweet) throws TwitterException {
        bot.replyOrTweet(tweet, getNextReply());
    }

    private String getNextReply() {
        String reply = replies[currentIndex];
        currentIndex++;

        if (currentIndex > maxIndex)
            currentIndex = 0;

        return reply;
    }

}
