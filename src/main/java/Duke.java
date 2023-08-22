import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> items = new ArrayList<>();
        printLine();
        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
        printLine();
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printLine();
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("\t" + String.valueOf(i + 1) + ". " + items.get(i));
                }
                printLine();
            } else if (input.split(" ")[0].equals("mark")) {
                Task item = items.get(Integer.valueOf(input.split(" ")[1]) - 1);
                item.completeTask();
            } else if (input.split(" ")[0].equals("unmark")) {
                Task item = items.get(Integer.valueOf(input.split(" ")[1]) - 1);
                item.undoTask();
            } else {
                printLine();
                System.out.println("\t" + "added: " + input);
                printLine();
                items.add(new ToDos(input));
            }
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
