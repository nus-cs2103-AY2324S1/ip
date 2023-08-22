import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tasks.Task;

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

        while(true) {
            String userInput = sc.nextLine().trim();

            if (userInput.toLowerCase().equals("bye")) {
                System.out.println("Bye, take care and see you soon! *tail wags*");
                break;
            } else if (userInput.toLowerCase().equals("list")) {
                this.displayTasks();
            } else if (userInput.startsWith("mark ")) {
                this.markTaskAsDone(userInput.substring(5));
            } else if (userInput.startsWith("unmark ")) {
                this.markTaskAsNotDone(userInput.substring(7));
            } else {
                this.addTask(userInput);
            }

            System.out.println("------------------------------------------------------------");
        }

        sc.close();
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
     * Add a new task to the list of tasks.
     * 
     * @param task Task to be added
     */
    private void addTask(String task) {
        this.tasks.add(new Task(task));
        System.out.println("Added: " + task);
    }
}
