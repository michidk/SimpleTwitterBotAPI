package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

/**
 * Filters replies - so only public tweets are shown
 *
 * Created by Michael Lohr on 19.06.2017.
 */
public class ReplyFilter extends Filter {

    @Override
    public boolean filter(Status tweet) {
        return tweet.getText().startsWith("@");
    }

}
