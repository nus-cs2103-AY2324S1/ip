import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        try {
            tasks.get(taskNumber - 1).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks.get(taskNumber - 1));
            Ui.printLine();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("The selected task does not exist");
            Ui.printLine();
        }
    }
}
