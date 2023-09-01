public class TodoCommand extends Command {
    private String taskName;
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String input) throws ChatbotException {
        if (input.length() < 6) throw new ChatbotException("missing info lah.");
        String taskName = input.substring(5);
        if (taskName.trim().length() > 0) {
            this.taskName = taskName;
        } else {
            throw new ChatbotException("missing info lah.");
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
        Todo temp = new Todo(this.taskName);
        taskList.add(temp);
        storage.save(taskList);
        Ui.printAddedTask(temp, taskList.size());
    }
}
