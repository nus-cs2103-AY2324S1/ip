import java.util.ArrayList;

public class List {
    private ArrayList<Task> list;

    public List() {
        this.list = new ArrayList<>();
    }

    public void addTask(String type, String input) throws CCException {
        Task task = null;
        switch (type) {
            case "todo":
                task = parseToDo(input);
                break;
            case "deadline":
                task = parseDeadline(input);
                break;
            case "event":
                task = parseEvent(input);
                break;
        }
        list.add(task);
        System.out.println(ChatterChicken.LINE
                + ChatterChicken.INDENT + "Got it. I've added this task:\n"
                + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
                + ChatterChicken.INDENT + "Now you have " + list.size() + " tasks in the list."
                + ChatterChicken.LINE);
    }

    private ToDo parseToDo (String input) throws CCException {
        if (input.equals("todo")) {
            throw new CCException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new ToDo(input.substring("todo".length()).trim());
    }

    private Deadline parseDeadline(String input) throws CCException {
        String[] fields = input.split("/");
        if (fields.length != 2) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        if (!fields[0].startsWith("deadline ") || !fields[1].startsWith("by ")) {
            throw new CCException("OOPS!!! Incorrect format for deadline.");
        }
        String name = fields[0].substring("deadline".length()).trim();
        String end = fields[1].substring("by".length()).trim();
        if (name.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for deadline detected.");
        }
        return new Deadline(name, end);
    }

    private Event parseEvent(String input) throws CCException{
        String[] fields = input.split("/");
        if (fields.length != 3) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        if (!fields[0].startsWith("event ") || !fields[1].startsWith("from ") || !fields[2].startsWith("to ")) {
            throw new CCException("OOPS!!! Incorrect format for event.");
        }
        String name = fields[0].substring("event".length()).trim();
        String start = fields[1].substring("from".length()).trim();
        String end = fields[2].substring("to".length()).trim();
        if (name.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new CCException("OOPS!!! Empty field for event detected.");
        }
        return new Event(name, start, end);
    }

    public void markTask(String input) throws CCException {
        try {
            Task task = list.get(getIndex(input));
            task.markDone();
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Nice! I've marked this task as done:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for marking list of length " + list.size());
        }
    }

    public void unmarkTask(String input) throws CCException {
        try {
            Task task = list.get(getIndex(input));
            task.unmarkDone();
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "OK, I've marked this task as not done yet:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask()
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + list.size());
        }
    }

    public void deleteTask(String input) throws CCException {
        try {
            int index = getIndex(input);
            Task task = list.get(index);
            list.remove(index);
            System.out.println(ChatterChicken.LINE
                    + ChatterChicken.INDENT + "Noted. I've removed this task:\n"
                    + ChatterChicken.INDENT_BIG + task.getTask() + "\n"
                    + ChatterChicken.INDENT + "Now you have " + list.size() + " tasks in your list."
                    + ChatterChicken.LINE);
        } catch (IndexOutOfBoundsException e) {
            throw new CCException("Invalid input for list of length " + list.size());
        }
    }

    private int getIndex(String input) {
        return input.charAt(input.length() - 1) - '0' - 1;
    }

    public void printList() {
        System.out.println(ChatterChicken.LINE + ChatterChicken.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(ChatterChicken.INDENT_BIG + (i + 1) + "." + list.get(i).getTask());
        }
        System.out.println(ChatterChicken.LINE);
    }
}
