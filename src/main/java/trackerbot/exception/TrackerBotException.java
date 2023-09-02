package trackerbot.exception;

/**
 * Dedicated Exception class for the TrackerBot.
 * <p>TrackerBotException handles any expected, recoverable errors that
 * happen in TrackerBot. The expected way to handle this exception is to
 * pass the error message into a Ui object for handling.</p>
 */
public class TrackerBotException extends Exception {
    public TrackerBotException(String errMsg) {
        super(errMsg);
    }
}
