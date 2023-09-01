public interface Command {

    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
    public void setLoading();

}
