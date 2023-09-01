import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String SEPARATION_LINE  = "____________________________________________________________";
    private static final String INDENTATION = "    ";
    private static final String TEXT_GREETING = "Hello, I'm Tasket\n" + INDENTATION + "What can I do for you?";
    private static final String TEXT_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String PATH_FOLDER = "./data";
    private static final String PATH_FILE = PATH_FOLDER + "/tasks.txt";

    private static String prompt = "";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static File dataFile = new File(PATH_FILE);
    private static File dataFolder = new File(PATH_FOLDER);

    public static void showSeparationLine() {
        System.out.println(INDENTATION + SEPARATION_LINE);
    }

    public static void showGreetingText() {
        showSeparationLine();
        System.out.println(INDENTATION + TEXT_GREETING);
        showSeparationLine();
    }

    public static void showGoodbyeText() {
        System.out.println(INDENTATION + TEXT_GOODBYE);
        showSeparationLine();
    }

    public static void showErrorText(String msg) {
        System.out.println(INDENTATION + "OOPS!!! " + msg);
        showSeparationLine();
    }

    public static void showLists() {
        System.out.println(INDENTATION + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.printf("%s%d. %s\n", INDENTATION, i, tasks.get(i - 1).toString());
        }
        showSeparationLine();
    }

    public static void identifyTaskType(String type, String prompt) throws TasketException {
        Task task = null;
        switch (type) {
        case "todo":
            task = createToDoTask(prompt);
            break;

        case "deadline":
            task = createDeadlineTask(prompt);
            break;

        case "event":
            task = createEventTask(prompt);
            break;
        }

        FileWriter fw = null;

        try {
            fw = new FileWriter(dataFile, true);
            fw.write(task.exportSaveFormat() + System.lineSeparator());
            tasks.add(task);

            System.out.println(INDENTATION + "Got it, I've added this task:" + System.lineSeparator()
                    + INDENTATION + task.toString());
            System.out.printf(INDENTATION + "Now you have %d tasks in the list\n", tasks.size());
            showSeparationLine();
        } catch (IOException e) {
            throw new TasketException("IO Error");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                throw new TasketException("IO Error");
            }
        }

    }

    public static Task createToDoTask(String prompt) throws TasketException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The description of todo cannot be empty");
        }

        return new ToDo(descriptions[1]);
    }

    public static Task createDeadlineTask(String prompt) throws TasketException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The description of deadline cannot be empty");
        }

        String[] desSplit = descriptions[1].split(" /by ", 2);
        if (desSplit.length != 2 || desSplit[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The deadline cannot be empty");
        }

        return new Deadline(desSplit[0], convertToDate(desSplit[1]));
    }

    public static Task createEventTask(String prompt) throws TasketException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The description of event cannot be empty");
        }

        String[] desSplit = descriptions[1].split(" /from ", 2);
        if (desSplit.length != 2 || desSplit[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The start time cannot be empty");
        }

        String[] eventLength = desSplit[1].split(" /to ", 2);
        if (eventLength.length != 2 || eventLength[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The end time cannot be empty");
        }

        return new Event(desSplit[0], eventLength[0], eventLength[1]);
    }

    public static String convertToDate(String deadline) {
        try {
            LocalDate date = LocalDate.parse(deadline);
            return date.format(DateTimeFormatter.ofPattern("yyyy MMM d"));
        } catch (DateTimeParseException exception) {
            return deadline;
        }
    }

    public static void deleteTask(String prompt) throws TasketException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The task index cannot be empty");
        }

        try {
            Task selectedTask = tasks.remove(Integer.parseInt(descriptions[1]) - 1);
            rewriteSaveFile();

            System.out.println(INDENTATION + "Noted. I've removed this task:\n"
                    + INDENTATION + "  " + selectedTask.toString());
            System.out.printf(INDENTATION + "Now you have %d tasks in the list\n", tasks.size());
            showSeparationLine();
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new TasketException("The task index exceeds the size of list");
        }
    }

    public static void markTaskDone(String prompt) throws TasketException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The task index cannot be empty");
        }

        try {
            Task selectedTask = tasks.get(Integer.parseInt(descriptions[1]) - 1);

            if (selectedTask.getStatusIcon().equals(" ")) {
                selectedTask.markAsDone();
                rewriteSaveFile();
            }

            System.out.println(INDENTATION + "Nice! I've marked this task as done:\n"
                    + INDENTATION + "  " + selectedTask.toString());
            showSeparationLine();
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new TasketException("The task index exceeds the size of list");
        }
    }

    public static void markTaskUndone(String text) throws TasketException {
        String[] descriptions = prompt.split(" ", 2);
        if (descriptions.length != 2 || descriptions[1].replaceAll(" ", "").isEmpty()) {
            throw new TasketException("The task index cannot be empty");
        }

        try {
            Task selectedTask = tasks.get(Integer.parseInt(descriptions[1]) - 1);

            if (selectedTask.getStatusIcon().equals("X")) {
                selectedTask.markAsUndone();
                rewriteSaveFile();
            }

            System.out.println(INDENTATION + "Ok. I've marked this task as not done yet:\n"
                    + INDENTATION + "  " + selectedTask.toString());
            showSeparationLine();
        } catch (NumberFormatException e) {
            throw new TasketException("The task index must be a number");
        } catch (IndexOutOfBoundsException e) {
            throw new TasketException("The task index exceeds the size of list");
        }
    }

    public static void rewriteSaveFile() throws TasketException {
        FileWriter fw = null;

        try {
            fw = new FileWriter(dataFile, false);
            fw.write("");

            for (Task task : tasks) {
                fw.append(task.exportSaveFormat()).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new TasketException("IO Error");
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                throw new TasketException("IO Error");
            }
        }
    }

    public static void retrieveSavedTasks() throws TasketException {
        Scanner sc = null;
        try {
            sc = new Scanner(dataFile);

            while (sc.hasNext()) {
                parseLine(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new TasketException("File not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public static void parseLine(String savedTask) {
        String[] taskElements = savedTask.split(" \\| ");
        try {
            Task generatedTask = null;
            switch (taskElements[0]) {
            case "T":
                if (taskElements.length != 3) {
                    throw new TasketException("Invalid task format");
                }
                generatedTask = new ToDo(taskElements[2]);
                break;
            case "D":
                if (taskElements.length != 4) {
                    throw new TasketException("Invalid task format");
                }
                generatedTask = new Deadline(taskElements[2], taskElements[3]);
                break;
            case "E":
                if (taskElements.length != 5) {
                    throw new TasketException("Invalid task format");
                }
                generatedTask = new Event(taskElements[2], taskElements[3], taskElements[4]);
                break;
            default:
                throw new TasketException("Invalid task type");
            }

            if (taskElements[1].equals("1")) {
                generatedTask.markAsDone();
            }

            tasks.add(generatedTask);

        } catch (TasketException e) {
            showErrorText(e.getMessage());
        }
    }

    public static void parseInput(String prompt) throws TasketException {
        String action = prompt.split(" ", 2)[0];

        switch (action) {
        case "todo":
        case "deadline":
        case "event":
            identifyTaskType(action, prompt);
            break;

        case "delete":
            deleteTask(prompt);
            break;

        case "mark":
            markTaskDone(prompt);
            break;

        case "unmark":
            markTaskUndone(prompt);
            break;

        case "list":
            showLists();
            break;

        case "bye":
            showGoodbyeText();
            break;

        default:
            throw new TasketException("I'm sorry, but I don't know what it means :(");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        try {
            dataFolder.mkdir();
            dataFile.createNewFile();

            retrieveSavedTasks();

            showGreetingText();

            while (!prompt.equals("bye")) {
                try {
                    System.out.println();
                    prompt = sc.nextLine();
                    showSeparationLine();

                    parseInput(prompt.trim());
                } catch (TasketException exception) {
                    showErrorText(exception.getMessage());
                }
            }

        } catch (IOException exception) {
            showErrorText("IO Error");
        } catch (TasketException exception) {
            showErrorText(exception.getMessage());
        } finally {
            sc.close();
        }

    }
}
