import java.util.stream.Collectors;

public class MarkCommand extends Command {
    private boolean mark;
    public MarkCommand(String desc, boolean mark) {
        setDescription(desc);
        this.mark = mark;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws KieraException {

        try {
            int index = Integer.parseInt(this.getDescription());
            Task t = tasks.getTaskByIndex(index);
            String notify;
            if (mark) {
                notify = "yay, you're one task down!";
                t.markAsDone();
            } else {
                notify = "ok, this task has been marked undone.";
                t.markAsUndone();
            }

            String result = tasks.getTasks().stream()
                    .map(task -> "     " + tasks.indexOf(task) + ". " + task + "\n")
                    .collect(Collectors.joining());
            System.out.println(notify);
            storage.save(tasks);
            ui.showList(result);
        } catch (IndexOutOfBoundsException e) {
            throw new KieraException("your task number is not in the task list!");
        }

    }

    @Override
    boolean isExit() {
        return false;
    }
}
