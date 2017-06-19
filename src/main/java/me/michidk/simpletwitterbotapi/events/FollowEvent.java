package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 20.09.2016.
 */
public class FollowEvent extends Event<Long> {

    private Long user;


    public FollowEvent(Long user) {
        this.user = user;
    }

    @Override
    public Long getQueryPart() {
        return user;
    }

}
