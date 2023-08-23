import java.util.ArrayList;
import java.util.Scanner;
public class Simon {
    public static void main(String[] args) {
        String inData = "";
        Scanner scan = new Scanner( System.in );
        ArrayList<Task> tasks = new ArrayList<>();
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
            } else if (inData.contains("todo")) {
                String name = inData.split("todo ")[1];
                ToDo todo = new ToDo (name);
                tasks.add(todo);
                System.out.println(spaceN + "Got it. I've added this task:\n" +
                        todo + String.format("\nNow you have %d %s in the list.",
                        tasks.size(), tasks.size() > 1 ? "tasks" : "task") + nSpace);
            } else if (inData.contains("deadline")) {
                String name = inData.split("deadline ")[1].split(" /by ")[0];
                String endDate = inData.split("deadline ")[1].split(" /by ")[1];
                Deadline deadline = new Deadline(name, endDate);
                tasks.add(deadline);
                System.out.println(spaceN + "Got it. I've added this task:\n" +
                        deadline + String.format("\nNow you have %d %s in the list.",
                        tasks.size(), tasks.size() > 1 ? "tasks" : "task") + nSpace);
            } else if (inData.contains("event")) {
                String name = inData.split("event ")[1].split(" /from ")[0];
                String startDate = inData.split("event ")[1].split(" /from ")[1].split(" /to ")[0];
                String endDate = inData.split("event ")[1].split(" /from ")[1].split(" /to ")[1];
                Event event = new Event(name, startDate, endDate);
                tasks.add(event);
                System.out.println(spaceN + "Got it. I've added this task:\n" +
                        event + String.format("\nNow you have %d %s in the list.",
                        tasks.size(), tasks.size() > 1 ? "tasks" : "task") + nSpace);
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
