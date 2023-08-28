import java.time.LocalDate;

public class DuringCommand extends Command {
    private final LocalDate localDate;
    public DuringCommand(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        uiDisplay.printMessage(taskList.getTasksDuring(localDate));
    }
}
