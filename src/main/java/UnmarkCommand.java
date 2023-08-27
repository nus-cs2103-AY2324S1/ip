class UnmarkCommand extends Command {
    private final int index;

    UnmarkCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ Which task?");
        }
        this.index = Integer.parseInt(input.strip());
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task unmarkedTask = taskList.unmarkTask(index);
        ui.showMessage(String.format("OK, I've marked this task as not done yet:\n    %s", unmarkedTask));
        storage.writeFile(taskList.stringToFile());
    }
}
