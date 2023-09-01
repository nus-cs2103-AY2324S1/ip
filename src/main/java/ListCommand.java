import java.util.stream.Collectors;

public class ListCommand extends Command {
    public ListCommand() {
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("nothing for you to do yet!");
        }
        String result = tasks.getTasks().stream()
                .map(task -> "     " + tasks.indexOf(task) + ". " + task + "\n")
                .collect(Collectors.joining());
        ui.showList(result);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
