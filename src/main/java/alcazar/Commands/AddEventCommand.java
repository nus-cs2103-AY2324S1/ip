package alcazar.Commands;

import alcazar.Exceptions.InvalidArgumentException;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.Tasks.Event;

public class AddEventCommand extends Command {
    private String inputPrompt;

    public AddEventCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }
    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidArgumentException {
        String commandContent = this.getCommandContent();
        String[] eventContents = this.extractEventContents(commandContent);
        tasks.add(new Event(eventContents[0], eventContents[1], eventContents[2]));
        storage.writeUp(tasks);
        return "Got it. I've added this task:\n "
                + tasks.elementAt(tasks.size() - 1) + "\n"
                + "Now you have " + tasks.size() + " tasks in the list\n";
    }

    @Override
    public boolean isExit() {
        return false;
    }
    public String getCommandContent() throws InvalidArgumentException {

        String commandContent = "";
        String[] inputWords = inputPrompt.split(" ");

        if (inputWords.length == 1) {
            throw new InvalidArgumentException(
                    "☹ OOPS!!! The description of an Event cannot be empty."
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
