public class MarkCommand extends Command {
    private int index;
    public static final String COMMAND_WORD = "mark";

    public MarkCommand(String input) throws ChatbotException{
        if (input.length() < 6) throw new ChatbotException("missing info lah.");
        int index = Integer.parseInt(input.substring(5)) - 1;
        if (index >= 0) {
            this.index = index;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException {
            if (this.index >= taskList.size()) {
                throw new ChatbotException("Your task list don't have this number lah.");
            }
            taskList.get(index).markAsDone();
            String content = "Done. You happy?\n" + taskList.get(index).toString();
            storage.save(taskList);
            Ui.printShortSandwich(content);
    }

}
