package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class WhitelistFilter extends Filter {

    private String[] words;


    public WhitelistFilter(String... words) {
        this.words = words;
    }

    @Override
    public boolean filter(Status tweet) {
        boolean filter = false;
        String txt = tweet.getText();
        for (int i = 0; i < words.length; i++) {
            if (txt.contains(words[i]))
                return false;
        }
        return true;
    }

}
