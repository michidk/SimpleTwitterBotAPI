package me.michidk.simpletwitterbotapi;

import me.michidk.simpletwitterbotapi.behaviour.Behaviour;
import twitter4j.*;
import twitter4j.conf.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public class TwitterBot {

    protected Twitter twitter4J;
    protected boolean debug = false;

    private Logger logger = Logger.getLogger(TwitterBot.class);

    private List<Behaviour> behaviours = new ArrayList<>();


    public TwitterBot() {
        twitter4J = TwitterFactory.getSingleton();
    }

    public TwitterBot(boolean debug) {
        this();

        this.debug = debug;
    }

    public TwitterBot(Configuration configuration) {
        twitter4J = new TwitterFactory(configuration).getInstance();
    }

    public void like(Status tweet) throws TwitterException {
        twitter4J.createFavorite(tweet.getId());
        if (debug)
            logger.info("Liked " + tweet.getText() + " by " + tweet.getUser().getScreenName());
    }

    public void follow(Status tweet) throws TwitterException {
        twitter4J.createFriendship(tweet.getUser().getId());
        if (debug)
            logger.info("Followed " + tweet.getUser().getScreenName());
    }

    public void retweet(Status tweet) throws TwitterException {
        twitter4J.retweetStatus(tweet.getId());
        if (debug)
            logger.info("Retweeted " + tweet.getText() + " by " + tweet.getUser().getScreenName());
    }

    public void replyOrTweet(Status tweet, String message) throws TwitterException {
        StatusUpdate reply = null;
        long replyTo = tweet.getId();

        reply = new StatusUpdate("@" + tweet.getUser().getScreenName() + " " + message);
        if (replyTo != -1)
            reply.setInReplyToStatusId(replyTo);

        twitter4J.updateStatus(reply);

        if (debug)
            logger.info("Replied " + reply.getStatus() + " to " + tweet.getText() + " by " + tweet.getUser().getScreenName() + " (" + replyTo + ")");
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

    public boolean isDebug() {
        return debug;
    }
}
