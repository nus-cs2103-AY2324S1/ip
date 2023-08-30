import com.sun.jdi.ArrayReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class Duke {
    private static String name = "WallE";
    private static List<Task> tasks = new ArrayList<>();
    private static class DukeException extends RuntimeException {
        public DukeException() {
            super();
        }
        public DukeException(String message) {
            super(message);
        }
    }

    public static void printGreeting() {
        System.out.println("\tHello! I'm " + name + "!");
        System.out.println("\tWhat can I do for you?");
    }
    public static void printDivider(boolean isIndented) {
        if (isIndented)
            System.out.println('\t' + "_________________________________________");
        else
            System.out.println("_________________________________________");
    }
    public static void printTaskAddedMessage(Task task) {
        System.out.println("\t\t Got it. I've added this task:");
        System.out.println(String.format("\t\t\t %s", task.toString()));
        System.out.println(String.format("\t\tNow you have %d tasks in the list.", tasks.size()));
    }
    public static void printTaskRemovedMessage(Task task) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task.toString());
    }
    public static void printEmptyDescriptionErrorMessage() {
        System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
    }
    public static void printTaskMarkedMessage(Task task) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + task.toString());
    }
    public static void printTaskUnmarkedMessage(Task task) {
        System.out.println("\tOk, I've marked this task as not done yet:");
        System.out.println("\t\t" + task.toString());
    }
    public static String extractSecondWordOnwards(String str) {
        String[] wordArray = str.split(" ");
        String secondWordOnwards = wordArray.length >= 2 ? wordArray[1] : "";
        for (int i = 2; i < wordArray.length; i++)  {
            secondWordOnwards += " " + wordArray[i];
        }
        return secondWordOnwards;
    }

    public static void saveTasks() {
        String directoryPath = "./data";
        String filePath = "./data/duke.txt";
        BufferedWriter writer = null;

        try {

            writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toString(true));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Make sure to close the writer in the finally block
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void loadTasks() {
        String directoryPath = "./data";
        String filePath = "./data/duke.txt";
        BufferedReader reader = null;

        try {
            // Create the directory if it doesn't exist
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Create the file if it doesn't exist
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming each line contains task information, parse and create tasks accordingly
                Task task = parseAndAddTaskFromString(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Make sure to close the reader in the finally block
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Task parseAndAddTaskFromString(String line) {
        String[] taskData = line.split("\\s*\\|\\s*");
        boolean isDone = taskData[1].equals("1");
        switch (taskData[0]) {
            case "T" :
                ToDo todo = new ToDo(taskData[2], isDone);
                tasks.add(todo);
                return todo;
            case "D":
                return processAndAddDeadline(taskData[2], taskData[3]);
            case "E":
        }       Event event = new Event(taskData[2], isDone, taskData[3], taskData[4]);
                tasks.add(event);
                return event;
    }

    public static Deadline processAndAddDeadline(String name, String deadlineString) {
        DateTimeFormatter formatter = null;
        Deadline deadline = null;
        if (isValidDate(deadlineString)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDeadlineDate = LocalDate.parse(deadlineString, formatter);
            deadline = new Deadline(name, false, parsedDeadlineDate);
            tasks.add(deadline);
            printTaskAddedMessage(deadline);
        } else if (isValidDateTime(deadlineString)) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime parsedDeadlineDateTime = LocalDateTime.parse(deadlineString, formatter);
            deadline = new Deadline(name, false, parsedDeadlineDateTime);
            tasks.add(deadline);
            printTaskAddedMessage(deadline);
        } else {
            System.out.println("\t Expected usage for deadline (time is optional):\n deadline {deadlineName} /by yyyy-MM-dd HHmm");
        }
        return deadline;
    }

    private static boolean isValidDate(String input) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private static boolean isValidDateTime(String input) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{4}$");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private static LocalDate parseDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(input, formatter);
    }

    private static LocalDateTime parseDateTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(input, formatter);
    }
    public static void main(String[] args) {
        loadTasks();
        Scanner scanner = new Scanner(System.in);
        printDivider(true);
        printGreeting();
        printDivider(true);
        String input;
        do {
            input = scanner.nextLine();
            if (!input.equals("bye")) {
                printDivider(true);
                if (!input.equals("list")) {
                    String[] inputWords = input.split(" ");
                    int id;
                    switch (inputWords[0]) {
                        case "todo":
                            String todoName = extractSecondWordOnwards(input);
                            if (todoName.length() == 0) {
                                printEmptyDescriptionErrorMessage();
                                break;
                            }
                            Task todo = new ToDo(todoName, false);
                            tasks.add(todo);
                            printTaskAddedMessage(todo);
                            break;
                        case "deadline":
                            String[] twoParts = input.split ("/by ");
                            String deadlineName = extractSecondWordOnwards(twoParts[0]);
                            if (deadlineName.length() == 0) {
                                printEmptyDescriptionErrorMessage();
                            }
                            DateTimeFormatter formatter = null;
                            String deadlineString = twoParts[1];
                            processAndAddDeadline(deadlineName, deadlineString);
                            break;
                        case "event":
                            String[] threeParts = input.split ("/");
                            String eventName = extractSecondWordOnwards(threeParts[0]);
                            if (eventName.length() == 0) {
                                printEmptyDescriptionErrorMessage();
                            }
                            try {
                                String eventStart = extractSecondWordOnwards(threeParts[1]);
                                String eventEnd = extractSecondWordOnwards(threeParts[2]);
                                Task event = new Event(eventName, false, eventStart, eventEnd);
                                tasks.add(event);
                                printTaskAddedMessage(event);
                                break;
                            } catch (RuntimeException e) {
                                throw new DukeException("Index likely out of bounds due to incorrect format of input. Expected usage: event {eventName} /from {eventStart} /to {eventEnd}");
                            } finally {
                                break;
                            }
                        case "mark":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                                tasks.get(id).markAsDone();
                                printTaskMarkedMessage(tasks.get(id));
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("Index out of bounds. Expected: mark {id}");
                            } finally {
                                break;
                            }
                        case "unmark":
                            try {
                                id = Integer.valueOf(inputWords[1]) - 1;
                                tasks.get(id).markAsUndone();
                                printTaskUnmarkedMessage(tasks.get(id));
                            } catch (ArrayIndexOutOfBoundsException e) {
                                throw new DukeException("Index out of bounds. Expected: unmark {id}");
                            } finally {
                                break;
                            }
                        case "delete":
                            id = Integer.valueOf(inputWords[1]) - 1;
                            Task removedTask = tasks.remove(id);
                            printTaskRemovedMessage(removedTask);
                            break;
                        default:
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    saveTasks();
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.println(String.format("\t%d.%s", i + 1, task.toString()));
                    }
                }
                printDivider(true);
            } else {
                break;
            }
        } while (true);

        printDivider(true);
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider(true);
    }
}
