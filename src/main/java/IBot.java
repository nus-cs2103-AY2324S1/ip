import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Objects;


/**
 * A transition class that contains the Task array,
 * parses command and invoke functions
 */
public class IBot {
    /* The Task array*/
    private final ArrayList<Task> lst;
    private final String filePath = "data/Duke.txt";

    IBot() throws IOException {
        lst = new ArrayList<>();

        String home = System.getProperty("user.home");
        boolean directoryExists = new java.io.File(home + "\\Documents\\ip\\data").exists();
        if(!directoryExists) {
            java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "ip", "data");
            directoryExists = java.nio.file.Files.exists(path);
            if (!directoryExists) {
                throw new IOException("There is no 'data' folder, please create one");
            }
        }
        File f = new File(filePath);
        if(f.exists()) {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String temp = s.nextLine();
                existTasks(temp);
            }
        }else {
            f.createNewFile();
        }
    }

    private void existTasks(String s) {
        String[] temp = s.split(" \\| ");
        int n = temp.length;
        Task t;
        if (n == 2) {
            t = Task.of(temp[1]);
        } else if (n == 3) {
            t = Task.of(temp[1], temp[2]);
        } else {
            t = Task.of(temp[1], temp[2], temp[3]);
        }

        if (Objects.equals(temp[0], "1")) {t.mark();}
        lst.add(t);
    }

    /**
     * All tasks are added to the array by this method
     * @param t The Task added
     */
    private void addTask(Task t) throws IOException {
        System.out.println("Got it. I've added this task:");
        lst.add(t);
        System.out.println("  " + t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(t.getText());
        fw.close();
    }

    /**
     * Add a type of Task that only has name
     * @param cmd The command after the keyword "todo",
     *            which only contains its name
     * @throws DukeException If there are nothing after keyword,
     * throw an exception
     */
    private void todo(String cmd) throws DukeException, IOException {
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
    private void deadline(String cmd) throws DukeException, IOException {
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
    private void event(String cmd) throws DukeException, IOException {
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
    private void mark(String cmd) throws DukeException, IOException {
        int index = getIndex(cmd);
        if (index != -1) {
            Task t = lst.get(index - 1);
            if (t.mark()) {
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t + "\n");
                changeFile();
            } else{
                throw new DukeException("☹ OOPS!!! This task has already be marked!\n");
            }
        }
    }

    /**
     * The method used to unmark the specific Task
     * @param cmd The command contains the target Task index
     * @throws DukeException Throw the exception formed during getting the index
     */
    private void unmark(String cmd) throws DukeException, IOException {
        int index = getIndex(cmd);
        if (index != -1) {
            Task t = lst.get(index - 1);
            if (t.unmark()) {
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(t + "\n");
                changeFile();
            } else{
                throw new DukeException("☹ OOPS!!! This task hasn't be marked yet!\n");
            }
        }

    }

    /**
     * The method used to delete the specific Task
     * @param cmd The command contains the target Task index
     * @throws DukeException Throw the exception formed during getting the index
     */
    private void delete(String cmd) throws DukeException, IOException {
        if (getIndex(cmd) != -1) {
            Task delete = lst.get(getIndex(cmd) - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + delete);
            lst.remove(lst.get(getIndex(cmd) - 1));
            System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
            changeFile();
        }
    }

    private void changeFile() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw = new FileWriter(filePath, true);
        for (Task curr : lst) {
            fw.write(curr.getText() + "\n");
        }
        fw.close();
    }

    /**
     * The method used to list all Tasked stored in the arraylist.
     */
    private void list() {
        if (!lst.isEmpty()) {
            System.out.println("Here are the tasks in your list:");
            lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + "." + x));
        } else {
            System.out.println("There is no task in your list\n");
        }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
