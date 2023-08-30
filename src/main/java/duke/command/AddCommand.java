package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.exception.DukeException;

import java.time.format.DateTimeParseException;

public class AddCommand extends Command{
    private String[] words;
    private String category;
    private boolean isExit = false;
    public AddCommand(String[] words, String category) {
        this.words = words;
        this.category = category;
    }

    @Override
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            switch (category) {
            case "T":
                if (words.length < 2) {
                    throw new DukeException(ui.messageCard("☹ OOPS!!! The description of a todo cannot be empty."));
                }

                //creating the to do
                Todo todo = new Todo(words[1]);

                //adding to taskArr and updates the storage
                tasks.addTask(todo);
                storage.updateFile(tasks);

                //printing messages
                String message = "Got it. I've added this task: \n" + todo +
                        "\nNow you have " + tasks.size() + " tasks in the list.";

                ui.printMessage(message);
                break;
            case "D":
                if (words.length < 2) {
                    throw new DukeException(ui.messageCard("☹ OOPS!!! The description of a deadline cannot be empty."));
                }

                String text = words[1];
                String[] split = text.split(" /by ", 2);
                String desc = split[0];
                String by = split[1];
                Deadline dl = new Deadline(desc, by);

                //add task to taskArr and updating the storage
                tasks.addTask(dl);
                storage.updateFile(tasks);

                //printing the messages
                String dlMessage = "Got it. I've added this task: \n" + dl +
                        "\nNow you have " + tasks.size() + " tasks in the list.";
                ui.printMessage(dlMessage);
                break;
            case "E":
                if (words.length < 2) {
                    throw new DukeException(ui.messageCard("☹ OOPS!!! The description of a event cannot be empty."));
                }

                String text2 = words[1];
                String[] slice = text2.split(" /from ", 2);
                String descr = slice[0].trim();
                String[] slicess = slice[1].split(" /to ", 2);
                String from = slicess[0].trim();
                String to = slicess[1].trim();

                Event e = new Event(descr, from, to);

                //add task to taskArr and updating storage
                tasks.addTask(e);
                storage.updateFile(tasks);

                //print messages
                String eMessage = "Got it. I've added this task: \n" + e +
                        "\nNow you have " + tasks.size() + " tasks in the list. ";
                ui.printMessage(eMessage);
                break;
            default:
                throw new DukeException(ui.messageCard("There may be wrong parameters inputted in, please check again!"));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
