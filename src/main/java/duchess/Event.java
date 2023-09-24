package duchess;

import java.util.ArrayList;

/**
 * Represents an Event Task, which is a Task with a name, start time and end time.
 */
public class Event extends Task {
    private String startTime;
    private String endTime;

    /**
     * Creates a new Event instance with the provided name, start time, end time and status.
     *
     * @param name      - the name of the Event.
     * @param startTime - the startTime in String format.
     * @param endTime   - the endTime in String format.
     * @param status    - the current task status of the Event.
     */
    public Event(String name, String startTime, String endTime, TaskStatus status) {
        super(name, status);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Creates a new Event instance with the provided name, start time, end time and status. The status
     * will be the default status in Task.
     *
     * @param name      - the name of the Event.
     * @param startTime - the startTime in String format.
     * @param endTime   - the endTime in String format.
     */
    public Event(String name, String startTime, String endTime) {
        super(name);

        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the String representation of this Event.
     *
     * @return the String representation of this Event.
     */
    @Override
    public String mainString() {
        return String.format("[E] %s (from: %s to: %s)", super.mainString(), this.startTime, this.endTime);
    }

    /**
     * Returns the String representation of this Event, for the purposes of saving.
     *
     * @return the String representation of this Event.
     */
    @Override
    public String toSaveString() {
        String saveString = String.format("E|%s|from:%s|to:%s|", super.toSaveString(), this.startTime, this.endTime);

        for (String tag : this.tags) {
            saveString += String.format("#%s|", tag);
        }

        saveString = saveString.substring(0, saveString.length() - 1);

        return saveString;
    }

    /**
     * Returns a new Event from a Save String.
     *
     * @return the Event that this String is represented by.
     */
    public static Event fromSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);
        ArrayList<String> tags = new ArrayList<>();

        TaskStatus taskStatus = TaskStatus.UNMARKED;
        String name = "";
        String startTime = "";
        String endTime = "";

        if (Integer.parseInt(splitString[1]) == 1) {
            taskStatus = TaskStatus.MARKED;
        }

        name = splitString[2];

        if (splitString[3].startsWith("from:")) {
            startTime = splitString[3].substring(5);
        }

        if (splitString[4].startsWith("to:")) {
            endTime = splitString[4].substring(3);
        }

        for (int i = 5; i < splitString.length; i++) {
            String dataString = splitString[i];

            if (dataString.startsWith("#")) {
                tags.add(dataString.substring(1));
            }
        }

        Event event = new Event(name, startTime, endTime, taskStatus);

        for (String tag : tags) {
            event.addTag(tag);
        }

        return event;
    }
}
