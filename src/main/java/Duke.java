import java.util.*;

public class Duke {
    static boolean isEnd = false;
    static List<Task> taskList = new ArrayList<>();
    static String greeting = "______________________________________\n"
            + "Hi, I'm Chatty\n"
            + "What do you need to do today?\n"
            + "______________________________________";
    static String goodbye = "______________________________________\n"
            + "Bye. Don't come back!\n"
            + "______________________________________";

    static void removeFromList(int index) throws DukeException {
        if (index < 0 || index > taskList.size()-1) {
            throw new DukeException("Wow, deleting a nonexistent task? Check your tasks again with 'list'.");
        }
        Task t = taskList.get(index);
        taskList.remove(t);
        String returnLine = "______________________________________\n"
                + "Looks like you have more time to sleep now. Deleted this for you:\n"
                + t.toString()
                + "\nYou now have " + taskList.size() + " things to do.\n"
                + "______________________________________\n";
        System.out.println(returnLine);
    }

    static void addToList(String str) throws DukeException {
        Task t = null;
        if (str.startsWith("todo")) {
            if (str.length() <= 5) {
                throw new DukeException("So um, what exactly do you need to do? Add it as the description of the todo.");
            } else {
                t = new ToDo(str.substring(5));
            }
        } else if (str.startsWith("event")) {
            if (str.length() <= 6) {
                throw new DukeException("So um, what exactly do you have? Add it as the description of the event.");
            }
            int indexFrom = str.lastIndexOf("/from");
            int indexTo = str.lastIndexOf("/to");
            t = new Event(str.substring(6, indexFrom-1),
                    str.substring(indexFrom+6, indexTo-1), str.substring(indexTo+4));
        } else if (str.startsWith("deadline")) {
            if (str.length() <= 9) {
                throw new DukeException("So um, what exactly do you need to do? Add it as the description of the deadline.");
            }
            int indexBy = str.lastIndexOf("/by");
            t = new Deadline(str.substring(9, indexBy-1), str.substring(indexBy+4));
        } else if (str.startsWith("mark")) {
            throw new DukeException("Specify the task number after the word 'mark', please. I can't read minds.");
        } else if (str.startsWith("unmark")) {
            throw new DukeException("Specify the task number after the word 'unmark', please. I can't read minds.");
        } else if (str.startsWith("delete")) {
            throw new DukeException("Specify the task number after the word 'delete', please. I can't read minds.");
        }
        if (t == null) {
            throw new DukeException("Uncivilised speech. Please try again with words I can understand.");
        } else {
            taskList.add(t);
            String returnLine = "______________________________________\n"
                    + "Ok. Your tasklist has grown longer with this addition:\n"
                    + t.toString()
                    + "\nYou now have " + taskList.size() + " things to do.\n"
                    + "______________________________________\n";
            System.out.println(returnLine);
        }
    }

    static void listTasks() {
        System.out.println("______________________________________");
        for (int i=1; i<=taskList.size(); i++) {
            Task t = taskList.get((i-1));
            System.out.format("%d. " + t.toString() + "\n", i);
        }
        System.out.println("______________________________________\n");
    }

    static boolean awaitCommand(String command) {
        try {
            if (command.equals("bye")) {
                isEnd = true;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark") && command.length() > 5) {
                int index = Integer.parseInt(command.substring(5));
                if (index <= 0 || index > taskList.size()) {
                    throw new DukeException("Wow, that's a nonexistent task. Check your tasks again with 'list'.");
                }
                taskList.get(index-1).markAsDone();
            } else if (command.startsWith("unmark") && command.length() > 7) {
                int index = Integer.parseInt(command.substring(7));
                if (index <= 0 || index > taskList.size()) {
                    throw new DukeException("Wow, that's a nonexistent task. Check your tasks again with 'list'.");
                }
                taskList.get(index-1).markAsUndone();
            } else if (command.startsWith("delete") && command.length() > 7) {
                int index = Integer.parseInt(command.substring(7));
                removeFromList(index-1);
            } else {
                addToList(command);
            }
            return true;
        } catch (DukeException e) {
            System.out.println("______________________________________\n");
            e.printMessage();
            System.out.println("______________________________________\n");
        } finally {
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        while (isEnd == false) {
            String command = sc.nextLine();
            awaitCommand(command);
        }
        System.out.println(goodbye);
    }
}
