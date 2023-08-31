import exceptions.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DevyBot {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public enum CommandType {
        TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, UNKNOWN
    }

    public static void main(String[] args) {

        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);

        printMessage("Hello! I'm DevyBot\nWhat can I do for you?");

        boolean run = true;

        while (run) {
            String userInput = scanner.nextLine();
            String[] wordsArray = userInput.split("\\s+");
            CommandType commandType = getCommandType(wordsArray[0]);

            try {
                switch (commandType) {
                    case TODO:
                        addTodoTask(userInput);
                        break;
                    case DEADLINE:
                        addDeadlineTask(userInput);
                        break;
                    case EVENT:
                        addEventTask(userInput);
                        break;
                    case MARK:
                        int markIndex = getIndex(wordsArray);
                        markTaskAsDone(markIndex);
                        break;
                    case UNMARK:
                        int unmarkIndex = getIndex(wordsArray);
                        markTaskAsUndone(unmarkIndex);
                        break;
                    case DELETE:
                        int deleteIndex = getIndex(wordsArray);
                        deleteTask(deleteIndex);
                        break;
                    case BYE:
                        printMessage("Bye. Hope to see you again soon!");
                        run = false;
                        break;
                    case LIST:
                        listTasks();
                        break;
                    default:
                        throw new UnknownCommandException();
                }
                saveTasksToFile();
            } catch (DevyBotException e) {
                printMessage(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void loadTasksFromFile() {
        try {
            File file = new File("./data/devybot.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskString = scanner.nextLine();
                String[] taskParts = taskString.split(" \\| ");

                String taskType = taskParts[0].trim();
                String taskStatus = taskParts[1].trim();
                String taskDescription = taskParts[2].trim();
                Task loadedTask;

                try {
                    switch (taskType) {
                        case "T":
                            loadedTask = new TodoTask(taskDescription);
                            if (taskStatus.equals("1")) {
                                loadedTask.markTask();
                            }
                            break;
                        case "D":
                            String taskBy = taskParts[3].trim();
                            if (taskBy.contains(" ")) {
                                // Contains time, parse as LocalDateTime
                                LocalDateTime dateTime = LocalDateTime.parse(taskBy,
                                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                                loadedTask = new DeadlineTask(taskDescription, dateTime);
                            } else {
                                // No time, parse as LocalDate
                                LocalDate date = LocalDate.parse(taskBy, DateTimeFormatter.ofPattern("d/M/yyyy"));
                                loadedTask = new DeadlineTask(taskDescription, date);
                            }
                            if (taskStatus.equals("1")) {
                                loadedTask.markTask();
                            }
                            break;
                        case "E":
                            String taskFrom = taskParts[3].trim();

                            LocalDateTime fromDateTime = LocalDateTime.parse(taskFrom,
                                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                            String taskTo = taskParts[4].trim();

                            LocalDateTime toDateTime = LocalDateTime.parse(taskTo,
                                    DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                            loadedTask = new EventTask(taskDescription, fromDateTime, toDateTime);
                            if (taskStatus.equals("1")) {
                                loadedTask.markTask();
                            }
                            break;
                        default:
                            throw new UnknownCommandException();

                    }
                    taskList.add(loadedTask);
                } catch (DevyBotException e) {
                    printMessage(e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            printMessage("No existing tasks file found. Starting with an empty task list.");
        }
    }

    private static void saveTasksToFile() {
        try {
            File dataDir = new File("./data");
            if (!dataDir.exists()) {
                dataDir.mkdir(); // Create the directory if it doesn't exist
            }

            FileWriter fileWriter = new FileWriter("./data/devybot.txt");
            for (Task task : taskList) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    public static void listTasks() {
        if (taskList.size() == 0) {
            printMessage("Currently no tasks available.");
            return;
        }
        String outpString = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            outpString += (i + 1) + ". " + taskList.get(i).toString() + "\n";
        }
        printMessage(outpString);
    }

    public static CommandType getCommandType(String command) {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandType.UNKNOWN;
        }
    }

    public static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public static void addTodoTask(String userInput) throws EmptyDescriptionException {
        String description = userInput.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("todo");
        }

        Task newTask = new TodoTask(description);
        taskList.add(newTask);

        String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                + " tasks in the list.";
        printMessage(outpString);
    }

    public static void addDeadlineTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /by ");

        String description = parts[0].substring(8).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("deadline");
        }

        String by = parts[1].trim();

        Task newTask;

        try {
            if (by.contains(" ")) {
                // Contains time, parse as LocalDateTime
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                newTask = new DeadlineTask(description, dateTime);
            } else {
                // No time, parse as LocalDate
                LocalDate date = LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
                newTask = new DeadlineTask(description, date);
            }

            taskList.add(newTask);

            String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            printMessage(outpString);
        } catch (DateTimeParseException e) {
            // Handle parsing error
            printMessage("Invalid date/time format. Please use 'd/M/yyyy' or 'd/M/yyyy HHmm'.");
        }
    }

    public static void addEventTask(String userInput) throws EmptyDescriptionException {
        String[] parts = userInput.split(" /from | /to ");

        String description = parts[0].substring(5).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("event");
        }

        String from = parts[1].trim();
        String to = parts[2].trim();

        try {
            // Contains time, parse as LocalDateTime
            LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
            Task newTask = new EventTask(description, fromDateTime, toDateTime);

            taskList.add(newTask);

            String outpString = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + taskList.size()
                    + " tasks in the list.";
            printMessage(outpString);
        } catch (DateTimeParseException e) {
            printMessage("Invald date/tim frmat. Please use 'd/M/yyyy HHmm'.");
        }
    }

    public static void markTaskAsDone(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        currentTask.markTask();

        String outpString = "Nice! I've marked this task as done:\n  " + currentTask;
        printMessage(outpString);
    }

    public static void markTaskAsUndone(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        currentTask.unmarkTask();

        String outpString = "OK, I've marked this task as not done yet:\n  " + currentTask;
        printMessage(outpString);
    }

    public static void deleteTask(int index) throws TaskIndexOutOfBoundsException {

        if (index >= taskList.size() || index < 0) {
            throw new TaskIndexOutOfBoundsException(index);
        }

        Task currentTask = taskList.get(index);
        taskList.remove(index);

        String outpString = "Noted. I've removed this task:\n  " + currentTask;
        printMessage(outpString);
    }

    public static int getIndex(String[] wordsArray) throws EmptyDescriptionException, NonIntegerInputException {
        try {
            if (wordsArray.length <= 1) {
                throw new EmptyDescriptionException(wordsArray[0].toString());
            }
            int index = Integer.parseInt(wordsArray[1]) - 1;
            return index;
        } catch (NumberFormatException e) {
            throw new NonIntegerInputException();
        }
    }
}
