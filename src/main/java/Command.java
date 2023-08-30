public abstract class Command {

    abstract void execute(TaskList taskList, Ui ui) throws DukeException;

}
