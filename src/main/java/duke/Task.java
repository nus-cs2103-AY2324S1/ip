package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task entered by the user.
 */
public class Task {
    private String name;
    private Boolean isDone;
    private String input;

    /**
     * A public constructor for the task class.
     *
     * @param name extracted from the user input
     * @param input the user input
     */

    public Task(String name, String input) {
        this.name = name;
        this.isDone = false;
        this.input = input;
    }

    /**
     * A method that sets the done flag to true.
     */

    public void setDone() {
        this.isDone = true;
    }

    /**
     * A method that sets the done flag to false.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Getter method for the done field.
     *
     * @return the boolean value of the done flag
     */

    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Getter method for the name field.
     *
     * @return the string name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for the input field.
     *
     * @return the string input
     */

    public String getInput() {
        return this.input;
    };

    /**
     * Saves the individual task as string into the file.
     *
     * @param filepath directs to the file
     */

    public void save(String filepath) {
        try {
            FileWriter myWriter = new FileWriter(filepath, true);
            myWriter.write(String.format("%s%s\n", this.getInput(), this.getDone() ? "1" : "0"));
            myWriter.close();
        } catch (IOException ex) {
            System.out.println("Error saving to file");
            System.exit(1);
        }
    }

    /**
     * String representation of the task.
     *
     * @return a string
     */
    @Override
    public String toString() {
        if (!this.isDone) {
            return "[ ] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }
}