import java.util.Scanner;
public class Eva {
    public static void main(String[] args) {
        String logo = "  ______          \n"
                + " |  ____|         \n"
                + " | |____   ____ _ \n"
                + " |  __\\ \\ / / _` |\n"
                + " | |___\\ V / (_| |\n"
                + " |______\\_/ \\__,_|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Eva.");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("\t____________________________________________________________");
                System.out.println("\t Bye. Hope to see you again soon!");
                System.out.println("\t____________________________________________________________");
                break;
            }

            System.out.println("\t____________________________________________________________");
            System.out.println("\t" + input);
            System.out.println("\t____________________________________________________________");
        }
        scanner.close();
    }
}
