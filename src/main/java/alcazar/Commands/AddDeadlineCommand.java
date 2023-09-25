package alcazar.Commands;

import alcazar.Exceptions.InvalidArgumentException;
import alcazar.Storage;
import alcazar.TaskList;
import alcazar.Tasks.Deadline;

public class AddDeadlineCommand extends Command {
    private String inputPrompt;

    public AddDeadlineCommand(String inputPrompt) {
        this.inputPrompt = inputPrompt;
    }

    @Override
    public String execute(TaskList tasks,
                          Storage storage) throws InvalidArgumentException {
        String commandContents = this.getCommandContent();
        String[] deadlineContents = this.extractDeadlineContents(commandContents);
        System.out.println(deadlineContents[1] + "//////////");
        tasks.add(new Deadline(deadlineContents[0], deadlineContents[1]));
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
//        for (i = 0; i < text.length(); i++) {
//            char ch = text.charAt(i);
//            if (ch == ' ') {
//                if (wrd.equals("/by")) {
//                    break;
//                }
//                str += wrd + " ";
//                wrd = "";
//            } else {
//                wrd += ch;
//            }
//        }
//        String[] deadArray = new String[2];
//        deadArray[0] = str.trim();
//        deadArray[1] = text.substring(i + 1);
//        return deadArray;
    }
}
