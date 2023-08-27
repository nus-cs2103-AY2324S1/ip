import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {

    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

    private Storage storage;
    private TaskList items;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            items = new TaskList(storage.load());
        } catch (DukeException c) {
            ui.showLoadingError();
            items = new TaskList();
        }
    }

    public void run() {
        ui.greet();

        while (true) {
            try {
                String input = ui.getInput();
                String[] inputArr = input.split(" ", 2);
                String keyword = inputArr[0];
                if (keyword.equals("bye")) {
                    ui.bye();
                    break;
                }
                if (keyword.equals("list")) {
                    ArrayList list = items.getItems();
                    ui.list(list);
                    continue;
                }

                String description = "";
                Task item;
                switch (keyword) {
                case "mark":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! Please include the task number you would like to mark.");
                    }
                    description = input.split(" ", 2)[1];
                    int markNumber = Integer.parseInt(description.trim());
                    item = items.mark(markNumber);
                    ui.markItem(item.toString());
                    break;
                case "unmark":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! Please include the task number you would like to unmark.");
                    }
                    description = input.split(" ", 2)[1];
                    int unmarkNumber = Integer.parseInt(description.trim());
                    item = items.unmark(unmarkNumber);
                    ui.unmarkItem(item.toString());
                    break;
                case "todo":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = input.split(" ", 2)[1];
                    item = items.addToDo(description);
                    ui.addItem(item.toString(), items.getCount());
                    break;
                case "deadline":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    description = input.split(" ", 2)[1];
                    String deadlineName = "";
                    String deadlineBy = "";
                    String[] deadlineDescription = description.split("/");
                    for (String str : deadlineDescription) {
                        if (str.startsWith("by")) {
                            deadlineBy = str.split(" ", 2)[1].trim();
                        } else {
                            deadlineName = str.trim();
                        }
                    }
                    if (deadlineName == "") {
                        throw new DukeException("OOPS!! Please include the name of the deadline.");
                    }
                    if (deadlineBy == "") {
                        throw new DukeException("OOPS!! Please include when the deadline is by.");
                    }
                    try {
                        LocalDateTime byParsed = LocalDateTime.parse(deadlineBy, Duke.DATETIME_INPUT_FORMAT);
                        item = items.addDeadline(deadlineName, byParsed);
                        ui.addItem(item.toString(), items.getCount());
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
                    }
                    break;
                case "event":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }
                    description = input.split(" ", 2)[1];
                    String eventName = "";
                    String eventFrom = "";
                    String eventTo = "";
                    String[] eventDescription = description.split("/");
                    for (String str : eventDescription) {
                        if (str.startsWith("from")) {
                            eventFrom = str.split(" ", 2)[1].trim();
                        } else if (str.startsWith("to")) {
                            eventTo = str.split(" ", 2)[1].trim();
                        } else {
                            eventName = str.trim();
                        }
                    }
                    if (eventName == "") {
                        throw new DukeException("OOPS!! Please include the name of the event.");
                    }
                    if (eventFrom == "") {
                        throw new DukeException("OOPS!! Please include when the event is from.");
                    }
                    if (eventTo == "") {
                        throw new DukeException("OOPS!! Please include when the event is till.");
                    }
                    try {
                        LocalDateTime fromParsed =  LocalDateTime.parse(eventFrom, Duke.DATETIME_INPUT_FORMAT);
                        LocalDateTime toParsed = LocalDateTime.parse(eventTo, Duke.DATETIME_INPUT_FORMAT);
                        item = items.addEvent(eventName, fromParsed, toParsed);
                        ui.addItem(item.toString(), items.getCount());
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.");
                    }
                    break;
                case "delete":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! Please include the task number you would like to delete.");
                    }
                    description = input.split(" ", 2)[1];
                    int indexDelete = Integer.parseInt(description);
                    Task deleted = items.delete(indexDelete);
                    ui.deleteItem(deleted.toString(), items.getCount());
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                storage.writeData(items.getItems());
            } catch (DukeException e) {
                ui.talk(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        new Duke("ip/src/data/duke.txt").run();
    }
}
