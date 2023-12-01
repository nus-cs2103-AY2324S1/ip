package duchess;

import java.util.ArrayList;

/**
 * An abstract class representing a Task.
 */
public abstract class Task {
    public static final String SAVE_STRING_DELIMITER = "\\|";

    protected ArrayList<String> tags;

    private String name;
    private TaskStatus status;

    /**
     * Returns a new Task object with the given parameters.
     *
     * @param name - the name of the Task.
     * @param TaskStatus - the status of the Task.
     */
    Task(String name, TaskStatus status) {
        this.name = name;
        this.status = status;

        this.tags = new ArrayList<>();
    }

    /**
     * Returns a new Task object with the given parameters and a default TaskStatus of unmarked.
     *
     * @param name - the name of the Task.
     */
    Task(String name) {
        this(name, TaskStatus.UNMARKED);
    }

    /**
     * Factory method of Task, creating a Task by parsing a String that represents a Task.
     *
     * @param s - the String that represents the Task.
     * @return    either the Task that was parsed successfully using the String, or null if the string is
     *            an invalid Task.
     */
    public static Task parseSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);

        // Check for the types
        if (splitString[0].equals("T")) {
            // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
            return ToDo.fromSaveString(s);
        } else if (splitString[0].equals("D")) {
            // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
            return Deadline.fromSaveString(s);
        } else if (splitString[0].equals("E")) {
            // Not enough arguments; minmally, it needs the Type, the Marked status, and the Name.
            return Event.fromSaveString(s);
        }

        return null;
    }

    /**
     * Changes this Task's status to the provided TaskStatus.
     *
     * @param newStatus - the new TaskStatus for this task's status to be changed to.
     */
    public void changeStatus(TaskStatus newStatus) {
        this.status = newStatus;
    }

    /**
     * Returns the name of this Task.
     *
     * @return the name of this Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Used for formatting the toString. Pre-string will be added before the mainString and
     * the postString, when calling toString.
     *
     * @return the pre-String for this Task.
     */
    public String preString() {
        return "";
    }

    /**
     * Used for formatting the toString. mainString will be in between pre-string and post-string when calling toString.
     *
     * @return the mainString for this Task.
     */
    public String mainString() {
        String thisString = "";
        if (this.status == TaskStatus.UNMARKED) {
            thisString += String.format("[ ] %s", this.name);
        }
        if (this.status == TaskStatus.MARKED) {
            thisString += String.format("[X] %s", this.name);
        }

        return thisString;
    }

    /**
     * Used for formatting the toString. post-String will be after main-string and post-string when calling toString.
     *
     * @return the post-String for this Task.
     */
    public String postString() {
        if (this.tags.size() <= 0) {
            return "";
        }

        String postString = "(";

        for (String s : this.tags) {
            postString += "#" + s + " ";
        }

        postString = postString.substring(0, postString.length() - 1);
        postString += ")";
        return postString;
    }

    /**
     * Returns a String representation of this task.
     *
     * @return the String representation of this task.
     */
    public String toString() {
        String thisString = "";

        String preString = this.preString();
        String mainString = this.mainString();
        String postString = this.postString();

        if (preString.length() > 0) {
            thisString += preString + " ";
        }

        thisString += mainString;

        if (postString.length() > 0) {
            thisString += " " + postString;
        }

        return thisString;
    }

    /**
     * Returns a String representation of this task, for the purposes of data saving.
     */
    public String toSaveString() {
        if (this.status == TaskStatus.UNMARKED) {
            return String.format("0|%s", this.name);
        }
        if (this.status == TaskStatus.MARKED) {
            return String.format("1|%s", this.name);
        }
        return "";
    }

    /**
     * Adds a tag to this Task's list of tags.
     *
     * @param tag - tag to be added
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Removes a tag from this Task's list of tags. If no such
     * tag is attached to this Task, does nothing.
     *
     * @param tag - tag to be removed.
     */
    public void removeTag(String tag) {
        // Iterate through the tag array in reverse since we are removing the tags.
        for (int i = tags.size() - 1; i >= 0; i--) {
            String currentTag = this.tags.get(i);

            if (currentTag.equals(tag)) {
                this.tags.remove(i);
                continue;
            }
        }
    }
}
