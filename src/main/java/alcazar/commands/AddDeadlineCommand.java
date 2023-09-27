package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidArgumentException;
import alcazar.tasks.Deadline;

/**
 * Encapsulates the command to add a deadline
 */
public class AddDeadlineCommand extends Command {

    /** The prompt for the deadline to be added*/
    private String inputPrompt;

    /**
     * Constructs a AddDeadlineCommand object
     * @param inputPrompt The prompt passed by the user for creating the deadline
     */
    public AddDeadlineCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the addition of a Deadline Task to the TaskList
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the respective deadline has been added to the TaskList
     * @throws InvalidArgumentException If no prompt/topic is given for the deadline
     */
    @Override
    public Response execute(TaskList tasks,
                            Storage storage) throws InvalidArgumentException {
        String commandContents = this.getCommandContent();
        String[] deadlineContents = this.extractDeadlineContents(commandContents);
        tasks.add(new Deadline(deadlineContents[0], deadlineContents[1]));
        storage.writeUp(tasks);
        String result = "Got it. I've added this task:\n "
                + tasks.elementAt(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size() + " tasks in the list\n";
        return new Response(result, this.isExit());
    }

    /**
     * Checks if this is an exit command
     * @return boolean depending on whether this is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
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
                    "☹ OOPS!!! The description of a Deadline cannot be empty."
            );
        }

        for (int i = 1; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }

    /**
     * Extracts the end timing of the deadline.
     * @param messageContent The input prompt
     * @return An array containing the command content and end timing of
     *      the Deadline
     */
    public String[] extractDeadlineContents(String messageContent) {

        int index;
        String deadlineTiming = "";
        String deadlinePrompt = "";
        String[] messageContentWords = messageContent.split(" ");

        //Extracting deadline prompt
        for (index = 0; index < messageContentWords.length; index++) {
            String word = messageContentWords[index];
            if (word.equals("/by")) {
                break;
            }
            deadlinePrompt += word + " ";
        }

        //Extracting deadline timing
        for (index = index + 1; index < messageContentWords.length; index++) {
            String word = messageContentWords[index];
            deadlineTiming += word + " ";
        }

        //Returning the extracted prompt and timing as an array
        String[] deadlineContents = {deadlinePrompt, deadlineTiming};
        return deadlineContents;
    }
}
