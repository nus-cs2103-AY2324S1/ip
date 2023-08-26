package spot;

import java.time.LocalDate;
import java.util.Scanner;

import spot.task.Task;

public class Ui {

    private final static String HELLO_MESSAGE = "Hello, it's Spot!";
    private final static String GOODBYE_MESSAGE = "Spot's going to take a nap now. Goodnight!";
    private final static String NEW_TASK_MESSAGE = "Spot will add this new task to your list: \n";
    private Scanner inputScanner;

    public Ui() {
        this.inputScanner = new Scanner(System.in);
    }

    public void sayHello() {
        System.out.println(HELLO_MESSAGE);
    }

    public void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void sayAdd(TaskList tasks, Task newTask) {
        System.out.println(NEW_TASK_MESSAGE + "  " + newTask);
        System.out.println("Tasks in list: " + tasks.getSize());
    }

    public String readCommand() {
        return inputScanner.nextLine();
    }

    public void listTasks(Ui ui, TaskList tasks) {
        tasks.listTasks(ui);
    }

    public void listTasks(Ui ui, TaskList tasks, LocalDate date) {
        tasks.listTasks(ui, date);
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
