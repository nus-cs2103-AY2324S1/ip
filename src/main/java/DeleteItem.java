public class DeleteItem extends Command{

    public DeleteItem(String input) {
        super(input);
    }
    
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) 
        throws InvalidIndexException {
        try {
        int index = Integer.parseInt(input);
        Task deletedTask = tasks.getTask(index);
        ui.print(new String[] {"I knew you couldn't finish it. Or maybe you did. I don't care. Deleted:",
            deletedTask.toString(),
            "Now you have an overwhelming "+ (tasks.getLength() - 1) +
            " things to do"});
        tasks.delete(Integer.parseInt(input));

        storage.saveTasks(tasks);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("Are you stupid? That's not a number.");
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("That's not even a number on the list, idiot.");
        }
    }
}
