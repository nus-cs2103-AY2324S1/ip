public class Task {
    private int id;
    private String description;
    private boolean completed;

    public static int numberOfTasks = 0;
    public static int numberOfCompletedTasks = 0;


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
        if (this.completed) {
            System.out.println(TextFormat.botReply("That was done already...\n" +
                    "are you sure you wanted to mark that?\n"
                    + this.toString()));
        } else {
            this.completed = true;
            numberOfCompletedTasks++;
            System.out.println(TextFormat.botReply("Yay! One step closer to playing with me!\n"
                    + this.toString()));
        }
    }

    public void markNotDone() {
        if (!this.completed) {
            System.out.println(TextFormat.botReply("Don't worry it's still not done\n" +
                    "What are you doing? Let's get it done now!\n"
                    + this.toString()));
        } else {
            this.completed = false;
            numberOfCompletedTasks--;
            System.out.println(TextFormat.botReply("Oh no... what happened :(\n"
                    + this.toString()));
        }
    }


    @Override
    public String toString() {
        String marker = "[ ]";
        if (completed) marker = "[X]";
        return this.id + "." + marker + " " + this.description + "\n";
    }
}
