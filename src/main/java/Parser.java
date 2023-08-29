import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    Scanner scanner;
    boolean notEnd;

    public Parser() {
        scanner = new Scanner(System.in);
        notEnd = true;
    }

    public void parse(Storage storage, DukeList ItemList, Ui ui) {
        while (notEnd) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    notEnd = false;
                    ui.exit();
                } else if (input.equals("list")) {
                    ItemList.displayList();
                } else {
                    String[] splitted = input.split(" ", 2);
                    if (input.startsWith("mark")) {
                        this.mark(splitted, ItemList);
                        storage.updateStorage(ItemList.getArrayList());
                    } else if (input.startsWith("unmark")) {
                        this.unmark(splitted, ItemList);
                        storage.updateStorage(ItemList.getArrayList());
                    } else if (input.startsWith("delete")) {
                        this.deleteTask(splitted, ItemList);
                        storage.updateStorage(ItemList.getArrayList());
                    } else if (input.startsWith("todo")) {
                        this.toDo(splitted, ItemList);
                        storage.updateStorage(ItemList.getArrayList());
                    } else if (input.startsWith("deadline")) {
                        this.deadline(splitted, ItemList);
                        storage.updateStorage(ItemList.getArrayList());
                    } else if (input.startsWith("event")) {
                        this.event(splitted, ItemList);
                        storage.updateStorage(ItemList.getArrayList());
                    } else {
                        throw new DukeException(" I'm sorry, but I don't know what that means :-(");
                    }
                }
            } catch (DukeException de) {
                System.out.println(de.getMessage());
            }
        }
    }

    /**
     * This method marks a task as done and handles the relevant exceptions
     * @param splitted Takes in the input that has been split by whitespace
     * @param ItemList  Takes in the tasklist that has been initialised
     * @throws DukeException
     */
    public void mark(String[] splitted, DukeList ItemList) throws DukeException {

        if (splitted.length == 1) {
            throw new DukeException("Please indicate which task you wish to mark off");
        }

        String trimmed = splitted[1].replaceAll("\\s+","");

        if (trimmed.equals("")) {
            throw new DukeException("Please indicate which task you wish to mark off");
        }

        if (!Character.isDigit(splitted[1].charAt(0))) {
            throw new DukeException("The second argument must be a digit");
        }
        if (Integer.parseInt(splitted[1]) > ItemList.getSize() || Integer.parseInt(splitted[1]) < 1) {
            throw new DukeException("Task number lies beyond the range of the current task list");
        }
        int taskNum = Integer.parseInt(splitted[1]);
        ItemList.setTaskAsDone(taskNum);
    }

    /**
     * This method unmarks and handles the relevant exceptions
     * @param splitted Takes in the input that has been split by whitespace
     * @param ItemList  Takes in the tasklist that has been initialised
     * @throws DukeException
     */
    public void unmark(String[] splitted, DukeList ItemList) throws DukeException {

        if (splitted.length == 1) {
            throw new DukeException("Please indicate which task you wish to unmark");
        }
        String trimmed = splitted[1].replaceAll("\\s+","");
        if (trimmed.equals("")) {
            throw new DukeException("Please indicate which task you wish to mark off");
        }
        if (!Character.isDigit(splitted[1].charAt(0))) {
            throw new DukeException("The second argument must be a digit");
        }
        if (Integer.parseInt(splitted[1]) > ItemList.getSize() || Integer.parseInt(splitted[1]) < 1) {
            throw new DukeException("Task number lies beyond the range of the current task list");
        }
        int taskNum = Integer.parseInt(splitted[1]);
        ItemList.setTaskAsUndone(taskNum);
    }


    /**
     * This method deletes a specific task and handles the relevant exceptions
     * @param splitted Takes in the input that has been split by whitespace
     * @param ItemList  Takes in the tasklist that has been initialised
     * @throws DukeException
     */
    public void deleteTask(String[] splitted, DukeList ItemList) throws DukeException {
        if (splitted.length == 1) {
            throw new DukeException("Please indicate which task you wish to delete");
        }

        String trimmed = splitted[1].replaceAll("\\s+","");

        if (trimmed.equals("")) {
            throw new DukeException("Please indicate which task you wish to delete");
        }

        if (!Character.isDigit(splitted[1].charAt(0))) {
            throw new DukeException("The second argument must be a digit");
        }

        if (Integer.parseInt(splitted[1]) > ItemList.getSize() || Integer.parseInt(splitted[1]) < 1) {
            throw new DukeException("Task number lies beyond the range of the current task list");
        }

        int taskNum = Integer.parseInt(splitted[1]);
        ItemList.deleteTask(taskNum);
    }

    /**
     * This method adds a to-do task to the task list and handles relevant exceptions
     * @param splitted Takes in the input that has been split by whitespace
     * @param ItemList  Takes in the tasklist that has been initialised
     * @throws DukeException
     */
    public void toDo(String[] splitted, DukeList ItemList) throws DukeException {
        //add items to the array
        if (splitted.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String trimmed = splitted[1].replaceAll("\\s+","");
        if (trimmed.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ItemList.addToDo(splitted[1]);
    }

    /**
     * This method adds a deadline task to the task list and handles relevant exceptions
     * @param splitted Takes in the input that has been split by whitespace
     * @param ItemList  Takes in the tasklist that has been initialised
     * @throws DukeException
     */
    public void deadline(String[] splitted, DukeList ItemList) throws DukeException {
        if (splitted.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }
        String trimmed = splitted[1].replaceAll("\\s+","");
        if (trimmed.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        if (!splitted[1].contains("/by")) {
            throw new DukeException("You forgot to indicate a specific deadline");
        }
        String[] deadline = splitted[1].split("/by", 2);
        String trimmedDeadline = deadline[1].replaceAll("\\s+","");

        if (trimmedDeadline.equals("")) {
            throw new DukeException("You forgot to indicate a specific deadline");
        }

        try {
            LocalDateTime by = formatInputDate(deadline[1].trim());
            System.out.println(by);
            ItemList.addDeadline(deadline[0], by);
        } catch (DateTimeParseException err) {
            System.out.println(err);
            System.out.println("Your date is not in the dd/mm/yy format");
        }
    }

    /**
     * This method adds an event task to the task list and handles relevant exceptions
     * @param splitted Takes in the input that has been split by whitespace
     * @param ItemList  Takes in the tasklist that has been initialised
     * @throws DukeException
     */
    public void event(String[] splitted, DukeList ItemList) throws DukeException {
        if (splitted.length == 1) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        String trimmed = splitted[1].replaceAll("\\s+","");

        if (trimmed.equals("")) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        if (!splitted[1].contains("/from") || !splitted[1].contains("/to")) {
            throw new DukeException("You forgot to indicate either/both a from and to datetime");
        }
        String[] from = splitted[1].split("/from", 2);
        String[] to = from[1].split("/to", 2);

        String trimmedFrom= to[0].replaceAll("\\s+","");
        String trimmedTo = to[1].replaceAll("\\s+","");
        if (trimmedFrom.equals("") || trimmedTo.equals("")) {
            throw new DukeException("You forgot to indicate either/both a from and to datetime");
        }
        try {
            LocalDateTime start = formatInputDate(to[0].trim());
            LocalDateTime end = formatInputDate(to[1].trim());
            ItemList.addEvent(from[0], start, end);
        } catch (DateTimeParseException err) {
            System.out.println("Your date is not in the d/MM/yyyy HHmm format");
        }
    }

    public LocalDateTime formatInputDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        return LocalDateTime.parse(date, formatter);
    }
}
