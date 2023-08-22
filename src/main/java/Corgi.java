import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;
import commands.CommandType;
import commands.InvalidCommandException;

public class Corgi {
    private List<Task> tasks;

    public static void main(String[] args) {
        Corgi bot = new Corgi();
        bot.start();
    }

    /**
     * Constructs new Corgi chatbot with an empty task list.
     */
    public Corgi() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Starts the chatbot - Corgi.
     */
    public void start() {
        String logo = "  ____ ___  ____   ____ ___\n"
                + " / ___/ _ \\|  _ \\ / ___|_ _|\n"
                + "| |  | | | | |_) | |  _ | |\n"
                + "| |__| |_| |  _ <| |_| || |\n"
                + " \\____\\___/|_| \\_\\\\____|___|\n";
        System.out.println(logo);
        System.out.println("Woof! I'm Corgi!");
        System.out.println("What can I do for you, hooman?\n");

        Scanner sc = new Scanner(System.in);

        loop: while(true) {
            String userInput = sc.nextLine().trim();

            if (userInput.equals("")) {
                continue;
            }

            System.out.println("------------------------------------------------------------");

            String[] inputParts = userInput.split(" ", 2);
            String cmdStr = inputParts[0];

            try {
                CommandType cmd = CommandType.getCommandType(cmdStr);

                switch (cmd) {
                    case BYE:
                        System.out.println("Bye, take care and see you soon! *tail wags*");
                        break loop;
                    case LIST:
                        this.displayTasks();
                        System.out.println("------------------------------------------------------------");
                        break;
                    case MARK:
                        markTaskAsDone(inputParts[1]);
                        break;
                    case UNMARK:
                        markTaskAsNotDone(inputParts[1]);
                        break;
                    case TODO:
                        addToDo(inputParts[1]);
                        break;
                    case DEADLINE:
                        addDeadline(inputParts[1]);
                        break;
                    case EVENT:
                        addEvent(inputParts[1]);
                        break;
                }
            } catch (InvalidCommandException e) {
                this.printException("Can't believe you're asking that! Grrr, what do you want now?");
            }

            System.out.println("------------------------------------------------------------");
        }

        sc.close();
    }

    /**
    * Prints an corgi-themed error message.
    *
    * @param msg The error message to display.
    */
    private void printException(String msg) {
        System.out.println("Woof?! 🤬 \n" + msg);
    }

    /**
     * Mark task as done.
     * 
     * @param indexStr Target task index.
     */
    private void markTaskAsDone(String indexStr) {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= 0 && index < this.tasks.size()) {
            Task target = tasks.get(index);
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + target);
        } else {
            System.out.println("Invalid task number!");
        }
    }

    /**
     * Mark task as not done.
     * 
     * @param indexStr Target task index.
     */
    private void markTaskAsNotDone(String indexStr) {
        int index = Integer.parseInt(indexStr) - 1;
        if (index >= 0 && index < this.tasks.size()) {
            Task target = tasks.get(index);
            target.markAsNotDone();
            System.out.println("OK, I've marked this task as not done yet:\n" + target);
        } else {
            System.out.println("Invalid task number!");
        }
    }

    /**
     * Display the list of tasks.
     */
    private void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i+1) + ") " + tasks.get(i));
            }
        }
    }

     /**
      * Add a new ToDo to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the todo
      */
    private void addToDo(String taskInfo) {
        Task newTask = new ToDo(taskInfo);

        this.tasks.add(newTask);
        System.out.println("Got it. I've added this ToDo:\n" + 
            " " + newTask + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
      * Add a new Deadline to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the deadline, including description and date/time details
      */
    private void addDeadline(String taskInfo) {
        String[] deadlineInfos = taskInfo.split(" /by ");
        String deadlineDesc = deadlineInfos[0];
        String by = deadlineInfos[1];

        Task newTask = new Deadline(deadlineDesc, by);

        this.tasks.add(newTask);

        System.out.println("Got it. I've added this deadline:\n" + 
            " " + newTask + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }

    /**
      * Add a new Event to the list of tasks based on the provided task type.
      *
      * @param taskInfo Information about the event, including description and date/time details
      */
    private void addEvent(String taskInfo) {
        String[] eventInfos = taskInfo.split(" /from ");
        String eventDesc = eventInfos[0];
        String[] eventDuration = eventInfos[1].split(" /to ");
        String from = eventDuration[0];
        String to = eventDuration[1];

        Task newTask = new Event(eventDesc, from, to);

        this.tasks.add(newTask);
        System.out.println("Got it. I've added this event:\n" + 
            " " + newTask + "\nNow you have " + this.tasks.size() + " tasks in the list.");
    }
}
