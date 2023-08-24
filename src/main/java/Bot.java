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

    private void notifyTaskAdded(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("     Now you have " + this.list.size() +  " tasks in the list.");
        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    public void addTask(String str) {
        Task task = new Todo(str);
        this.list.addTask(task);
        notifyTaskAdded(task);
    }

    public void addTask(String str, String deadline) {
        Task task = new Deadline(str, deadline);
        this.list.addTask(task);
        notifyTaskAdded(task);
    }

    public void addTask(String str, String startTime, String endTime) {
        Task task = new Event(str, startTime, endTime);
        this.list.addTask(task);
        notifyTaskAdded(task);
    }

    public void deleteTask(int taskIdx) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + this.list.deleteTask(taskIdx));
        System.out.println("     Now you have " + this.list.size() + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
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
