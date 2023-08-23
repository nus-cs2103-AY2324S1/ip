import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo =
                "██████╗░██╗░░░░░░█████╗░░█████╗░██████╗░\n" +
                "██╔══██╗██║░░░░░██╔══██╗██╔══██╗██╔══██╗\n" +
                "██████╦╝██║░░░░░██║░░██║██║░░██║██████╔╝\n" +
                "██╔══██╗██║░░░░░██║░░██║██║░░██║██╔═══╝░\n" +
                "██████╦╝███████╗╚█████╔╝╚█████╔╝██║░░░░░\n" +
                "╚═════╝░╚══════╝░╚════╝░░╚════╝░╚═╝░░░░░";

        boolean isEcho = true;

        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BloopBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isEcho) {
            String strInput = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(strInput);
            System.out.println("____________________________________________________________");

            if (strInput.equalsIgnoreCase("bye")) {
                isEcho = false;
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}