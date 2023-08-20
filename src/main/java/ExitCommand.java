public class ExitCommand implements Command{
    @Override
    public  boolean execute(TaskList tasks, Ui ui){
        ui.respond("Bye. Hope to see you again soon!");
        return true;
    }
}
