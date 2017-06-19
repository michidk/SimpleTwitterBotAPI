package me.michidk.simpletwitterbotapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael Lohr on 16.06.2017.
 */
public final class KeywordUtils {

    public static boolean checkWholeKeyword(String text, String keyword) {
        Pattern pattern = Pattern.compile("(?i)(^|.*\\s)" + keyword + "((\\p{Punct}|\\s).*|$)");

        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}
