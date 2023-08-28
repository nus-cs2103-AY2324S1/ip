/**
 * ip Project Duke Chat bot
 *
 * @author Aaron Tay
 * @since 2023-08-24
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Obi-wan Kenobi";
        String line = "_____________________________________";
        String FILEPATH = "./src/main/data/duke.txt";
        File f = new File(FILEPATH);
        ArrayList<Task> taskList = new ArrayList<>();

        try {

            if (f.exists()) {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    addFileTask(taskList, s.nextLine());
                }
            } else {
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello There! I am " + name);
        System.out.println("What can I do for you?");
        System.out.println(line);

        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ", 2);

            if (command[0].equals("bye") && command.length == 1) {
                break;
            } else if (command[0].equals("list") && command.length == 1) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.print((i + 1) + "." + taskList.get(i).toString() + "\n");
                }
            } else if (command[0].equals("mark") || command[0].equals("unmark") || command[0].equals("delete")) {
                try {
                    editTask(command, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    addTask(taskList, command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println(line);
        }

        try {
            writeTaskToFile(FILEPATH, taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Bye. May the force be with you!");
    }

    private static void writeTaskToFile(String FILEPATH, ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);

        for (int i = 0; i < taskList.size(); i++) {
            fw.write(taskList.get(i).fileString() + "\n");
        }

        fw.close();
    }

    private static void addFileTask(ArrayList<Task> taskList, String line) {
        String[] task = line.split("\\|");
        String taskName = task[0].trim();
        String isMarked = task[1].trim();
        String taskDesc = task[2].trim();

        switch (taskName) {
        case "T":
            if (isMarked.equals("1")) {
                taskList.add(new ToDo(taskDesc, true));
            } else {
                taskList.add(new ToDo(taskDesc));
            }

            break;
        case "D":
            String taskDate = task[3].trim();

            if (isMarked.equals("1")) {
                taskList.add(new Deadline(taskDesc, true, taskDate));
            } else {
                taskList.add(new Deadline(taskDesc, taskDate));
            }

            break;
        case "E":
            String[] taskEvent = task[3].split("-");

            if (isMarked.equals("1")) {
                taskList.add(new Event(taskDesc, true, taskEvent[0].trim(), taskEvent[1].trim()));
            } else {
                taskList.add(new Event(taskDesc, taskEvent[0].trim(), taskEvent[1].trim()));
            }

            break;
        }
    }

    /**
     * Creates a Task and adds it to the task list.
     *
     * @param taskList The list of task created by user.
     * @param cmd The input command from user.
     * @throws InvalidCommandException Handles missing or wrong input commands by user.
     * @throws InvalidDescriptionException Handle empty task descriptions.
     */
    private static void addTask(ArrayList<Task> taskList, String[] cmd)
            throws InvalidCommandException, InvalidDescriptionException {

        if (cmd[0].equals("todo")) {
            if (cmd.length == 1 || cmd[1].equals("")) {
                throw new InvalidDescriptionException("todo");
            }

            taskList.add(new ToDo(cmd[1].trim()));
        } else if (cmd[0].equals("deadline")) {

            if (cmd.length == 1 || cmd[1].equals("") || cmd[1].trim().charAt(0) == '/') {
                throw new InvalidDescriptionException("deadline");
            }

            String[] task = cmd[1].split("/by ", 2);
            if (task.length == 1 || task[1].equals("")) {
                throw new InvalidCommandException("☹ OOPS!!! Need to include /by date for deadline.");
            }

            taskList.add(new Deadline(task[0].trim(), task[1].trim()));
        } else if (cmd[0].equals("event")) {

            if (cmd.length == 1 || cmd[1].equals("") || cmd[1].trim().charAt(0) == '/') {
                throw new InvalidDescriptionException("description");
            }

            String[] event = cmd[1].split("/from ", 2);

            if (event.length == 1 || event[1].equals("")) {
                throw new InvalidCommandException("☹ OOPS!!! Need to include /from date for an event.");
            }

            String[] dates = event[1].split("/to ", 2);

            if (dates.length == 1 || dates[1].equals("")) {
                throw new InvalidCommandException("☹ OOPS!!! Need to include /to date for an event.");
            }

            taskList.add(new Event(event[0].trim(), dates[0].trim(), dates[1].trim()));
        } else {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(taskList.get(taskList.size() - 1).toString());
        System.out.println("Now you have " + (taskList.size()) + " tasks in the list.");
    }

    /**
     * Edits the task specified. Can be either marking the task as done, unmarking the task or deleting the task.
     *
     * @param cmd The input command from user.
     * @param taskList The list of task created by user.
     * @throws InvalidCommandException Handles missing or wrong input commands by user.
     * @throws InvalidIndexException Handles invalid task index given by user.
     */
    public static void editTask (String[] cmd, ArrayList<Task> taskList) throws InvalidCommandException
            , InvalidIndexException {
        String regex = "-?\\d+";
        if (cmd.length == 1) {
            throw new InvalidCommandException("Need to include index for task marking!");
        }

        if (cmd[1] == " ") {
            throw new InvalidCommandException("Please include index for task marking");
        }

        if (!cmd[1].matches(regex)) {
            throw new InvalidCommandException("Can only use integers as index for marking!");
        }

        int pos = Integer.parseInt(cmd[1]);

        if (pos > taskList.size() || pos <= 0) {
            throw new InvalidIndexException();
        }

        if (cmd[0].equals("mark")) {
            taskList.get(pos - 1).markTask();
            System.out.println("Nice! I've marked this task as done:");
        } else if (cmd[0].equals("unmark")){
            taskList.get(pos - 1).unmarkTask();
            System.out.println("OK, I've marked this task as not done yet:");
        } else {
            System.out.println("Noted. I've removed this task:");
        }

        System.out.println(taskList.get(pos - 1).toString());
        if (cmd[0].equals("delete")) {
            taskList.remove(pos - 1);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        }
    }
}