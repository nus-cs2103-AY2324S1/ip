import java.util.ArrayList;
import java.util.Scanner;

public class ChatterBot {
    public static void main(String[] args) {

        String logo = "ChatterBot";
        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userMessage = scanner.nextLine();

            if (userMessage.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userMessage.toLowerCase().equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task t : list) {
                    System.out.println((list.indexOf(t) + 1) + ". "
                            + t.toString());
                }
            } else if (userMessage.startsWith("mark") && isInteger(userMessage.substring(5))) {
                String toMark = userMessage.substring(5);
                list.get(Integer.parseInt(toMark) - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + "[X] " + list.get(Integer.parseInt(toMark) - 1).description);
            } else if (userMessage.startsWith("unmark")) {
                String toUnmark = userMessage.substring(7);
                list.get(Integer.parseInt(toUnmark) - 1).markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[ ] " + list.get(Integer.parseInt(toUnmark) - 1).description);
            } else {
                if (userMessage.startsWith("deadline")) {
                    try {
                        if (userMessage.length() <= 9) {
                            throw new IllegalArgumentException("No task description");
                        }
                        int slashDeadline = userMessage.indexOf("/");
                        String deadlineDescription = userMessage.substring(9, slashDeadline).trim();
                        String deadlineBy = userMessage.substring(slashDeadline + 3).trim();
                        Deadline d = new Deadline(deadlineDescription, deadlineBy);
                        list.add(d);
                        System.out.println("Got it. I've added this task:\n" + d.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("OOPS!!! Invalid input!");
                    }
                } else if (userMessage.startsWith("todo")) {
                    try {
                        if (userMessage.length() <= 5) {
                            throw new IllegalArgumentException("No task description");
                        }
                        Todo td = new Todo(userMessage.substring(5));
                        list.add(td);
                        System.out.println("Got it. I've added this task:\n" + td.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e){
                        System.out.println("OOPS!!! Invalid input!");
                    }
                } else if (userMessage.startsWith("event")) {
                    try {
                        if (userMessage.length() <= 6) {
                            throw new IllegalArgumentException("No task description");
                        }
                        String[] eventSplit = userMessage.split("/");
                        String eventDescription = eventSplit[0].substring(6);
                        String eventTo = eventSplit[1].substring(5);
                        String eventFrom = eventSplit[2].substring(3);
                        Event e = new Event(eventDescription, eventTo, eventFrom);
                        list.add(e);
                        System.out.println("Got it. I've added this task:\n" + e.toString() + "\nNow you have " + list.size() + " tasks in the list.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("OOPS!!! Invalid input!");
                    }
                } else {
                    System.out.println("Unknown command: " + userMessage);
                }
            }
        }
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}