import java.time.LocalDate;

public class BeforeCommand extends Command{
    private final LocalDate localDate;
    public BeforeCommand(LocalDate localDate) {
        this.localDate = localDate;
    }
    @Override
    public void execute(Storage storage, UiDisplay uiDisplay, TaskList taskList) throws DookException{
        uiDisplay.printMessage(taskList.getTasksBefore(localDate));
    }
}
