package spot;

import java.time.LocalDate;

import spot.task.Task;

/**
 * Represents the user interface of the chatbot.
 */
public class Ui {

    private static final String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
    private static final String NEW_TASK_MESSAGE = "Spot will add this new task to your list: \n";
    private StringBuilder output;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.output = new StringBuilder();
    }

    /**
     * Resets the ouptut of the Ui object.
     */
    public void resetOutput() {
        output.setLength(0);
    }

    /**
     * Returns String representation of the ouptut of the Ui object.
     */
    public String getOutput() {
        return output.toString();
    }

    /**
     * Sets output to goodbye message.
     */
    public void setGoodbye() {
        output.append(GOODBYE_MESSAGE);
    }

    /**
     * Sets output to task added message.
     *
     * @param tasks Current TaskList.
     * @param newTask Task to be added.
     */
    public void setAdd(TaskList tasks, Task newTask) {
        output.append(NEW_TASK_MESSAGE + "  " + newTask + "\n");
        output.append("Tasks in list: " + tasks.getSize());
    }

    /**
     * Displays a list of the user's current tasks.
     *
     * @param ui Ui object of the current chatbot instance.
     * @param tasks Current TaskList.
     */
    public void listTasks(Ui ui, TaskList tasks) {
        tasks.listTasks(ui);
    }

    /**
     * Displays a list of the user's current tasks falling on a specified date.
     *
     * @param ui Ui object of the current chatbot instance.
     * @param tasks Current TaskList.
     * @param date Specified date.
     */
    public void listTasks(Ui ui, TaskList tasks, LocalDate date) {
        tasks.listTasks(ui, date);
    }

    /**
     * Sets output to error message.
     *
     * @param error Error message.
     */
    public void setError(String error) {
        output.append(error);
    }

    /**
     * Sets output to message.
     *
     * @param message Regular message.
     */
    public void setMessage(String message) {
        output.append(message + "\n");
    }

}
