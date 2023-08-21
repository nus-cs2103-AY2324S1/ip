import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Mil {
    public static void main(String[] args) {
        String logo = " ____     ____    _    _\n"
                + "|     \\__/    |  | |  | |\n"
                + "|  | \\ _ / |  |  | |  | |\n"
                + "|  |       |  |  | |  | |____\n"
                + "|__|       |__|  |_|  |______|\n";
        String horLine = "__________________________________________________________________________";
        String indentation = "     ";
        System.out.println(logo);
        System.out.println(indentation + horLine);
        System.out.println(indentation + "Hi there, I'm Mil - your personal chatbot.\n     How can I help you today?");
        System.out.println(indentation + horLine);
        Scanner scanner = new Scanner(System.in);
        String input;

        List<Task> taskList = new ArrayList<>();

        while(scanner.hasNext()) {
            input = scanner.nextLine();
            if(input.equals("bye")) {
                System.out.println(indentation + horLine);
                System.out.println(indentation + "Have a nice day and see you again soon!");
                System.out.println(indentation + horLine);
                break;
            } else if(input.equals("list")) {
                System.out.println(indentation + horLine);
                System.out.println(indentation+ "Here are the tasks in your list:");
                int i = 1;
                for (Task task : taskList) {
                    System.out.println(String.format("%s%d.%s",
                            indentation, i, task.toString()));
                    i++;
                }
                System.out.println(indentation + horLine);
                continue;
            } else if(input.startsWith("mark") || input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index < 0 || index >= taskList.size()) {
                    System.out.println(indentation + horLine);
                    System.out.println("Invalid input, please try again.");
                    System.out.println(indentation + horLine);
                    continue;
                }
                Task task = taskList.get(index);
                if(input.startsWith("mark")) {
                    task.markAsDone();
                    System.out.println(indentation + horLine);
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + task.toString());
                    System.out.println(indentation + horLine);
                    continue;
                } else {
                    task.markAsUndone();
                    System.out.println(indentation + horLine);
                    System.out.println(indentation + "OK, I've marked this task as not done yet:");
                    System.out.println(indentation  + task.toString());
                    System.out.println(indentation + horLine);
                    continue;
                }
            } else if(input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                Task task;
                if(input.startsWith("todo")) {
                    task = new Todo(input.substring(5));
                } else if(input.startsWith("deadline")) {
                    task = new Deadline(input.split("/")[0].substring(9),
                            input.split("/")[1].substring(3));
                } else {
                    task = new Event(input.split("/")[0].substring(6),
                            input.split("/")[1].substring(5),
                            input.split("/")[2].substring(3));
                }
                taskList.add(task);
                System.out.println(indentation + horLine);
                System.out.println(indentation + "Got it. I've added this task:");
                System.out.println(indentation + "  " + task.toString());
                System.out.println(indentation + "Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(indentation + horLine);
            }
        }


    }
}
