import java.util.Scanner;
public class Duke {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printHorizontalLine();
        System.out.println("\t " + "Hello! I'm Buddy! \n" +
               "\t " + "What can I do for you? ");
        printHorizontalLine();

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t " + "Bye! Hope to see you again soon! ");
                printHorizontalLine();
                break;
            }
            System.out.println("\t " + input);
            printHorizontalLine();
        }
        scanner.close();

    }

    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }
}
