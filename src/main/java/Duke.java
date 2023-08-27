import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {

    /** name of ChatBot */
    private final String name = "Ken";

    /** store user Input in Task array */
    private ArrayList<Task> lists;

    /**
     * Initialize the fixed sized array.
     */
    public Duke() {
        this.lists = new ArrayList<>();
    }
    /**
     * Return the name of the Duke ChatBot.
     *
     * @return this.name.
     */
    private String getName() {
        return this.name;
    }

    /**
     * Return specific String output based on user Input.
     *
     * @param input User input from main.
     * @return output reply.
     */
    private String replyUser(String input) {
        String output;
        switch (input) {
            case "bye":
                output = "Bye. Hope to see you again soon!";
                break;
            case "barbie":
                output = "Hi barbie!";
                break;
            case "list":
                output = outputList();
                break;
            default:
                if (input.startsWith("mark ")) {
                    try {
                        Integer i = Integer.valueOf(input.substring(5));
                        if (i <= this.lists.size()) {
                            this.lists.get(i - 1).markAsDone();
                            output = "Nice! I've marked this task as done: \n" + this.lists.get(i - 1).toString();
                        } else {
                            output = "☹ OOPS!!! The number input does not exist.";
                        }
                    } catch (NumberFormatException err) {
                        output = "☹ OOPS!!! The number input does not exist.";
                    }
                } else if(input.startsWith("unmark ")) {
                    try {
                        Integer i = Integer.valueOf(input.substring(7));
                        if (i <= this.lists.size()) {
                            this.lists.get(i - 1).markAsUndone();
                            output = "OK, I've marked this task as not done yet: \n" + this.lists.get(i - 1).toString();
                        } else {
                            output = "☹ OOPS!!! The number input does not exist.";
                        }
                    } catch (NumberFormatException err){
                        output = "☹ OOPS!!! The number input does not exist.";
                    }
                } else {
                    if (input.startsWith("todo ")) {
                        String desc = input.substring(5);
                        if (desc.length() == 0) {
                            output = "☹ OOPS!!! The description of a todo cannot be empty.";
                        } else {
                            this.lists.add(new ToDo(desc));
                            output = "Got it. I've added this task: \n" + this.lists.get(this.lists.size() - 1) + "\n"
                                    + "Now you have " + this.lists.size() + " tasks in the list.";
                        }
                    } else if (input.startsWith("deadline ")) {
                        try {
                            int index = input.indexOf("/by");
                            String date;
                            if (isValidDate(input.substring(index + 4))) {
                                date = parseDate(input.substring(index + 4));
                            } else {
                                date = input.substring(index + 4);
                            }
                            this.lists.add(new Deadline(input.substring(9,index - 1), date));
                            output = "Got it. I've added this task: \n" + this.lists.get(this.lists.size() - 1) + "\n"
                                    + "Now you have " + this.lists.size() + " tasks in the list.";
                        } catch (StringIndexOutOfBoundsException err) {
                            output= "☹ OOPS!!! The deadline format is incorrect! \n" +
                                    "follow the format: deadline description /by end date";
                        }

                    } else if (input.startsWith("event ")) {
                        try {
                            int indexFrom = input.indexOf("/from");
                            int indexTo = input.indexOf("/to");
                            String dateFrom;
                            if (isValidDate(input.substring(indexFrom + 6,indexTo - 1))) {
                                dateFrom = parseDate(input.substring(indexFrom + 6,indexTo - 1));
                            } else {
                                dateFrom = input.substring(indexFrom + 6,indexTo - 1);
                            }
                            String dateTo;
                            if (isValidDate(input.substring(indexTo + 4))) {
                                dateTo = parseDate(input.substring(indexTo + 4));
                            } else {
                                dateTo = input.substring(indexTo + 4);
                            }
                            this.lists.add(new Event(input.substring(6,indexFrom - 1),
                                    dateFrom,
                                    dateTo));
                            output = "Got it. I've added this task: \n" + this.lists.get(this.lists.size() - 1) + "\n"
                                    + "Now you have " + this.lists.size() + " tasks in the list.";
                        } catch (StringIndexOutOfBoundsException err){
                            output= "☹ OOPS!!! The event format is incorrect! \n" +
                                    "follow the format: event description /from start date /to end date";
                        }
                    } else if (input.startsWith("delete ")) {
                        try {
                            Integer i = Integer.valueOf(input.substring(7));
                            if (i <= this.lists.size()) {
                                output = "Noted. I've removed this task: \n" + this.lists.get(i - 1).toString() +
                                "\nNow you have " + (this.lists.size() - 1) + " tasks in the list.";
                                this.lists.remove(i - 1);
                            } else {
                                output = "☹ OOPS!!! The number input does not exist.";
                            }
                        } catch (NumberFormatException err){
                            output = "☹ OOPS!!! The number input does not exist.";
                        }
                    } else {
                        output = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
                    }
                }
                break;
        }

        return output;
    }

    /**
     * Output all the user input.
     *
     * @return this.lists
     */
    private String outputList() {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list: ");
        int i = 1;
        for (Task val : this.lists) {
            output.append("\n").append(i).append(". ").append(val);
            i++;
        }

        return output.toString();
    }

    /**
     * Read task from duke.txt file and copy to ArrayList list.
     */
    private void readFile() {
        try {
            File f = new File("data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] task = s.nextLine().split("\\|");
                stringToList(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Copy every line of task from file to ArrayList list.
     * @param task String array that store task in file.
     */
    private void stringToList(String[] task) {

        switch (task[0]) {
        case "T":
            this.lists.add(new ToDo(task[1]));
            break;
        case "D":
            this.lists.add(new Deadline(task[1], task[3]));
            break;
        case "E":
            this.lists.add(new Event(task[1], task[3], task[4]));
            break;
        }

        if (task[2].equals("1")) {
            this.lists.get(this.lists.size() - 1).markAsDone();
        }
    }

    /**
     * Write the task list to file.
     */
    private void writeToFile() {
        try{
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task i : this.lists) {
                fw.write(i.fileFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("The file doesn't exist!");
        }
    }

    /**
     * check if the input is a valid Date.
     * valid date format: dd/MM/yyyy HHmm
     * @param input of String type
     * @return true if it is a valid date format
     */
    private boolean isValidDate(String input) {
        String[] splitInput = input.split("/");

        if (splitInput.length != 3) {
            return false;
        }

        if (!isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");

        if (yearAndTime.length != 2) {
            return false;
        }

        if (!isNumeric(yearAndTime[0]) || !isNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    /**
     * Convert a valid String date to a different format.
     *
     * @param input of String type.
     * @return new String date format: MMM dd yyyy hh:mm a
     */
    private String parseDate(String input) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime d1 = LocalDateTime.parse(input, formatter);

            DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
            return d1.format(returnFormatter);

        } catch (Exception e) {
            return input;
        }
    }

    /**
     * Check if the string input is a numeric characters.
     *
     * @param str of String type.
     * @return true if the str only contain numeric characters.
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static void main(String[] args) {

        Duke chatBot = new Duke();
        chatBot.readFile();

        String horLine = "____________________________________________________________";
        String userInput = "";
        Scanner input = new Scanner(System.in);

        System.out.println(horLine);
        System.out.println("Hello! I'm " + chatBot.getName() + "!");
        System.out.println("What can I do for you?");
        System.out.println(horLine);

        while (!userInput.equals("bye")) {
            userInput = input.nextLine();
            System.out.println(horLine);
            System.out.println(chatBot.replyUser(userInput));
            System.out.println(horLine);
            chatBot.writeToFile();
        }
    }
}
