import java.util.Scanner;

public class Moss {
    public static void main(String[] args) {
        String greet = "____________________________________________________________\n"
                + "Hello! I'm Moss \n"
                + "What can I do for you? \n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        String message = sc.next();
        while (!message.equals("bye")) {
            echo(message);
            message = sc.next();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void echo(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}
