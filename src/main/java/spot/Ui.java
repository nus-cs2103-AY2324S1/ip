package spot;

import java.time.LocalDate;
import java.util.Scanner;

import spot.exception.SpotException;
import spot.task.Task;

public class Ui {

    private final static String HELLO_MESSAGE = "Hello, it's Spot!";
    private final static String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
    private final static String NEW_TASK_MESSAGE = "Spot will add this new task to your list: \n";
    private Scanner inputScanner;

    /**
     * Constructs an instance of the Ui class.
     */
    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    /**
     * Displays hello message to the user.
     */
    public void sayHello() {
        System.out.println(HELLO_MESSAGE);
    }

    /**
     * Displays goodbye message to the user.
     */
    public void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Displays task added message to the user.
     *
     * @param tasks Current TaskList.
     * @param newTask Task to be added.
     */
    public void sayAdd(TaskList tasks, Task newTask) {
        System.out.println(NEW_TASK_MESSAGE + "  " + newTask);
        System.out.println("Tasks in list: " + tasks.getSize());
    }

    /**
     * Reads the next command from the user.
     *
     * @return String representation of the user's next command.
     */
    public String readCommand() {
        return inputScanner.nextLine();
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
     * Displays error message to the user.
     *
     * @param error Error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays message to the user.
     *
     * @param message Regular message.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
