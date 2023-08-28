import java.time.LocalDate;

public class AfterCommand extends Command {
    private final LocalDate localDate;
    public AfterCommand(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        uiDisplay.printMessage(taskList.getTasksAfter(localDate));
    }
}
