public class UnmarkCommand implements Command{
    @Override
    public  boolean execute(TaskList tasks, Ui ui){
        String msg = ui.getLastMsg();
        String[] words= msg.toLowerCase().split("\\s+");
        int index = Integer.parseInt(words[1])-1;
        tasks.unmark(index);
        ui.respond("OK, I've marked this task as not done yet:"+"\n"+tasks.get(index));
        return false;
    }
}
