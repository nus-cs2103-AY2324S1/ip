import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

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

            if (userInput.equals("")) {
                continue;
            }

            System.out.println("------------------------------------------------------------");

            String[] parts = userInput.split(" ", 2);
            
            if (parts.length == 1) {
                if (userInput.toLowerCase().equals("bye")) {
                    System.out.println("Bye, take care and see you soon! *tail wags*");
                    break;
                } else if (userInput.toLowerCase().equals("list")) {
                    this.displayTasks();
                    System.out.println("------------------------------------------------------------");
                    continue;
                }
            }

            String cmd = parts[0];
            String details = parts[1];

            if (cmd.equals("mark")) {
                this.markTaskAsDone(details);
            } else if (cmd.equals("unmark")) {
                this.markTaskAsNotDone(details);
            } else if (cmd.equals("todo")){
                this.addTask(details,"todo");
            } else if (cmd.equals("deadline")){
                this.addTask(details,"deadline");
            } else if (cmd.equals("event")){
                this.addTask(details,"event");
            } else {
                this.addTask(details);
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

    private void addTask(String taskInfo, String type) {
        Task newTask = null;

        switch (type) {
            case "todo":
                newTask = new ToDo(taskInfo);
                break;
            case "deadline":
                String[] deadlineInfos = taskInfo.split(" /by ");
                String deadlineDesc = deadlineInfos[0];
                String by = deadlineInfos[1];
                newTask = new Deadline(deadlineDesc, by);
                break;
            case "event":
                String[] eventInfos = taskInfo.split(" /from ");
                String eventDesc = eventInfos[0];
                String[] eventDuration = eventInfos[1].split(" /to ");
                String from = eventDuration[0];
                String to = eventDuration[1];
                newTask = new Event(eventDesc, from, to);
                break;
        } 

        if (newTask != null) {
            this.tasks.add(newTask);
            System.out.println("Got it. I've added this task:\n" + 
                " " + newTask + "\nNow you have " + this.tasks.size() + " tasks in the list.");
        }
    }
}
