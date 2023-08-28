public class ListCommand extends Command{

    @Override
    public void execute(TaskList list) {
        Ui.ui.listPrompt(list);
        Duke.run();
    }
}
