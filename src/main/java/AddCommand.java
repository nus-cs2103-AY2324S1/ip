public class AddCommand implements Command{
    @Override
    public boolean execute(TaskList tasks,Ui ui){
        String task = ui.getLastMsg();
        tasks.add(new Task(task));
        ui.respond("added: "+task);
        return false;
    }
}
