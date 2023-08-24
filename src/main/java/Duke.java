import java.util.*;
public class Duke {

    private static final String[] tasks = new String[100];
    private static int index = 0;

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
    private static void AddTasks(String task) {
        Duke.tasks[Duke.index] = task;
        Duke.index++;
    }

    //Prints All Tasks
    private static void ListTasks() {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + Duke.tasks[i]);
        }
    }

    // A display everytime receive an input
    private static boolean DisplayInfo(String msg) {
        if (!msg.equals("bye") && !msg.equals("list")){
            System.out.println("____________________________________________");
            System.out.println("added: " + msg);
            AddTasks(msg);
            System.out.println("____________________________________________");
        } else if (msg.equals("list")) {
            System.out.println("____________________________________________");
            ListTasks();
            System.out.println("____________________________________________");
        }
        return msg.equals("bye");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        OnEnter();

        boolean saysBye;
        do {
            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            saysBye = DisplayInfo(line);
        } while(!saysBye);

        OnExit();
    }
}
