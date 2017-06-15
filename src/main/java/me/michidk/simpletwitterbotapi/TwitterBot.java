package me.michidk.simpletwitterbotapi;

import me.michidk.simpletwitterbotapi.behaviour.Behaviour;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class TwitterBot {

    protected Twitter twitter4J;

    private List<Behaviour> behaviours = new ArrayList<>();


    public TwitterBot() {
        twitter4J = TwitterFactory.getSingleton();
    }

    public TwitterBot(Configuration configuration) {
        twitter4J = new TwitterFactory(configuration).getInstance();
    }

    public void like(Status tweet) throws TwitterException {
        twitter4J.createFavorite(tweet.getId());
    }

    public void follow(Status tweet) throws TwitterException {
        twitter4J.createFriendship(tweet.getUser().getId());
    }

    public void retweet(Status tweet) throws TwitterException {
        twitter4J.retweetStatus(tweet.getId());
    }

    public void reply(Status tweet, String message) throws TwitterException {
        twitter4J.updateStatus("@" + tweet.getUser().getScreenName() + " " + message);
    }

    public Twitter getTwitter4J() {
        return twitter4J;
    }

    public boolean addBehaviour(Behaviour behaviour) {
        behaviour.initialize(this);
        behaviour.start();
        return behaviours.add(behaviour);
    }

    public boolean removeBehaviour(Behaviour behaviour) {
        behaviour.stop();
        return behaviours.remove(behaviour);
    }
}
