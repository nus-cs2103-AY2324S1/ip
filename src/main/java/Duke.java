import java.util.Scanner;
public class Duke {
    private String message;
    private Task[] tasks;
    private int count;

    public Duke() {
        this.message = "";
        this.count = 0;
        this.tasks = new Task[100];
    }
    public void greet() {
        this.indent();
        System.out.println("\t \t \t \t Greetings, I am Vision. How may I assist you today?");
        this.indent();
    }

    public void indent() {
        System.out.println("\t \t \t \t_______________________________________________________________");
    }

    public void list() {
        this.indent();
        System.out.println("\t \t \t \t Tasks displayed. Your guidance is requested.");
        for (int i = 0; i < this.count; i++) {
            this.tasks[i].displayTask(i + 1);
        }
        this.indent();
    }

//    public void appendList() {
//        this.tasks[this.count] = new Task(this.message);
//        this.count++;
//    }

    public void mark() {
        try {
            int index = Character.getNumericValue(this.message.charAt(5));
            this.tasks[index - 1].completeTask();
            this.indent();
            System.out.println("\t \t \t \t The following task is marked as complete:");
            this.tasks[index - 1].displayTask(index);
            System.out.println("\t \t \t \t Is there anything else I can assist you with?");
            this.indent();
        } catch (Exception e) {
            this.indent();
            System.out.println("\t \t \t \t Something went wrong. Please try again!");
            this.indent();
        }
    }

    public void unmark() {
        try {
            int index = Character.getNumericValue(this.message.charAt(7));
            this.tasks[index - 1].revertTask();
            this.indent();
            System.out.println("\t \t \t \t The following task is has been unmarked:");
            this.tasks[index - 1].displayTask(index);
            System.out.println("\t \t \t \t Is there anything else I can assist you with?");
            this.indent();
        } catch (Exception e) {
            this.indent();
            System.out.println("\t \t \t \t Something went wrong. Please try again!");
            this.indent();
        }
    }

    public void todo() {
        try {
            String description = this.message.substring(5);
            this.tasks[this.count] = new Todo(description);
            this.indent();
            System.out.println("\t \t \t \t Added the following task to the list.");
            this.tasks[this.count].displayTask(this.count + 1);
            this.count++;
            System.out.println(String.format("\t \t \t \t You currently have %d tasks in your list.", this.count));
            this.indent();
        } catch (Exception e) {
            this.indent();
            System.out.println("\t \t \t \t Something went wrong. Please try again!");
            this.indent();
        }
    }

    public void deadline() {
        try {
            int index = this.message.indexOf("/");
            String description = this.message.substring(9, index - 1);
            String deadline = this.message.substring(index + 1);
            this.tasks[this.count] = new Deadline(description, deadline);
            this.indent();
            System.out.println("\t \t \t \t Added the following task to the list.");
            this.tasks[this.count].displayTask(this.count + 1);
            this.count++;
            System.out.println(String.format("\t \t \t \t You currently have %d tasks in your list.", this.count));
            this.indent();
        } catch (Exception e) {
            this.indent();
            System.out.println("\t \t \t \t Something went wrong. Please try again!");
            this.indent();
        }
    }

    public void event() {
        try {
            int index1 = this.message.indexOf("/");
            int index2 = this.message.indexOf("/", index1 + 1);
            String description = this.message.substring(6, index1 - 1);
            String from = this.message.substring(index1 + 1, index2 - 1);
            String to = this.message.substring(index2 + 1);
            this.tasks[this.count] = new Event(description, from, to);
            this.indent();
            System.out.println("\t \t \t \t Added the following task to the list.");
            this.tasks[this.count].displayTask(this.count + 1);
            this.count++;
            System.out.println(String.format("\t \t \t \t You currently have %d tasks in your list.", this.count));
            this.indent();
        } catch (Exception e) {
            this.indent();
            System.out.println("\t \t \t \t Something went wrong. Please try again!");
            this.indent();
        }
    }

    public void exit() {
        this.indent();
        System.out.println("\t \t \t \t I shall now take my leave. Farewell!");
        this.indent();
    }

    public void interact() {
        Scanner input = new Scanner(System.in);

        while (true) {
            this.updateMessage(input.nextLine());
            if (this.message.equals("bye")) {
                break;
            } else if (this.message.equals("list")) {
                this.list();
            } else if (this.message.startsWith("mark")) {
                this.mark();
            } else if (this.message.startsWith("unmark")) {
                this.unmark();
            } else if (this.message.startsWith("todo")) {
                this.todo();
            } else if (this.message.startsWith("deadline")) {
                this.deadline();
            } else if (this.message.startsWith("event")) {
                this.event();
            } else {
                this.indent();
                System.out.println("\t \t \t \t " + this.message);
                this.indent();
            }
        }
    }

    public void updateMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Duke) {
            Duke d = (Duke) obj;
            return this.message == d.message;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to read input
        Duke bot = new Duke();

        bot.greet();
        bot.interact();
        bot.exit();

        return;
    }
}
