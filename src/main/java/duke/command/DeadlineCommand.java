package duke.command;

import duke.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        String[] splitText = text.split("/", 2);

        String description = splitText[0].substring(8);

        if (description.isEmpty()) {
            throw new DukeException("I apologise, sir. " +
                    "But the description and deadline cannot be empty");
        } else {

            String deadlineText = splitText[1].substring(3);
            try {
                LocalDateTime deadline = LocalDateTime.parse(deadlineText);
                Deadline dl = new Deadline(description.trim(), deadline);
                list.add(dl);

                ui.buildMessage("Noted Sir. I've added this task to your list: \n");
                ui.buildMessage(String.format("\t [%s] [%s] %s \n", dl.getTag(), dl.getStatusIcon(),
                        dl.toString()));
                ui.buildMessage(String.format("As of now, you have %d tasks on the agenda.\n",
                        list.size()));

                storage.appendToFile(text + "\n");

                return ui.sendMessage();

            } catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTTime. " +
                        "Example: 2023-12-12T06:30:00");
            } catch (IOException e) {
                ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                return ui.sendMessage();
            }
        }
    }
}