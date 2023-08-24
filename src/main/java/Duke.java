import java.util.*;
public class Duke {

    //private static final Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int count = 0;
    private static boolean terminate = false;
    private static String seperation = "____________________________________________";

    // A greeting display everytime entering the program
    private static void OnEnter () {
        System.out.println(seperation);
        System.out.println("Hello! I am YOU");
        System.out.println("What can I do for you?");
        System.out.println(seperation);
    }

    // An exit display everytime exits the program
    private static void OnExit() {
        System.out.println(seperation);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(seperation);
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
        System.out.println(seperation);
        try{
            if (msg.startsWith("todo")) {
                task = new ToDo(msg);
                addTasks(task);
            } else if(msg.startsWith("deadline")) {
                task = new Deadline(msg);
                addTasks(task);
            } else if(msg.startsWith("event")){
                task = new Event(msg);
                addTasks(task);
            } else {
                throw new DukeUnknownInstruction();
            }
            System.out.println("Now you have " + Duke.count + " tasks in the list.");
            System.out.println(seperation);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deleteTask(int index){
        Task delete = tasks.remove(index);
        count --;
        System.out.println(seperation);
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + delete.toString());
        System.out.println("Now you have " + Duke.count + " tasks in the list.");
        System.out.println(seperation);
    }

    private static void readInputs(String msg) {
        if (msg.equals("list")) {
            System.out.println(seperation);
            listTasks();
            System.out.println(seperation);
        } else if (msg.equals("bye")) {
            Duke.terminate = true;
        } else {
            boolean isMark = msg.matches(".*\\040[0-9]");
            if (isMark) {
                String[] part = msg.split("\\s+");
                int ind = Integer.parseInt(part[1]) - 1;
                if (part[0].equals("mark")) {
                    tasks.get(ind).MarkAsDone();
                } else if (part[0].equals("unmark")) {
                    tasks.get(ind).MarkAsUnDone();
                } else if (part[0].equals("delete")) {
                    deleteTask(ind);
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
