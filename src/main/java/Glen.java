import java.util.Scanner;
import java.util.ArrayList;
import Task;
import Deadline;
import Event;
import Todo;

public class Glen {
    static final String HORLINE = "_____________________________________________________\n";
    static ArrayList<Task> tasks = new ArrayList<Task>();
    
    public static void main(String[] args) {
        System.out.println(intro());
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String low = input.toLowerCase();
        while (!low.equals("bye")) {
            if (low.equals("list")) {
                System.out.println(lst());
            } else {
                int index = Math.max(0, low.indexOf(" "));
                String firstWord = low.substring(0, index);
                String end = "";
                try {
                    end = input.substring(++index);
                } finally {
                    if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                        int taskIndex = -1;
                        try {
                            taskIndex = Integer.valueOf(end) - 1;
                        } catch (Exception NumberFormatException) {}
                        if (taskIndex >= 0 && taskIndex < tasks.size()) {
                            toggle(firstWord, taskIndex);
                        } else {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Please select a valid item to mark/unmark.\n" + HORLINE);
                        }   
                    } else if (firstWord.equals("deadline")) {
                        try {
                            int separatorIndex = end.indexOf("/by");
        
                            String string1 = end.substring(0, separatorIndex).trim();
                            String string2 = end.substring(separatorIndex + 3).trim();

                            System.out.println(addDeadline(string1, string2));
                        } catch (Exception StringIndexOutOfBoundsException) {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Your request needs to be formatted as:\ndeadline <task name> /by <time>\n" + HORLINE);
                        }
                    } else if (firstWord.equals("event")) {
                        try {
                            int fromIndex = end.indexOf("/from");
                            int toIndex = end.indexOf("/to");
            
                            String string1 = end.substring(0, fromIndex).trim();
                            String string2 = end.substring(fromIndex + 6, toIndex).trim();
                            String string3 = end.substring(toIndex + 4).trim();

                            System.out.println(addEvent(string1, string2, string3));
                        } catch (Exception StringIndexOutOfBoundsException) {
                            System.out.println(HORLINE + "\u2639 OOPS!!! Your request needs to be formatted as:\nevent <event name> /from <start time> /to <end time>\n" + HORLINE);
                        }
                    } else if (firstWord.equals("todo")) {
                        String trimmed = end.trim();
                        if (trimmed.equals("")) {
                            System.out.println(HORLINE + "\u2639 OOPS!!! The description of a todo cannot be empty.\n" + HORLINE);
                        } else {
                            System.out.println(addTodo(trimmed));
                        }
                    } else {
                        System.out.println(HORLINE + "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n" + HORLINE);
                    }
                }
            }
            input = scan.nextLine();
            low = input.toLowerCase();
        }
        System.out.println(exit());
        scan.close();
    }

    static String intro() {
        String logo = "  _____ _            \n" +
                      " / ____| |           \n" +
                      "| |  __| | ___ _ __  \n" +
                      "| | |_ | |/ _ \\  _ \\ \n" +
                      "| |__| | |  __/ | | |\n" +
                      " \\_____|_|\\___|_| |_|\n\n";
        String introText = "Hello, I'm Glen!\n" + 
                           "What can I do for you?\n";
        return HORLINE + logo + introText + HORLINE;
    }

    static String addDeadline(String inp, String by) {
        Task newTask = new Deadline(inp, by);
        tasks.add(newTask);
        return HORLINE + "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    static String addEvent(String inp, String from, String to) {
        Task newTask = new Event(inp, from, to);
        tasks.add(newTask);
        return HORLINE + "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    static String addTodo(String inp) {
        Task newTask = new Todo(inp);
        tasks.add(newTask);
        return HORLINE + "Got it. I've added this task:\n  " + newTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.\n" + HORLINE;
    }

    static void toggle(String requestType, int taskIndex) {
        if (taskIndex > tasks.size()) {
            System.out.println("Invalid item selected. Please try again.");
        } else {
            Task task = tasks.get(taskIndex);
            if (requestType.equals("mark") && task.getStatusIcon().equals("[X] ")) {
                System.out.println(HORLINE + "Task is currently marked. Did you mean to unmark the task?\n" + HORLINE);
            } else if (requestType.equals("unmark") && task.getStatusIcon().equals("[ ] ")) {
                System.out.println(HORLINE + "Task is currently unmarked. Did you mean to mark the task?\n" + HORLINE);
            } else {
                task.toggle();
                if (task.getStatusIcon().equals("[X] ")) {
                    System.out.println(HORLINE + "Nice! I've marked this task as done:");
                    System.out.println("  [X] " + task.getDescription() + "\n" + HORLINE);
                } else {
                    System.out.println(HORLINE + "OK, I've marked this task as not done yet:");
                    System.out.println("  [ ] " + task.getDescription() + "\n" + HORLINE);
                }
            }
        }
    }

    static String lst() {
        String temp = "Here are the tasks in your list:\n";
        for(int i = 0; i < tasks.size(); i++) {
            Task tempTask = tasks.get(i);
            temp += String.valueOf(i + 1) + "." + tempTask.toString() + "\n";
        }  
        return HORLINE + temp + HORLINE;
    }

    static String exit() {
        return HORLINE + "Bye. Hope to see you again soon!\n" + HORLINE;
    }
}
