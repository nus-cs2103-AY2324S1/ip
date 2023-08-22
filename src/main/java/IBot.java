import java.util.ArrayList;

/**
 * A transition class that contains the Task array,
 * parses command and invoke functions
 */
public class IBot {
    /* The Task array*/
    private ArrayList<Task> lst = new ArrayList<>();

    /**
     * All tasks are added to the array by this method
     * @param t The Task added
     */
    private void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        lst.add(t);
        System.out.println("  " + t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
    }

    /**
     * Add a type of Task that only has name
     * @param cmd The command after the keyword "todo",
     *            which only contains its name
     * @throws DukeException If there are nothing after keyword,
     * throw an exception
     */
    private void todo(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            addTask(Task.of(cmd.substring(1)));
        }
    }

    /**
     * Add a type of Task that has name and deadline
     * @param cmd The command after the keyword "deadline",
     *            which contains its name and deadline
     * @throws DukeException If there are nothing after keyword or no time,
     * throw an exception
     */
    private void deadline(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }else if(!cmd.matches(" \\S.*\\s/by\\s\\S.*")){
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to add a task:\n  " +
                            "deadline <task name> /by <deadline>\n");
        }else {
            String[] temp = cmd.split(" /by ");
            addTask(Task.of(
                    temp[0].substring(1),
                    temp[1]));
        }
    }

    /**
     * Add a type of Task that has name, start time and end time.
     * @param cmd The command after the keyword "event",
     *            which contains its name, start time and end time.
     * @throws DukeException If there are nothing after keyword or lack of component,
     * throws an exception
     */
    private void event(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.\n");
        }else if(!cmd.matches(" \\S.*\\s/from\\s\\S.*\\s/to\\s\\S.*")){
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to add a task:\n  " +
                            "event <task name> /from <begin time> /to <end time>\n");
        }else {
            String[] temp = cmd.split(" /");
            addTask(Task.of(
                    temp[0].substring(1),
                    temp[1].substring(5),
                    temp[2].substring(3)));
        }
    }

    /**
     * The method used to get the index of Task user wants to act on
     * @param cmd The command that contains a number
     * @return A number which is an index.
     * @throws DukeException If there are nothing or not a number,
     * throw an exception
     */
    private int getIndex(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException(
                    "☹ OOPS!!! You need to tell me which task you want to act on.\n");
        }else if(!cmd.matches(" \\d*")){
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to act on a task:\n  " +
                            "<your action> <task number>\n" +
                            "You can find the task number by calling 'list'\n");
        }else {
            int index = Integer.parseInt(cmd.substring(1));
            if (index <= lst.size() && index > 0) {
                return index;
            } else {
                System.out.println("☹ OOPS!!! There is no such a task\n");
            }
        }
        return -1;
    }

    /**
     * The method used to mark the specific Task
     * @param cmd The command contains the target Task index
     * @throws DukeException Throw the exception formed during getting the index
     */
    private void mark(String cmd) throws DukeException {
        int index = getIndex(cmd);
        if (index != -1) {
            lst.get(index - 1).mark();
        }
    }

    /**
     * The method used to unmark the specific Task
     * @param cmd The command contains the target Task index
     * @throws DukeException Throw the exception formed during getting the index
     */
    private void unmark(String cmd) throws DukeException {
        if (getIndex(cmd) != -1) {
            lst.get(getIndex(cmd) - 1).unmark();
        }

    }

    /**
     * The method used to delete the specific Task
     * @param cmd The command contains the target Task index
     * @throws DukeException Throw the exception formed during getting the index
     */
    private void delete(String cmd) throws DukeException{
        if (getIndex(cmd) != -1) {
            Task delete = lst.get(getIndex(cmd) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + delete);
            lst.remove(lst.get(getIndex(cmd) - 1));
            System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
        }
    }

    /**
     * The method used to list all Tasked stored in the arraylist.
     */
    private void list() {
        System.out.println("Here are the tasks in your list:");
        lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + "." + x));
        System.out.println("");
    }

    /**
     * Parse the instruction scanned by scanner by distinguish the keyword
     * @param instr The instruction scanned by scanner
     * @return Whether the scanner should keep scanning or not
     */
    public boolean parse(String instr) {
        String keyWord = instr.split(" ")[0];
        try{
            switch (keyWord) {
                case "":
                    throw new DukeException("☹ OOPS!!! You said nothing!\n");
                case "todo":
                    todo(instr.substring(4));
                    break;
                case "deadline":
                    deadline(instr.substring(8));
                    break;
                case "event":
                    event(instr.substring(5));
                    break;
                case "mark":
                    mark(instr.substring(4));
                    break;
                case "unmark":
                    unmark(instr.substring(6));
                    break;
                case "delete":
                    delete(instr.substring(6));
                    break;
                case "list":
                    list();
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return false;
                default:
                    throw new DukeException("☹ OOPS!!! I can't understand.\n");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
