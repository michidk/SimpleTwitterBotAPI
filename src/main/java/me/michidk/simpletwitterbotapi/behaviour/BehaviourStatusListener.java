package me.michidk.simpletwitterbotapi.behaviour;

import me.michidk.simpletwitterbotapi.events.Event;
import me.michidk.simpletwitterbotapi.events.FollowEvent;
import me.michidk.simpletwitterbotapi.events.KeywordEvent;
import me.michidk.simpletwitterbotapi.filters.Filter;
import me.michidk.simpletwitterbotapi.reactions.Reaction;
import me.michidk.simpletwitterbotapi.utils.KeywordUtils;
import twitter4j.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class BehaviourStatusListener implements StatusListener {

    private Logger logger = Logger.getLogger(Behaviour.class);

    private Behaviour behaviour;

    private List<Event> events;


    public BehaviourStatusListener(Behaviour behaviour, List<Event> events) {
        this.behaviour = behaviour;
        this.events = events;
    }

    @Override
    public void onStatus(Status status) {

        String text = status.getText();
        User user = status.getUser();
        boolean debug = behaviour.getTwitterBot().isDebug();

        if (debug)
            logger.info("Received status:" + text + " by " + user.getScreenName());

        if (!checkIfKeywordOfCurrentBehaviour(text)) {
            if (debug)
                logger.info("Status didn't pass keyword check");

            return;
        }

        if (filter(status)) {
            return;
        }

        try {
            for (Reaction reaction : behaviour.getReactions())
                reaction.react(behaviour.getTwitterBot(), status);
        } catch (TwitterException e) {
            logger.error("Couldn't perform reaction: " + e.getErrorMessage());
        }
    }

    private boolean checkIfKeywordOfCurrentBehaviour(String text) {
        for (Event event : events) {

            if (event instanceof KeywordEvent) {

                KeywordEvent keywordEvent = (KeywordEvent) event;
                String keyword = keywordEvent.getQueryPart();

                if (keywordEvent.doesFilterWholeWords()) {
                    if (KeywordUtils.checkWholeKeyword(text, keyword))
                        return true;
                } else {
                    if (text.toLowerCase().contains(keyword.toLowerCase()))
                        return true;
                }

            }

        }

        return false;
    }

    private boolean filter(Status status) {
        for (Filter filter : behaviour.getFilters()) {
            if (filter.filter(status)) {
                if (behaviour.getTwitterBot().isDebug())
                    logger.info("Status didn't pass filter: " + filter.getClass().getName());

                return true;
            }
        }

        return false;
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {

    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {
        logger.warn(stallWarning.getMessage());
    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
    }

};
