import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    static final String FILE_NAME = "data/duke.txt";

    private static ArrayList<Task> tasklist;
    public static void main(String[] args) {
        try {
            String dir = "data";
            Path path = Paths.get(dir);
            Files.createDirectory(path);
        } catch (IOException e){
            System.err.println("Failed to Create Directory!" +e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        try {
            tasklist = loadTasks();
        } catch (FileNotFoundException e) {
            tasklist = new ArrayList<Task>();
        }
        String dottedLine = "____________________________________________________________\n";

        System.out.println(dottedLine +
                "Hello! I'm Charlie\n" +
                "What can I do for you?\n" +
                dottedLine);

        while (true) {
            String input = scanner.nextLine();
            String instruction = input.split(" ", 2)[0];

            try {
                if (input.equals("bye")) {
                    scanner.close();
                    exitBot();
                    break;
                } else if (input.equals("list")) {
                    printlist(tasklist);

                } else if (input.startsWith("mark")) {
                    markResponse(input, tasklist);

                } else if (input.startsWith("unmark")) {
                    unmarkResponse(input, tasklist);

                } else if (input.startsWith("todo")) {
                    addTodo(input, tasklist);

                } else if (input.startsWith("deadline")) {
                    addDeadline(input, tasklist);

                } else if (input.startsWith("event")) {
                    addEvent(input, tasklist);

                } else if (input.startsWith("delete")) {
                    delete(input, tasklist);

                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(dottedLine +
                        e.getMessage() +
                        "\n" + dottedLine);
            }
        }
    }
    private static void printlist(ArrayList<Task> arr) {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (Task t : arr) {
            System.out.printf("%d. %s%n", arr.indexOf(t) + 1, t.toString());
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void markResponse(String input, ArrayList<Task> tasklist) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (result < 0 || (result + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        tasklist.get(result).mark();
        rewriteFile(tasklist);
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                tasklist.get(result).toString() +
                "\n____________________________________________________________\n");
    }

    private static void unmarkResponse(String input, ArrayList<Task> tasklist) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (result < 0 || (result + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        tasklist.get(result).unmark();
        rewriteFile(tasklist);
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet:\n" +
                tasklist.get(result).toString() +
                "\n____________________________________________________________\n");
    }

    private static void delete(String input, ArrayList<Task> tasklist) throws DukeException {
        int spaceIndex = input.indexOf(" ");
        int result = Integer.parseInt(input.substring(spaceIndex + 1)) - 1;
        if (result < 0 || (result + 1) > tasklist.size()) {
            throw new DukeException("☹ OOPS!!! The task number is invalid.");
        }
        String deleted = tasklist.get(result).toString();
        tasklist.remove(result);
        rewriteFile(tasklist);

        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" +
                deleted +
                "\nNow you have " + tasklist.size() + " tasks in the list." +
                "\n____________________________________________________________\n");
    }
    private static void addTodo(String input, ArrayList<Task> tasklist) throws DukeException {
        int spaceIndex = input.indexOf(" ");

        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        String task = input.substring(spaceIndex + 1);
        Task newTask = new ToDo(task);
        tasklist.add(newTask);
        try {
            appendToFile(FILE_NAME, newTask.saveString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\nNow you have " + tasklist.size() + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void addDeadline(String input, ArrayList<Task> tasklist) throws DukeException{
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        int dateIndex = input.indexOf("/by");
        if (dateIndex == -1 || dateIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! You forgot to include the deadline.");
        }

        String task = input.substring(spaceIndex + 1, dateIndex);
        String deadline = input.substring(dateIndex + 4);
        Task newTask = new Deadline(task, deadline);
        tasklist.add(newTask);
        try {
            appendToFile(FILE_NAME, newTask.saveString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\nNow you have " + tasklist.size() + " tasks in the list." +
                "\n____________________________________________________________\n");
    }

    private static void addEvent(String input, ArrayList<Task> tasklist) throws DukeException{
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1 || spaceIndex + 1 >= input.length()) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        int startIndex = input.indexOf("/from");
        int endIndex = input.indexOf("/to");

        if (startIndex == -1 || endIndex == -1 || startIndex > endIndex) {
            throw new DukeException("☹ OOPS!!! Invalid event format.");
        }
        if (startIndex + 5 >= endIndex) {
            throw new DukeException("☹ OOPS!!! Missing event start date/time.");
        }
        if (endIndex + 3 >= input.length()) {
            throw new DukeException("☹ OOPS!!! Missing event end date/time.");
        }

        String task = input.substring(spaceIndex + 1, startIndex);
        String start = input.substring(startIndex + 6, endIndex);
        String end = input.substring(endIndex + 4);
        Task newTask = new Event(task, start, end);
        tasklist.add(newTask);
        try {
            appendToFile(FILE_NAME, newTask.saveString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task:\n" +
                newTask.toString() +
                "\nNow you have " + tasklist.size() + " tasks in the list." +
                "\n____________________________________________________________\n");
    }


    private static void exitBot() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
<<<<<<< HEAD

    /**
     * Overwrites content in a file with new content.
     *
     * @param filePath File path of the file to be appended to.
     * @param textToAdd String to be added to file.
     * @throws IOException if writing to file is not allowed.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    /**
     * Appends a new line of content to a file.
     *
     * @param filePath File path of the file to be appended to.
     * @param textToAppend String to be appended to file.
     * @throws IOException if writing to file is not allowed.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Rewrites the whole txt file.
     *
     * @param tasklist ArrayList of tasks to write to file.
     */
    private static void rewriteFile(ArrayList<Task> tasklist) {
        try {
            writeToFile(FILE_NAME, tasklist.get(0).saveString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (int i = 1; i < tasklist.size(); i++) {
            try {
                appendToFile(FILE_NAME, tasklist.get(i).toString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Loads content from text file into an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks with content of text file.
     * @throws FileNotFoundException if file is not valid.
     */
    private static ArrayList<Task> loadTasks() throws FileNotFoundException {
        File f = new File(FILE_NAME);
            Scanner s = new Scanner(f);
            ArrayList<Task> tasklist = new ArrayList<>();
            while (s.hasNext()) {
                String nextTask = s.nextLine();
                String[] input = nextTask.split(" \\| ");
                String category = input[0];
                String status = input[1];
                String description = input[2];

                switch (category) {
                case "T":
                    Task todoTask = new ToDo(description);
                    if (!status.equals("0")) {
                        todoTask.mark();
                    }
                    tasklist.add(todoTask);
                    break;
                case "D":
                    String deadline = input[3];
                    Task deadlineTask = new Deadline(description, deadline);
                    if (!status.equals("0")) {
                        deadlineTask.mark();
                    }
                    tasklist.add(deadlineTask);
                    break;
                case "E":
                    String from = input[3].split("-")[0];
                    String to = input[3].split("-")[1];
                    Task eventTask = new Event(description, from, to);
                    if (!status.equals("0")) {
                        eventTask.mark();
                    }
                    tasklist.add(eventTask);
                    break;
                }
            }

        return tasklist;
    }
}
=======
>>>>>>> branch-Level-7

    /**
     * Overwrites content in a file with new content.
     *
     * @param filePath File path of the file to be appended to.
     * @param textToAdd String to be added to file.
     * @throws IOException if writing to file is not allowed.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    /**
     * Appends a new line of content to a file.
     *
     * @param filePath File path of the file to be appended to.
     * @param textToAppend String to be appended to file.
     * @throws IOException if writing to file is not allowed.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Rewrites the whole txt file.
     *
     * @param tasklist ArrayList of tasks to write to file.
     */
    private static void rewriteFile(ArrayList<Task> tasklist) {
        try {
            writeToFile(FILE_NAME, tasklist.get(0).saveString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        for (int i = 1; i < tasklist.size(); i++) {
            try {
                appendToFile(FILE_NAME, tasklist.get(i).saveString() + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Loads content from text file into an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks with content of text file.
     * @throws FileNotFoundException if file is not valid.
     */
    private static ArrayList<Task> loadTasks() throws FileNotFoundException {
        File f = new File(FILE_NAME);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasklist = new ArrayList<>();
        while (s.hasNext()) {
            String nextTask = s.nextLine();
            String[] input = nextTask.split(" \\| ");
            String category = input[0];
            String status = input[1];
            String description = input[2];

            switch (category) {
            case "T":
                Task todoTask = new ToDo(description);
                if (!status.equals("0")) {
                    todoTask.mark();
                }
                tasklist.add(todoTask);
                break;
            case "D":
                String deadline = input[3];
                Task deadlineTask = new Deadline(description, deadline);
                if (!status.equals("0")) {
                    deadlineTask.mark();
                }
                tasklist.add(deadlineTask);
                break;
            case "E":
                String from = input[3].split("-")[0];
                String to = input[3].split("-")[1];
                Task eventTask = new Event(description, from, to);
                if (!status.equals("0")) {
                    eventTask.mark();
                }
                tasklist.add(eventTask);
                break;
            }
        }

        return tasklist;
    }
}
