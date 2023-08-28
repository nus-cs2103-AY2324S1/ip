public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int i) {
        index = i;
    }

    @Override
    public void execute(TaskList list) {
        list.mark(index);
        Ui.ui.markPrompt(list.list().get(index));
        Duke.run();
    }
}
