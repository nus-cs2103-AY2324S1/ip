import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        ArrayList<Task> items = new ArrayList<>();
        FunnyList items = new FunnyList();
        printLine();
        System.out.println("\tHello! I'm FUNNY.\n\tWhat can I do for you?");
        printLine();
        String input = scanner.nextLine().trim();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
//                printLine();
//                for (int i = 0; i < items.size(); i++) {
//                    System.out.println("\t" + String.valueOf(i + 1) + ". " + items.get(i));
//                }
//                printLine();
                items.displayList();
            } else {
                String command = input.split(" ")[0];
                if (command.equals("mark")) {
                    Task item = items.get(Integer.valueOf(input.split(" ")[1]) - 1);
                    item.completeTask();
                } else if (command.equals("unmark")) {
                    Task item = items.get(Integer.valueOf(input.split(" ")[1]) - 1);
                    item.undoTask();
                } else if (command.equals("todo")) {
                    items.add(new ToDo(input.split(" ", 2)[1]));
                } else if (command.equals("deadline")) {
                    String[] data = input.split(" /by ");
                    items.add(new Deadline(data[0].split(" ", 2)[1], data[1]));
                } else if (command.equals("event")) {
                    String[] data = input.split(" /from ");
                    String[] period = data[1].split( "/to ");
                    items.add(new Event(data[0].split(" ", 2)[1], period));
                }
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
