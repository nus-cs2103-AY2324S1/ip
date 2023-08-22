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
        String[] tasks = new String[100];
        int counter = 0;
        while (!input.equals("bye")) {
            Scanner myObj = new Scanner(System.in);
            input = myObj.nextLine();
            if (input.equals("bye")) {
                printHorizontalLine(40);
                System.out.println("Bye! Hope to see you again soon! SIUUUU\n");
                printHorizontalLine(40);
                break;
            }
            if (input.equals("list")) {
                printHorizontalLine(40);
                for (int i = 1; i < counter + 1; i++) {
                    System.out.println(i + ". " + tasks[i-1]);
                }
                System.out.println();
                printHorizontalLine(40);
            } else {
                tasks[counter] = input;
                counter++;
                printHorizontalLine(40);
                System.out.println("added: " + input + "\n");
                printHorizontalLine(40);
            }
        }
    }
}
