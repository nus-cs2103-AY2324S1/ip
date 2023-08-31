package adam.command;

import adam.Storage;
import adam.Ui;
import adam.TaskList;
import adam.exception.DateException;
import adam.exception.NoDescriptionException;
import adam.exception.DeadlineException;
import adam.exception.EventsException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddCommand implements Command {
    private String[]tokens;
    private String item;
    private String input;
    public AddCommand(String[]tokens, String item, String input) {
        this.tokens = tokens;
        this.item = item;
        this.input = input;
    }
    public void execute(TaskList tasks, Storage storage, Ui ui){
        if (tokens.length == 1) {
            throw new NoDescriptionException();
        }
        switch (input) {
            case "todo":
                tasks.addTodo(item);
                break;
            case "deadline":
                String[] by = item.split(" /by ");
                if (by.length == 0) {
                    throw new NoDescriptionException();
                }
                if (by[0].equals("")) {
                    throw new NoDescriptionException();
                }
                if (by.length != 2) {
                    throw new DeadlineException();
                }
                try {
                    LocalDate.parse(by[1]);
                } catch (DateTimeParseException e) {
                    throw new DateException();
                }
                tasks.addDeadline(by[0], by[1]);
                break;
            case "event":
                String[] divide1 = item.split(" /from ");
                if (divide1.length == 0) {
                    throw new NoDescriptionException();
                }
                if (divide1[0].equals("")) {
                    throw new NoDescriptionException();
                }
                if (divide1.length != 2) {
                    throw new EventsException();
                }
                String text = divide1[0];
                String[] divide2 = divide1[1].split(" /to ");
                if (divide2.length != 2) {
                    throw new EventsException();
                }
                String from = divide2[0];
                String to = divide2[1];
                try {
                    LocalDate.parse(from);
                } catch (DateTimeParseException e) {
                    throw new DateException();
                }
                try {
                    LocalDate.parse(to);
                } catch (DateTimeParseException e) {
                    throw new DateException();
                }
                tasks.addEvent(text, from, to);
                break;
            default:
                System.out.println("Wrong input");
        }
        tasks.save(storage);
    }
}
