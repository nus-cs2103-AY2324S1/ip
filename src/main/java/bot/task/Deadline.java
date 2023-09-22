package bot.task;

import bot.exception.DateTimeParseBotException;
import bot.parsers.DatetimeParser;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private static final String TASK_HEADER = "[D] ";
    private final LocalDateTime time;

    /**
     * Creates an instance of a Deadline object
     *
     * @param name the Task description
     * @param time the deadline of the Deadline object
     * @throws DateTimeParseBotException if the deadline was not formatted correct.
     */
    public Deadline(String name, String time) throws DateTimeParseBotException {
        super(name);
        this.time = DatetimeParser.parseTimeInput(time);
    }

    /**
     * Creates an instance of a Deadline object
     *
     * @param bool the state of the Deadline object's completion
     * @param name the Task description
     * @param time the deadline of the Deadline object
     * @throws DateTimeParseBotException if the deadline was not formatted correct.
     */
    public Deadline(String bool, String name, String time) throws DateTimeParseBotException {
        super(name, Boolean.parseBoolean(bool));
        this.time = DatetimeParser.convertToLocalDateTime(time);
    }

    /**
     * Returns a string formatted according to the Deadline object to be stored in data/task.txt
     *
     * @return a string formatted according to an Deadline object
     */
    @Override
    public String fileWriteFormatted() {
        return Deadline.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR +
                super.fileWriteFormatted() + Task.UNIQUE_FILE_SEPARATOR + this.time;
    }

    /**
     * Returns a string representation of Deadline object
     *
     * @return a string representation of Deadline object
     */
    @Override
    public String toString() {
        return Deadline.TASK_HEADER + super.toString() + "(by: " +
                DatetimeParser.reformatTimeOutput(this.time) + ")";
    }
}
