import java.util.Map;

abstract class Command {
    Map<String, String> parameterMap;

    protected Command() {}

    public void addParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
