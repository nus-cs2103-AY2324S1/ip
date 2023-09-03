package duke.task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Emcapsulates a Todo task
 * @author Donovan Chan Jia Jun
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Checks if both objects are equal.
     *
     * @param o Object to be compared against
     * @return {@code true} if both objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        Todo task = (Todo) o;
        return super.getName().equals(task.getName());
    }

    /**
     * Write the Todo object to the storage file in its format.
     *
     * @param fileWriter Filewriter that writes to a specific output file
     */
    public void writeToFile(FileWriter fileWriter) {
        String marking = super.isComplete() ? "0" : "1";
        try {
            fileWriter.write("T" + "|" + marking + "|" + super.getName());
            fileWriter.write(System.lineSeparator());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the string representation of the Todo Object.
     *
     * @return String Represents the Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s %s", super.getMarking(), super.name);
    }
}
