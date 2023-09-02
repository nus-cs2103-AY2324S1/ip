package task;

import exception.DateTimeParseBotException;
import parsers.DatetimeParser;

import java.time.LocalDateTime;

public class Event extends Task {

    private static final String TASK_HEADER = "[E] ";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String name, String startTime, String endTime) throws DateTimeParseBotException {
        super(name);
        this.startTime = DatetimeParser.parseTimeInput(startTime);
        this.endTime = DatetimeParser.parseTimeInput(endTime);
    }

    public Event(String bool, String name, String startTime, String endTime) throws DateTimeParseBotException {
        super(name, Boolean.parseBoolean(bool));
        this.startTime = DatetimeParser.convertToLocalDateTime(startTime);
        this.endTime = DatetimeParser.convertToLocalDateTime(endTime);
    }

    @Override
    public String fileWriteFormatted() {
        return Event.TASK_HEADER + Task.UNIQUE_FILE_SEPARATOR +
                super.fileWriteFormatted() + Task.UNIQUE_FILE_SEPARATOR + this.startTime +
                Task.UNIQUE_FILE_SEPARATOR + this.endTime;
    }

    @Override
    public String toString() {
        return Event.TASK_HEADER + super.toString()+
                " (from: " + DatetimeParser.reformatTimeOutput(this.startTime) +
                " to: " + DatetimeParser.reformatTimeOutput(this.endTime) + ")";
    }
}
