package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Parser class is responsible for parsing user input and file input
 * to create and manipulate tasks in the Duke application.
 */
public class Parser {
    /**
     * Parses user input and performs corresponding actions on the task list.
     *
     * @param userInput The user's input command.
     * @param taskList  The list of tasks to be manipulated.
     */
    public static void parseUserInput(String userInput, TaskList taskList) {
        if (userInput.equals("list")) {
            // If user wants to check list
            Ui.showList(taskList);

        } else if (userInput.startsWith("mark")) {
            // If user wants to mark something
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    taskList.markTask(taskList.getTask(index));
                } catch (NumberFormatException e) {
                    Ui.showError("Invalid task number");
                }
            } else {
                // Invalid command format
                Ui.showError("Invalid command format");
            }

        } else if (userInput.startsWith("unmark")) {
            // If user wants to unmark something
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    taskList.unmarkTask(taskList.getTask(index));
                } catch (NumberFormatException e) {
                    Ui.showError("Invalid task number");
                }
            } else {
                // Invalid command format
                Ui.showError("Invalid command format");
            }

        } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
            // User creates a task
            Task newTask = null;

            if (userInput.startsWith("todo")) {
                // If user wants to create a ToDos task
                if (userInput.length() < 5) {
                    // Incorrect input format for todos
                    Ui.showError("Incorrect input format for todo");
                } else {
                    String description = userInput.substring(5).trim();
                    if (description.isBlank()) {
                        // If there's do description
                        Ui.showError("The description of a todo cannot be empty");
                    } else {
                        newTask = new ToDos(description);
                        taskList.addTask(newTask);
                    }
                }

            } else if (userInput.startsWith("deadline")) {
                // If user wants to create a Deadlines task
                String[] parts = userInput.split("/by");

                if (parts.length == 2) {
                    // Makes sure deadline format is followed (e.g. there's /by)
                    String description = parts[0].substring(9).trim();
                    String by = parts[1].trim();

                    // Parse the date and time
                    LocalDate deadlineDate = LocalDate.parse(by);

                    if (description.isBlank() || by.isBlank()) {
                        // If description or by is empty
                        Ui.showError("The description or by of a deadline cannot be empty");
                    } else {
                        newTask = new Deadlines(description, deadlineDate);
                        taskList.addTask(newTask);
                    }
                } else {
                    // Incorrect input format for deadline
                    Ui.showError("Incorrect input format for deadline");
                }

            } else {
                // If user wants to create an Events task
                String[] parts = userInput.split("/from|/to");

                if (parts.length == 3) {
                    // Makes sure deadline format is followed (e.g. there's /from and /to)
                    String description = parts[0].substring(6).trim();
                    String from = parts[1].trim();
                    String to = parts[2].trim();

                    if (description.isBlank() || from.isBlank() || to.isBlank()) {
                        // If description, from, or to is empty
                        Ui.showError("The description or from or to of an event cannot be empty");
                    } else {
                        newTask = new Events(description, from, to);
                        taskList.addTask(newTask);
                    }
                } else {
                    // Incorrect input format for events
                    Ui.showError("Incorrect input format for event");
                }
            }

            if (newTask != null) {
                // If task is initialized
                Ui.showAddedTask(newTask, taskList);
            }

        } else if (userInput.startsWith("delete")) {
            // If user wants to delete a task
            String[] parts = userInput.split(" ");
            if (parts.length == 2) {
                try {
                    int index = Integer.parseInt(parts[1]) - 1;
                    taskList.deleteTask(taskList.getTask(index));
                } catch (NumberFormatException e) {
                    Ui.showError("Invalid task number");
                }
            } else {
                // Invalid command format
                Ui.showError("Invalid command format");
            }
        } else {
            // Other inputs
            Ui.showError("I'm sorry, but I don't know what that means");
        }
    }

    /**
     * Parses a string representation of a task and creates a Task object.
     * This method takes a string representation of a task in a specific format
     * and parses it to create a corresponding Task object. The input string is
     * split using the "|" delimiter and the task type, status, description, and
     * other relevant information are extracted to create the appropriate task.
     * The created task object is then returned.
     *
     * @param line The string representation of a task to be parsed.
     * @return A Task object created from the parsed string representation.
     */
    public static Task parseFileInput(String line) {
        String[] parts = line.split("\\|");
        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        switch (type) {
        case "T":
            task = new ToDos(description);
            break;
        case "D":
            String by = parts[3].trim();

            // Parse the date and time
            LocalDate deadlineDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy"));

            task = new Deadlines(description, deadlineDate);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();

            task = new Events(description, from, to);
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }
}