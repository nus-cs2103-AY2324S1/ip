import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "_".repeat(35) + "\n";
        Scanner sc = new Scanner(System.in);
        String task = "";
        ArrayList<String> arr = new ArrayList<>();
        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
        while (!task.equals("bye")) {
            task = sc.nextLine();
            arr.add(task);
            System.out.println(horizontalLine + task + "\n" + horizontalLine);
        }
        System.out.println("Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
