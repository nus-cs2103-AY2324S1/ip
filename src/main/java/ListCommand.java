public class ListCommand implements Command{
    @Override
    public  boolean execute(TaskList tasks,Ui ui){
        ui.respond(tasks.toString());
        return false;
    }
}
