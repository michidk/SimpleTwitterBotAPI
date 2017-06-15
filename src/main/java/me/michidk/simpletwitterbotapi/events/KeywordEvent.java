package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class KeywordEvent extends Event<String> {

    private String keyword;
    private boolean filterWholeWords = false;   // detect only " full words " instead of "fullwords"

    public KeywordEvent(String keyword) {
        this.keyword = keyword;
    }

    public KeywordEvent(String keyword, boolean filterWholeWords) {
        this(keyword);

        this.filterWholeWords = filterWholeWords;
    }

    @Override
    public String getQueryPart() {
        return keyword;
    }

    public boolean doesFilterWholeWords() {
        return filterWholeWords;
    }
}
