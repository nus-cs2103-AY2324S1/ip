import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //private static final Task[] tasks = new Task[100];
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;
    private static boolean terminate = false;
    private static final FileHandler duke = new FileHandler();
    private static final String separation = "____________________________________________";

    // A greeting display everytime entering the program
    private static void OnEnter() {
        System.out.println(separation);
        System.out.println("Hello! I am YOU");
        System.out.println("What can I do for you?");
        System.out.println(separation);
    }

    // An exit display everytime exits the program
    private static void OnExit() {
        System.out.println(separation);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(separation);
    }

    //Reads and store input
    private static void addTasks(Task task) {
        Duke.tasks.add(task);
        Duke.count++;
    }

    //Prints All Tasks
    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + Duke.tasks.get(i));
        }
    }

    // A display everytime receive an input
    private static void displayInfo(String msg) {
        Task task;
        System.out.println(separation);
        try {
            if (msg.startsWith("todo")) {
                task = new ToDo(msg);
                addTasks(task);
            } else if (msg.startsWith("deadline")) {
                task = new Deadline(msg);
                addTasks(task);
            } else if (msg.startsWith("event")) {
                task = new Event(msg);
                addTasks(task);
            } else {
                throw new DukeUnknownInstruction();
            }
            duke.writeFile(task.toString());
            System.out.println("Now you have "
                    + Duke.count
                    + (count <= 1 ? " task" : " tasks")
                    + " in the list.");
            System.out.println(separation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deleteTask(int index) {
        Task delete = tasks.remove(index);
        count--;
        System.out.println(separation);
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + delete.toString());
        System.out.println("Now you have " + Duke.count + " tasks in the list.");
        System.out.println(separation);
        duke.DeleteLine(delete.toString());
    }

    private static void readInputs(String msg) {
        if (msg.equals("list")) {
            System.out.println(separation);
            listTasks();
            System.out.println(separation);
        } else if (msg.equals("bye")) {
            Duke.terminate = true;
        } else {
            boolean isKeyword = msg.matches(".*\\040[0-9]");
            if (isKeyword) {
                String[] part = msg.split("\\s+");
                int ind = Integer.parseInt(part[1]) - 1;
                try {
                    if (ind > Duke.count || ind < 0) {
                        throw new IndexOutOfBoundsException();
                    }
                    switch (part[0]) {
                    case "mark":
                        tasks.get(ind).MarkAsDone(duke);
                        break;
                    case "unmark":
                        tasks.get(ind).MarkAsUnDone(duke);
                        break;
                    case "delete":
                        deleteTask(ind);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(separation);
                    System.out.println("The given index is not in the available range");
                    System.out.println(separation);
                }

            } else {
                displayInfo(msg);
            }
        }
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        OnEnter();
        duke.fileCreate(tasks);
        count = tasks.size();

        Scanner sc = new Scanner(System.in);
        while (!Duke.terminate && sc.hasNextLine()) {
            String line = sc.nextLine();
            readInputs(line);
        }

        OnExit();
    }
}
