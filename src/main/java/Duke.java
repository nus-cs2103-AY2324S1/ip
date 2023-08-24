import java.util.Scanner;

/**
 * Chatbot class
 */
public class Duke {
    /**
     * The main method
     * 
     * @param args the input argument
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int numOfTasks = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "    ____________________________________________________________\n" +
                       "    Hello! I'm Not A ChatBot\n" + 
                       "    What can I do for you?\n" +
                       "    ____________________________________________________________";
        String end = "    ____________________________________________________________\n" +
                     "    Bye. Hope to see you again soon!\n" +
                     "    ____________________________________________________________";
        System.out.println(intro);
        String message = scanner.nextLine();
        while(!message.equals("bye")) {
            try{
                System.out.println("    ____________________________________________________________");
                if(message.equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    for(int i = 0; i < numOfTasks; i++) {
                        System.out.println("    " + (i + 1) + ". " + tasks[i]);
                    }
                } else if(message.split(" ")[0].equals("mark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= numOfTasks && Integer.parseInt(message.split(" ")[1]) > 0) {
                    tasks[Integer.parseInt(message.split(" ")[1]) - 1].mark();
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("    " + tasks[Integer.parseInt(message.split(" ")[1]) - 1]);
                } else if(message.split(" ")[0].equals("unmark") && message.split(" ").length == 2 && isInt(message.split(" ")[1]) 
                        && Integer.parseInt(message.split(" ")[1]) <= numOfTasks && Integer.parseInt(message.split(" ")[1]) > 0) {
                    tasks[Integer.parseInt(message.split(" ")[1]) - 1].unmark();
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("    " + tasks[Integer.parseInt(message.split(" ")[1]) - 1]);
                } else if(ToDo.isToDo(message)) {
                    tasks[numOfTasks] = new ToDo(message.substring(5));
                    numOfTasks++;
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks[numOfTasks - 1]);
                    System.out.println("    Now you have " + numOfTasks + " tasks in the list.");
                } else if(Deadline.isDeadline(message)) {
                    String name = message.substring(9, message.indexOf("/by "));
                    String deadline = message.substring(message.indexOf("/by ") + 4);
                    tasks[numOfTasks] = new Deadline(name, deadline);
                    numOfTasks++;
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks[numOfTasks - 1]);
                    System.out.println("    Now you have " + numOfTasks + " tasks in the list.");
                } else if(Event.isEvent(message)) {
                    String name = message.substring(6, message.indexOf("/from "));
                    String afterFrom = message.substring(message.indexOf("/from ") + 5);
                    String start = afterFrom.substring(0, afterFrom.indexOf("/to "));
                    String endTime = afterFrom.substring(afterFrom.indexOf("/from ") + 6);
                    tasks[numOfTasks] = new Event(name, start, endTime);
                    numOfTasks++;
                    System.out.println("    Got it. I've added this task:");
                    System.out.println("    " + tasks[numOfTasks - 1]);
                    System.out.println("    Now you have " + numOfTasks + " tasks in the list.");
                } else {
                    throw new InvalidCommandException();
                }
            } catch(InvalidToDoException e) {
                System.out.println("    " + e.getMessage());
            } catch(InvalidEventException e) {
                System.out.println("    " + e.getMessage());
            } catch(InvalidDeadlineException e) {
                System.out.println("    " + e.getMessage());
            } catch(InvalidCommandException e) {
                System.out.println("    " + e.getMessage());
            }
            System.out.println("    ____________________________________________________________");
            message = scanner.nextLine();
        }
        System.out.println(end);
        scanner.close();
    }

    /**
     * Merthod to check whether a string is integer
     * 
     * @param str the string that wanted to be checked
     * @return whether the string is integer
     */
    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}