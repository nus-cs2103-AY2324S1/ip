public interface Command {
    TaskList execute(TaskList tasks, Ui ui, Storage storage) throws Exception;
    boolean isExit();
}
