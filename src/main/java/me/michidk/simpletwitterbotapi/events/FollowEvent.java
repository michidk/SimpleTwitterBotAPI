package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 20.09.2016.
 */
public class FollowEvent extends Event<Long> {

    private Long users;


    public FollowEvent(Long users) {
        this.users = users;
    }

    @Override
    public Long getQueryPart() {
        return users;
    }

}
