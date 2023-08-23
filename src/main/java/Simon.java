import java.util.ArrayList;
import java.util.Scanner;
public class Simon {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner( System.in );
        ArrayList<Task> tasks = new ArrayList<Task>();
        String space = "____________________________________________________________";
        String nSpace = "\n____________________________________________________________";
        String spaceN = "____________________________________________________________\n";
        String greetings = "Hello! I'm Simon\n" +
                "What can I do for you?\n" +
                space;

        String bye = "Bye. Hope to see you again soon!" + nSpace;

        // Start Program
        System.out.println(spaceN + SimonAscii.toStr());
        System.out.println(greetings);

        while (!inData.equals("bye")) {
            inData = scan.nextLine();
            if (inData.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    String status = tasks.get(i).isDone ? "[X] " : "[ ] ";
                    System.out.println((i + 1) + ". " + status + tasks.get(i));
                }
                System.out.println(space);
            } else if (inData.contains("unmark")) {
                String[] split = inData.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" +
                        "[ ] " + tasks.get(index) + nSpace);
            } else if (inData.contains("mark")) {
                String[] split = inData.split(" ");
                int index = Integer.parseInt(split[1]) - 1;
                tasks.get(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" +
                        "[X] " + tasks.get(index) + nSpace);
            }
            else {
                tasks.add(new Task(inData));
                System.out.println("added: " + inData + nSpace);
            }
        }
        
        System.out.println(bye);
    }
}
