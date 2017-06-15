package me.michidk.simpletwitterbotapi.behaviour;

import me.michidk.simpletwitterbotapi.events.Event;
import me.michidk.simpletwitterbotapi.filters.Filter;
import me.michidk.simpletwitterbotapi.reactions.Reaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael Lohr on 15.06.2017.
 */
public class BehaviourBuilder {

    List<Event> eventList = new ArrayList<>();
    List<Filter> filterList = new ArrayList<>();
    List<Reaction> reactionList = new ArrayList<>();


    public BehaviourBuilder() {
    }

    public BehaviourBuilder(Event[] events, Filter[] filters, Reaction[] reactions) {
        this.eventList = Arrays.asList(events);
        this.filterList = Arrays.asList(filters);
        this.reactionList = Arrays.asList(reactions);
    }

    public BehaviourBuilder(List<Event> eventList, List<Filter> filterList, List<Reaction> reactionList) {
        this.eventList = eventList;
        this.filterList = filterList;
        this.reactionList = reactionList;
    }

    public BehaviourBuilder listen(Event... event) {
        eventList.addAll(Arrays.asList(event));
        return this;
    }

    public BehaviourBuilder filter(Filter... filter) {
        filterList.addAll(Arrays.asList(filter));
        return this;
    }

    public BehaviourBuilder react(Reaction... reaction) {
        reactionList.addAll(Arrays.asList(reaction));
        return this;
    }

    public Behaviour build() {
        return new Behaviour(this);
    }

    public static BehaviourBuilder create() {
        return new BehaviourBuilder();
    }

}