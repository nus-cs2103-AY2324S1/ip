package duke;

import duke.exceptions.WrongMarkException;
import duke.tasks.Task;

import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class Ui {
    private final String LOGO = "" +
            "  /\\______/\\ \n" +
            " /  O    O  \\ \n" +
            "|    -_-     | \n" +
            "|   \\___/    | \n" +
            " \\__________/ \n";

    public Ui() {
    }

    public void showWelcome() {
        separatorLines();
        System.out.println(LOGO);
        System.out.println("Call me sillyBOT\n" +
                "Tell me what silly things you are gonna do, if you are haha?");
        separatorLines();
    }

    public void showExit() {
        separatorLines();
        System.out.println("See ya! I know you are gonna procrastinate again.");
        separatorLines();
    }

    private void separatorLines() {
        System.out.println("_".repeat(50));
    }

    public String getUserInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What do you want to do, eh?");
        return sc.nextLine();
    }

    public void showAlreadyDone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("So silly ah! How mark already done work? You trying to be smart?");
    }

    public void showInvalidIndex() {
        separatorLines();
        System.out.println("You silly ah! You need to tell which one!");
        separatorLines();
    }

    public void showAlreadyUndone() throws WrongMarkException {
        separatorLines();
        throw new WrongMarkException("Yea I know you didn't do it! Why tell me again? Start working now!");
    }

    public void deleteTaskMessage() {
        separatorLines();
        System.out.println("Hard to believe you actually did that!\n" +
                "Now you have " + Task.taskCount + " tasks in the list.");
        separatorLines();
    }

    public void addTaskMessage(Task task) {
        separatorLines();
        System.out.println("For some reason I believe you are gonna do that!\n" + task + "\n" +
                "Now you have " + Task.taskCount + " tasks in the list.");
        separatorLines();
    }
}
