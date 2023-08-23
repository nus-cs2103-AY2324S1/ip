import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = "Chaty";
        System.out.println("Hello! I'm " + name + "\n" +
                "What can I do for you?" + "\n\n");
        String echo = scan.nextLine();
        while (!echo.equals("bye")) {
            System.out.println(echo);
            echo = scan.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
