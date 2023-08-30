import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Nobita {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int size = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Nobita");
        System.out.println("What can I do for you?");
        readFile();
        program();
        writeFile();
        exit();
    }

    private static void program() {
        Scanner sc = new Scanner(System.in);
        boolean live = true;
        while (live) {
            String[] input = sc.nextLine().split(" ",2);
            try {
                switch (input[0].toLowerCase()) {
                case "bye":
                    live = false;
                    break;
                case "list":
                    int listNum = 1;
                    for (Task task: tasks) {
                        printMessage(listNum + ". " + task);
                        ++listNum;
                    }
                    break;
                case "mark": {
                    int target = Integer.parseInt(input[1]) - 1;
                    markTask(target, false);
                    break;
                }
                case "unmark": {
                    int target = Integer.parseInt(input[1]) - 1;
                    markTask(target, true);
                    break;
                }
                case "todo":
                    if (input.length < 2) {
                        throwDescriptionException("todo");
                    }
                    ToDo newToDo = new ToDo(input[1]);
                    addTask(newToDo);
                    break;
                case "deadline":
                    if (input.length < 2) {
                        throwDescriptionException("deadline");
                    }
                    String[] deadlineFields = input[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlineFields[0], deadlineFields[1]);
                    addTask(newDeadline);
                    break;
                case "event":
                    if (input.length < 2) {
                        throwDescriptionException("event");
                    }
                    String[] eventFields = input[1].split(" /from ");
                    String[] fromAndTo = eventFields[1].split(" /to ");
                    Event newEvent = new Event(eventFields[0], fromAndTo[0], fromAndTo[1]);
                    addTask(newEvent);
                    break;
                case "delete":
                    int target = Integer.parseInt(input[1]) - 1;
                    deleteTask(target);
                    break;
                default:
                    throw new NobitaException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (NobitaException e) {
                printMessage(e.toString());
            }
        }
    }

    private static void readFile() {
        try {
            checkFileExist();
            File file = new File(getFilePath());
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                Task toAdd = new Task("Test Subject");
                switch (data[0]) {
                    case "T":
                        toAdd = new ToDo(data[2]);
                        break;
                    case "D":
                        toAdd = new Deadline(data[2], data[3]);
                        break;
                    case "E":
                        toAdd = new Event(data[2], data[3], data[4]);
                        break;
                    default:
                }
                if (data[1].equals("1")) {
                    toAdd.markComplete();
                }
                tasks.add(toAdd);
            }
        } catch (Exception e) {
            printMessage(e.toString());
        }
    }

    private static void writeFile() {
        try {
            checkFileExist();
            FileWriter file = new FileWriter(getFilePath());
            for (Task task: tasks) {
                file.write(task.toFileFormat());
                file.write("\n");
            }
            file.close();
        } catch (IOException e) {
            printMessage(e.toString());
        }
    }

    private static String getFilePath() {
        String home = System.getProperty("user.dir");
        java.nio.file.Path pathDirectory = java.nio.file.Paths.get(home,"src", "main", "data");
        try {
            java.nio.file.Files.createDirectories(pathDirectory);
        } catch (IOException e) {
            printMessage(e.toString());
        }
        return java.nio.file.Paths.get(pathDirectory.toString(), "nobita.txt").toString();
    }
    private static void checkFileExist() {
        File file = new File(getFilePath());
        try {
            file.createNewFile(); // create new file if file does not exist
        } catch (Exception e) {
            printMessage(e.toString());
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);
        printMessage("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() +" tasks in the list.");
    }

    private static void deleteTask(int targetTask) {
        Task task = tasks.remove(targetTask);
        printMessage("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() +" tasks in the list.");
    }

    private static void markTask(int targetTask, boolean isComplete) {
        Task task = tasks.get(targetTask);
        if (isComplete)  {
            task.markComplete();
            printMessage("Nice! I've marked this task as done:\n" + task.toString());
        } else {
            task.markIncomplete();
            printMessage("OK, I've marked this task as not done yet:\n" + task.toString());
        }
    }

    private static void throwDescriptionException(String command) throws NobitaException {
        throw new NobitaException("The description of a " + command + " cannot be empty.\n"
                    + "Please specify a description.");
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static void exit(){
        System.out.println("Bye. Hope to see you again soon!");
    }
}
