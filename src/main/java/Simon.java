import java.util.ArrayList;
import java.util.Scanner;
public class Simon {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner( System.in );
        ArrayList<Task> tasks = new ArrayList<Task>();
        String greetings = "____________________________________________________________\n" +
                "Hello! I'm Simon\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";

        String bye = "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        // Start Program
        System.out.println(greetings);
        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            if (inData.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String status = tasks.get(i).isDone ? "[X] " : "[ ] ";
                    System.out.println((i + 1) + ". " + status + tasks.get(i));
                }
                System.out.println("\n____________________________________________________________");
            } else if (inData.contains("unmark")) {
                String[] split = inData.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).markAsUndone();
            }
            else {
                tasks.add(new Task(inData));
                System.out.println("added: " + inData + "\n____________________________________________________________");
            }
        }
        System.out.println(bye);
    }
}
