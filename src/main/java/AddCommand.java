/**
 * Command that adds the task to the task list.
 */
public class AddCommand implements Command{

    /**
     * Determines the type of task using the latest chat message,
     * Creates a new task with the correct type and adds it to the list.
     *
     * @param tasks The task list to which the new task will be added.
     * @param ui The user interface used to retrieve the last user message.
     * @return {@code false} as the program should continue running.
     */
    @Override
    public boolean execute(TaskList tasks,Ui ui){
        String userInput = ui.getLastMsg();
        Task task = null;
        if (userInput.startsWith("todo")) {
            task = new Todo(userInput.substring(5));
            tasks.add(task);
        } else if (userInput.startsWith("deadline")) {
            String[] words = userInput.substring(9).split("/by", 2);
            task = new Deadline(words[0].trim(), words[1].trim());
            tasks.add(task);
        } else if (userInput.startsWith("event")) {
            String[] words = userInput.substring(6).split("/from", 2);
            String description = words[0].trim();
            String[] time = words[1].split("/to");
            String from = time[0].trim();
            String to = time[1].trim();
            task = new Event(description, from, to);
            tasks.add(task);
        }

        System.out.println("Got it. I've added this task:" + "\n" + task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        return false;
    }
}
