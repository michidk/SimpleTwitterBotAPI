package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

/**
 * Created by Michael Lohr on 15.06.2017.
 *
 * Ensures that the keywords appear as whole word and not just randomly in the query.
 */
public class SingleWordFilter extends Filter
{

    private String[] keywords;


    public SingleWordFilter(String... keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean filter(Status tweet) {
        boolean filter = false;
        String txt = tweet.getText();
        for (int i = 0; i < keywords.length; i++) {
            if (checkKeyword(txt, keywords[i]))
                return false;
        }
        return true;
    }

    private static boolean checkKeyword(String text, String keyword) {
        if (!text.contains(keyword))
            return false;

        // Check if keyword is the first word or if there is a space in front of it
        boolean startValid = text.indexOf(keyword) == 0 || text.contains(" " + keyword);

        // Check if keyword is the last word or if there is a space behind it
        boolean endValid = text.indexOf(keyword) == text.length() - keyword.length() || text.contains(keyword + " ");

        return startValid && endValid;
    }

}
