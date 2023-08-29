import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Sana {

    public enum Commands {
        LIST("list"),
        BYE("bye"),
        MARK("mark"),
        UNMARK("unmark"),
        DELETE("delete"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        UNKNOWN("unknown");

        private final String command;

        Commands(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
    protected static ArrayList<Task> tasksList = new ArrayList<>();
    protected static String divider = "_______________________________________\n";
    protected static String greeting = "Hello I'm Sana!\nWhat can I do for you today?\n";
    protected static String bye = "Bye. Hope to see you again soon!\n";

    public static void main(String[] args) throws IOException {

        Scanner myObj = new Scanner(System.in);
        System.out.println(divider + greeting + divider);

        while(true) {
            String cmd = myObj.next();

            Commands command = determineCommand(cmd);

            if (command == Commands.BYE ) {
                System.out.println(divider + bye + divider);
                break;
            }
            String userInput = myObj.nextLine();

            switch (command) {
            case LIST:
                try {
                    list(tasksList);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case MARK:
                try {
                    mark(tasksList, userInput);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case UNMARK:
                try {
                    unmark(tasksList, userInput);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case DELETE:
                try {
                    delete(tasksList, userInput);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case TODO:
                try {
                    todo(tasksList, userInput);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case DEADLINE:
                try {
                    deadline(tasksList, userInput);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case EVENT:
                try {
                    event(tasksList, userInput);
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
                break;
            case UNKNOWN:
                try {
                    throw new SanaException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (SanaException e) {
                    System.out.println(divider + e.getMessage() + "\n" + divider);
                }
            }
        }
    }

    private static void list(ArrayList<Task> tasksList) throws SanaException {

        StringBuilder tasks = new StringBuilder();
        try {
            Scanner scan = new Scanner(new File(Paths.get(
                    "/Users/ariellacallista/Desktop/SanaTasks.txt").toString()));
            while(scan.hasNextLine()) {
                tasks = tasks.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        if (tasks.toString().isBlank()) {
            throw new SanaException("Your list is empty! Add tasks first to display list");
        }

        System.out.println(divider + "Here are the tasks in your list:\n" + tasks + divider);
    }

    private static void mark(ArrayList<Task> tasksList, String userInput) throws SanaException {
        int taskId = Integer.parseInt(userInput.substring(1));
        if (taskId > tasksList.size() || taskId == 0) {
            throw new SanaException("No such task!");
        }
        tasksList.get(taskId - 1).markAsDone();
        System.out.println(divider + "Nice! I've marked this task as done:\n"
                + tasksList.get(taskId - 1).toString() + "\n" + divider);
    }

    private static void unmark(ArrayList<Task> tasksList, String userInput) throws SanaException {
        int taskId = Integer.parseInt(userInput.substring(1));
        if (taskId > tasksList.size() || taskId == 0) {
            throw new SanaException("No such task!");
        }
        tasksList.get(taskId - 1).markAsNotDone();
        System.out.println(divider + "OK, I've marked this task as not done yet:\n"
                + tasksList.get(taskId - 1).toString() + "\n" + divider);
    }

    private static void delete(ArrayList<Task> tasksList, String userInput) throws SanaException {
        int taskId = Integer.parseInt(userInput.substring(1));
        if (taskId > tasksList.size() || taskId == 0) {
            throw new SanaException("No such task!");
        }
        Task deletedTask = tasksList.get(taskId - 1);
        tasksList.remove(taskId - 1);
        System.out.println(divider + "Noted. I've removed this task:\n" + deletedTask.toString() + "\n"
                + "Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list\n" + divider);
    }

    private static void todo(ArrayList<Task> tasksList, String userInput) throws SanaException, IOException {
        if (userInput.isEmpty()) {
            throw new SanaException("OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTodo = new Todo(userInput);
        tasksList.add(newTodo);

        saveTasks("/Users/ariellacallista/Desktop", "/Users/ariellacallista/Desktop/SanaTasks.txt", newTodo);

        System.out.println(divider + "Got it. I've added this task:\n" + newTodo + "\n"
                + "Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list\n" + divider);
    }

    private static void deadline(ArrayList<Task> tasksList, String userInput) throws SanaException, IOException {
        if (userInput.isEmpty()) {
            throw new SanaException("OOPS!!! Incomplete task description.\nMake sure you follow the format " +
                    "'deadline [name of task] /by [deadline]'");
        }

        int lastDescId = userInput.indexOf('/');
        if (lastDescId == -1 || userInput.length() < lastDescId + 4 || userInput.substring(lastDescId + 4).isBlank()) {
            throw new SanaException("OOPS!! The deadline cannot be empty.\nMake sure you follow the format " +
                    "'deadline [name of task] /by [deadline]'");
        }

        String desc = userInput.substring(0, lastDescId - 1);
        String by = userInput.substring(lastDescId + 4);
        Task newDeadline = new Deadline(desc, by);
        tasksList.add(newDeadline);

        saveTasks("/Users/ariellacallista/Desktop", "/Users/ariellacallista/Desktop/SanaTasks.txt", newDeadline);

        System.out.println(divider + "Got it. I've added this task:\n" + newDeadline + "\n"
                + "Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list\n" + divider);
    }


    private static void event(ArrayList<Task> tasksList, String userInput) throws SanaException, IOException {
        if (userInput.isEmpty()) {
            throw new SanaException("OOPS!!! Incomplete command. Make sure you follow the format " +
                    "'event [name of task] /from [from] /to [to]'");
        }

        int lastDescId = userInput.indexOf('/');
        Task newEvent = getEvent(userInput, lastDescId);
        tasksList.add(newEvent);

        saveTasks("/Users/ariellacallista/Desktop", "/Users/ariellacallista/Desktop/SanaTasks.txt", newEvent);

        System.out.println(divider + "Got it. I've added this task:\n" + newEvent + "\n"
                + "Now you have " + tasksList.size() + (tasksList.size() <= 1 ? " task" : " tasks")
                + " in the list\n" + divider);
    }

    private static Task getEvent(String userInput, int lastDescId) throws SanaException {
        if (lastDescId == -1 || userInput.length() < lastDescId + 6 || userInput.substring(lastDescId + 6).isBlank()) {
            throw new SanaException("OOPS!! The 'from' field cannot be empty.\nMake sure you follow the format " +
                    "'deadline [name of task] /from [from] /to [to]'");
        }
        String desc = userInput.substring(0, lastDescId - 1);

        int lastFromId = userInput.indexOf('/', lastDescId + 1);
        if (lastFromId == -1 || userInput.length() < lastFromId + 4 || userInput.substring(lastFromId + 4).isBlank()) {
            throw new SanaException("OOPS!! The 'to' field cannot be empty.\nMake sure you follow the format " +
                    "'deadline [name of task] /from [from] /to [to]'");
        }
        String from = userInput.substring(lastDescId + 6, lastFromId - 1);
        String to = userInput.substring(lastFromId + 4);

        Task newEvent = new Event(desc, from, to);
        return newEvent;
    }

    private static Commands determineCommand(String cmd) {
        for (Commands command : Commands.values()) {
            if (command.getCommand().equals(cmd)) {
                return command;
            }
        }
        return Commands.UNKNOWN;
    }

    private static void saveTasks(String folderPath, String filePath, Task task)  {
        File folder = new File(folderPath);

        // CHeck if folder exists, if not create one
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Check if file exists, if not create one
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        // write to file

        try {
            FileWriter writer = new FileWriter(Paths.get(filePath).toString(), true);
            writer.write(task.toString() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
