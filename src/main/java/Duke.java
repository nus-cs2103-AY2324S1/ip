import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static ArrayList<Task> currList = new ArrayList<>();
    private static final String FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) {
        greetUser();

        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String currInput = sc.nextLine().trim();
                processInput(currInput);
                saveTasksToFile();
            } catch (DukeException e) {
                System.out.println(">  " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(">  Please provide a valid task number.");
            }
            System.out.println("------------------------------------------------");
        }
    }

    private static void greetUser() {
        String name = "misty";
        String logo = "|\\---/|\n" +
                "| o_o |\n" +
                " \\___/";
        System.out.println(">  Hello from " + name + "\n" + logo);
        System.out.println(">  What can misty help you with?");
        System.out.println("------------------------------------------------");
        loadTasksFromFile();

    }

    private static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    Task task = Task.fromFileString(line);
                    if (task != null) {
                        currList.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println(">  An error occurred while loading the tasks from the file: " + e.getMessage());
        }
    }
    private static void processInput(String currInput) throws DukeException {
        if (currInput.equals("bye")) {
            System.out.println(">  ok thanks bye");
            System.exit(0);
        } else if (currInput.equals("list")) {
            listTasks();
        } else if (currInput.startsWith("todo")) {
            addToDo(currInput);
        } else if (currInput.startsWith("deadline")) {
            addDeadline(currInput);
        } else if (currInput.startsWith("event")) {
            addEvent(currInput);
        } else if (currInput.startsWith("mark")) {
            markTask(currInput);
        } else if (currInput.startsWith("unmark")) {
            unmarkTask(currInput);
        } else if (currInput.startsWith("delete")) {
            deleteTask(currInput);
        } else {
            if (currInput.isEmpty()) {
                throw new DukeException("The description of a task cannot be empty.");
            } else {
                throw new DukeException("Please specify the keyword of the what you would like do.");
            }
        }
    }

    private static void listTasks() {
        if (currList.isEmpty()) {
            System.out.println(">  You have no tasks :)");
        } else {
            System.out.println(">  Your tasks: ");
            for (int i = 0; i < currList.size(); i++) {
                System.out.println((i + 1) + ") " + currList.get(i));
            }
        }
    }

    private static void addToDo(String currInput) throws DukeException {
        if (currInput.length() <= 5 || currInput.substring(5).trim().isEmpty()) {
            throw new DukeException("The description of a To Do task cannot be empty.");
        }
        String description = currInput.substring(5).trim();
        currList.add(new Todo(description));
        System.out.println(">  Added To Do Task: " + currList.get(currList.size() - 1));
    }

    private static void addDeadline(String currInput) throws DukeException {
        if (!currInput.contains("/by")) {
            throw new DukeException("A Deadline task should have a '/by' followed by the deadline time.");
        }
        String[] sections = currInput.split("/by");
        if (sections.length < 2 || sections[0].trim().length() <= 9 || sections[1].trim().isEmpty()) {
            throw new DukeException("Incorrect format for deadline.");
        }
        String description = sections[0].substring(9).trim();
        String by = sections[1].trim();

        try {
            LocalDateTime deadlineDateTime = parseDateTime(by);
            currList.add(new Deadline(description, deadlineDateTime));
            System.out.println(">  Added Deadline Task: " + currList.get(currList.size() - 1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date/time format for deadline. Please use 'yyyy-MM-dd HHmm'.");
        }
    }
    private static void addEvent(String currInput) throws DukeException {
        if (!currInput.contains("/from") || !currInput.contains("/to")) {
            throw new DukeException("An Event task should have a '/from' and '/to' with respective times.");
        }
        String[] sections = currInput.split("/from|/to");
        if (sections.length < 3 || sections[0].trim().length() <= 6 || sections[1].trim().isEmpty() || sections[2].trim().isEmpty()) {
            throw new DukeException("Incorrect format for event.");
        }
        String description = sections[0].substring(6).trim();
        String from = sections[1].trim();
        String to = sections[2].trim();

        try {
            LocalDateTime fromDateTime = parseDateTime(from);
            LocalDateTime toDateTime = parseDateTime(to);
            currList.add(new Event(description, fromDateTime, toDateTime));
            System.out.println(">  Added Event Task: " + currList.get(currList.size() - 1));
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect date/time format for event. Please use 'yyyy-MM-dd HHmm'.");
        }
    }
    private static void markTask(String currInput) throws DukeException {
        int index = Integer.parseInt(currInput.split(" ")[1]) - 1;
        if (index < 0 || index >= currList.size()) {
            throw new DukeException("Task " + (index + 1) + " was not found.");
        }
        currList.get(index).setCompleted();
        System.out.println(">  ok, you have completed Task " + (index + 1));
        System.out.println(currList.get(index));
    }

    private static void unmarkTask(String currInput) throws DukeException {
        int index = Integer.parseInt(currInput.split(" ")[1]) - 1;
        if (index < 0 || index >= currList.size()) {
            throw new DukeException("Task " + (index + 1) + " was not found.");
        }
        currList.get(index).setNotCompleted();
        System.out.println(">  ok, you haven't completed Task " + (index + 1));
        System.out.println(currList.get(index));
    }

    private static void deleteTask(String currInput) throws DukeException {
        String[] parts = currInput.split(" ");
        if (parts.length < 2) {
            throw new DukeException("Please provide a valid task number to delete.");
        }

        try {
            int index = Integer.parseInt(parts[1]) - 1;
            if (index < 0 || index >= currList.size()) {
                throw new DukeException("Task " + (index + 1) + " was not found.");
            }

            Task deleted = currList.remove(index);
            System.out.println(">  Task " + (index + 1) + " has been removed");
            System.out.println(">  " + deleted + " has been deleted.");
        } catch (NumberFormatException e) {
            throw new DukeException("Please provide a valid task number to delete.");
        }
    }

    private static void saveTasksToFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : currList) {
                fileWriter.write(task.toFileString() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(">  An error occurred while saving the tasks to a file: " + e.getMessage());
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }}
