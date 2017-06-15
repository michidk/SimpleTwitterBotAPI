package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 20.09.2016.
 */
public class FollowEvent extends Event<long[]> {

    private long[] users;


    public FollowEvent(long... users) {
        this.users = users;
    }

    @Override
    public long[] getQueryPart() {
        return users;
    }

}
