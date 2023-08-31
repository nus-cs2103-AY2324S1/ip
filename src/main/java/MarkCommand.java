public class MarkCommand extends Command {
    private String[] splitTask;

    public MarkCommand(String[] splitTask) {
        this.splitTask = splitTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        int index;

        try {
            index = Integer.parseInt(splitTask[1]);
        } catch (NumberFormatException e) {
            throw new DukeInvalidMarkException(splitTask[0]);
        }

        if (index > 0 && taskList.get(index - 1) != null) {
            taskList.get(index - 1).mark();
            ui.printMark(taskList.get(index - 1));
        }
        storage.writeFile(taskList);
    }
}
