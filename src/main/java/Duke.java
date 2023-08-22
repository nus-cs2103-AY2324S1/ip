import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        printLine();
        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
        printLine();
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            printLine();
            System.out.println("\t" + input);
            printLine();
            input = scanner.nextLine().trim();
        }
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }


    public static void printLine() {
        System.out.print("\t");
        for (int i = 0; i < 80; i++) {
            System.out.print("─");
        }
        System.out.println();
    }
}
