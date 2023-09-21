package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class ViewTagsCommand implements Command{
    private final String indexString;
    public ViewTagsCommand(String indexString) {
        this.indexString = indexString;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        try {
            String tags = taskList.printTags(Integer.parseInt(indexString) - 1);
            setOutput(tags);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            taskList.print();
            throw new InvalidParametersException("Insert an integer from the list");
        }
    }

    /**
     * Replaces existing string in Output with a new string.
     *
     * @param string String to be replaced.
     */
    private void setOutput(String string) {
        output.delete(0, output.length());
        output.append(string);
    }
}
