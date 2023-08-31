import java.util.ArrayList;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        try {
            tasks.get(taskNumber - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNumber - 1));
            Ui.printLine();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("The selected task does not exist.");
            Ui.printLine();
        }
    }
}
