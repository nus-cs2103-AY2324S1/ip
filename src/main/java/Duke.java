import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    private static Scanner prompt = new Scanner(System.in);
    private static List taskList = new List();

    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) throws DukeException {
        try {
            if (!createFile()) {
                taskList = taskList.loadFromFile();
            }
            greet();
        }
        catch (DukeException exception) {
            System.out.println(exception);
        } catch (IOException e) {
            throw new DukeException("OOPS!! Error loading file");
        } finally {
            echo();
        }
    }

    public static boolean createFile() throws DukeException {
        File dir = new File("./data");
        boolean dirExists = dir.exists();
        if (!dirExists) {
            dir.mkdir();
        }
        try {
            File file = new File(FILE_PATH);
             return file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("OOPS!! File cannot be created.");
        }
    }

    public static void greet() throws DukeException {
        System.out.println("Hello! I'm Oranges.");
        System.out.println("What can I do for you?");
        echo();
    }

    public static void echo() throws DukeException {
        String promptText = prompt.nextLine();
        if (promptText.equals("bye")) {
            exit();
        }
        else if (promptText.equals("list")) {
            if (taskList.size() == 0) {
                System.out.println("Your task list is empty!");
            }
            list();
        }
        else if (promptText.startsWith("todo") || promptText.startsWith("deadline") || promptText.startsWith("event")) {
            createTask(promptText);
        }
        else if (promptText.startsWith("mark") || promptText.startsWith("unmark")){
            markTask(promptText);
        }
        else if (promptText.startsWith("delete")) {
            deleteTask(Integer.valueOf(promptText.substring(7)) - 1);
        }
        else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        echo();
    }

    public static void deleteTask(int i) throws DukeException {
        try {
            taskList.delete(taskList.get(i));
        }
        catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!! This task doesn't exist!");
        }
    }

    public static void createTask(String promptText) throws DukeException {
        if (promptText.startsWith("todo")) {
            try {
                Task todo = new Todo(promptText.substring(5));
                taskList.add(todo, true);
                taskList.writeToFile();
            }
            catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of a todo cannot be empty.");
            }
        }
        else if (promptText.startsWith("deadline")) {
            try {
                String[] parts = promptText.split("/");
                Task deadline = new Deadline(parts[0].substring(9), parts[1].substring(2));
                taskList.add(deadline, true);
                taskList.writeToFile();
            }
            catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of a deadline cannot be empty.");
            }
        }
        else {
            try {
                String[] parts = promptText.split("/");
                Task event = new Event(parts[0].substring(6), parts[1].substring(4), parts[2].substring(2));
                taskList.add(event, true);
                taskList.writeToFile();
            }
            catch (StringIndexOutOfBoundsException s) {
                throw new DukeException("OOPS!! The description of an event cannot be empty.");
            }
        }
    }
    public static void markTask(String promptText) throws DukeException {
        try {
            int i = Integer.parseInt(promptText.substring(promptText.length() - 1));
            Task t = taskList.get(i-1);
            if (promptText.startsWith("unmark")) {
                t.unmark();
                taskList.writeToFile();
            }
            else {
                t.mark(true);
                taskList.writeToFile();
            }
        }
        catch (NumberFormatException n) {
            throw new DukeException("OOPS!! You must mark/unmark an index.");
        }
        catch (IndexOutOfBoundsException i) {
            throw new DukeException("OOPS!! This index doesn't exist.");
        }
    }



    public static void list() throws DukeException {
        taskList.list();
        echo();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
