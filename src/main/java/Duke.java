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
            } else {
                tasks[numOfTasks] = new Task(message);
                numOfTasks++;
                System.out.println("    added: " + message);
            }
            System.out.println("    ____________________________________________________________");
            message = scanner.nextLine();
        }
        System.out.println(end);
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

/**
 * Class for task
 */
class Task {
    // Attribute
    private String name;
    private boolean mark = false;

    // Constructor
    
    /**
     * The constructor of Task class
     * 
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    // Methods

    /**
     * Method to mark the task
     */
    public void mark() {
        this.mark = true;
    }

    /**
     * Method to unmark the task
     */
    public void unmark() {
        this.mark = false;
    }

    /**
     * Method to return the string implementation of Task
     * 
     * @return the string implementation of Task
     */
    @Override
    public String toString() {
        return "[" + (this.mark ? "X" : " ") +"] " + this.name;
    }
}
