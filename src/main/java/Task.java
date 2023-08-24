public abstract class Task {
    protected String description;
    protected boolean completed;

    public static int numberOfTasks = 0;
    public static int numberOfCompletedTasks = 0;


    public Task(String d) {
        this.description = d;
        this.completed = false;
        numberOfTasks++;
    }

    public static Task addTask(String command, String input) {
        Task newTask;
        if (command.equals("todo")) {
            newTask = new ToDo(input);
        } else if (command.equals("deadline")){
            String[] parts = input.split("/by", 2);
            newTask = new Deadline(parts[0], parts[1]);
        } else {
            String[] message = input.split("/from", 2);
            String[] fromto = message[1].split("/to", 2);
            newTask = new Event(message[0], fromto[0], fromto[1]);
        }
        System.out.println(TextFormat.botReply("Gotchu! noted down: \n" +
                TextFormat.indentLineBy(newTask.toString(), 2) +
                "Now you have " +
                numberOfTasks +
                " tasks in the list!"));
        return newTask;
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
    public abstract String toString();
}
