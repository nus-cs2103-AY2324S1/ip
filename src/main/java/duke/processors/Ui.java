package duke.processors;

import duke.exception.DukeEmptyCommandException;
import java.util.Scanner;
public class Ui {
    private String separation = "_______________________________________________";
    private Scanner sc = new Scanner(System.in);
    public Ui() {
    }
    public void OnEnter() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(separation);
        System.out.println("Hello! I am YOU");
        System.out.println("What can I do for you?");
        System.out.println(separation);
    }

    public void OnExit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separation);
    }

    public String readCommand() throws DukeEmptyCommandException {

        String command = sc.nextLine();
        if (command.isEmpty()) {
            throw new DukeEmptyCommandException();
        }
        return command;
    }

    public void printNumOfTasks (TaskList tasks) {
        System.out.println("Now you have "
                + tasks.getCount()
                + (tasks.getCount() <= 1 ? " task" : " tasks")
                + " in the list.");
    }
    public void showLine() {

        System.out.println(separation);
    }

    public void showError(String err) {

        System.out.println(err);
    }
}
