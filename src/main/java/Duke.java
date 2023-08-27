import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates the logic of a Chat bot
 *
 * @author Rayson
 */
public class Duke {

    /**
     * Represents the different commands accepted by the chatbot
     */
    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    // CONSTANTS
    private static final String LINE = "_______________________________________";
    private static final String DIR_NAME = "./data";
    private static final String FILE_NAME = "duke.txt";

    private static void sendIntroduction() {
        String logo = "                     _                 _      \n" +
                " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n" +
                "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n" +
                "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n" +
                "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n" +
                "              |_|                             ";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }
    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();

        // check for the storage file
        try {
            File file = new File(DIR_NAME + "/" + FILE_NAME);
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (Exception e) {
            try {
                if (new File(DIR_NAME).mkdir()) {
                    System.out.println("Sorry, directory does not exist. Creating now...");
                }
                if (new File(DIR_NAME + "/" + FILE_NAME).createNewFile()) {
                    System.out.println("Sorry, file does not exist. Creating now...");
                }
            } catch (Exception error) {
                System.out.println("Error... Unable to create files");
            }
        }

        Scanner scanner = new Scanner(System.in);

        sendIntroduction();

        try {
            while (true) {
                String input = scanner.nextLine();
                System.out.println(LINE);

                // Use Regex to extract the first word even with preceding whitespace
                String command = input.replaceAll("^\\W*\\b(\\w+).*", "$1").toUpperCase();

                try { // In case there are exceptions
                    // User wants to end the chatbot
                    if (command.equals(Command.BYE.name())) {
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(LINE);
                        break;
                    }

                    // List out all the tasks in the chatbot
                    if (command.equals(Command.LIST.name())) {

                        if (tasks.size() == 0) {
                            throw new EmptyTaskListException();
                        }

                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            System.out.println(String.format("%d. %s", i + 1, task));
                        }
                        System.out.println(LINE);
                        continue;
                    }

                    // Mark a task as done
                    if (command.equals(Command.MARK.name())) {
                        String desc = input.replaceAll("[^0-9]", "");
                        if (desc.equals("")) {
                            throw new NoIndexException("No Index");
                        }
                        int index = Integer.parseInt(desc);
                        if (tasks.size() < index || index < 1) {
                            throw new NoIndexException(Integer.toString(index));
                        }

                        Task task = tasks.get(index - 1);
                        task.markDone();
                        System.out.println("Great job completing the task! I've marked it as done.");
                        System.out.println(task);
                        System.out.println(LINE);
                        continue;
                    }

                    // Unmark a done task
                    if (command.equals(Command.UNMARK.name())) {
                        String desc = input.replaceAll("[^0-9]", "");
                        if (desc.equals("")) {
                            throw new NoIndexException("No Index");
                        }
                        int index = Integer.parseInt(desc);
                        if (tasks.size() < index || index < 1) {
                            throw new NoIndexException(Integer.toString(index));
                        }

                        Task task = tasks.get(index - 1);
                        task.markUndone();
                        System.out.println("Oops... Did you mark it incorrectly?");
                        System.out.println(task);
                        System.out.println(LINE);
                        continue;
                    }

                    // Add a todo to the chatbot
                    if (command.equals(Command.TODO.name())) {
                        String desc = input.replaceAll("^\\s*todo\\s*", "");
                        if (desc.equals("")) {
                            throw new NoDescriptionException("todo");
                        }

                        Task task = new ToDo(desc);
                        tasks.add(task);
                        System.out.println("Got it!. I've added this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
                        continue;
                    }


                    // Add a deadline
                    if (command.equals(Command.DEADLINE.name())) {
                        String desc_time = input.replaceAll("^\\s*deadline\\s*", "");

                        String[] strings = desc_time.split(" /by ");

                        if (desc_time.equals("")) {
                            throw new NoDescriptionException("deadline");
                        }
                        if (strings.length == 1) {
                            throw new UnknownTimeException(strings[0]);
                        }

                        Task task = new Deadline(strings[0], strings[1]);
                        tasks.add(task);
                        System.out.println("Got it!. I've added this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
                        continue;
                    }

                    // Add an Event
                    if (command.equals(Command.EVENT.name())) {
                        String content = input.replaceAll("^\\s*event\\s*", "");
                        if (content.equals("")) {
                            throw new NoDescriptionException("event");
                        }

                        String[] desc_time = content.split(" /from ");
                        String[] times = desc_time[1].split(" /to ");

                        if (times.length == 1) {
                            throw new UnknownTimeException(desc_time[0]);
                        }

                        Task task = new Event(desc_time[0], times[0], times[1]);
                        tasks.add(task);
                        System.out.println("Got it!. I've added this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
                        continue;
                    }

                    // Delete a task from the chatbot
                    if (command.equals(Command.DELETE.name())) {
                        String desc = input.replaceAll("[^0-9]", "");
                        if (desc.equals("")) {
                            throw new NoIndexException("No Index");
                        }
                        int index = Integer.parseInt(desc);

                        if (tasks.size() < index || index < 1) {
                            throw new NoIndexException(Integer.toString(index));
                        }
                        Task task = tasks.get(index - 1);
                        tasks.remove(index - 1);
                        System.out.println("Noted... I've removed this task:");
                        System.out.println(" " + task);
                        System.out.printf("Now you have %d tasks in the list%n", tasks.size());
                        System.out.println(LINE);
                        continue;
                    }

                    throw new UnknownCommandException(input);

                } catch (UnknownTimeException | UnknownCommandException | EmptyTaskListException |
                         NoDescriptionException | NoIndexException e) {
                    System.out.println(e.getMessage());
                    System.out.println(LINE);
                }


            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Respironix has encountered an issue; exiting");
        }
    }
}
