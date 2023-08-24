import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        String[] ls = new String[100];
        Scanner scanner = new Scanner(System.in);
        printHorizontalLine();
        System.out.println("\t " + "Hello! I'm Buddy! \n" +
               "\t " + "What can I do for you? ");
        printHorizontalLine();
        int count = 0;
        while(count < 100) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("\t " + "Bye! Hope to see you again soon! ");
                printHorizontalLine();
                break;
            }
            else if (input.equals("list")) {
                printHorizontalLine();
                for (int i = 1; i <= count; i++) {
                    System.out.println("\t" + i + ". " + ls[i-1]);
                }
                printHorizontalLine();
            }
            else {
                printHorizontalLine();
                System.out.println("\t" + "added: " + input);
                ls[count] = input;
                count++;
                printHorizontalLine();
            }
        }
        scanner.close();

    }
    public static void printHorizontalLine() {
        System.out.println("    ________________________________________________");
    }
}
