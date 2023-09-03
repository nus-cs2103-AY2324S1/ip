public class DefaultCommand extends Command {
    protected String response;
    public DefaultCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        System.out.println(ui.format_response(
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-("
        ));
    }
}
