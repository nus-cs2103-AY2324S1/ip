import java.util.Scanner;
import java.util.ArrayList;
public class Duke {

    Scanner userInput = new Scanner(System.in);
    ArrayList<Task> taskList = new ArrayList<Task>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.start();

    }
    public void line() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    public void start() {
        line();
        System.out.println(" Hello! I'm JARVIS");
        System.out.println("What can I do for you?");
        line();

        while (true) {
            String input = userInput.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                line();
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                Task currTask = taskList.get(taskIndex);
                currTask.taskDone(true);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                Task currTask = taskList.get(taskIndex);
                currTask.taskDone(false);
            } else {
                taskList.add(new Task(input));
                System.out.println("added: " + input);
                line();
            }
        }
    }

    public void list() {
        line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            Task t = taskList.get(i);
            System.out.println(index + "." + t.toString());
        }
    }

    public class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void taskDone(boolean status) {
            this.isDone = status;
            if (status) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println(this.toString());
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
        }
    }
}
