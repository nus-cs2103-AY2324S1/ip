package duke.exceptions;

public class BackwardsTimeException extends Exception {

    public BackwardsTimeException() {
        super("Sorry... The provided timeframe is impossible as the deadline is earlier than the start...");
    }

}
