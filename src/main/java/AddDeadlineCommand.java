public class AddDeadlineCommand extends Command {
    private String description;
    private String time;

    public AddDeadlineCommand(String description, String time) {
        this.description = description;
        this.time = time;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) throws ChatbotException {
        taskManager.addDeadlines(description, time);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
