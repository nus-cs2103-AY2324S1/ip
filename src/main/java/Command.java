public interface Command {
    void execute(TaskList tasks, UI ui, Storage storage) throws Exception;
}
