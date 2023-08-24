import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "_".repeat(35) + "\n";
        Scanner sc = new Scanner(System.in);
        String task = "";
        System.out.println("Hello! I'm Bot\n" + "What can I do for you?\n" + horizontalLine);
        while (!task.equals("bye")) {
            task = sc.nextLine();
            System.out.println(horizontalLine + task + "\n" + horizontalLine);
        }
        System.out.println("Bye. Hope to see you again soon!\n" + horizontalLine);
    }
}
