public class Bot {
    private String botName;
    private TaskList list;

    public Bot(String botName) {
        this.botName = botName;
        this.list = new TaskList();
    }

    public void greeting() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm " + this.botName);
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void markTask(int taskIdx) {
        this.list.mark(taskIdx, true);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this.list.showTask(taskIdx));
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void unmarkTask(int taskIdx) {
        this.list.mark(taskIdx, false);
        System.out.println("    ____________________________________________________________");
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this.list.showTask(taskIdx));
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void addTask(String str) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     added: " + str);
        System.out.println("    ____________________________________________________________");
        System.out.println();
        this.list.addTask(new Task(str));
    }

    public void showTask() {
        this.list.showList();
    }

    public void goodBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
