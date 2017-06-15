package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

/**
 * Created by Michael Lohr on 19.09.2016.
 */
public abstract class Filter {

    /***
     * Filters a tweet
     * @param tweet the tweet to be filtered
     * @return true if the tweet should be filtered
     */
    public abstract boolean filter(Status tweet);

}
