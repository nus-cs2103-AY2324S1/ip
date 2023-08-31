import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static boolean isEnd = false;
    private final ArrayList<Task> taskList = new ArrayList<>();
    static String greeting = "______________________________________\n"
            + "Hi, I'm Chatty\n"
            + "What do you need to do today?\n"
            + "______________________________________";
    static String goodbye = "______________________________________\n"
            + "Bye. Don't come back!\n"
            + "______________________________________";

    private void removeFromList(int index) throws DukeException {
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

    private void addToList(String str) throws DukeException {
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
            if (!str.contains("/from") || !str.contains("/to")) {
                throw new DukeException("When's the event? Write it explicitly."
                        + "eg. Holiday /from 2023-12-07 1800 /to 2023-12-20 1800");
            }
            int indexFrom = str.lastIndexOf("/from");
            int indexTo = str.lastIndexOf("/to");
            try {
                t = new Event(str.substring(6, indexFrom - 1),
                        str.substring(indexFrom + 6, indexTo - 1), str.substring(indexTo + 4));
            } catch (DateTimeParseException parseError) {
                throw new DukeException("Enter a proper date in the YYYY-MM-DD format."
                        + "eg. Holiday /from 2023-12-07 1800 /to 2023-12-20 1800");
            }
        } else if (str.startsWith("deadline")) {
            if (str.length() <= 9) {
                throw new DukeException("So um, what exactly do you need to do? Add it as the description of the deadline.");
            }
            if (!str.contains("/by")) {
                throw new DukeException("When's the deadline? Write it explicitly."
                        + "eg. Assignment /by 2023-12-12 1800");
            }
            int indexBy = str.lastIndexOf("/by");
            try {
                t = new Deadline(str.substring(9, indexBy - 1), str.substring(indexBy + 4));
            } catch (DateTimeParseException parseError) {
                throw new DukeException("Enter a proper date in the YYYY-MM-DD format."
                        + "eg. Assignment /by 2023-12-12 1800");
            }
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

    private void listTasks() {
        System.out.println("______________________________________");
        for (int i=1; i<=taskList.size(); i++) {
            Task t = taskList.get((i-1));
            System.out.format("%d. " + t.toString() + "\n", i);
        }
        System.out.println("______________________________________\n");
    }

    private boolean awaitCommand(String command) {
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
                this.writeData();
            }
            return true;
        } catch (DukeException e) {
            System.out.println("______________________________________\n");
            e.printMessage();
            System.out.println("______________________________________\n");
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            return true;
        }
    }

    private void loadData() throws IOException {
        Scanner sc = new Scanner(new File("data/duke.txt"));
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split("\\|");
            for (int i = 0; i < line.length; i++) {
                line[i] = line[i].strip();
            }

            switch (line[0]) {
                case "T":
                    taskList.add(new ToDo(line[2]));
                    break;
                case "D":
                    taskList.add(new Deadline(line[2], line[3]));
                    break;
                case "E":
                    taskList.add(new Event(line[2], line[3], line[4]));
                    break;
            }
        }
    }

    private void writeData() throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task t: taskList) {
            fw.write(t.toWriteString()+"\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        try {
            if (!Files.isDirectory(Paths.get("data/"))) {
                Files.createDirectories(Paths.get("data/"));
            }

            if (!Files.exists(Paths.get("data/duke.txt"))) {
                Files.createFile(Paths.get("data/duke.txt"));
            }
            duke.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(greeting);
        Scanner sc = new Scanner(System.in);
        while (isEnd == false) {
            String command = sc.nextLine();
            duke.awaitCommand(command);
        }
        System.out.println(goodbye);
    }
}
