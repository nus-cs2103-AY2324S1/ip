import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
            String logo = "____________________________________________________________\n Hello! I'm bob \n What can I do for you? \n ____________________________________________________________";
            System.out.println(logo);
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            while (!input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" " + input);
                System.out.println("____________________________________________________________");
                input = scanner.nextLine();
            }
            System.out.println(" Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }
}

