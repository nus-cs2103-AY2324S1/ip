import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Sae class represents an interactive task manager.
 * Users can add, list, mark, and unmark tasks using this program.
 */
public class Sae {

    private final Storage storage;
    private final ArrayList<Task> store;

    public Sae(String filePath) {
        ArrayList<Task> temp;
        this.storage = new Storage(filePath);
        try {
            temp = storage.loadTasks(filePath);
        } catch (IOException e) {
            temp = new ArrayList<Task>();
        }
        this.store = temp;
    }
    public static void main(String[] args) {
        System.out.println("Hello! I'm Sae\nWhat can I do for you?");

        // Create an instance of Sae
        Sae saeInstance = new Sae("./data/sae.txt");

        ArrayList<Task> store = saeInstance.store; // Access store from the instance

        Scanner input = new Scanner(System.in);

        while (true) {
            String str = input.nextLine();

            String[] commandTask = str.split(" ", 2);

            String command = commandTask[0];

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            try {
                executeCommand(store, commandTask);
                saeInstance.storage.saveTasks(store);
            } catch (SaeException e) {
                System.out.println("☹ " + e.getMessage());
            } catch (IOException e) {
                System.out.println("An error occurred while saving tasks: " + e.getMessage());
            }
        }
        input.close();
    }

    /**
     * Executes the command provided by the user and performs the corresponding action.
     *
     * @param store The ArrayList containing the tasks.
     * @param commandTask The user's input split into command and content.
     * @throws SaeException If an error specific to the Sae chatbot occurs.
     */
    private static void executeCommand(ArrayList<Task> store, String[] commandTask) throws SaeException {
        String command = commandTask[0];

        try {
            if (command.equals("delete")) {
                int number = Integer.parseInt(commandTask[1]);
                Task curr = store.get(number - 1);
                curr.deleteTask(store, commandTask);
            } else if (command.equals("list")) {
                listTasks(store);
            } else if (command.equals("mark")) {
                int number = Integer.parseInt(commandTask[1]);
                Task curr = store.get(number - 1);
                curr.markTask(store, commandTask);
            } else if (command.equals("unmark")) {
                int number = Integer.parseInt(commandTask[1]);
                Task curr = store.get(number - 1);
                curr.unmarkTask(store, commandTask);
            } else if (command.equals("todo")) {
                if (commandTask.length < 2 || commandTask[1].isEmpty()) {
                    throw new InvalidTodoException();
                }
                Todo newTask = new Todo(commandTask[1]);
                newTask.addTodoTask(store, commandTask);
                newTask.addtoStore();
            } else if (command.equals("deadline")) {
                if (commandTask.length < 2 || !commandTask[1].contains("/by")) {
                    throw new InvalidDeadlineException();
                }
                String[] parts = commandTask[1].split("/by");
                String description = parts[0].trim();
                String dateTimeString = parts[1].trim();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);

                Deadline newTask = new Deadline(description, dateTime);
                newTask.addDeadlineTask(store, commandTask);
                newTask.addtoStore();
            } else if (command.equals("event")) {
                if (commandTask.length < 2 || !commandTask[1].contains("/from") || !commandTask[1].contains("/to")) {
                    throw new InvalidEventException();
                }
                String[] parts = commandTask[1].split("/from|/to");
                Event newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                newTask.addEventTask(store, commandTask);
                newTask.addtoStore();
            } else {
                throw new SaeException();
            }
        } catch (SaeException errorMessage) {
            System.out.println(errorMessage.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidDateTimeFormatException e) {
            System.out.println("Invalid Date/Time");
            throw new RuntimeException(e);
        }
    }

    /**
     * Lists all tasks in the store along with their statuses.
     *
     * @param store The ArrayList containing the tasks.
     */
    private static void listTasks(ArrayList<Task> store) {
        int len = store.size();
        for (int i = 0; i < len; i++) {
            Task curr = store.get(i);
            System.out.println((i + 1) + "." + curr.toString());
        }
    }

}