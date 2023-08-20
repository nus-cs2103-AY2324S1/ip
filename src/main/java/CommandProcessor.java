public class CommandProcessor {
    private final TaskList tasks;

    CommandProcessor() {
        this.tasks = new TaskList();
    }
    //processCommand is a method that process the command and prints the relevant output
    void processCommand(String command) {
        if (command.equals("list")) {
            this.tasks.listContent();
            return;
        }

        Task task = new Task(command);
        tasks.add(task);
    }
}
