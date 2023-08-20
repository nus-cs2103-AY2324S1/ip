public class MarkCommand implements Command{
    @Override
    public  boolean execute(TaskList tasks, Ui ui){
        String msg = ui.getLastMsg();
        String[] words= msg.toLowerCase().split("\\s+");
        int index = Integer.parseInt(words[1])-1;
        tasks.mark(index);
        ui.respond("Nice! I've marked this task as done: "+"\n"+tasks.get(index));
        return false;
    }
}
