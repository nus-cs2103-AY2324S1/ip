import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final String name = "Duck";
    private static final String exitMessage = "bye";

    private static ArrayList<String> list = new ArrayList<>();

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

    public static boolean isBye(String str) {
        return str.equalsIgnoreCase(exitMessage);
    }

    public static boolean checkList(String str) {
        return str.equalsIgnoreCase("list");
    }

    public static void typeMessage() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!isBye(str)){
            //echo(str);
            if (checkList(str)) {
                display();
            } else {
                echo(str);
                list.add(str);
            }
            str = sc.nextLine();
        }
        if (isBye(str)) {
            exit();
        }
    }

    public static void display() {
        int count = 0;
        int serial = 1;
        System.out.println("____________________________________________________________");
        while (count < list.size()) {
            System.out.println(serial + ". " + list.get(count));
            count++;
            serial++;
        }
        System.out.println("____________________________________________________________");
    }

    public static void echo(String str) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + str);
        System.out.println("____________________________________________________________");

    }
    public static void main(String[] args) {
        welcomeMessage();
        typeMessage();
    }
}