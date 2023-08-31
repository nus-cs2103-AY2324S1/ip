import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

public class Duke {

    public static String dash = "\t-------------------------------------------------------------";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void welcomeMessage() {

        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\tHello from\n" + logo);
        System.out.println(dash);
        System.out.println("\t Hello! I'm YOLO \n\t What can I do for you? \n");
        System.out.println(dash);
        System.out.println();
        processFile();
    }

    public static void farewell() {

        System.out.println(dash);
        System.out.println("\t Bye. Hope to see you again soon! \n");
        System.out.println(dash);
    }

    public static void listAllTask() {

        System.out.println(dash);
        System.out.println("\tHere " + (taskList.size() > 1 ? "are" : "is") +
                " the " + (taskList.size() > 1 ? "tasks" : "task") + " in your list: ");
        if (taskList.size() > 0) {
            for (int i = 1; i < taskList.size() + 1; i++) {
                System.out.println("\t" + i + "." + taskList.get(i - 1).toString());
            }
        }
        System.out.println();
        System.out.println(dash);
    }

    public static void mark(int i) {

        taskList.get(i - 1).mark();
        System.out.println(dash);
        System.out.println(" \tNice! I've marked this task as done:");
        System.out.println("\t  " + taskList.get(i - 1).toString());
        System.out.println();
        System.out.println(dash);
        writeInto();
    }

    public static void unmark(int i) {

        taskList.get(i - 1).unmark();
        System.out.println(dash);
        System.out.println(" \tOk! I've marked this task as not done yet:");
        System.out.println("\t  " + taskList.get(i - 1).toString());
        System.out.println();
        System.out.println(dash);
        writeInto();
    }

    public static void addTodo(String message) throws UnmatchedArgumentException {

        System.out.println(dash);
        System.out.println("\tGot it. I've added this task: ");
        Task todo = new Todo(message, false);
        taskList.add(todo);
        System.out.println("\t  " + todo);
        System.out.println("\tNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(dash);
        writeInto();
    }

    public static void addDeadline(String message) throws UnmatchedArgumentException {

        String[] arr = message.split("/");
        if (arr.length < 2) {
            throw new UnmatchedArgumentException(arr.length, 2);
        }
        Deadline dl = new Deadline(arr[0], false, arr[1].substring(3));
        taskList.add(dl);
        System.out.println(dash);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + dl);
        System.out.println("\tNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(dash);
        writeInto();
    }

    public static void addEvent(String message) throws UnmatchedArgumentException {

        String[] arr = message.split("/");
        if (arr.length < 3) {
            throw new UnmatchedArgumentException(arr.length, 3);
        }
        Event e = new Event(arr[0], false, arr[1].substring(5), arr[2].substring(3)); //here
        taskList.add(e);
        System.out.println(dash);
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t  " + e);
        System.out.println("\tNow you have " + taskList.size() + (taskList.size() > 1 ? " tasks" : " task") + " in the list.");
        System.out.println();
        System.out.println(dash);
        writeInto();
    }

    public static void delete(int index) {

        System.out.println(dash);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + taskList.get(index - 1));
        taskList.remove(index - 1);
        System.out.println("\tNow that you have " + taskList.size() + (taskList.size() < 2 ? " task" : " tasks") + " in the list.");
        System.out.println();
        System.out.println(dash);
        writeInto();
    }

    public static void processFile() {

        Path relativePath = Paths.get("data", "duke.txt"); // does it create a file with provided string or ...?
        try {
            if (Files.exists(relativePath)) {
                List<String> content = Files.readAllLines(relativePath);
                if (content.size() > 0) {
                    for (String line : content) {
                        processLines(line);
                    }
                } // else no task yet
            } else {
                Files.createFile(relativePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) { // catch for wrong format or corrupted file
            System.out.println(e.getMessage());
        }
    }

    public static void writeInto() {

        Path relativePath = Paths.get("data", "duke.txt");
        ArrayList<String> content = new ArrayList<>();

        for (Task t : taskList) {
            content.add(t.contentLine());
        }
        try {
            Files.write(relativePath, content);
        } catch (Exception e) {
            System.out.println("\t" + e);
        }
    }

    public static void processLines(String line) throws Exception {

        String[] taskArr = line.split("/");
        switch (taskArr[0]) {
            case "T":
                taskList.add(new Todo(taskArr[2], !taskArr[1].isBlank()));
                break;

            case "D":
                taskList.add(new Deadline(taskArr[2], !taskArr[1].isBlank(), taskArr[3]));
                break;

            case "E":
                taskList.add(new Event(taskArr[2], !taskArr[1].isBlank(), taskArr[3], taskArr[4]));
                break;

            default:
                throw new Exception("Some of the content is not in the correct format or it is corrupted");
        }
    }

    public static void main(String[] args) {

        welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String message = sc.nextLine();
        String[] splitted = message.split(" ", 2);

        while (!splitted[0].equals("bye")) {

            try {
                if (splitted[0].equals("list")) {
                    listAllTask();
                } else if (splitted[0].equals("mark")) {

                    if (splitted.length > 1) {
                        int index = Integer.parseInt(splitted[1]);
                        if (index > 0 && index <= taskList.size()) {
                            mark(index);
                        } else {
                            throw new InvalidIndexException();
                        }
                    } else {
                        throw new InvalidIndexException();
                    }
                } else if (splitted[0].equals("delete")) {

                    if (splitted.length > 1) {
                        int index = Integer.parseInt(splitted[1]);
                        if (index > 0 && index <= taskList.size()) {
                            delete(index);
                        } else {
                            throw new InvalidIndexException();
                        }
                    } else {
                        throw new InvalidIndexException();
                    }
                } else if (splitted[0].equals("unmark")) {

                    if (splitted.length > 1) {
                        int index = Integer.parseInt(splitted[1]);
                        if (index > 0 && index <= taskList.size()) {
                            unmark(index);
                        } else {
                            throw new InvalidIndexException();
                        }
                    } else {
                        throw new InvalidIndexException();
                    }
                } else if (splitted[0].equals("todo")) {

                    if (splitted.length > 1) {
                        addTodo(splitted[1]);
                    } else {
                        throw new EmptyDescriptionException("todo");
                    }
                } else if (splitted[0].equals("deadline")) {
                    if (splitted.length > 1) {
                        addDeadline(splitted[1]);
                    } else {
                        throw new EmptyDescriptionException("deadline");
                    }
                } else if (splitted[0].equals("event")) {
                    if (splitted.length > 1) {
                        addEvent(splitted[1]);
                    } else {
                        throw new EmptyDescriptionException("event");
                    }
                } else {
                    throw new NoSuchCommandException();
                }
            } catch (NoSuchCommandException e) {
                System.out.println(e);
            } catch (EmptyDescriptionException e) {
                System.out.println(e);
            } catch (UnmatchedArgumentException e) {
                System.out.println(e);
            } catch (InvalidIndexException e) {
                System.out.println(e);
            }
            System.out.println();
            message = sc.nextLine();
            splitted = message.split(" ", 2);
        }
        farewell();
    }
}
