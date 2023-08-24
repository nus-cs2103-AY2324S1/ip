import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = "Wiz";
        System.out.println("Hello from " + logo +
                "\nWhat can I do for you?\n" +
                "Bye! Hope to see you again");
        System.out.println("--------------------------");

        while(true) {
            String command = scanner.nextLine();
            System.out.println("--------------------------");
            System.out.println(command);
            System.out.println("--------------------------");

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("--------------------------");
                break;
            }
        }
    }
}
