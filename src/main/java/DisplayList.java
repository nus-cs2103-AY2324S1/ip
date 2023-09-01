public class DisplayList extends Command{

    public DisplayList(String input) {
        super(input);
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        String[] taskStrings = new String[100] ; 

        for (int i = 1; i < tasks.getLength() + 1; i++) {
            String listString = (i) + ". " + tasks.getTask(i).toString();
            taskStrings[i - 1] = listString;
        }

        ui.print(taskStrings);
    }
}
