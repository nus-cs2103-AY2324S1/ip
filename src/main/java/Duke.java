import java.util.*;
public class Duke {

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

    // A display everytime receive an input
    private static boolean displayInfo(String msg) {
        if (!msg.equals("bye")){
            System.out.println("____________________________________________");
            System.out.println(msg);
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
            saysBye = displayInfo(sc.nextLine());
        } while(!saysBye);

        OnExit();
    }
}
