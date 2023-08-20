public class AddCommand extends Command{
    @Override
    public  void execute(TaskList tasks,Ui ui){
        String task = ui.getLastMsg();
        tasks.add(new Task(task));
        ui.respond("added: "+task);
    }

    @Override
    public  boolean isExit(){
        return false;
    }
}
