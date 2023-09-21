package duke.taskmanagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public Ui ui;
    public TaskList taskList;

    /**
     * Constructor for Parser class.
     * @param ui The ui object.
     * @param taskList The TaskList object.
     */
    public Parser(Ui ui, TaskList taskList){
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * To read the command from user input and
     * parse it to Ui object to print statement.
     * @param list The tasklist object to handle the tasks.
     * @param cmd The string input by user.
     */
    public String readCmd(TaskList list, String cmd) {
            if (cmd.equals("bye")) {
                return byeGreet();
            } else if (cmd.equals("list")) {
                return getList(list);
            } else if (cmd.contains("unmark")) {
                return unmark(list, cmd);
            } else if (cmd.contains("mark")) {
                return mark(list, cmd);
            } else if (cmd.contains("delete")) {
                return delete(list, cmd);
            } else if (cmd.contains("deadline")) {
                return deadline(list, cmd);
            } else if (cmd.contains("todo")){
                return todo(list, cmd);
            } else if (cmd.contains("event")) {
                return event(list, cmd);
            } else if (cmd.contains("find")) {
                return find(list, cmd);
            } else if(cmd.contains("help")) {
                return ui.help();
            } else {
                DukeException exp = new DukeException("");
                return exp.nothing();
            }
    }

    private String byeGreet() {
        ui.closeScanner();
        return ui.bye();
    }

    private String getList(TaskList list) {
        int size = list.getListSize();
        return ui.printList(size, list.getList());
    }

    private String unmark(TaskList list, String cmd) {
        int startIndex = -1;
        for (int i = 5; i < cmd.length(); i++) {
            if (Character.isDigit(cmd.charAt(i))) {
                startIndex = i;
                break;
            }
        }

        // If a digit is found, extract the integer
        if (startIndex != -1) {
            int endIndex = startIndex;
            while (endIndex < cmd.length() && Character.isDigit(cmd.charAt(endIndex))) {
                endIndex++;
            }

            String indexStr = cmd.substring(startIndex, endIndex);
            int index = Integer.parseInt(indexStr);

            // Check if the index is within a valid range
            if (index >= 0 && index <= list.getListSize()) {
                return list.unmark(index);
            } else {
                return "Invalid index. The index should be between 0 and " + (list.getListSize() );
            }
        } else {
            return "Invalid index format"; // Handle the case where no valid index is found
        }
    }

    private String mark(TaskList list, String cmd) {
        // Find the starting index of the integer
        int startIndex = -1;
        for (int i = 5; i < cmd.length(); i++) {
            if (Character.isDigit(cmd.charAt(i))) {
                startIndex = i;
                break;
            }
        }

        // If a digit is found, extract the integer
        if (startIndex != -1) {
            int endIndex = startIndex;
            while (endIndex < cmd.length() && Character.isDigit(cmd.charAt(endIndex))) {
                endIndex++;
            }

            String indexStr = cmd.substring(startIndex, endIndex);
            int index = Integer.parseInt(indexStr);

            // Check if the index is within a valid range
            if (index >= 0 && index <= list.getListSize()) {
                return list.mark(index);
            } else {
                return "Invalid index. The index should be between 0 and " + (list.getListSize() );
            }
        } else {
            return "Invalid index format"; // Handle the case where no valid index is found
        }
    }

    private String delete(TaskList list, String cmd) {
        // Find the starting index of the integer
        int startIndex = -1;
        for (int i = 7; i < cmd.length(); i++) {
            if (Character.isDigit(cmd.charAt(i))) {
                startIndex = i;
                break;
            }
        }

        // If a digit is found, extract the integer
        if (startIndex != -1) {
            int endIndex = startIndex;
            while (endIndex < cmd.length() && Character.isDigit(cmd.charAt(endIndex))) {
                endIndex++;
            }

            String indexStr = cmd.substring(startIndex, endIndex);
            int index = Integer.parseInt(indexStr);

            // Check if the index is within a valid range
            if (index >= 0 && index <= list.getListSize()) {
                // Call the deleteTask method with the extracted index
                return list.deleteTask(index);
            } else {
                return "Invalid index. The index should be between 0 and " + (list.getListSize());
            }
        } else {
            return "Invalid index format"; // Handle the case where no valid index is found
        }
    }

    private String deadline(TaskList list, String cmd) {
        String[] parts = cmd.split("/by");
        if (parts.length == 2) {
            String description = parts[0].replace("deadline", "").trim(); // Remove "deadline"
            String deadline = parts[1].trim();
            return invalidDate(deadline, description, list);
        } else {
            return "please follow the format: deadline /by yyyy-mm-dd";
        }
    }

    private String invalidDate(String deadline, String description, TaskList list) {
        try {
            if (!deadline.isEmpty()) {
                LocalDate d1 = LocalDate.parse(deadline);
                String formattedDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                Task task = new Deadline(description, formattedDate, false);
                return list.addDeadlineTask(task);
            } else {
                DukeException exp = new DukeException("deadline");
                return exp.toString();
            }
        } catch (DateTimeParseException e) {
            return "Invalid date. Please use a valid date!";
        }
    }
    private String todo(TaskList list, String cmd) {
        String[] parts = cmd.split(" ", 2);
        if (parts.length == 2) {
            String desc = parts[1].trim();
            Task task = new ToDo(desc, false);
            return list.addToDoTask(task);
        } else {
            DukeException exp = new DukeException("todo");
            return exp.toString();
        }
    }

    private String event(TaskList list, String cmd) {
        String[] parts = cmd.split("/from");
        if (parts.length == 2) {
            String desc = parts[0].replace("event", "").trim();
            String rest = parts[1].trim();
            String[] restParts = rest.split("/to");
            String from = restParts[0].trim();
            String till = restParts[1].trim();
            Task task = new Event(desc, from, till, false);
            return list.addEventTask(task);
        } else {
            DukeException exp = new DukeException("event");
            return exp.toString();
        }
    }

    private String find(TaskList list, String cmd) {
        String[] parts = cmd.split(" ");
        String keyword = parts[1];
        return ui.printFilterList(list.find(keyword));
    }

}
