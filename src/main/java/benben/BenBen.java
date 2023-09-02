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
    public void todo(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        for (int i =  1; i < strSplit.length; i++) {
            des = des + " " + strSplit[i];
        }
        des = des.trim();

        if (des.length() == 0) {
            throw new BenBenException("Please follow the format: todo todo details");
        }

        Task t = new Todo(des);
        tasks.add(t);
        storage.write(tasks);
        ui.showAdd(t.toString(), tasks.size());
    }

    /**
     * Creates a Deadline task in the list with the specific description and deadline
     *
     * @param str the string command from the user
     * @throws BenBenException when  the command from the user is of the wrong format
     */
    public void deadline(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        String ddl = "";
        boolean isDes = true;
        for (int i =  1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/by")) {
                isDes = false;
                continue;
            }

            if (isDes) {
                des = des + " " + strSplit[i];
            } else {
                ddl = ddl + " " + strSplit[i];
            }
        }
        des = des.trim();
        ddl = ddl.trim();

        if (des.length() == 0 || ddl.length() == 0 ) {
            throw new BenBenException("Please follow the format: deadline deadline details /by yyyy/mm/dd");
        }

        Task t = new Deadline(des, ddl);
        tasks.add(t);
        storage.write(tasks);
        ui.showAdd(t.toString(), tasks.size());
    }

    /**
     + Creates a Event task in the list with the specific description, start time and end time
     *
     * @param str the string command from the user
     * @throws BenBenException when  the command from the user is of the wrong format
     */
    public void event (String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        String start = "";
        String end = "";
        boolean isDes = true;
        boolean isStart = false;
        for (int i =  1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/from")) {
                isDes = false;
                isStart = true;
                continue;
            }
            if (strSplit[i].equals("/to")) {
                isStart = false;
                continue;
            }


            if (isDes && !isStart) {
                des = des + " " + strSplit[i];
            } else if (!isDes && isStart) {
                start = start + " " + strSplit[i];
            } else {
                end = end + " " + strSplit[i];
            }
        }
        des = des.trim();
        start = start.trim();
        end = end.trim();

        if (des.length() == 0 || start.length() == 0 || end.length() == 0 ) {
            throw new BenBenException("Please follow the format: event event details /from yyyy-MM-dd HH-mm /to yyyy-MM-dd HH-mm");
        }

        Task t = new Event(des, start, end);
        tasks.add(t);
        ui.showAdd(t.toString(), tasks.size());
        storage.write(tasks);
    }

    /**
     * Iterates the list and prints out the tasks currently in the task list and their status
     */
    public void iterList() {
        ui.showList(tasks);
    }

    /**
     * Marks a given task as done
     *
     * @param str the string command from the suer
     * @throws BenBenException if the command is not of the required format or if the target task does not exist
     */
    public void mark(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to mark!");
        }

        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to mark!");
        }
        int x;

        try {
            x = Integer.parseInt(strSplit[1]);
            tasks.get(x - 1).mark();
            ui.showMark(tasks.get(x - 1).toString());
            storage.write(tasks);
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
    public void unmark(String str) throws BenBenException{

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
            ui.showUnmark(tasks.get(x - 1).toString());
            storage.write(tasks);
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
    public void remove(String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to remove!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to remove!");
        }
        int x;

        try {
            x = Integer.parseInt(strSplit[1]);

            Task temp = tasks.get(x - 1);
            tasks.remove(x - 1);

            ui.showRemove(temp.toString(), tasks.size());
            storage.write(tasks);
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new BenBenException("The task you are trying to remove does not exist!");
        }
    }

    /**
     * Exits the program
     */
    public void find(String str) throws BenBenException{
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
        ui.showMatching(relevantTasks);
    }

    public void exit() {
        ui.showExit();
        System.exit(0);
    }

    /**
     * Starts the chatbot
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String next = sc.nextLine();
                Parser.parse(this, next);
            } catch (BenBenException e) {
                ui.show(e.toString());
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws BenBenException if there is any error and prints teh error message
     */
    public static void main(String[] args) throws BenBenException {
        new BenBen("./src/main/java/tasks.txt").run();
    }
}

