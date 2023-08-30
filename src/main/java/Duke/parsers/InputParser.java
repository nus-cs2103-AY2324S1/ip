package Duke.parsers;


import Duke.exceptions.DukeException;
import Duke.fileHandler.Storage;
import Duke.tasks.Deadline;
import Duke.tasks.Event;
import Duke.tasks.Task;
import Duke.tasks.Todo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class InputParser {


    /***
     * Checks user inputs, if invalid throws DukeException
     * @param str User input split by each word
     * @param task type of task - todo,event,deadline,mark/unmark
     * @throws DukeException
     */
    public static void inputChecker(String[] str, String task) throws DukeException{

        if (str.length <2 ) {
            throw new DukeException("☹ OOPS!!! The description of a " +task+ " cannot be empty.");
        }
    }

    /***
     * receives string of date and time, returns Date object
     * @param str
     * @return Date
     * @throws DukeException
     */
    public static Date dateParser(String str) throws DukeException{

        if (str.length() < 15){
            throw new DukeException("invalid date, must be of the form dd/mm/yyyy hhmm");
        }
        String newStr = str.substring(0,13) +":" + str.substring(13);
        System.out.println(newStr);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            Date d1 = df.parse(newStr);
            return d1;
        }
        catch (Exception e){
            System.out.println(e);
            throw new DukeException("invalid date");
        }
    }


    public static void parse(ArrayList<Task> tasks) {
        boolean flag = true;


        Scanner userInput = new Scanner(System.in);

        while (flag == true) {
            String input = userInput.nextLine();
            String[] splitStr = input.trim().split("\\s+");

            if (input.equals("bye")) {
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }

            //list out each task from Duke.tasks ArrayList
            else if (input.equals("list")) {
                System.out.println("  Here are the Duke.tasks in your list:");
                for (int i = 0; i < Task.getSize(); i++) {
                    int index = i + 1;
                    System.out.println("  " + index + "." + tasks.get(i).toString());

                }
            }
            //create Todo object
            else if (splitStr[0].equals("todo")) {
                try {
                    inputChecker(splitStr, "todo");
                    Todo t = new Todo(input.substring(5));
                    tasks.add(t);
                    Storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }
            //create deadline object, splitting the due date by "/" and stripping off the by:
            else if (splitStr[0].equals("deadline")) {
                try {
                    inputChecker(splitStr, "deadline");
                    String[] deadlineArr = input.split("/by ");
                    Date deadline = dateParser(deadlineArr[1]);

                    Deadline d = new Deadline(deadlineArr[0].substring(9), deadline);
                    tasks.add(d);
                    Storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            //create event object, splitting the due date by "/" and stripping off the to: and from:
            else if (splitStr[0].equals("event")) {
                try {
                    inputChecker(splitStr, "event");
                    int startIndex = input.indexOf("/from ");
                    int endIndex = input.indexOf("/to");

                    Date from = dateParser(input.substring(startIndex + 6, endIndex - 1));
                    Date to = dateParser(input.substring(endIndex + 4));

                    Event e = new Event(input.substring(6, startIndex), from, to);
                    tasks.add(e);
                    Storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            //mark or unmark an existing task
            else if (splitStr[0].equals("mark") || splitStr[0].equals("unmark")) {
                try {
                    inputChecker(splitStr, "mark/unmark");
                    int index = Integer.parseInt(splitStr[1]);
                    Task item = tasks.get(index - 1);
                    item.setAction(splitStr[0]);
                    Storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            }

            //delete task from Duke.tasks ArrayList
            else if (splitStr[0].equals("delete")) {
                try {
                    inputChecker(splitStr, "delete");
                    int index = Integer.parseInt(splitStr[1]);
                    Task item = tasks.remove(index - 1);
                    item.delete();
                    Storage.saveTasks(tasks);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }

            //unknown command
            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("----------------------------------------");
        }
    }
}
