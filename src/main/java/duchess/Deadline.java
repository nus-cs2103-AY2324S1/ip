package duchess;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A deadline is a Task but with a deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Creates a new Deadline instance with the provided name, deadline and status.
     *
     * @param name              - the name of the Deadline.
     * @param deadline          - the deadline in String format.
     * @param status            - the current task status of the Deadline.
     * @throws DuchessException   if the deadlineString is not in a recongnized Date format.
     */
    public Deadline(String name, String deadlineString, TaskStatus status) throws DuchessException {
        super(name, status);

        this.deadline = Utility.parseDateString(deadlineString);
    }

    /**
     * Creates a new Deadline instance with the provided name and deadline.
     * The status will be the default status in Task.
     *
     * @param name              - the name of the Deadline.
     * @param deadline          - the deadline in String format.
     * @throws DuchessException   if the deadlineString is not in a recongnized Date format.
     */
    public Deadline(String name, String deadlineString) throws DuchessException {
        super(name);

        this.deadline = Utility.parseDateString(deadlineString);
    }

    /**
     * Returns the String representation of this Deadline.
     *
     * @return the String representation of this Deadline.
     */
    @Override
    public String mainString() {
        return String.format("[D] %s (by: %s)", super.mainString(), this.deadline);
    }

    /**
     * Returns the String representation of this Deadline, for the purposes of saving.
     *
     * @return the String representation of this Deadline.
     */
    @Override
    public String toSaveString() {
        String saveString = String.format("D|%s|by:%s|", super.toSaveString(), this.deadline);

        for (String tag : this.tags) {
            saveString += String.format("#%s|", tag);
        }

        saveString = saveString.substring(0, saveString.length() - 1);

        return saveString;
    }

    /**
     * Returns a new Deadline from a Save String.
     *
     * @return the Deadline that this String is represented by, or null if the Deadline string is ill-formatted.
     */
    public static Deadline fromSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);

        TaskStatus taskStatus = TaskStatus.UNMARKED;
        String name = "";
        String deadlineString = "";
        ArrayList<String> tags = new ArrayList<>();

        if (Integer.parseInt(splitString[1]) == 1) {
            taskStatus = TaskStatus.MARKED;
        }

        name = splitString[2];

        if (splitString[3].startsWith("by:")) {
            deadlineString = splitString[3].substring(3);
        }

        for (int i = 4; i < splitString.length; i++) {
            String dataString = splitString[i];

            if (dataString.startsWith("#")) {
                tags.add(dataString.substring(1));
            }
        }

        try {
            Deadline deadline = new Deadline(name, deadlineString, taskStatus);

            for (String tag : tags) {
                deadline.addTag(tag);
            }

            return deadline;

        } catch (DuchessException e) {
            return null;
        }
    }
}
