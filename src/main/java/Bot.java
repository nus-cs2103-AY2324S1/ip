public class Bot {
    private final String botName;
    private final TaskList list;
    private final String HORIZONTAL_LINE
            = "    ____________________________________________________________";

    public Bot(String botName) {
        this.botName = botName;
        this.list = new TaskList();
    }

    public void greeting() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Hello! I'm " + this.botName);
        System.out.println("     What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public void markTask(int taskId) {
        this.list.mark(taskId, true);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + this.list.showTask(taskId));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public void unmarkTask(int taskId) {
        this.list.mark(taskId, false);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + this.list.showTask(taskId));
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    private void notifyTaskAdded(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("     Now you have " + this.list.size() +  " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
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

    public void deleteTask(int taskId) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + this.list.deleteTask(taskId));
        System.out.println("     Now you have " + this.list.size() + " tasks in the list.");
        System.out.println(HORIZONTAL_LINE);
    }

    public void showTask() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Here are the tasks in your list:");
        System.out.println(this.list.showList());
        System.out.println(HORIZONTAL_LINE);
        System.out.println();
    }

    public void goodBye() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }
}
