package benben;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The type BenBen is a chatbot that interacts with the user by taking input from the system
 * and prints its response as system output.
 * It can create and edit a task list for a user.
 */
public class BenBen {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Instantiates a new BenBen with the given task list
     *
     * @param filePath the file path of the task list
     */
    public BenBen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BenBenException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Creates a Todo task in the list with the specific description
     *
     * @param str the string command from the user
     * @throws BenBenException when  the command from the user is of the wrong format
     */
    public String todo(String str) {
        Task t = Parser.parseTodo(str);
        tasks.add(t);
        storage.write(tasks);
        return ui.showAdd(t.toString(), tasks.size());
    }

    /**
     * Creates a Deadline task in the list with the specific description and deadline
     *
     * @param str the string command from the user
     * @throws BenBenException when  the command from the user is of the wrong format
     */
    public String deadline(String str) throws BenBenException {
        Task t = Parser.parseDeadline(str);
        tasks.add(t);
        storage.write(tasks);
        return ui.showAdd(t.toString(), tasks.size());
    }

    /**
     + Creates a Event task in the list with the specific description, start time and end time
     *
     * @param str the string command from the user
     * @throws BenBenException when  the command from the user is of the wrong format
     */
    public String event (String str) throws BenBenException{
        Task t = Parser.parseEvent(str);
        tasks.add(t);
        storage.write(tasks);
        return ui.showAdd(t.toString(), tasks.size());
    }

    /**
     * Iterates the list and prints out the tasks currently in the task list and their status
     */
    public String iterList() {
        return ui.showList(tasks);
    }

    /**
     * Marks a given task as done
     *
     * @param str the string command from the suer
     * @throws BenBenException if the command is not of the required format or if the target task does not exist
     */
    public String mark(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        assert strSplit.length == 2;
        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to mark!");
        }

        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to mark!");
        }

        int x;

        try {
            x = Integer.parseInt(strSplit[1]);
            assert x > 0 && x < tasks.size();
            tasks.get(x - 1).mark();
            storage.write(tasks);
            return ui.showMark(tasks.get(x - 1).toString());
        } catch(NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch(NullPointerException e) {
            throw new BenBenException("The task you are trying to mark does not exist!");
        }
    }

    /**
     * Marks a given task as not done
     *
     * @param str the string command from the suer
     * @throws BenBenException if the command is not of the required format or if the target task does not exist
     */
    public String unmark(String str) throws BenBenException{

        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to unmark!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to unmark!");
        }
        int x;

        try {
            x = Integer.parseInt(strSplit[1]);
            tasks.get(x - 1).unmark();
            storage.write(tasks);
            return ui.showUnmark(tasks.get(x - 1).toString());
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException e) {
            throw new BenBenException("The task you are trying to unmark does not exist!");
        }
    }

    /**
     * Removes a task from the list
     *
     * @param str the string command from the user
     * @throws BenBenException if the command is not of the required format or if the target task does not exist
     */
    public String remove(String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");
        int x;
        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to remove!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to remove!");
        }
        try {
            x = Integer.parseInt(strSplit[1]);
            Task temp = tasks.get(x - 1);
            tasks.remove(x - 1);
            storage.write(tasks);
            return ui.showRemove(temp.toString(), tasks.size());
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new BenBenException("The task you are trying to remove does not exist!");
        }
    }

    /**
     * Finds the task with the keyword
     *
     * @param str the string command from the user
     * @throws BenBenException if the command is not of the required format
     */
    public String find(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a keyword for your search!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one keyword to search!");
        }

        ArrayList<Task> relevantTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(strSplit[1])) {
                relevantTasks.add(t);
            }
        }
        return ui.showMatching(relevantTasks);
    }

    /**
     * Exits the program
     */
    public String exit() {
        System.exit(0);
        return ui.showExit();
    }


    public String getResponse(String input) {
        try {
            return Parser.parse(this, input);
        } catch (BenBenException e) {
            return e.toString();
        }

    }

    public String sort(String str) {
        String[] strSplit = str.split("\\s+");
        String key = "all";
        if (strSplit.length == 2) {
            key = strSplit[1];
        }
        String s = "";
        switch (key) {
            case "all":
                s = ui.showSorted(this.tasks.sortByDescription(), "all Tasks", "description");
                break;
            case "todo":
                s = ui.showSorted(this.tasks.sortTodo(), "Todos", "description");
                break;
            case "deadline":
                s = ui.showSorted(this.tasks.sortDeadline(), "Deadlines", "deadline");
                break;
            case "event":
                s = ui.showSorted(this.tasks.sortEvent(), "Events", "start date");
                break;
            default:
                throw new BenBenException("Please do indicate how you want BenBen tos ort the tasks!");
        }
        return s;
    }
}

