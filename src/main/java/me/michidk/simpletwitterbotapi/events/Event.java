package me.michidk.simpletwitterbotapi.events;

/**
 * Created by Michael Lohr on 18.09.2016.
 */
public abstract class Event<T> {

    public abstract T getQueryPart();

}
