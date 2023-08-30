import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bongo {
    String pathname = "data/bongo.txt";
    ArrayList<Task> tasks;
    Scanner inputScanner;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    enum FileAction {
        MARK_TASK,
        UNMARK_TASK,
        ADD_TASK,
        DELETE_TASK
    }

    public Bongo() {
        this.tasks = new ArrayList<>();
        this.inputScanner = new Scanner(System.in);
    }

    public void greet() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Bongo!\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
    }

    public void bye() {
        String goodbyeMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(goodbyeMessage);
        inputScanner.close();
    }

    public void checkIfFilesExist() {
        File file = new File(this.pathname);
        String directoryPath = file.getParent();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean isDirectoryCreated = directory.mkdirs();
            if (isDirectoryCreated) {
                System.out.println("Directory created: " + directoryPath);
            }
        }
        if (!file.exists()) {
            try {
                boolean isFileCreated = file.createNewFile();
                if (isFileCreated) {
                    System.out.println("File created: " + this.pathname);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    public void loadTasksFromStorage() throws FileNotFoundException {
        File file = new File(this.pathname);
        Scanner fileScanner = new Scanner(file);
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] arr = line.split("\\|");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = arr[i].trim();
            }
            boolean isTaskMarkedDone = arr[1].equals("1");
            switch (arr[0]) {
                case "T":
                    tasks.add(new Todo(arr[2], isTaskMarkedDone));
                    break;
                case "D":
                    tasks.add(new Deadline(arr[2], isTaskMarkedDone, formatDateTime(arr[3])));
                    break;
                case "E":
                    tasks.add(new Event(arr[2], isTaskMarkedDone, formatDateTime(arr[3]), formatDateTime(arr[4])));
                    break;
            }
        }
        fileScanner.close();
    }

    public void appendToTextFile(Task newTask) {
        try {
            File file = new File(this.pathname);
            FileWriter fw = new FileWriter(this.pathname, true);
            String newLine = "";
            String isTaskMarkedDone = newTask.isDone ? "1" : "0";
            if (newTask instanceof Todo) {
                newLine = String.join(" | ", "T", isTaskMarkedDone, newTask.description);
            } else if (newTask instanceof Deadline) {
                Deadline newDeadline = (Deadline) newTask;
                newLine = String.join(" | ", "D", isTaskMarkedDone, newDeadline.description, formatter.format(newDeadline.deadline));
            } else if (newTask instanceof Event) {
                Event newEvent = (Event) newTask;
                newLine = String.join(" | ", "E", isTaskMarkedDone, newEvent.description, formatter.format(newEvent.from), formatter.format(newEvent.to));
            }
            if (file.length() != 0) {
                fw.write(String.format("\n%s", newLine));
            } else {
                fw.write(newLine);
            }

            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public void editTextFile(FileAction action, int taskNumber) {
        try {
            File file = new File(this.pathname);
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            int currentLine = 1;
            while ((line = fileReader.readLine()) != null) {
                if (currentLine == taskNumber) {
                    String[] lineWordsArr = line.split("\\|");
                    for (int i = 0; i < lineWordsArr.length; i++) {
                        lineWordsArr[i] = lineWordsArr[i].trim();
                    }
                    switch (action) {
                        case MARK_TASK:
                            lineWordsArr[1] = "1";
                            stringBuilder.append(String.join(" | ", lineWordsArr)).append("\n");
                            break;
                        case UNMARK_TASK:
                            lineWordsArr[1] = "0";
                            stringBuilder.append(String.join(" | ", lineWordsArr)).append("\n");
                            break;
                        case DELETE_TASK:
                            currentLine++;
                            continue;
                    }
                } else {
                    stringBuilder.append(line).append("\n");
                }
                currentLine++;
            }
            fileReader.close();

            // Write the modified content back to the file
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(stringBuilder.toString().trim());
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("Problem editing the file.");
            e.printStackTrace();
        }
    }

    public LocalDateTime formatDateTime(String datetime) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(datetime, this.formatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException(String.format("Please enter a valid datetime in the format of %s", "DD/MM/YYYY HHMM"), datetime, e.getErrorIndex());
        }
    }

    public void listAllTasks() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            allTasks.append(String.format(" %d. %s\n", i + 1, tasks.get(i)));
        }
        String tasksList = "____________________________________________________________\n" +
                " Here are the tasks in your list:\n" +
                allTasks +
                "____________________________________________________________";
        System.out.println(tasksList);
    }

    public void addTodo(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of a todo cannot be empty.");
        String newTaskDesc = String.join(" ", input).substring(input[0].length() + 1);
        this.addTask(new Todo(newTaskDesc));
    }

    public void addDeadline(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of a deadline cannot be empty.");
        String taskInput = String.join(" ", input).substring(input[0].length() + 1);
        int index = taskInput.indexOf("/by");
        if (index == -1) throw new BongoException("OOPS!!! Please include the deadline.");
        String taskDesc = taskInput.substring(0, index - 1);
        String taskDeadline = taskInput.substring(index + 4);
        LocalDateTime deadline = formatDateTime(taskDeadline);
        this.addTask(new Deadline(taskDesc, deadline));
    }

    public void addEvent(String[] input) throws BongoException {
        if (input.length <= 1)
            throw new BongoException("OOPS!!! The description of an event cannot be empty.");
        String taskInput = String.join(" ", input).substring(input[0].length() + 1);
        int fromIndex = taskInput.indexOf("/from");
        int toIndex = taskInput.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1)
            throw new BongoException("OOPS!!! Please include the to and from date/time of the event.");
        String taskDesc = taskInput.substring(0, fromIndex - 1);
        String taskFrom = taskInput.substring(fromIndex + 6, toIndex - 1);
        String taskTo = taskInput.substring(toIndex + 4);
        LocalDateTime from = formatDateTime(taskFrom);
        LocalDateTime to = formatDateTime(taskTo);
        this.addTask(new Event(taskDesc, from, to));
    }

    public void addTask(Task newTask) {
        tasks.add(newTask);
        String echoMessage = "____________________________________________________________\n" +
                " Got it. I've added this task:\n" +
                String.format("  %s\n", newTask) +
                String.format(" Now you have %d tasks in the list.\n", tasks.size()) +
                "____________________________________________________________";
        System.out.println(echoMessage);
        appendToTextFile(newTask);
    }

    public void markTaskDone(int taskIndex) {
        Task chosenTask = tasks.get(taskIndex);
        chosenTask.markAsDone();
        String taskStatusMessage = " Nice! I've marked this task as done:\n" +
                String.format("  %s\n", chosenTask);
        printTaskUpdateMessage(taskStatusMessage);
        editTextFile(FileAction.MARK_TASK, taskIndex + 1);
    }

    public void markTaskUndone(int taskIndex) {
        Task chosenTask = tasks.get(taskIndex);
        chosenTask.markAsUndone();
        String taskStatusMessage = " OK, I've marked this task as not done yet:\n" +
                String.format("  %s\n", chosenTask);
        printTaskUpdateMessage(taskStatusMessage);
        editTextFile(FileAction.UNMARK_TASK, taskIndex + 1);
    }

    public void deleteTask(int taskIndex) {
        Task chosenTask = tasks.get(taskIndex);
        tasks.remove(taskIndex);
        String taskStatusMessage = " Noted. I've removed this task:\n" +
                String.format("  %s\n", chosenTask) +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
        printTaskUpdateMessage(taskStatusMessage);
        editTextFile(FileAction.DELETE_TASK, taskIndex + 1);
    }

    public void printTaskUpdateMessage(String taskStatusMessage) {
        String finalMessage = "____________________________________________________________\n" +
                taskStatusMessage +
                "____________________________________________________________";
        System.out.println(finalMessage);
    }

    public void processUserInput() {
        label:
        while (this.inputScanner.hasNext()) {
            String[] input = inputScanner.nextLine().split(" ");
            try {
                switch (input[0]) {
                    case "bye":
                        this.bye();
                        break label;
                    case "list":
                        this.listAllTasks();
                        break;
                    case "mark":
                    case "unmark":
                    case "delete":
                        if (tasks.size() == 0) throw new BongoException("OOPS!!! There are currently no tasks.");
                        if (input.length < 2) throw new BongoException("OOPS!!! Please include the task index.");
                        int taskIndex = Integer.parseInt(input[1]) - 1;
                        if (taskIndex < 0 || taskIndex >= tasks.size())
                            throw new BongoException("OOPS!!! Task does not exist.");
                        if (input[0].equals("mark")) {
                            this.markTaskDone(taskIndex);
                        } else if (input[0].equals("unmark")) {
                            this.markTaskUndone(taskIndex);
                        } else {
                            this.deleteTask(taskIndex);
                        }
                        continue;
                    case "todo":
                        this.addTodo(input);
                        break;
                    case "deadline": {
                        this.addDeadline(input);
                        break;
                    }
                    case "event": {
                        this.addEvent(input);
                        break;
                    }
                    default:
                        throw new BongoException();
                }
            } catch (BongoException e) {
                System.out.println(printError(e.getMessage()));
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Bongo bongo = new Bongo();
        bongo.checkIfFilesExist();
        bongo.greet();
        bongo.loadTasksFromStorage();
        bongo.processUserInput();
    }

    public static String printError(String error) {
        return "____________________________________________________________\n" +
                String.format(" %s\n", error) +
                "____________________________________________________________";
    }
}

