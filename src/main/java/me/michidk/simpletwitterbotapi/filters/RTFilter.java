package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

/**
 * Created by Michael Lohr on 19.09.2016.
 *
 * Many retweet-bots write 'RT' in front of their tweet instead of actually retweeting the tweet.
 * This filter will block those tweets.
 */
public class RTFilter extends Filter {

    @Override
    public boolean filter(Status tweet) {
        return tweet.isRetweet() || tweet.getText().startsWith("RT");
    }

}
