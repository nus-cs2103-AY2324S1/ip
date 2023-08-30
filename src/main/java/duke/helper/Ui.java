package duke.helper;
import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {
    private Scanner scan;
    private final String LINES;
    public Ui() {
        this.LINES = "______________________________";
        this.scan = new Scanner(System.in);
    }
    public void showLoadingError() {
        System.out.println("Error! Cannot initialise new Storage");
    }

    public String readCommand() {
        // no string processing
        String command = this.scan.nextLine();
        return command;
    }
    public void greet() {
        this.printLines();
        System.out.println("Hello! I'm MeowBot!");
        System.out.println("What can I do for you ?");
        this.printLines();
    }
    public void bye() {
        this.printLines();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLines() {
        System.out.println(this.LINES);
    }

    public void printAddTask(int length, Task task) {
        System.out.println("MEOW got it. I've added this task:\n   " + task);
        System.out.println("Now you have " + length + " meow-tasks in the list.");
        this.printLines();
    }

    public void printMarkTask(int taskNumber, Task wantedTask) {
        System.out.println("Nice! I've meowrked this task as done: ");
        System.out.println("   " + wantedTask);
        System.out.println(this.LINES);
    }

    public void printUnmarkTask(int taskNumber, Task wantedTask) {
        System.out.println("Ok, get your task done soon, I'll be waiting!");
        System.out.println(" " + wantedTask);
        System.out.println(this.LINES);
    }

    public void printDeleteTask(int length, Task wantedTask) {
        System.out.println("Meow... ok, I've removed this task: ");
        System.out.println(" " + wantedTask);
        System.out.println("Now you have " + length + " meow-tasks in the list.");
        System.out.println(this.LINES);
    }

    public void displayTasks(TaskList tasks) {
        System.out.println(this.LINES);
        System.out.println("Meoowww here are your tasks");
        for (int i = 1; i < tasks.size() + 1; i++) {
            System.out.println(i + ". " + tasks.getTask(i - 1));
        }
        System.out.println(this.LINES);
    }

    public void printEmptyFind(String keyword) {
        System.out.println("Meow :( found no tasks with " + keyword);
    }

    public void printFindRes(String res) {
        System.out.println(this.LINES);
        System.out.println("Meow Here are your matching tasks !" + res);
        System.out.println(this.LINES);
    }


}
