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
        System.out.println("\t \t \t \t Greetings, I am Vision. How may I assist you today? \uD83E\uDD16\uD83D\uDD0D");
        this.indent();
    }

    public void indent() {
        System.out.println("\t \t \t \t_______________________________________________________________");
    }

    public void list() {
        this.indent();
        System.out.println("\t \t \t \t Tasks displayed. Your guidance is requested. \uD83D\uDCCB\uD83E\uDD16");
        for (int i = 0; i < this.count; i++) {
            this.tasks[i].displayTask(i + 1);
        }
        this.indent();
    }

    public void appendList() {
        this.tasks[this.count] = new Task(this.message);
        this.count++;
    }

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

    public void exit() {
        this.indent();
        System.out.println("\t \t \t \t I shall now take my leave. If you require further assistance, \n" +
                "\t \t \t \t do not hesitate to seek my guidance once more. Farewell. \uD83E\uDD16\uD83D\uDC4B");
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
            } else {
                this.appendList();
                this.indent();
                System.out.println("\t \t \t \t Added: " + this.message);
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
