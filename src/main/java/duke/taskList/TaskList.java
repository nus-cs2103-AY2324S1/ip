package duke.taskList;

import duke.dukeException.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    public void deleteTask(String command) {
        String splitCommand[] = command.split(" ");
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

        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Noted. I've removed this duke.task:");
        int indexToRemove = Integer.parseInt(command.substring(7)) - 1;
        Task taskToRemove = list.get(indexToRemove);
        System.out.println(taskToRemove);
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
                if (data.equals("")) { break; }
                FileWriter myWriter = new FileWriter("./src/main/data/tmpDuke.txt", true);
                myWriter.write(data + "\n");
                myWriter.close();
            }
            myReader.close();
            new File("./src/main/data/tmpDuke.txt").renameTo(currFile);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }


    /**
     * Adds a event into ArrayList.
     * @param command The duke.command specifying what Event to add.
     */
    public void addEvent(String command) {
//        event project meeting /from Mon 2pm /to 4pm
        String splitCommand[] = command.split(" ");

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
            }

            if (hasTo == false) {
                throw new DukeException("Please insert event is /to when");
            }

        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Got it, I've added this event.");
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
            myWriter.write(  "E | 0 | " + description + "| " + from + "| " + to +"\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println(eventTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }


    /**
     * Add a Todo into the ArrayList.
     * @param command The duke.command specifying what todo to add.
     */
    public void addTodo(String command) {

        String splitCommand[] = command.split(" ");

        try {
            if (splitCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Got it, I've added this duke.task.");
        String description = command.substring(5);
        Task todoTask = new Todo(description);
        try {
            FileWriter myWriter = new FileWriter("./src/main/data/duke.txt", true);
            myWriter.write(  "T | 0 | " + description + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        list.add(todoTask);
        System.out.println(todoTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }

    /**
     * Add a deadline into the list.
     * @param command Command specifying what deadline to add.
     */
    public void addDeadline(String command) {
        String splitCommand[] = command.split(" ");

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

        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        printHorizontalLine();
        System.out.println("Got it, I've added this deadline.");
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
            myWriter.write(  "D | 0 | " + description + "| " + date + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        list.add(deadlineTask);
        System.out.println(deadlineTask);
        System.out.println("Now you have " + list.size() + " tasks in the list." );
        printHorizontalLine();
    }

    /**
     * Mark the duke.task if duke.task is undone.
     * @param index Specifying which duke.task in the ArrayList.
     */
    public void markCurrentTaskUndone(int index) {
        list.get(index).markUndone();
    }

    /**
     * Mark the duke.task if duke.task is done,
     * @param index Specifying which duke.task in the ArrayList.
     */
    public void markCurrentTaskDone(int index) {
        list.get(index).markDone();
    }

    /**
     * Print out all the tasks in the ArrayList.
     */
    public void printList() {
        printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int printIndex = i + 1;
            System.out.println(printIndex + "." + list.get(i).toString());
        }
        printHorizontalLine();
    }

    /**
     * Finds the tasks in the command and print them out.
     * @param command The command specifying the task.
     */
    public void findTasks(String command) {
        String splitCommand[] = command.split(" ");

        try {
            if (splitCommand.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
        }
        catch (DukeException e) {
            printHorizontalLine();
            System.out.println(e.getMessage());
            printHorizontalLine();
            return;
        }
        String toFind = command.substring(5);
        int printIndex = 0;
        printHorizontalLine();
        System.out.println("Here are the matching tasks in your list: ");
        for (Task task : list) {
            if (task.getDescription().contains(toFind)) {
                printIndex = printIndex + 1;
                System.out.println(printIndex + "." + task.toString());

            }
        }
        printHorizontalLine();
    }


}
