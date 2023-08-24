import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        final String UNKNOWN_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        final String NAME = "CathyTheChattyCat";
        String lineBreak = "\n_________________________________________\n";
        System.out.println(lineBreak + "Hello! I'm " + NAME);
        System.out.printf("What can I do for you?" + lineBreak);


        String message;
        ArrayList<Task> userList = new ArrayList<>();
        Scanner userInput = new Scanner(System.in);

        do {

            message = userInput.nextLine();
            if (message.equalsIgnoreCase("bye")) break;
            System.out.print(lineBreak + message + lineBreak);
        } while (!message.equalsIgnoreCase("bye"));

        System.out.print(lineBreak + "Hope to see you again soon!" + lineBreak);
        userInput.close();

//        do {
//                message = userInput.nextLine();
//                Task task = null;
//            try {
//                // Listing things out
//                if (message.equalsIgnoreCase("list")) {
//                    System.out.println(lineBreak);
//                    System.out.println("Here are the tasks in your list:");
//                    for (int i = 0; i < userList.size(); i++) {
//                        int index = i + 1;
//                        System.out.println(index + ". " + userList.get(i));
//                    }
//                    System.out.println(lineBreak);
//                }
//                //marking tasks
//                if (message.startsWith("mark")) {
//                    int taskIndex = Integer.parseInt(message.substring(5)) - 1;
//                    if (taskIndex >= 0 && taskIndex < userList.size()) {
//                        userList.get(taskIndex).markDone();
//                        System.out.println(lineBreak + "Nice! I've marked this task as done:");
//                        System.out.println("  " + userList.get(taskIndex) + lineBreak);
//                    } else {
//                        System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
//                    }
//                    //userList.remove(task);
//                }
//                //un marking task
//                if (message.startsWith("unmark")) {
//                    int taskIndex = Integer.parseInt(message.substring(7)) - 1;
//                    if (taskIndex >= 0 && taskIndex < userList.size()) {
//                        userList.get(taskIndex).unmarkDone();
//                        System.out.println(lineBreak + "OK, I've marked this task as not done yet:");
//                        System.out.println("  " + userList.get(taskIndex) + lineBreak);
//                    } else {
//                        System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
//                    }
//                    //userList.remove(task);
//                }
//                //deleting task
//                if (message.startsWith("delete")) {
//                    int taskIndex = Integer.parseInt(message.substring(7)) - 1;
//                    if (taskIndex >= 0 && taskIndex < userList.size()) {
//                        Task removing = userList.get(taskIndex);
//                        System.out.println(lineBreak + "Noted. I've removed this task:");
//                        System.out.println("  " + removing);
//                        userList.remove(removing);
//                        System.out.println("Now you have " + userList.size() + " tasks in the list" + lineBreak);
//                    } else {
//                        System.out.println(lineBreak + "Invalid Task Number" + lineBreak);
//                    }
//                    continue;
//                }
//                //taking in the different task
//                if (!message.equalsIgnoreCase("bye")) {
//                    if (message.equalsIgnoreCase("list")) continue;
//                    if (message.startsWith("mark")) continue;
//                    if (message.startsWith("unmark")) continue;
//                    if (message.startsWith("todo")) {
//                        String info = message.substring(5);
//                        if (info.isEmpty()) {
//                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//                        }
//                        task = new Todo(info);
//                    }
//                    if (message.startsWith("deadline")) {
//                        String info = message.substring(9);
//                        String[] split = info.split("/by");
//                        if (split.length != 2) {
//                            throw new DukeException("☹ OOPS!!! The description of a deadline is invalid.");
//                        }
//                        task = new Deadline(split[0], split[1]);
//                    }
//                    if (message.startsWith("event")) {
//                        String info = message.substring(6);
//                        String[] split = info.split("/from | /to ");
//                        if (split.length != 3) {
//                            System.out.println("hi");
//                            throw new DukeException("☹ OOPS!!! The description of a event is invalid.");
//                        }
//                        task = new Event(split[0], split[1], split[2]);
//                    }
//                    if (task != null) {
//                        System.out.println(lineBreak + "Got it. I've added this task:");
//                        userList.add(task);
//                        int size = userList.size();
//                        System.out.println(task);
//                        System.out.println("Now you have " + size + " tasks in the list." + lineBreak);
//                        continue;
//                    }
//                    throw new DukeException(UNKNOWN_COMMAND);
//                } else {
//                    if (message.equalsIgnoreCase("bye")) break;
//                    throw new DukeException(UNKNOWN_COMMAND);
//                }
//            } catch (DukeException e) {
//                System.out.println(lineBreak + e.getMessage() + lineBreak);
//            } catch (Exception e) {
//                System.out.println(lineBreak + "☹ OOPS!!! The description is invalid :(." + lineBreak);
//            }
//        } while (!message.equalsIgnoreCase("bye"));
//        System.out.print(lineBreak + "Hope to see you again soon!" + lineBreak);
//        userInput.close();
   }
}

