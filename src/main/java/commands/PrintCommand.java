package commands;
import exception.URChatBotException;
import storage.Storage;
import taskList.TaskList;
import ui.Ui;

public class PrintCommand extends Command{
    private String formattedDate;

    public PrintCommand(String formattedDate) {
        super("Print");
        this.formattedDate = formattedDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws URChatBotException {
        int count = 0;
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            if (tasks.getTasks().get(i).toString().contains(formattedDate)) {
                count ++;
                System.out.println(tasks.getTasks().get(i).toString() + "\n");
            }
        }
        if (count <= 1) {
            ui.showPrintMessage(count, formattedDate);
        } else {
            ui.showPrintMessagePlural(count, formattedDate);
        }
    }
}
