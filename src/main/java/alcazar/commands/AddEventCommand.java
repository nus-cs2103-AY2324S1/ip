package alcazar.commands;

import alcazar.Response;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.exceptions.InvalidArgumentException;
import alcazar.tasks.Event;

/**
 * Encapsulates the command to add an Event
 */
public class AddEventCommand extends Command {

    /** The prompt passed by the user to add an Event*/
    private String inputPrompt;

    /**
     * Constructs an AddEventCommand object
     * @param inputPrompt The prompt passed by the user to add an event.
     */
    public AddEventCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    /**
     * Executes the addition of a Event Task to the TaskList
     * @param tasks the TaskList containing all the tasks upto present
     * @param storage The Storage which stores all the tasks upto now
     * @return A Response to showcase that the respective event has been added to the TaskList
     * @throws InvalidArgumentException If no prompt/topic is given for the event
     */
    @Override
    public Response execute(
            TaskList tasks, Storage storage) throws InvalidArgumentException {
        String commandContent = this.getCommandContent();
        String[] eventContents = this.extractEventContents(commandContent);
        tasks.add(new Event(eventContents[0], eventContents[1], eventContents[2]));
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
            throw new InvalidArgumentException("☹ OOPS!!! "
                    + "The description of an Event cannot be empty."
            );
        }

        for (int i = 1; i < inputWords.length; i++) {
            commandContent += inputWords[i] + " ";
        }

        commandContent = commandContent.trim();
        return commandContent;
    }

    /**
     * Extracts the start and end timings of a deadline
     * @param messageContent The input text
     * @return Returns and array containing the command content,
     *      the start and the end times
     */
    public String[] extractEventContents(String messageContent) {

        int index;
        String eventStartTiming = "";
        String eventEndTiming = "";
        String eventPrompt = "";
        String[] messageContentWords = messageContent.split(" ");

        //Extracting event prompt
        for (index = 0; index < messageContentWords.length; index++) {
            String word = messageContentWords[index];
            if (word.equals("/from")) {
                break;
            }
            eventPrompt += word + " ";
        }

        //Extracting event start timing
        for (index = index + 1; index < messageContentWords.length; index++) {
            String word = messageContentWords[index];
            if (word.equals("/to")) {
                break;
            }
            eventStartTiming += word + " ";
        }

        //Extracting event end timing
        for (index = index + 1; index < messageContentWords.length; index++) {
            String word = messageContentWords[index];
            eventEndTiming += word + " ";
        }

        //Returning the extracted prompt and timing as an array
        String[] eventContents = {eventPrompt, eventStartTiming, eventEndTiming};
        return eventContents;
    }
}