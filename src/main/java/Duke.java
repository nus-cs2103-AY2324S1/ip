import java.util.Scanner;

public class Duke {

    public static String horizontalLine = "_".repeat(35) + "\n";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String task = "";

        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
        while (true) {
            task = sc.nextLine();
            if (task.equals("bye")) {
                break;
            }
            if (task.equals("list")) {
                Task.printList();
                continue;
            }
            new Task(task);
        }
        System.out.println(horizontalLine+ "Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
