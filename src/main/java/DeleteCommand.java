import java.util.ArrayList;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Storage storage, ArrayList<Task> tasks) {
        try {
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(taskNumber - 1));
            tasks.remove(this.taskNumber-1);
            Ui.printLine();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("The selected task does not exist.");
            Ui.printLine();
        }
    }
}
