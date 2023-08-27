class MarkCommand extends Command {

    private final int index;

    MarkCommand(String input) throws DukeException{
        if (input == null) {
            throw new DukeException(" ☹ Which task?");
        }
        this.index = Integer.parseInt(input.strip());
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task markedTask = taskList.markTask(index);
        ui.showMessage(String.format("Nice! I've marked this task as done:\n    %s", markedTask));
        storage.writeFile(taskList.stringToFile());
    }
}
