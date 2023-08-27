import java.time.LocalDate;

public class DateSearchCommand implements Command{
    private final LocalDate date;

    DateSearchCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception{
        ui.showMessage(" These are events happening on that day\n");
        String result = tasks.dateSearch(date);
        ui.showMessage(result);
        return tasks;
    }
}
