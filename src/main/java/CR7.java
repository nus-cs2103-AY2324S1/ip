import java.util.Scanner;
public class CR7 {
    // Function to print a horizontal line
    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("-");
        }
        System.out.println(); // Move to the next line
    }

    public static void main(String[] args) {
        printHorizontalLine(40);
        System.out.println("Hello! I'm CR7\n" + "What can I do for you?\n");
        String input = "";
        while (!input.equals("bye")) {
            Scanner myObj = new Scanner(System.in);
            input = myObj.nextLine();
            if (input.equals("bye")) {
                printHorizontalLine(40);
                System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
                printHorizontalLine(40);
                break;
            }
            printHorizontalLine(40);
            System.out.println(input + "\n");
            printHorizontalLine(40);
        }
    }
}
