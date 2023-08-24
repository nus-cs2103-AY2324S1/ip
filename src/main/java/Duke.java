import java.util.*;
public class Duke {

    private static final Task[] tasks = new Task[100];
    private static int index = 0;
    private static boolean terminate = false;

    // A greeting display everytime entering the program
    private static void OnEnter () {
        System.out.println("____________________________________________");
        System.out.println("Hello! I am YOU");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
    }

    // An exit display everytime exits the program
    private static void OnExit() {
        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }

    //Reads and store input
    private static void addTasks(Task task) {
        Duke.tasks[Duke.index] = task;
        Duke.index++;
    }

    //Prints All Tasks
    private static void listTasks() {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + Duke.tasks[i]);
        }
    }

    // A display everytime receive an input
    private static void displayInfo(String msg) {
        System.out.println("____________________________________________");
        System.out.println("Got it. I've added this task:");
        if (msg.substring(0, 4).equals("todo")) {
            addTasks(new ToDo(msg));
        } else if(msg.substring(0, 8).equals("deadline")) {
            addTasks(new Deadline(msg));
        } else {
            addTasks(new Event(msg));
        }
        System.out.println("Now you have " + Duke.index + " tasks in the list.");
        System.out.println("____________________________________________");
    }

    private static void readInputs(String msg) {
        if (msg.equals("list")) {
            System.out.println("____________________________________________");
            listTasks();
            System.out.println("____________________________________________");
        } else if (msg.equals("bye")) {
            Duke.terminate = true;
        } else {
            boolean isMark = msg.matches(".*[0-9]");
            if (isMark) {
                String[] part = msg.split("\\s+");
                int ind = Integer.parseInt(part[1]) - 1;
                if (part[0].equals("mark")) {
                    tasks[ind].MarkAsDone();
                } else if (part[0].equals("unmark")) {
                    tasks[ind].MarkAsUnDone();
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

        do {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            readInputs(line);
        } while(!Duke.terminate);

        OnExit();
    }
}
