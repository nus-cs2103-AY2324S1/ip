import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OnCommand extends Command{

    private LocalDate localDate;

    public OnCommand(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

        ArrayList<Task> taskArrayList = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i) instanceof Deadline) {
                if (localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(((Deadline) tasks.getTask(i)).getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                    taskArrayList.add(tasks.getTask(i));
                }
            } else if (tasks.getTask(i) instanceof Event) {
                if (localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(((Event) tasks.getTask(i)).getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) || localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).equals(((Event) tasks.getTask(i)).getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
                    taskArrayList.add(tasks.getTask(i));
                }
            }

        }


        ui.showOnDate(taskArrayList);

    }
}
