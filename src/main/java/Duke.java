import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        int taskCount = 0;
        Task[] tasks =  new Task[100];
        System.out.println("Hello! I'm Chatty\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        while (isRunning) {
            try {
                String userInput = scanner.nextLine();
                System.out.println("____________________________________________________________");
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    isRunning = false;
                } else if (userInput.equals("list")){
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(i + 1 + "." + tasks[i]);
                    }
                } else if (userInput.startsWith("mark ")) {
                    System.out.println("Nice! I've marked this task as done:");
                    int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
                    tasks[taskIndex].switchCheck();
                    System.out.println(tasks[taskIndex].toString());
                } else if (userInput.startsWith("unmark ")) {
                    System.out.println("OK, I've marked this task as not done yet:");
                    int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
                    tasks[taskIndex].switchCheck();
                    System.out.println(tasks[taskIndex].toString());
                } else if (userInput.startsWith("todo ")) {
                    if (userInput.length() <= 5) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newToDo = new Todo(userInput.substring(5));
                    System.out.println("Got it. I've added this task:");
                    tasks[taskCount] = new Todo(userInput);
                    taskCount++;
                    System.out.println(newToDo.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("deadline ")) {
                    System.out.println("Got it. I've added this task:");
                    String description = userInput.substring(9, userInput.indexOf("/by")).trim();
                    String by = userInput.substring(userInput.indexOf("/by") + 4).trim();
                    Task newDeadline = new Deadline(description, by);
                    tasks[taskCount] = newDeadline;
                    taskCount++;
                    System.out.println(newDeadline.toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list.");
                } else if (userInput.startsWith("event ")) {
                    System.out.println("Got it. I've added this task:");
                    String description = userInput.substring(6, userInput.indexOf("/from")).trim();
                    String from = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to")).trim();
                    String to = userInput.substring(userInput.indexOf("/to") + 4).trim();
                    Task newEvent = new Event(description, from, to);
                    tasks[taskCount] = newEvent;
                    taskCount++;
                    System.out.println(newEvent.toString());
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                // Handle Duke-specific exceptions with meaningful error messages
                System.out.println(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
