package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a task entered by the user.
 */
public class Task {
    private String name;
    private Boolean done;
    private String input;

    /**
     * A public constructor for the task class.
     *
     * @param name extracted from the user input
     * @param input the user input
     */

    public Task(String name, String input) {
        this.name = name;
        this.done = false;
        this.input = input;
    }

    /**
     * A method that changes the done flag.
     */

    public void toggleDone() {
        this.done = !this.done;
    }

    /**
     * Getter method for the done field.
     *
     * @return the boolean value of the done flag
     */

    public boolean getDone() {
        return this.done;
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
            myWriter.write(this.getInput() + "\n");
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
        if (!this.done) {
            return "[ ] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }
}