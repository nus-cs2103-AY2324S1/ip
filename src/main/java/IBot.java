import java.util.ArrayList;
public class IBot {
    private ArrayList<Task> lst = new ArrayList<>();

    private void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        lst.add(t);
        System.out.println("  " + t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
    }

    private void todo(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            addTask(new Todo(cmd.substring(1)));
        }
    }

    private void deadline(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.\n");
        }else if(!cmd.matches(" \\S.*\\s/by\\s\\S.*")){
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to add a task:\n  " +
                            "deadline <task name> /by <deadline>\n");
        }else {
            String[] temp = cmd.split(" /by ");
            addTask(new Deadline(
                    temp[0].substring(1),
                    temp[1]));
        }
    }

    private void event(String cmd) throws DukeException {
        if (cmd.isEmpty() || cmd.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.\n");
        }else if(!cmd.matches(" \\S.*\\s/from\\s\\S.*\\s/to\\s\\S.*")){
            throw new DukeException(
                    "☹ OOPS!!! Please follow the following pattern to add a task:\n  " +
                            "event <task name> /from <begin time> /to <end time>\n");
        }else {
            String[] temp = cmd.split(" /");
            addTask(new Event(
                    temp[0].substring(1),
                    temp[1].substring(5),
                    temp[2].substring(3)));
        }
    }

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

    private void mark(String cmd) throws DukeException {
        int index = getIndex(cmd);
        if (index != -1) {
            lst.get(index - 1).mark();
        }
    }

    private void unmark(String cmd) throws DukeException {
        if (getIndex(cmd) != -1) {
            lst.get(getIndex(cmd) - 1).unmark();
        }

    }

    private void delete(String cmd) throws DukeException{
        if (getIndex(cmd) != -1) {
            Task delete = lst.get(getIndex(cmd) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + delete);
            lst.remove(lst.get(getIndex(cmd) - 1));
            System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
        }
    }

    private void list() {
        System.out.println("Here are the tasks in your list:");
        lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + "." + x));
        System.out.println("");
    }
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
