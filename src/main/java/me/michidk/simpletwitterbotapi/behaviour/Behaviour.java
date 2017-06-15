package me.michidk.simpletwitterbotapi.behaviour;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Longs;
import me.michidk.simpletwitterbotapi.TwitterBot;
import me.michidk.simpletwitterbotapi.events.Event;
import me.michidk.simpletwitterbotapi.events.FollowEvent;
import me.michidk.simpletwitterbotapi.events.HashtagEvent;
import me.michidk.simpletwitterbotapi.events.KeywordEvent;
import me.michidk.simpletwitterbotapi.filters.Filter;
import me.michidk.simpletwitterbotapi.filters.SingleWordFilter;
import me.michidk.simpletwitterbotapi.reactions.Reaction;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class Behaviour {

    private Event[] events;
    private Filter[] filters;
    private Reaction[] reactions;

    private TwitterBot twitterBot;
    private FilterQuery query;
    private Logger logger = Logger.getLogger(Behaviour.class);
    private TwitterStream stream = TwitterStreamFactory.getSingleton();
    private StatusListener statusListener = new BehaviourStatusListener(this);


    protected Behaviour(BehaviourBuilder builder) {
        this.events = Iterables.toArray(builder.eventList, Event.class);
        this.filters = Iterables.toArray(builder.filterList, Filter.class);
        this.reactions = Iterables.toArray(builder.reactionList, Reaction.class);
    }

    public void initialize(TwitterBot twitterBot) {
        this.twitterBot = twitterBot;
        setupQueries();
    }

    private void setupQueries() {
        List<String> currentKeywords = new ArrayList<>();
        List<String> wholeKeywords = new ArrayList<>();
        List<Long> currentFollows = new ArrayList<>();

        for(Event event : events) {
            if (event instanceof KeywordEvent) {
                KeywordEvent keyword = (KeywordEvent) event;

                currentKeywords.add(keyword.getQueryPart());

                if (keyword.isFilterWholeWords())
                    wholeKeywords.add(keyword.getQueryPart());
            } else if (event instanceof FollowEvent) {
                FollowEvent follow = (FollowEvent) event;

                for (long id : follow.getQueryPart())
                    currentFollows.add(id);
            }
        }

        query = new FilterQuery();
        query.track(Iterables.toArray(currentKeywords, String.class));
        query.follow(Longs.toArray(currentFollows));

        // fix twitter query detects not only whole words
        filters = Arrays.copyOf(filters, filters.length + 1);
        filters[filters.length - 1] = new SingleWordFilter(Iterables.toArray(wholeKeywords, String.class));
    }

    public void start() {
        if (events == null)
            throw new BehaviourSetupException("Events not set up");
        if (filters == null)
            throw new BehaviourSetupException("Filters not set up");
        if (reactions == null)
            throw new BehaviourSetupException("Reactions not set up");

        stream.addListener(statusListener);
        stream.filter(query);
    }

    public void stop() {
        stream.removeListener(statusListener);
    }


    protected boolean passFilters(Status status) {
        for (Filter filter : filters) {
            if (filter.filter(status))
                return false;
        }

        return true;
    }

    protected Reaction[] getReactions() {
        return reactions;
    }

    protected TwitterBot getTwitterBot() {
        return twitterBot;
    }

    protected Logger getLogger() {
        return logger;
    }

}