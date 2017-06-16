package me.michidk.simpletwitterbotapi;

import com.google.common.collect.Iterables;
import com.google.common.primitives.Longs;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Michael Lohr on 15.06.2017.
 *
 * Singleton, because you can only have one Twitter stream
 * https://stackoverflow.com/questions/19369347/multiple-user-streams-in-twitter4j
 */
public class QueryManager {

    private static final QueryManager instance = new QueryManager();

    private List<String> keywords = new ArrayList<>();
    private List<Long> followedUserIDs = new ArrayList<>();

    private FilterQuery query = new FilterQuery();
    private TwitterStream stream = TwitterStreamFactory.getSingleton();


    private QueryManager() {
        if (instance != null)
            Logger.getLogger(QueryManager.class).warn("Created additonal instance of QueryManager - ignoring");
    }

    public boolean addKeyword(String keyword) {
        boolean result = this.keywords.add(keyword);
        apply();

        return result;
    }

    public boolean addKeywords(List<String> keywords) {
        boolean result = this.keywords.addAll(keywords);
        apply();

        return result;
    }

    public boolean removeKeyword(String keyword) {
        boolean result = this.keywords.remove(keyword);
        apply();

        return result;
    }

    public boolean removeKeywords(List<String> keywords) {
        boolean result = this.keywords.removeAll(keywords);
        apply();

        return result;
    }

    public boolean addFollwedUser(Long userID) {
        boolean result = this.followedUserIDs.add(userID);
        apply();

        return result;
    }

    public boolean addFollowedUsers(List<Long> usersIDs) {
        boolean result = this.followedUserIDs.addAll(usersIDs);
        apply();

        return result;
    }

    public boolean removeFollowedUser(Long userID) {
        boolean result = this.followedUserIDs.remove(userID);
        apply();

        return result;
    }

    public boolean removeFollowedUsers(List<Long> userIDs) {
        boolean result = this.followedUserIDs.removeAll(userIDs);
        apply();

        return result;
    }

    public void addListener(StatusListener listener) {
        stream.addListener(listener);
    }

    public void removeListener(StatusListener listener) {
        stream.removeListener(listener);
    }

    // don't use this
    public void reset() {
        keywords.clear();
        followedUserIDs.clear();

        apply();
    }

    private void apply() {
        query.track(Iterables.toArray(keywords, String.class));
        query.follow(Longs.toArray(followedUserIDs));

        stream.filter(query);
    }

    public static QueryManager getInstance() {
        return instance;
    }
}
