public class AddEventCommand extends Command {
    boolean isExit = false;
    String allDetails;
    public AddEventCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length <= 1) {
            throw new BareumException("You are missing some arguments :(\n" +
                    "Current number of arguments: " + commandInputs.length +
                    "\nCorrect format: event <description> /from <start time> /to <end time>");
        }
        this.allDetails = commandInputs[1];
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        try {
            EventTask task = EventTask.makeEvent(allDetails);
            taskList.addTask(task);
            storage.saveNewTask(task);
            String added = "I have added this task:\n" + task + "\nYou now have "
                    + taskList.size() + " task(s) in your list.";
            Ui.reply(added);
        } catch (BareumException e) {
            Ui.reply("Oops! The description of an event cannot be empty.\n" +
                    "Correct format: event <description> /from <start time> /to <end time>");
        }
    }
}
