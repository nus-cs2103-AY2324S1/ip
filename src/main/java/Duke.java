import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] data = new Task[100];
        int index = 0;

        String logo =
                ".______     ______   .___________.\n" +
                        "|   _  \\   /  __  \\  |           |\n" +
                        "|  |_)  | |  |  |  | `---|  |----`\n" +
                        "|   _  <  |  |  |  |     |  |     \n" +
                        "|  |_)  | |  `--'  |     |  |     \n" +
                        "|______/   \\______/      |__|     \n";

        System.out.println("_________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("_________________________________________");

        while (true) {
            String input = scanner.nextLine();
            System.out.println("_________________________________________");
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++) {
                    System.out.println((i + 1) + "." + data[i]);
                }
            } else if (input.contains("todo")) {
                String subInput = input.substring(5);
                ToDo t = new ToDo(subInput);
                data[index] = t;
                index++;
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + (index) + " tasks in the list.");

            } else if (input.contains("deadline")) {
                String[] split = input.split(" /by ", 2);
                Deadline t = new Deadline(split[0].substring(9), split[1]);
                data[index] = t;
                index++;
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + (index) + " tasks in the list.");
            } else if (input.contains("event")) {
                String[] split = input.split(" /from ", 2);
                String description = split[0].substring(6);
                String[] duration = split[1].split(" /to ", 2);
                Event t = new Event(description, duration[0], duration[1]);
                data[index] = t;
                index++;
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + (index) + " tasks in the list.");
            } else if (input.contains("unmark")) {
                String subInput = input.substring(7);
                int targetIndex = Integer.parseInt(subInput);
                System.out.println("Ok, I've marked this task as not done yet:");
                Task marked = data[targetIndex - 1];
                marked.markAsUndone();
                System.out.println(marked);
            } else if (input.contains("mark")) {
                String subInput = input.substring(5);
                int targetIndex = Integer.parseInt(subInput);
                System.out.println("Nice! I've marked this task as done:");
                Task marked = data[targetIndex - 1];
                marked.markAsDone();
                System.out.println(marked);
            } else {
                System.out.println(input + "is invalid");
            }
            System.out.println("_________________________________________");
        }
    }
}
