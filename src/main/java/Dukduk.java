import java.util.Scanner;

public class Dukduk {
    public static void main(String[] args) {
        printLine();
        System.out.println(" Hello! I'm Dukduk");
        System.out.println(" What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(" ");
            String input = scanner.nextLine();
            printLine();
            System.out.println(" " + input);
            printLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                printLine();
                break;
            }
        }
        scanner.close();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
}
