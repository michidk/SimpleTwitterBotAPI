package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

/**
 * Created by Michael Lohr on 19.06.2017.
 */
public class RetweetFilter extends Filter {

    @Override
    public boolean filter(Status tweet) {
        return tweet.isRetweet();
    }

}
