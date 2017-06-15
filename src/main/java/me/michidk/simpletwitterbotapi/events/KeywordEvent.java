package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class KeywordEvent extends Event<String> {

    private String keyword;


    public KeywordEvent(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String getQueryPart() {
        return keyword;
    }

}
