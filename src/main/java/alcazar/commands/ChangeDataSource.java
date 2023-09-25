package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.AlcazarException;
import alcazar.exceptions.InvalidArgumentException;

public class ChangeDataSource extends Command {
    private String inputPrompt;

    public ChangeDataSource(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the changing of the file path
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the file path has changed
     * @throws InvalidArgumentException If no file path is given
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidArgumentException {
        String filePath = this.getCommandContent();
        String response = "Great!, I've change the data source file path to: " + filePath;
        Response changeFileResponse = new Response(response, this.isExit());
        changeFileResponse.setFilePath(filePath);
        return changeFileResponse;
    }

    /**
     * Extracts the content, the text after the command specification, of the command
     * passed by the user
     * @return String containing the command content
     * @throws InvalidArgumentException If command is passed without content
     */
    public String getCommandContent() throws InvalidArgumentException {

        String commandContent = "";
        String[] inputWords = inputPrompt.split(" ");

        if (inputWords.length == 1) {
            throw new InvalidArgumentException(
                    "☹ OOPS!!! The file path cannot be empty"
            );
        }

        for (int i = 2; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }

    /**
     * Checks if this is an exit command
     * @return boolean depending on whether this is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
