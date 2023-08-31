package duke;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task {
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name.trim();
        this.isDone = false;
    }

    Task(String name, boolean isDone) {
        this.name = name.trim();
        this.isDone = isDone;
    }

    /**
     * Checks if this task's name contains the specified substring
     *
     * @param subString Substring to check for
     * @return true if this task's name contains the substring
     */
    public boolean hasSubstring(String subString) {
        Pattern pattern = Pattern.compile(subString, Pattern.LITERAL);
        Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    public void setIsDone(boolean isDone) throws LukeException {
        if (this.isDone == isDone) {
            throw new LukeException("Task is already marked as done");
        }
        this.isDone = isDone;
    }

    public String toSaveStr() {
        return (isDone ? "Done" : "Not Done")
                + " | " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Task) {
            Task taskObj = (Task) o;

            return this.name.equals(taskObj.name)
                    && this.isDone == taskObj.isDone;
        }

        return false;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "x" : " ";
        return "[" + doneIndicator + "] " + name;
    }
}
