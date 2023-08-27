class TodoCommand extends Command {
    private final ToDo todo;

    TodoCommand(String input) throws DukeException {
        if (input == null) {
            throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
        }
        this.todo = new ToDo(input.strip());
    }

    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.addTask(todo);
        ui.showMessage(String.format("Got it. I've added this task:\n    " +
                "%s\nNow you have %d tasks in the list.", todo, taskList.getListSize()));
        storage.appendFile(todo);
    }
}
