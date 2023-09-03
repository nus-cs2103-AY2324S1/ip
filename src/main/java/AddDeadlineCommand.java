public class AddDeadlineCommand extends Command {
    String allDetails;


    public AddDeadlineCommand(String[] commandInputs) throws BareumException {
        if (commandInputs.length <= 1) {
            throw new BareumException("You are missing some arguments :(\n" +
                    "Current number of arguments: " + commandInputs.length +
                    "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>");
        }
        this.allDetails = commandInputs[1];
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws BareumException {
        try {
            DeadlineTask task = DeadlineTask.makeDeadline(allDetails);
            taskList.addTask(task);
            storage.saveNewTask(task);
            String added = "I have added this task:\n" + task + "\nYou now have "
                    + taskList.size() + " task(s) in your list.";
            Ui.reply(added);
        } catch (BareumException e) {
            Ui.reply("Oops! The description of a deadline cannot be empty.\n" +
                    "Correct format: deadline <description> /by <due date in YYYY-MM-DD>");
        }
    }
}
