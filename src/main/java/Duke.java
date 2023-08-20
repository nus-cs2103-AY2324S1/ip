import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Task[] list = new Task[100];
        int number = 0;

        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Muddy\n" + "What can I do for you?");

        while (true) {
            String input = in.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else if (input.equalsIgnoreCase("list")) {
                if (number == 0) {
                    System.out.println("List is empty");
                    continue;
                } else {
                    System.out.println("List:");
                    for (int i = 0; i < number; i++) {
                        Task item = list[i];
                        System.out.println((i + 1) + ". " + item.toString());
                    }
                }

            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    list[taskIndex].markAsDone();
                    Task item = list[taskIndex];
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(item.toString());
                } else {
                    System.out.println("You have chosen an invalid task");
                }

            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    list[taskIndex].markAsNotDone();
                    Task item = list[taskIndex];
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(item.toString());
                } else {
                    System.out.println("You have chosen an invalid task");
                }

            } else if (input.startsWith("todo")) {
                String task = input.substring(5).trim();
                ToDo todo = new ToDo(task);
                list [number] = todo;
                number++;
                System.out.println("Got it. I've added this task: \n" + todo.toString());
                System.out.println("Now you have " + (number) + " tasks in the list.");

            } else if (input.startsWith("deadline")) {
                int byIndex = input.indexOf("/by");
                if (byIndex != -1) {
                    String task = input.substring(9, byIndex).trim(); // Task description
                    String date = input.substring(byIndex + 3).trim(); // Deadline day

                    Deadline deadline = new Deadline(task, date);
                    list [number] = deadline;
                    number++;
                    System.out.println("Got it. I've added this task: \n" + deadline.toString());
                    System.out.println("Now you have " + (number) + " tasks in the list.");
                } else {
                    System.out.println("Invalid input format.");
                }

            } else if (input.startsWith("event")) {
                int fromIndex = input.indexOf("/from");
                int toIndex = input.indexOf("/to");
                if (fromIndex != -1 && toIndex != -1) {
                    String task = input.substring(6, fromIndex).trim(); // Task description
                    String startDate = input.substring(fromIndex + 6, toIndex).trim(); // Start date
                    String endDate = input.substring(toIndex + 4).trim(); // End date

                    Events event = new Events(task, startDate, endDate);
                    list [number] = event;
                    number++;
                    System.out.println("Got it. I've added this task: \n" + event.toString());
                    System.out.println("Now you have " + (number) + " tasks in the list.");

                } else {
                    System.out.println("Invalid input format.");
                }
            }
            else {
                System.out.println("Please select the type of task");
            }
        }
        in.close();
    }
}
