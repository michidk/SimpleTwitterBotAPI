package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class HashtagEvent extends KeywordEvent {

    public HashtagEvent(String keyword) {
        super("#" + keyword, true);
    }

}