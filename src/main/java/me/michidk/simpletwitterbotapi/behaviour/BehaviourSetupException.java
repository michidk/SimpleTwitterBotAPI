package me.michidk.simpletwitterbotapi.behaviour;

/**
 * Created by Michael Lohr on 20.09.2016.
 */
public class BehaviourSetupException extends RuntimeException {

    public BehaviourSetupException() {

    }

    public BehaviourSetupException(String message) {
        super (message);
    }

    public BehaviourSetupException(Throwable cause) {
        super (cause);
    }

    public BehaviourSetupException(String message, Throwable cause) {
        super (message, cause);
    }

}
