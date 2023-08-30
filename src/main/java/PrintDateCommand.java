public class PrintDateCommand extends Command {

    private String commandBody;

    public PrintDateCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String err = "OOPS!!! The command for print_date task is invalid.";
        String[] printTask = commandBody.split(" /on ");
        if (printTask.length != 2) {
            throw new PrintDateException(err);
        }
        if (!printTask[0].equals("deadline") && !printTask[0].equals("event")) {
            throw new PrintDateException(err);
        }

        try {
            taskList.printDateTask(Keyword.valueOf(printTask[0].toUpperCase()), Time.parseDate(printTask[1]), ui);
        } catch (DukeException e) {
            throw new PrintDateException(err);
        }
    }
}
