package boti.task;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeParseException;

import boti.exception.InvalidTimedTaskException;



/**
 * Class for timed task (task that can be done in a given period of time)
 */
public class TimedTask extends Task {
    private Duration duration;
    private String durationType;

    /**
     * Instantiates timed task
     *
     * @param name the name of the timed task
     * @param duration the duration of the timed task
     */
    public TimedTask(String name, Duration duration, String durationType) {
        super(name);
        this.duration = duration;
        this.durationType = durationType;
    }

    /**
     * Creates a new timed task based on the message
     *
     * @param message the message to create the new timed task
     * @return the new timed task
     * @throws InvalidTimedTaskException when the timed task command message is invalid
     */
    public static TimedTask create(String message) throws InvalidTimedTaskException {
        assert message.split(" ")[0].equalsIgnoreCase("timed") : "First word of message must be timed";
        try {
            String name = message.substring(6, message.indexOf("/in "));
            String durationString = message.substring(message.indexOf("/in ") + 4);
            String[] durationStringSplit = durationString.split(" ");
            if (durationStringSplit.length == 2) {
                long durationLength = Long.parseLong(durationStringSplit[0]);
                String durationParameter = durationStringSplit[1];
                switch (durationParameter.toLowerCase()) {
                case "day":
                case "days":
                    return new TimedTask(name, Duration.ofDays(durationLength), "day");
                case "hour":
                case "hours":
                    return new TimedTask(name, Duration.ofHours(durationLength), "hour");
                case "minute":
                case "minutes":
                    return new TimedTask(name, Duration.ofMinutes(durationLength), "minute");
                case "second":
                case "seconds":
                    return new TimedTask(name, Duration.ofSeconds(durationLength), "second");
                default:
                    throw new InvalidTimedTaskException();
                }
            }
            throw new InvalidTimedTaskException();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTimedTaskException();
        } catch (DateTimeParseException e) {
            throw new InvalidTimedTaskException();
        }
    }

    /**
     * Creates new TimedTask based on the string stored in the storage
     *
     * @param stored the string stored in the storage
     * @return the TimedTask based on the string stored in the storage
     * @throws IOException when the string stored does not belong to TimedTask
     */
    public static TimedTask createFromStorage(String stored) throws IOException {
        assert stored.split(" \\| ")[0].equals("TT") : "The first part of the string stored is TT";
        String[] splitTaskInString = stored.split(" \\| ");
        String mark = splitTaskInString[1];
        String description = splitTaskInString[2];
        String timedTaskString = splitTaskInString[3];
        String durationType = splitTaskInString[4];
        TimedTask timedTask = new TimedTask(description, Duration.parse(timedTaskString), durationType);
        if (mark.equals("1")) {
            timedTask.mark();
        }
        return timedTask;
    }

    @Override
    public String toString() {
        switch (durationType) {
        case "day":
            return "[TT]" + super.toString() + "(in: " + duration.toDays() + " days)";
        case "hour":
            return "[TT]" + super.toString() + "(in: " + duration.toHours() + " hours)";
        case "minute":
            return "[TT]" + super.toString() + "(in: " + duration.toMinutes() + " minutes)";
        default:
            assert durationType == "seconds" : "The only case left possible is when the duration is in second";
            return "[TT]" + super.toString() + "(in: " + duration.toSeconds() + " seconds)";
        }
    }

    @Override
    public String storeInString() {
        return "TT | " + (getMark() ? "1 | " : "0 | ") + getName() + " | " + duration + " | " + durationType;
    }
}
