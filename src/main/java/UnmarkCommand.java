public class UnmarkCommand extends Command{

    private int index;
    public UnmarkCommand (int i) {
        index = i;
    }

    @Override
    public void execute(TaskList list) {
        list.unmark(index);
        Ui.ui.unmarkPrompt(list.list().get(index));
        Duke.run();
    }
}
