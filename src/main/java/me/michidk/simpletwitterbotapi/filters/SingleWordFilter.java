package me.michidk.simpletwitterbotapi.filters;

import twitter4j.Status;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean checkKeyword(String text, String keyword) {
        Pattern pattern = Pattern.compile("(?i)(^|.*\\s)" + keyword + "((\\p{Punct}|\\s).*|$)");
        Matcher matcher = pattern.matcher(text);
        System.out.println(keyword + " in \"" + text + "\" :" + matcher.matches());
        return matcher.matches();
    }

}
