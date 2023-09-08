package duke;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Creates a new instance of the Parser class.
 *
 * The Parser class is responsible for parsing user commands and performing
 * tasks accordingly.
 */
public class Parser {
    /**
     * Constructs a new Parser instance.
     *
     * The Parser class is responsible for parsing user commands and performing
     * tasks based on those commands.
     */
    public Parser() {
    }
    /**
     * Checks if the given input string represents an exit command.
     *
     * @param input The input string to check.
     * @return True if the input string is "bye" (case-insensitive), indicating an exit command; otherwise, false.
     */
    public static boolean isExitCommand(String input) {
        return input.equalsIgnoreCase("bye");
    }

    /**
     * Parses and handles the user's command to perform various tasks.
     *
     * @param command The user's command to be processed.
     * @param filePath The user's filepath to be written to.
     * @param taskList The user's taskList to ammend.
     * @throws DukeException If the command is not recognised, or error.
     */
    public static void parseCommand(String command, String filePath, TaskList taskList) throws DukeException {
        String[] separateCommand = command.split(" ");
        System.out.println("--------------------------");
        if (command.equals("list")) {
            System.out.println("Task List:");
            taskList.printTaskList();
        } else if (command.startsWith("mark") || command.startsWith("unmark")) {
            try {
                if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > taskList.getSize()) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
                int taskNumber = Integer.parseInt(separateCommand[1]);
                if (command.startsWith("mark")) {
                    taskList.getTaskItem(taskNumber - 1).markAsDone();
                    System.out.println(" Marked as done: ");
                } else if (command.startsWith("unmark")) {
                    taskList.getTaskItem(taskNumber - 1).markAsUndone();
                    System.out.println(" Marked as not done yet: ");
                }
                writeLine(filePath, taskList);
                System.out.println("   " + taskList.getTaskItem(taskNumber - 1).toString());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                throw new DukeException("☹ OOPS!!! Invalid number");
            }
        } else if (command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event")
                || command.startsWith("delete")) {
            if (command.startsWith("todo")) {
                try {
                    String description = command.substring(5);
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task currTask = new ToDo(description);
                    taskList.addTask(currTask);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (command.startsWith("deadline")) {
                try {
                    String[] parts = command.split("/by"); //   2/12/2019 1800
                    String description = parts[0].substring(9).trim();
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    if (parts.length == 1) {
                        throw new DukeException("☹ OOPS!!! There must be a date and time.");
                    }
                    String byID = parts[1].trim();
                    Task currTask = new Deadline(description, byID);
                    if (currTask.toString() != null) {
                        taskList.addTask(currTask);
                    } else {
                        throw new DukeException("☹ OOPS!!! Invalid Date/Time format, use DD/MM/YYYY HHmm");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (command.startsWith("event")) {
                try {
                    String[] parts = command.split("/from");
                    String description = parts[0].substring(6).trim();
                    if (description.length() == 0) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] timeParts = parts[1].split("/to");
                    String start = timeParts[0].trim();
                    String end = timeParts[1].trim();
                    Task currTask = new Event(description, start, end);
                    taskList.addTask(currTask);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
            } else if (command.startsWith("delete")) {
                try {
                    if (separateCommand.length > 2 || Integer.parseInt(separateCommand[1]) > taskList.getSize()) {
                        throw new DukeException("☹ OOPS!!! Invalid number");
                    }
                    int taskNumber = Integer.parseInt(separateCommand[1]);
                    if (command.startsWith("delete")) {
                        System.out.println(" Noted. I've removed this duke.task:");
                        System.out.println("   " + taskList.getTaskItem(taskNumber - 1).toString());
                        taskList.deleteTask(taskNumber - 1);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new DukeException("☹ OOPS!!! Invalid number");
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (command.startsWith("delete")) {
                System.out.println(" Now you have " + taskList.getSize() + " tasks in the list.");
            } else {
                System.out.println(" I've added this duke.task:" + "\n" + "   " + taskList.getTaskItem(taskList.getSize() - 1).toString()
                        + "\n" + " Now you have " + taskList.getSize() + " tasks in the list.");
            }
            writeLine(filePath, taskList);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Writes the contents of a TaskList to a specified file path.
     *
     * @param filePath  The file path to write the TaskList contents to.
     * @param taskList  The TaskList containing tasks to be written to the file.
     * @throws DukeException If an error occurs while writing to the file, or if the description of a task is empty.
     */
    public static void writeLine(String filePath, TaskList taskList) throws DukeException {
        File resourceFile = new File(filePath);
        try {
            FileWriter writer = new FileWriter(resourceFile);
            for (Task task : taskList.getTasks()) {
                writer.write(task.toTxtString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

}
