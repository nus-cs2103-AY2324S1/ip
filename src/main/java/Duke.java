import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final String name = "Duck";
    private static final String exitMessage = "bye";

    public static void welcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon !");
        System.out.println("____________________________________________________________");
    }

    public static void typeMessage() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!(str.equalsIgnoreCase(exitMessage))){
            echo(str);
            str = sc.nextLine();
        }
        exit();
    }

    public static void echo(String str) {
        System.out.println("____________________________________________________________");
        System.out.println(str);
        System.out.println("____________________________________________________________");

    }
    public static void main(String[] args) {
        welcomeMessage();
        typeMessage();
    }
}
