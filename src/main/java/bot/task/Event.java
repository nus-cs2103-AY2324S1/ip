package bot.task;

import bot.exception.DateTimeParseBotException;
import bot.parsers.DatetimeParser;

import java.time.LocalDateTime;

public class Event extends Task {

    private static final String TASK_HEADER = "[E] ";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates an instance of an Event object
     *
     * @param name the Task description
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @throws DateTimeParseBotException if the startTime or endTime were not formatted correct.
     */
    public Event(String name, String startTime, String endTime) throws DateTimeParseBotException {
        super(name);
        this.startTime = DatetimeParser.parseTimeInput(startTime);
        this.endTime = DatetimeParser.parseTimeInput(endTime);
    }

    /**
     * Creates an instance of an Event object
     *
     * @param bool the state of event completion
     * @param name the Task description
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @throws DateTimeParseBotException if the startTime or endTime were not formatted correct.
     */
    public Event(String bool, String name, String startTime, String endTime) throws DateTimeParseBotException {
        super(name, Boolean.parseBoolean(bool));
        this.startTime = DatetimeParser.convertToLocalDateTime(startTime);
        this.endTime = DatetimeParser.convertToLocalDateTime(endTime);
    }

    /**
     * Returns a string formatted according to the Event object to be stored in data/task.txt
     *
     * @return a string formatted according to an Event object
     */
    @Override
    public String fileWriteFormatted() {
        return Event.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR +
                super.fileWriteFormatted() + Task.UNIQUE_FILE_SEPARATOR + this.startTime +
                Task.UNIQUE_FILE_SEPARATOR + this.endTime;
    }

    /**
     * Returns a string representation of Event object
     *
     * @return a string representation of Event object
     */
    @Override
    public String toString() {
        return Event.TASK_HEADER + super.toString()+
                " (from: " + DatetimeParser.reformatTimeOutput(this.startTime) +
                " to: " + DatetimeParser.reformatTimeOutput(this.endTime) + ")";
    }
}
