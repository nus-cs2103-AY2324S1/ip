import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final String NAME = "Obi-wan Kenobi";
    private static final String DIVIDER = "_____________________________________";
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Generates and prints welcome message when Chat Bot is started.
     */
    public void startBot() {
        System.out.println("Hello There! I am " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Generates and prints closing message when Chat Bot is stopped.
     */
    public void endBot() {
        System.out.println("Bye. May the force be with you!");
    }

    public void respondUser(String response) {
        out.println(response);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void showErrorMessage(DukeException e) {
        out.println(e.getMessage());
    }

    public void showLine() {
        out.println(DIVIDER);
    }

//    public void getUserInput(TaskList tasks) {
//        while (true) {
//            String input = in.nextLine();
//            String[] command = input.split(" ", 2);
//            String action = command[0];
//
//            if (action.equals("bye") && command.length == 1) {
//                break;
//            } else if (command[0].equals("list") && command.length == 1) {
//                out.println("Here are the tasks in your list:");
//                out.println(tasks.toString());
//            } else if (action.equals("mark") || action.equals("unmark") || action.equals("delete")) {
//                try {
//                    tasks.editTask(command);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                try {
//                    tasks.addTask(command);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//
//            System.out.println(DIVIDER);
//        }
//    }

    public void showLoadingError() {
        out.println("Invalid file path given");
    }

    public void showWritingError() {
        out.println("Unable to write to file");
    }

}
