import java.util.Scanner;

public class Sally {
    public static void main(String[] args) {
        System.out.println("Hey! It's Sally here!\n" + "How can I help you today?\n \n");

        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye! See you again next time.");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("My List:");
                    for (int i = 0; i < taskCount; i++) {
                        Task task = tasks[i];
                        System.out.println((i + 1) + ". " + task);
                    }
                } else if (input.startsWith("mark")) {
                    if (input.length() < 6 || !input.contains(" ")) {
                        throw new SallyException("OOPS! Provide a valid task number to mark.");
                    }

                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("OOPS! Provide a valid task number to mark.");
                    } else {
                        Task task = tasks[taskIndex];
                        task.mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(" " + task);
                    }
                } else if (input.startsWith("unmark")) {
                    if (input.length() < 8 || !input.contains(" ")) {
                        throw new SallyException("OOPS! Provide a valid task number to unmark.");
                    }

                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("OOPS! Provide a valid task number to unmark.");
                    } else {
                        Task task = tasks[taskIndex];
                        task.unmark();
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println(" " + task);
                    }
                } else if (input.startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new SallyException("OOPS! The description of a todo cannot be empty.");
                    }

                    tasks[taskCount] = new Todo(input.substring(5));
                    taskCount++;
                    System.out.println("Added to My List: ");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    if (input.length() < 10 || !input.contains(" /by ")) {
                        throw new SallyException("OOPS! The description of a deadline cannot be incomplete, you need a 'by'.");
                    }

                    String[] parts = input.split(" /by ");
                    tasks[taskCount] = new Deadline(parts[0].substring(9), parts[1]);
                    taskCount++;
                    System.out.println("Added to My List: ");
                    System.out.println(" " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    if (input.length() < 7 || !input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new SallyException("OOPS! The description of an event cannot be incomplete, you need a '/from' and '/to'.");
                    }

                    String[] parts = input.split(" /from | /to ");
                    tasks[taskCount] = new Event(parts[0].substring(6), parts[1], parts[2]);
                    taskCount++;
                    System.out.println("Added to My List:");
                    System.out.println("  " + tasks[taskCount - 1]);
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else {
                    System.out.println("Sorry, I do not understand the command :-(");
                }
            } catch (SallyException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
