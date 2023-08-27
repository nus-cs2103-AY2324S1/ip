import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final DateTimeFormatter DATETIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

//    private Storage storage;
//    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
    }

    public void run() {
        ArrayList<Task> items = new ArrayList<>();

        // Read data from duke.txt to be pre-populated into items
        try {
            readData(items);
        } catch (DukeException e) {
            ui.talk(e.getMessage());
            return;
        }

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
                    String list = "";
                    int count = items.size();
                    if (count == 0) {
                        ui.talk("Your list is currently empty.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            list += "  " + (i + 1) + ". " + items.get(i) + "\n";
                        }
                        ui.talk(list);
                    }
                    continue;
                }

                String description = "";
                switch (keyword) {
                case "mark":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! Please include the task number you would like to mark.");
                    }
                    description = input.split(" ", 2)[1];
                    int indexMark = Integer.parseInt(description.trim()) - 1;
                    items.get(indexMark).markDone();
                    ui.talk("Nice! I've marked this task as done:\n  " + items.get(indexMark));
                    break;
                case "unmark":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! Please include the task number you would like to unmark.");
                    }
                    description = input.split(" ", 2)[1];
                    int indexUnmark = Integer.parseInt(description.trim()) - 1;
                    items.get(indexUnmark).markUnDone();
                    ui.talk("OK, I've marked this task as not done yet:\n  " + items.get(indexUnmark));
                    break;
                case "todo":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    description = input.split(" ", 2)[1];
                    items.add(new ToDo(description));
                    ui.talk("Got it. I've added this task:\n  " + items.get(items.size() - 1) + "\n Now you have "
                            + items.size() + " tasks in your list.");
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
                    items.add(new Deadline(deadlineName, deadlineBy));
                    ui.talk("Got it. I've added this task:\n  " + items.get(items.size() - 1) + "\n Now you have "
                            + items.size() + " tasks in your list.");
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
                    items.add(new Event(eventName, eventFrom, eventTo));
                    ui.talk("Got it. I've added this task:\n  " + items.get(items.size() - 1) + "\n Now you have " + items.size()
                            + " tasks in your list.");
                    break;
                case "delete":
                    if (inputArr.length != 2) {
                        throw new DukeException("OOPS!!! Please include the task number you would like to delete.");
                    }
                    description = input.split(" ", 2)[1];
                    int indexDelete = Integer.parseInt(description) - 1;
                    Task deleted = items.get(indexDelete);
                    items.remove(indexDelete);
                    ui.talk("Noted. I've removed this task:\n " + deleted + "\n Now you have " + items.size() + " tasks in your list");
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                writeData(items);
            } catch (DukeException e) {
                ui.talk(e.getMessage());
            }
        }
    }

    public static void readData(ArrayList<Task> items) throws DukeException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("ip/src/data/duke.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineArr = line.split("\\|");

                String taskType = lineArr[0];
                boolean isDone = Integer.parseInt(lineArr[1]) == 1 ? true : false;
                String name = lineArr[2];
                switch (taskType) {
                case "T":
                    items.add(new ToDo(name, isDone));
                    break;
                case "D":
                    String by = lineArr[3];
                    items.add(new Deadline(name, LocalDateTime.parse(by), isDone));
                    break;
                case "E":
                    String from = lineArr[3];
                    String to = lineArr[4];
                    items.add(new Event(name, LocalDateTime.parse(from), LocalDateTime.parse(to), isDone));
                    break;
                default:
                    continue;
                }
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! I am unable to read your duke.txt data file. Exiting....");
        }
    }

    public static void writeData(ArrayList<Task> items) throws DukeException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ip/src/data/duke.txt"));
            for (Task t : items) {
                writer.write(t.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! I am unable to write to your duke.txt data file. Exiting....");
        }
    }

    public static void main(String[] args) {

        new Duke("ip/src/data/duke.txt").run();
    }
}
