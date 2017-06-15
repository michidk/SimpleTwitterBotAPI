package me.michidk.simpletwitterbotapi.behaviour;

import me.michidk.simpletwitterbotapi.reactions.Reaction;
import twitter4j.*;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class BehaviourStatusListener implements StatusListener {

    private Behaviour behaviour;


    protected BehaviourStatusListener(Behaviour behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public void onStatus(Status status) {
        if (!behaviour.passFilters(status))
            return;

        String text = status.getText();
        User user = status.getUser();

        behaviour.getLogger().debug(user + ": " + text);

        try {
            for (Reaction reaction : behaviour.getReactions())
                reaction.react(behaviour.getTwitterBot(), status);
        } catch (TwitterException e) {
            behaviour.getLogger().error("Couldn't perform reaction: " + e.getErrorMessage());
        }
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
        behaviour.getLogger().warn(stallWarning.getMessage());
    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
    }

};
