package duke.taskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import duke.dukeException.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Contains the duke.task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> list = new ArrayList<>();


    /**
     * Constructor for TaskList.
     */
    public TaskList() {

    }


    /**
     * Get the list.
     * @return ArrayList returns the ArrayList.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Prints a horizontal line.
     */
    public static void printHorizontalLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    /**
     * Deletes a duke.task from ArrayList.
     * @param command The duke.command specifying which duke.task to delete.
     */
    public String deleteTask(String command) {
        String[] splitCommand = command.split(" ");
        String strToPrint = "";
        try {
            if (splitCommand.length != 2) {
                throw new DukeException("Please insert a numerical value to delete");
            }
            Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
            if (!pattern.matcher(splitCommand[1]).matches()) {
                throw new DukeException("Please enter a numerical value");
            } else {
                if (Integer.parseInt(splitCommand[1]) > list.size()) {
                    throw new DukeException("The delete value is out of range");
                }
            }
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return e.getMessage();
        }
        strToPrint += ("Noted. I've removed this duke.task:\n");
        int indexToRemove = Integer.parseInt(command.substring(7)) - 1;
        Task taskToRemove = list.get(indexToRemove);
        strToPrint += (taskToRemove) + "\n";
        list.remove(indexToRemove);
        try {
            File myObj = new File("./src/main/data/tmpDuke.txt");
            myObj.createNewFile();
            int currLine = 0;
            File currFile = new File("./src/main/data/duke.txt");
            Scanner myReader = new Scanner(currFile);
            while (myReader.hasNextLine()) {
                if (indexToRemove == currLine) {
                    String data = myReader.nextLine();
                    currLine++;
                    continue;
                }
                currLine++;
                String data = myReader.nextLine();
                if (data.equals("")) {
                    break;
                }
                FileWriter myWriter = new FileWriter("./src/main/data/tmpDuke.txt", true);
                myWriter.write(data + "\n");
                myWriter.close();
            }
            myReader.close();
            new File("./src/main/data/tmpDuke.txt").renameTo(currFile);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "An error occurred";
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "An error occurred";
        }
        return strToPrint += ("Now you have " + list.size() + " tasks in the list.");
    }


    /**
     * Adds a event into ArrayList.
     * @param command The duke.command specifying what Event to add.
     */
    public String addEvent(String command) {
        String[] splitCommand = command.split(" ");
        String strToPrint = "";
        try {
            boolean hasFrom = false;
            boolean hasTo = false;


            if (splitCommand.length == 1) {
                throw new DukeException("duke.task.Event Argument Empty");
            }

            for (int i = 0; i < splitCommand.length; i++) {
                if (splitCommand[i].equals("/from")) {
                    hasFrom = true;
                    if (i == 1) {
                        throw new DukeException("There is no description");
                    }
                    if (i == splitCommand.length - 1) {
                        throw new DukeException("Please insert /from dates");
                    }
                    if (splitCommand[i + 1].equals("/to")) {
                        throw new DukeException("Please insert /from dates");
                    }
                }

                if (hasFrom && splitCommand[i].equals("/to")) {
                    hasTo = true;
                    if (i == splitCommand.length - 1) {

                        throw new DukeException("Please insert /to dates");
                    }
                }
            }

            if (hasFrom == false) {
                throw new DukeException("Please insert event is /from when");
            } else if (hasTo == false) {
                throw new DukeException("Please insert event is /to when");
            }
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return e.getMessage();
        }
        printHorizontalLine();
        strToPrint += ("Got it, I've added this event.\n");
        String currStr = command.substring(6);
        int fromIndex = currStr.indexOf("/");
        String description = currStr.substring(0, fromIndex);
        currStr = currStr.substring(fromIndex + 6);
        int toIndex = currStr.indexOf("/");
        String from = currStr.substring(0, toIndex);
        String to = currStr.substring(toIndex + 4);
        Task eventTask = new Event(description, from, to);
        list.add(eventTask);
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write("E | 0 | " + description + "| " + from + "| " + to + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "An error occurred";
        }
        strToPrint += (eventTask) + "\n";
        return strToPrint += ("Now you have " + list.size() + " tasks in the list.");
    }


    /**
     * Add a Todo into the ArrayList.
     * @param command The duke.command specifying what todo to add.
     */
    public String addTodo(String command) {

        String[] splitCommand = command.split(" ");

        String strToPrint = "";

        try {
            if (splitCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return e.getMessage();
        }
        strToPrint += ("Got it, I've added this duke.task.\n");
        String description = command.substring(5);
        Task todoTask = new Todo(description);
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write("T | 0 | " + description + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "An error occurred";
        }

        list.add(todoTask);
        strToPrint += (todoTask) + "\n";
        return strToPrint += ("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Add a deadline into the list.
     * @param command Command specifying what deadline to add.
     */
    public String addDeadline(String command) {
        String[] splitCommand = command.split(" ");

        String strToPrint = "";

        try {
            boolean hasBy = false;

            if (splitCommand.length == 1) {
                throw new DukeException("duke.task.Deadline Argument Empty");
            }

            for (int i = 0; i < splitCommand.length; i++) {
                if (splitCommand[i].equals("/by")) {
                    hasBy = true;
                    System.out.println(i);
                    if (i == 1) {
                        throw new DukeException("There is no description");
                    }
                    if (i == splitCommand.length - 1) {
                        throw new DukeException("Please insert the deadline");
                    }
                }
            }

            if (hasBy == false) {
                throw new DukeException("Please insert deadline is /by when");
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        strToPrint += ("Got it, I've added this deadline.\n");
        String currStr = command.substring(9);
        int dateIndex = currStr.indexOf("/");
        String date = currStr.substring(dateIndex + 4);
        String description = currStr.substring(0, dateIndex);
        Task deadlineTask;
        try {
            LocalDate d1 = LocalDate.parse(date);
            deadlineTask = new Deadline(description, date, d1);
        } catch (DateTimeParseException e) {
            deadlineTask = new Deadline(description, date);;
        }

        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write("D | 0 | " + description + "| " + date + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "An error occurred";
        }

        list.add(deadlineTask);
        strToPrint += (deadlineTask) + "\n";
        return strToPrint += ("Now you have " + list.size() + " tasks in the list.");
    }

    /**
     * Mark the duke.task if duke.task is undone.
     * @param index Specifying which duke.task in the ArrayList.
     */
    public String markCurrentTaskUndone(int index) {
        return list.get(index).markUndone();
    }

    /**
     * Mark the duke.task if duke.task is done,
     * @param index Specifying which duke.task in the ArrayList.
     */
    public String markCurrentTaskDone(int index) {
        list.get(index).markDone();
        return list.get(index).markDone();
    }

    /**
     * Print out all the tasks in the ArrayList.
     */
    public String printList() {
        String strToPrint = "Here are the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            int printIndex = i + 1;
            strToPrint = strToPrint + (printIndex + "." + list.get(i).toString() + "\n");
        }

        return strToPrint;
    }

    /**
     * Finds the tasks in the command and print them out.
     * @param command The command specifying the task.
     */
    public String findTasks(String command) {
        String[] splitCommand = command.split(" ");

        String strToPrint = "";

        try {
            if (splitCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return e.getMessage();
        }
        String toFind = command.substring(5);
        int printIndex = 0;
        strToPrint += ("Here are the matching tasks in your list: \n");
        for (Task task : list) {
            if (task.getDescription().contains(toFind)) {
                printIndex = printIndex + 1;
                strToPrint += (printIndex + "." + task.toString()) + "\n";

            }
        }
        return strToPrint;
    }


}
