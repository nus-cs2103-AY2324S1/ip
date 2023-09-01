import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
public class Duke {

    private enum Commands {
        todo, deadline, event, mark, unmark, list, delete, bye;
    }
    public static void main(String[] args) {
        try {
            File myChatBot = new File("./data/gideon.txt");
            if (myChatBot.createNewFile()) {
                System.out.println("File created: " + myChatBot.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Gideon");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
                }
            } else if (userInput.startsWith("mark")) {
                try {
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    Task markedTask = tasks.get(id - 1);
                    markedTask.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(markedTask.getDescription());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("To mark a task you need to include the index");
                }
            } else if (userInput.startsWith("unmark")) {
                try {
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    Task unmarkedTask = tasks.get(id - 1);
                    unmarkedTask.markAsUnDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(unmarkedTask.getDescription());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("To unmark a task you need to include the index");
                }
            } else if (userInput.startsWith("unmark")) {
                int id = Integer.parseInt(userInput.split(" ")[1]);
                Task unmarkedTask = tasks.get(id - 1);
                unmarkedTask.markAsUnDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(unmarkedTask.getDescription());
            } else if (userInput.startsWith("todo")) {
                try {
                    String description = userInput.substring(5);
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (userInput.startsWith("deadline")) {
                try {
                    int index = userInput.indexOf("/");
                    String description = userInput.substring(9, index - 1);
                    String date = userInput.substring(index + 3);
                    Deadline deadline = new Deadline(description, date);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (userInput.startsWith("event")) {
                try {
                    int indexOfFrom = userInput.indexOf("/");
                    String description = userInput.substring( 6, indexOfFrom - 1);
                    String duration = userInput.substring(indexOfFrom + 4);
                    int indexOfTo = duration.indexOf("/");
                    String from = duration.substring(1, indexOfTo - 1);
                    String to = duration.substring(indexOfTo + 1);
                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event.getDescription());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                }
            } else if (userInput.startsWith("delete")) {
                try {
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    Task taskToBeRemoved = tasks.get(id - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskToBeRemoved.getDescription());
                    tasks.remove(taskToBeRemoved);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("To delete a task you have to include the index");
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
    }
}
