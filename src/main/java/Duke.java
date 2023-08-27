import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jarvis");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String command = userInput.nextLine();

        while (!command.startsWith("bye")) {

            if (command.startsWith("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                int counter = 0;
                while (counter != list.size()) {
                    counter++;
                    System.out.println(" " + counter + "." + list.get(counter - 1).toString());
                }
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("mark")) {
                list.get(Integer.valueOf(command.split(" ")[1]) - 1).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("  " + list.get(Integer.valueOf(command.split(" ")[1]) - 1).toString());
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("todo")) {
                ToDo newToDo = new ToDo(command.split(" ", 2)[1]);
                list.add(newToDo);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newToDo.toString());
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("deadline")) {
                String deadline = command.split(" /by ", 2)[1];
                String name = command.split(" /by ", 2)[0].split(" ", 2)[1];
                Deadline newDeadline = new Deadline(deadline, name);
                list.add(newDeadline);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newDeadline.toString());
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else if (command.startsWith("event")) {
                String startTime = command.split(" /from ", 2)[1]
                        .split(" /to ", 2)[0];
                String endTime = command.split(" /to ", 2)[1];
                String name = command.split(" /from ", 2)[0].split(" ", 2)[1];
                Event newEvent = new Event(name, startTime, endTime);
                list.add(newEvent);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newEvent.toString());
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
                System.out.println("____________________________________________________________");
            } else {
                list.add(new Task(command));
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + command);
                System.out.println("____________________________________________________________");
            }
            command = userInput.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
