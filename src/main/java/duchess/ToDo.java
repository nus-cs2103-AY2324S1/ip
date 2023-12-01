package duchess;

import java.util.ArrayList;

/**
 * This class represents a ToDo, or a Task with only a name and no other attributes.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo instance with the provided name and status.
     *
     * @param name   - the name of the ToDo.
     * @param status - the current task status of the ToDo.
     */
    public ToDo(String name, TaskStatus status) {
        super(name, status);
    }

    /**
     * Creates a new ToDo instance with the provided name. The status will be the default status in Task.
     *
     * @param name   - the name of the ToDo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the String representation of this ToDo.
     *
     * @return the String representation of this ToDo.
     */
    @Override
    public String mainString() {
        return String.format("[T] %s", super.mainString());
    }

    /**
     * Returns the String representation of this ToDo, for the purposes of saving.
     *
     * @return the String representation of this ToDo.
     */
    @Override
    public String toSaveString() {
        String saveString = String.format("T|%s|", super.toSaveString());

        for (String tag : this.tags) {
            saveString += String.format("#%s|", tag);
        }

        saveString = saveString.substring(0, saveString.length() - 1);

        return saveString;
    }

    /**
     * Returns a new ToDo from a Save String.
     *
     * @return the ToDo that this String is represented by.
     */
    public static ToDo fromSaveString(String s) {
        String[] splitString = s.split(Task.SAVE_STRING_DELIMITER);
        ArrayList<String> tags = new ArrayList<>();

        TaskStatus taskStatus = TaskStatus.UNMARKED;
        String name = "";

        if (Integer.parseInt(splitString[1]) == 1) {
            taskStatus = TaskStatus.MARKED;
        }

        name = splitString[2];

        for (int i = 3; i < splitString.length; i++) {
            String dataString = splitString[i];

            if (dataString.startsWith("#")) {
                tags.add(dataString.substring(1));
            }
        }

        ToDo todo = new ToDo(name, taskStatus);

        for (String tag : tags) {
            todo.addTag(tag);
        }

        return todo;
    }
}
