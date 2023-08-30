import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class ChatBot {
    static final String name = "4F5DA2";
    static final String localDirectoryPath = "./data";
    static final String localFilePath = localDirectoryPath + "/chatbot.txt";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);
    static final int maxNumberOfTasks = 100;

    static ArrayList<Task> taskList = new ArrayList<>(maxNumberOfTasks);

    public static void greet() {
        System.out.println(line);
        System.out.println("\tWelcome back, human!");
        System.out.println("\tI'm your personal ChatBot, " + name + ".");
        System.out.println("\tWhat can I do for you today?");
        System.out.println(line);
    }

    public static void readData() throws LocalFileException{
        try {
            File d = new File(localDirectoryPath);
            if (!d.exists() || !d.isDirectory()) {
                Files.createDirectory(Paths.get(localDirectoryPath));
            }
            File f = new File(localFilePath);
            if (!f.exists() || !f.isFile()) {
                Files.createFile(Paths.get(localFilePath));
            }
            // Local File is present
            Scanner initialDataScanner = new Scanner(f);
            while (initialDataScanner.hasNext()) {
                taskList.add(parseTaskString(initialDataScanner.nextLine()));
            }
        } catch (IOException e) {
            throw new FilePermissionException(localFilePath);
        } catch (InvalidTaskStringException e) {
            throw new FileCorruptedException(localFilePath);
        }

    }

    public static void exit() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void addTask(Task task) throws LocalFileException {
        taskList.add(task);
        writeToDataFile(taskListToStrings());
        output(String.format("\tGot it. I've added this task:\n\t\t%s\n\tNow you have %d tasks in the list",
                task.toString(),
                taskList.size()
                ));
    }

    public static void markAs(boolean isDone, int index) throws InvalidTaskIndexException, LocalFileException{
        if (index < 1 || index > taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task task = taskList.get(index-1);
        task.markAs(isDone);
        writeToDataFile(taskListToStrings());
        output(String.format("\t%s\n\t%s",
                isDone ? "Nice! I've marked this task as done:"
                       : "OK, I've marked this task as not done yet:",
                task));
    }

    public static void deleteTask(int index) throws InvalidTaskIndexException , LocalFileException{
        if (index < 1 || index > taskList.size()) {
            throw new InvalidTaskIndexException();
        }
        Task task = taskList.get(index-1);
        taskList.remove(task);
        writeToDataFile(taskListToStrings());
        System.out.println(line);
        System.out.println(String.format("\tNoted. I've removed this task:\n\t%s", task.toString()));
        System.out.println(String.format("\tNow you have %d tasks in the list.", taskList.size()));
        System.out.println(line);
    }

    public static String taskListToStrings() {
        StringBuilder sb = new StringBuilder();
        for (Task task: taskList) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void writeToDataFile(String data) throws LocalFileException {
        try {
            FileWriter fw = new FileWriter(localFilePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new FilePermissionException(localFilePath);
        }
    }

    public static void listTasks() {
        System.out.println(line);
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("\t%d.%s",
                    i+1,
                    task.toString()));
        }
        System.out.println(line);
    }

    private static Task parseTaskString(String taskString) throws InvalidTaskStringException {
        if (taskString.isEmpty()) {
            throw new InvalidTaskStringException();
        }
        String firstWord = taskString.split(" ")[0];
        try {
            switch (firstWord.charAt(1)) {
                case 'T':
                    return parseTodoTaskString(taskString);
                case 'D':
                    return parseDeadlineTaskString(taskString);
                case 'E':
                    return parseEventTaskString(taskString);
                default:
                    throw new InvalidTaskStringException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static boolean parseTaskIsDone(String taskString) throws InvalidTaskStringException {
        try {
            switch (taskString.charAt(4)) {
            case 'X':
                return true;
            case ' ':
                return false;
            default:
                throw new InvalidTaskStringException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseTodoTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            String taskName = taskString.substring(7);
            return new ToDoTask(taskName, isDone);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseDeadlineTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            int idOfBy = taskString.indexOf("(by:");
            if (idOfBy == -1) {
                throw new InvalidTaskStringException();
            }
            String taskName = taskString.substring(7, idOfBy - 1);
            String deadlineWholeString = taskString.substring(idOfBy);
            String deadline = deadlineWholeString.substring(5, deadlineWholeString.length() - 1);
            return new DeadlineTask(taskName, isDone, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseEventTaskString(String taskString) throws InvalidTaskStringException {
        try {
            boolean isDone = parseTaskIsDone(taskString);
            int idOfFrom = taskString.indexOf("(from:");
            int idOfTo = taskString.indexOf("to:");
            if (idOfFrom == -1 || idOfTo == -1) {
                throw new InvalidTaskStringException();
            }
            String taskName = taskString.substring(7, idOfFrom - 1);
            String fromWholeString = taskString.substring(idOfFrom, idOfTo);
            String toWholeString = taskString.substring(idOfTo);
            String from = fromWholeString.substring(7, fromWholeString.length() - 1);
            String to  = toWholeString.substring(4, toWholeString.length() - 1);
            return new EventTask(taskName, isDone, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskStringException();
        }
    }

    private static Task parseTodoTaskCommand(String command) throws TodoMissingFieldException{
        try {
            return new ToDoTask(command.substring(5));
        } catch (IndexOutOfBoundsException e) {
            throw new TodoMissingFieldException();
        }

    }

    private static Task parseDeadlineTaskCommand(String command) throws DeadlineMissingFieldException{
        int idOfBy = command.indexOf("/by");
        if (idOfBy == -1) {
            throw new DeadlineMissingFieldException();
        }
        try {
            String name = command.substring(9, idOfBy - 1);
            String deadline = command.substring(idOfBy + 4);
            if (name.isEmpty() || deadline.isEmpty()) {
                throw new DeadlineMissingFieldException();
            }
            return new DeadlineTask(name, deadline);
        } catch (IndexOutOfBoundsException e) {
            throw new DeadlineMissingFieldException();
        }
    }

    private static Task parseEventTaskCommand(String command) throws EventMissingFieldException {
        int idOfFrom = command.indexOf("/from");
        int idOfTo = command.indexOf("/to");
        if (idOfFrom == -1 || idOfTo == -1) {
            throw new EventMissingFieldException();
        }
        try {
            String name = command.substring(6, idOfFrom - 1);
            String from = command.substring(idOfFrom + 6, idOfTo - 1);
            String to = command.substring(idOfTo + 4);
            if (name.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new EventMissingFieldException();
            }
            return new EventTask(name, from, to);
        } catch (IndexOutOfBoundsException e) {
            throw new EventMissingFieldException();
        }
    }

    public static void nextCommand() {
        try {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
                return;
            } else if (command.equals("list")) {
                listTasks();
            } else {
                String[] words = command.split(" ");
                if (words[0].equals("mark") || words[0].equals("unmark")) {
                    if (words.length != 2) {
                        throw new MarkMissingFieldException();
                    }
                    try {
                        markAs(words[0].equals("mark"), Integer.parseInt(words[1]));
                    } catch (NumberFormatException e) {
                        throw new InvalidTaskIndexException();
                    }
                } else if (words[0].equals("delete")) {
                    if (words.length != 2) {
                        throw new DeleteMissingFieldException();
                    }
                    try {
                        deleteTask(Integer.parseInt(words[1]));
                    } catch (NumberFormatException e) {
                        throw new InvalidTaskIndexException();
                    }
                } else {
                    switch (words[0]) {
                    case "todo":
                        addTask(parseTodoTaskCommand(command));
                        break;
                    case "deadline":
                        addTask(parseDeadlineTaskCommand(command));
                        break;
                    case "event":
                        addTask(parseEventTaskCommand(command));
                        break;
                    default:
                        throw new IllegalCommandException();
                    }
                }
            }
            nextCommand();
        } catch (ChatBotException e) {
            output(e.toString());
            nextCommand();
        }
    }

    public static void main(String[] args) {
        greet();
        try {
            readData();
            nextCommand();
        } catch (LocalFileException e) {
           output(e.toString());
        }
    }
}
