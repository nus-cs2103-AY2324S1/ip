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
                        System.out.println((i + 1) + ". " + "[" + item.getStatusIcon()+ "]" + item.getDescription());
                    }
                }
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    list[taskIndex].markAsDone();
                    Task item = list[taskIndex];
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + item.getStatusIcon() + "] " + item.getDescription());
                } else {
                    System.out.println("You have chosen an invalid task");
                }
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < number) {
                    list[taskIndex].markAsNotDone();
                    Task item = list[taskIndex];
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + item.getStatusIcon() + "] " + item.getDescription());
                } else {
                    System.out.println("You have chosen an invalid task");
                }
            }
            else {
                list[number] = new Task(input);
                number++;
                System.out.println("added: " + input);
            }
        }
        in.close();
    }
}
