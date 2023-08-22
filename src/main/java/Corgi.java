import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Corgi {
    private List<String> tasks;

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
            } else {
                this.addTask(userInput);
            }

            System.out.println("------------------------------------------------------------");
        }

        sc.close();
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
        this.tasks.add(task);
        System.out.println("Added: " + task);
    }
}
