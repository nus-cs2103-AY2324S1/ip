package duke.command;

import duke.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand implements Command {
    @Override
    public String execute(String text, UI ui, TaskList list, Storage storage) throws DukeException {
        String[] splitText = text.split("/");
        String description = splitText[0].substring(5);

        if (description.isEmpty()) {
            throw new DukeException("I apologise, sir. " +
                    "But the description, start and end cannot be empty");

        } else {
            String startText = splitText[1].trim().substring(5);
            String endText = splitText[2].trim().substring(3);

            try {
                LocalDateTime start = LocalDateTime.parse(startText);
                LocalDateTime end = LocalDateTime.parse(endText);
                Event event = new Event(description.trim(), start, end);
                list.add(event);
                ui.buildMessage("Noted Sir. I've added this task to your list: \n");
                ui.buildMessage(String.format("\t [%s] [%s] %s \n", event.getTag(),
                        event.getStatusIcon(),
                        event.toString()));
                ui.buildMessage(String.format("As of now, you have %d tasks on the agenda. \n",
                        list.size()));
                storage.appendToFile(text + "\n");
                return ui.sendMessage();

            }  catch (DateTimeParseException e) {
                throw new DukeException("Invalid Date Format: should be YYYY-MM-DDTTime. " +
                        "Example: 2023-12-12T06:30:00");
            } catch (IOException e) {
                ui.buildMessage("Something went wrong: " + e.getMessage() + "\n");
                return ui.sendMessage();
            }
        }
    }
}
