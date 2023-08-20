public class ListCommand extends Command{
    @Override
    public  void execute(TaskList tasks,Ui ui){
        ui.respond(tasks.toString());
    }

    @Override
    public  boolean isExit(){
        return false;
    }
}
