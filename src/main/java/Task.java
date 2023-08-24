public class Task {
    private int id;
    private String description;
    private boolean completed;

    public static int numberOfTasks = 0;


    public Task(int i, String d) {
        this.id = i;
        this.description = d;
        this.completed = false;
        numberOfTasks++;
    }

    public static void addTask(Task task) {
        System.out.println(TextFormat.botReply("gotchu! noted down... "
            + task.description));
    }

    public void markDone() {
        this.completed = true;
        System.out.println(TextFormat.botReply("Yay! One step closer to playing with me!\n"
            + this.toString()));
    }

    public void markNotDone() {
        this.completed = false;
        System.out.println(TextFormat.botReply("Oh no... what happened :(\n"
                + this.toString()));
    }


    @Override
    public String toString() {
        String marker = "[ ]";
        if (completed) marker = "[X]";
        return this.id + "." + marker + " " + this.description + "\n";
    }
}
