import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> arr = new ArrayList<>();
    private static String dataPath = "./data/duke.txt";
    private static final String HORIZONTAL_LINE = "    _______________________________________________________________";

    public static void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void printList() {
        int index = 0;
        System.out.println("    Here are the tasks in your list:");
        for (Task task: arr) {
            System.out.println("    " + (++index) + "." + task.toString());
        }
    }

    public static void addTask(String task) throws DukeException {
        if (task.startsWith("todo")) {
            if (task.length() < 6) throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println("    Got it. I've added this task:");
            arr.add(new Todo(task.substring(5)));
            System.out.println("      " + arr.get(arr.size() - 1).toString());
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
        }
        else if (task.startsWith("deadline")) {
            if (task.length() < 10) throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            System.out.println("    Got it. I've added this task:");
            String description = "";
            String by = "";
            for (int i = 9; i < task.length(); ++i) {
                if (task.charAt(i) == '/') {
                    description = task.substring(9, i - 1);
                    by = task.substring(i + 4);
                    break;
                }
            }
            arr.add(new Deadline(description, by));
            System.out.println("      " + arr.get(arr.size() - 1).toString());
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
        }
        else if (task.startsWith("event")) {
            if (task.length() < 7) throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            System.out.println("    Got it. I've added this task:");
            int slash1 = -1;
            int slash2 = -1;
            for (int i = 0; i < task.length(); ++i) {
                if (task.charAt(i) == '/') {
                    if (slash1 == -1) slash1 = i;
                    else slash2 = i;
                }
            }
            String description = task.substring(6, slash1 - 1);
            String from = task.substring(slash1 + 6, slash2 - 1);
            String to = task.substring(slash2 + 4);
            arr.add(new Event(description, from, to));
            System.out.println("      " + arr.get(arr.size() - 1).toString());
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void deleteTask(int index) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + arr.get(index - 1).toString());
        arr.remove(index - 1);
        System.out.println("    Now you have " + arr.size() + " tasks in the list.");
    }

    public static void markTaskDone(int index) {
        arr.get(index - 1).markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + arr.get(index - 1).toString());
    }

    public static void markTaskNotDone(int index) {
        arr.get(index - 1).markAsNotDone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + arr.get(index - 1).toString());
    }

    public static void processCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            bye();
        }
        else if (command.equals("list")) {
            printList();
        }
        else if (command.startsWith("mark")) {
            int index = 0;
            for (int i = 5; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            markTaskDone(index);
        }
        else if (command.startsWith("unmark")) {
            int index = 0;
            for (int i = 7; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            markTaskNotDone(index);
        }
        else if (command.startsWith("delete")) {
            int index = 0;
            for (int i = 7; i < command.length(); ++i) {
                index *= 10;
                index += command.charAt(i) - 48;
            }
            deleteTask(index);
        }
        else {
            addTask(command);
        }
        try {
            if (!command.equals("list")) {
                writeFile();
            }
        } catch (IOException ioe) {
            System.out.println("    " + ioe.getMessage());
        }
    }

    public static void writeFile() throws IOException {
        FileWriter fw = new FileWriter(dataPath);
        for (Task t: arr) {
            fw.write(t.writeFile());
            fw.write("\n");
        }
        fw.close();
    }

    public static void readFile() throws FileNotFoundException {
        File file = new File(dataPath);
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String input = s.nextLine();
            if (input.startsWith("T")) {
                String description = input.substring(8);
                Todo todo = new Todo(description);
                if (input.charAt(4) == '1') todo.markAsDone();
                arr.add(todo);
            }
            else if (input.startsWith("D")) {
                String description = "";
                String by = "";
                int count = 0, slash1 = -1, slash2 = -1;
                for (int i = 0; i < input.length(); ++i) {
                    if (input.charAt(i) == '|') {
                        ++count;
                        if (count == 2) slash1 = i;
                        if (count == 3) slash2 = i;
                    }
                }
                description = input.substring(slash1 + 2, slash2 - 1);
                by = input.substring(slash2 + 2);
                Deadline deadline = new Deadline(description, by);
                if (input.charAt(4) == '1') deadline.markAsDone();
                arr.add(deadline);
            }
            else if (input.startsWith("E")) {
                String description = "";
                String from = "";
                String to = "";
                int count = 0, slash1 = -1, slash2 = -1, slash3 = -1;
                for (int i = 0; i < input.length(); ++i) {
                    if (input.charAt(i) == '|') {
                        ++count;
                        if (count == 2) slash1 = i;
                        if (count == 3) slash2 = i;
                        if (count == 4) slash3 = i;
                    }
                }
                description = input.substring(slash1 + 2, slash2 - 1);
                from = input.substring(slash2 + 2, slash3 - 1);
                to = input.substring(slash3 + 2);
                Event event = new Event(description, from, to);
                if (input.charAt(4) == '1') event.markAsDone();
                arr.add(event);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Hello! I'm Thinh's chatbot\n    What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        try {
            readFile();
        } catch (FileNotFoundException e) {
            File dir = new File("./data");
            dir.mkdir();
            File dataFile = new File ("./data/duke.txt");
            try {
                dataFile.createNewFile();
                System.out.println("    Created new data file");
            } catch (IOException ioe) {
                System.out.println("    " + ioe.getMessage());
            }
        }
        Scanner in = new Scanner(System.in);
        String inputStr;
        do {
            inputStr = in.nextLine();
            System.out.println(HORIZONTAL_LINE);
            try {
                processCommand(inputStr);
            } catch (DukeException e) {
                System.out.println("    " + e.getMessage());
            }
            System.out.println(HORIZONTAL_LINE);
        } while (!inputStr.equals("bye"));
    }
}
