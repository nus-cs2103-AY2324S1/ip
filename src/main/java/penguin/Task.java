

package penguin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Task contains information about a task that was input by the user for Penguin bot to remember.
 *
 */
public class Task {
    protected boolean done;
    protected String name;

    public Task(String name) {
        this.done = false;
        this.name = name;
    }
    /**
     * Returns information about the task for output to the user.
     *
     * @return Information about the task in user-output format.
     */
    public String getDisplay() {
        String output = "";
        if (this.done) {
            output += "[X]";
        } else {
            output += "[ ]";
        }
        output += this.name;
        return output;
    }
    /**
     * Returns information about the task for internal storage purposes.
     *
     * @return Information about the task in internal storage format.
     */
    public String getSaveDisplay() {
        String output = "";
        if (this.done) {
            output += "1 | ";
        } else {
            output += "0 | ";
        }
        output += this.name;
        return output;
    }
}
