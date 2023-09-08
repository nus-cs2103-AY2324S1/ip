
public abstract class AddCommand extends Command {

    private TaskType taskType;

    public AddCommand(TaskType type, String input) {
        super(input);
        this.taskType = type;
    }

}
