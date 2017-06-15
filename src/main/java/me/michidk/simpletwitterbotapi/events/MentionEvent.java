package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class MentionEvent extends KeywordEvent {

    public MentionEvent(String username) {
        super("@" + username, true);
    }

}
