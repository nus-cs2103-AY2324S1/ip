package duke;

import java.util.Scanner;
import java.util.Arrays;
public class Ui {
    public static String line = "\t____________________________________________________________\n";
    public static String exitMessage = line + "\tBye. Hope to see you again\n" + line;
    protected TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void startUi() {
        String greeting =
                line +
                        "\tHello! I'm DukeBot\n" +
                        "\tWhat can I do for you?\n" +
                        line;
        System.out.println(greeting);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String echo = myObj.nextLine();  // Read user input

        while (!echo.equalsIgnoreCase("bye")) {
            try {
                String[] words = echo.split(" ");
                if (echo.equalsIgnoreCase("list")) {
                    this.tasks.printListContent();
                } else if (words[0].equalsIgnoreCase("mark")) {
                    char lastCharacter = echo.charAt(echo.length() - 1);
                    this.tasks.markTaskAsDone(lastCharacter);
                } else if (words[0].equalsIgnoreCase("unmark")) {
                    char lastCharacter = echo.charAt(echo.length() - 1);
                    this.tasks.unmarkTask(lastCharacter);
                } else if (words[0].equalsIgnoreCase("delete")) {
                    char lastCharacter = echo.charAt(echo.length() - 1);
                    this.tasks.deleteTask(lastCharacter);
                } else if (words[0].equalsIgnoreCase("todo")) {
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    tasks.toDoHandler(description, false, false);
                } else if (words[0].equalsIgnoreCase("deadline")) {
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    tasks.deadlineHandler(description, false, false);
                } else if (words[0].equalsIgnoreCase("event")) {
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    tasks.eventHandler(description, false, false);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (NotANumberException | EmptyDescriptionException | InvalidCommandException e) {
                //exception caught
            }
            echo = myObj.nextLine();  // Read user input
        }
        System.out.println(exitMessage);
    }
}
