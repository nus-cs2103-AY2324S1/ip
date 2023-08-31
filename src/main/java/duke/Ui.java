package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    String logo = "   / \\__\n"
            + "  (    @\\___\n"
            + "  /          O\n"
            + " /   (_____/\n"
            + "/_____/   \n";

    public Ui() {
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("________________________________________");
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello I'm Barkley\n"+logo);
        System.out.println("Howl can I help you?");
        showLine();
    }

    public void showExit() {
        System.out.println("Goodbye! Have a paw-some day :-) \n" + logo);
    }

    public void showError(DukeException e) {
        System.out.println(e.getMessage());
    }

    public void showDateTimeParseError() {
        System.out.println("Error! Date/time should be in 'dd-mm-yyy hh:mm' format");
    }

    public void showAdd(Task task, int taskListLength) {
        System.out.println("Woof luck with your new task: \n" + task.toString());
        System.out.println("You now have " + taskListLength + " tasks in the list");
    }

    public void showDelete(Task task, int taskListLength) {
        System.out.println("Okay! Another dog-gone task down:  \n" + task.toString());
        System.out.println("You now have " + taskListLength + " tasks in the list");
    }

    public void showMark(Task task) {
        System.out.println("Furtastic job completing this task: \n" + task.toString());
    }

    public void showUnmark(Task task) {
        System.out.println("That's ruff! I've unmarked this task:  \n" + task.toString());
    }

    public void showList(ArrayList<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }

}
