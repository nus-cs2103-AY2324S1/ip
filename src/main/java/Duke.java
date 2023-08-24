import java.util.Scanner;

public class Duke {
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

    private static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class Task {
    // Attribute
    private String name;
    private boolean mark = false;

    // Constructor
    public Task(String name) {
        this.name = name;
    }

    // Methods
    public void mark() {
        this.mark = true;
    }

    public void unmark() {
        this.mark = false;
    }

    @Override
    public String toString() {
        return "[" + (this.mark ? "X" : " ") +"] " + this.name;
    }
}
