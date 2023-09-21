package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class TagCommand implements Command {
    private final String stringIndex;
    private final String tag;

    /**
     * Initializes Tag command.
     *
     * @param stringIndex
     * @param tag
     */
    public TagCommand(String stringIndex, String tag) {
        this.stringIndex = stringIndex;
        this.tag = tag;
    }

    /**
     * Adds a tag to task at specified index.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     * @throws InvalidParametersException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        try {
            taskList.addTag(Integer.parseInt(stringIndex) - 1, tag);
            storage.save(taskList.getTasks());
            setOutput("Tag #" + tag + " successfully added");
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
