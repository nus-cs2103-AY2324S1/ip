class DeleteCommand extends Command {
    private final int index;

    DeleteCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ Which task?");
        }
        this.index = Integer.parseInt(input.strip());
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task deletedTask = taskList.deleteTask(index);
        ui.showMessage(String.format("Noted. I've removed this task:\n    " +
                "%s\nNow you have %d tasks in the list.", deletedTask, taskList.getListSize()));
        storage.writeFile(taskList.stringToFile());
    }
}
