public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Ui.reply("Here are your current tasks!");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
    }
}
