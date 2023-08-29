import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    /**
     * The main method of the Duke application.
     * Handles user interactions and manages task-related commands.
     *
     * @param args Command-line arguments (not used).
     * @throws DukeException If an error occurs during user input processing.
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    static ArrayList<Task> taskArray = new ArrayList<>();
    static TaskList taskList =  new TaskList(taskArray);
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        } catch (IOException e){
            System.out.println("Something went wrong while loading saved task file.");
        }
    }

    public void run() {
        // Send welcome message
        Ui.printWelcomeMessage();

        try {
            taskList = new TaskList(Storage.load());
            if (taskList.listTasks() == "") {
                System.out.println("There are no tasks in your list at the moment. Add some!");
            } else {
                System.out.println("Here are the tasks in your list:");
                System.out.println(taskList.listTasks());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (FileUnloadableException e) {
            System.out.println("File cannot be loaded.");
        } catch (IOException e) {
            System.out.println("e.getMessage");
        }

        // Implement function to read user input via keyboard
        Scanner scan = new Scanner(System.in);

        while (true) {
            try {
                String userInput = scan.nextLine().trim();
                if (Objects.equals(userInput, "bye")) {
                    taskList.updateTaskFile();
                    Ui.printExitMessage();
                    break;

                } else if (Objects.equals(userInput, "list")) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(TaskList.listTasks());

                } else if (userInput.startsWith("mark")) {
                    taskList.markTask(userInput);
                    taskList.updateTaskFile();

                } else if (userInput.startsWith("unmark")) {
                    taskList.unmarkTask(userInput);
                    taskList.updateTaskFile();

                } else if (userInput.startsWith("todo")) {
                    if (userInput.equals("todo")) {
                        throw new EmptyTaskException("todo");
                    }
                    String taskName = userInput.substring("todo".length()).trim();
                    taskList.makeToDo(taskName);
                    taskList.updateTaskFile();

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.equals("deadline")) {
                        throw new EmptyTaskException("deadline");
                    } else if (userInput.endsWith("/by")) {
                        throw new EmptyDateException("deadline");
                    }
                    String[] deadlineParts = taskList.getDeadlineParts(userInput);
                    String taskName = deadlineParts[0];
                    LocalDateTime by = Storage.saveAsDate(deadlineParts[1]);
                    taskList.makeDeadline(taskName, by);
                    taskList.updateTaskFile();

                } else if (userInput.startsWith("event")) {
                    if (userInput.equals("event")) {
                        throw new EmptyTaskException("event");
                    }
                    String[] eventParts = taskList.getEventParts(userInput);
                    String taskName = eventParts[0];
                    LocalDateTime start = Storage.saveAsDate(eventParts[1]);
                    LocalDateTime end = Storage.saveAsDate(eventParts[2]);
                    taskList.makeEvent(taskName, start, end);
                    taskList.updateTaskFile();

                } else if (userInput.startsWith("delete")) {
                    taskList.deleteTask(userInput);
                    taskList.updateTaskFile();
                }
                else {
                    throw new InvalidInputException("Invalid Input");
                }
            } catch (InvalidInputException | EmptyTaskException | EmptyDateException | OutOfRangeException | IOException | DateTimeParseException e) {
                System.out.println(e);
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
