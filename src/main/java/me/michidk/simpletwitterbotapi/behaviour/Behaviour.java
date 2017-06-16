package me.michidk.simpletwitterbotapi.behaviour;

import me.michidk.simpletwitterbotapi.QueryManager;
import me.michidk.simpletwitterbotapi.TwitterBot;
import me.michidk.simpletwitterbotapi.events.Event;
import me.michidk.simpletwitterbotapi.events.FollowEvent;
import me.michidk.simpletwitterbotapi.events.KeywordEvent;
import me.michidk.simpletwitterbotapi.filters.Filter;
import me.michidk.simpletwitterbotapi.reactions.Reaction;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class Behaviour {

    private List<Event> events = new ArrayList<>();
    private List<Filter> filters = new ArrayList<>();
    private List<Reaction> reactions = new ArrayList<>();

    private List<String> keywords = new ArrayList<>();
    private List<Long> followedUserIDs = new ArrayList<>();

    private TwitterBot twitterBot;
    private Logger logger = Logger.getLogger(Behaviour.class);

    private StatusListener statusListener;


    protected Behaviour(BehaviourBuilder builder) {
        this.events = builder.eventList;
        this.filters = builder.filterList;
        this.reactions = builder.reactionList;
    }

    public void initialize(TwitterBot twitterBot) {
        this.twitterBot = twitterBot;

        for (Event event : events) {
            if (event instanceof KeywordEvent) {
                keywords.add(((KeywordEvent) event).getQueryPart());
            } else if (event instanceof FollowEvent) {
                followedUserIDs.add(((FollowEvent) event).getQueryPart());
            }
        }

        statusListener = new BehaviourStatusListener(this, events);
    }

    public void configure() {
        if (twitterBot == null) {
            logger.error("TwitterBot not initialized!");
            return;
        }

        if (events.size() == 0)
            logger.warn("No events defined");
        if (filters.size() == 0)
            logger.warn("No filters defined");
        if (reactions.size() == 0)
            logger.warn("No reactions defined");

        QueryManager qm = QueryManager.getInstance();
        qm.addListener(statusListener);
        qm.addFollowedUsers(followedUserIDs);
        qm.addKeywords(keywords);

        if (twitterBot.isDebug())
            logger.info("Started Behaviour");
    }

    public void unconfigure() {
        QueryManager qm = QueryManager.getInstance();
        qm.removeListener(statusListener);
        qm.removeFollowedUsers(followedUserIDs);
        qm.removeKeywords(keywords);

        if (twitterBot.isDebug())
            logger.info("Stopped Behaviour");
    }

    protected List<Event> getEvents() {
        return events;
    }

    protected List<Filter> getFilters() {
        return filters;
    }

    protected List<Reaction> getReactions() {
        return reactions;
    }

    protected TwitterBot getTwitterBot() {
        return twitterBot;
    }

}
