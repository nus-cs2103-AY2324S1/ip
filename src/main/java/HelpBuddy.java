import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A HelpBuddy class that produces messages to user.
 */

public class HelpBuddy {
    private static String DIR = "./data";
    private static String FILENAME = "helpbuddy.txt";
    private static String DATAPATH = String.valueOf(Paths.get(HelpBuddy.DIR, HelpBuddy.FILENAME));

    /** A string that produces a line to segment messages produced. */
    private final static String horizontal = "    ____________________________________________________________";
    /** An ArrayList that holds everything the user has typed into the chatbot. */
    private ArrayList<Task> userInput = new ArrayList<>(100);

    public HelpBuddy() {
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    /**
     * @return hello message.
     */
    public static String printHelloMessage() {
        return "Hello! I'm HelpBuddy.\n" + "    What can I do for you?\n";
    }

    /**
     * @return bye message.
     */
    public static String printByeMessage() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * A method that segments replies from HelpBuddy.
     *
     * @param s The string that HelpBuddy replies.
     */
    private void printMessageBlock(String s) {
         System.out.println(horizontal + "\n" + "    " + s + horizontal + "\n");
    }

    /**
     * A method that prints the response for command mark.
     * @param i The value keyed in by user.
     * @throws HelpBuddyException If the input value by user is invalid.
     */
    public void printMarkOutput(int i) throws HelpBuddyException {
        int numOfTasks = userInput.size();
        if (numOfTasks == 0 || i < 0 || i > numOfTasks - 1) {
            throw new HelpBuddyException("Invalid task number.\n");
        } else {
            Task t = this.userInput.get(i);
            String status = t.getStatusIcon();
            if (status == "X") {
                throw new HelpBuddyException("Task is already marked as done.\n");
            } else {
                t.updateDone();
                printMessageBlock("Nice! I've marked this task as done:\n      " + t + "\n");
            }
        }
    }

    /**
     * A method that prints the response for command unmark.
     * @param i The value keyed in by user.
     * @throws HelpBuddyException If the input value by user is invalid.
     */
    public void printUnmarkOutput(int i) throws HelpBuddyException {
        int numOfTasks = userInput.size();
        if (numOfTasks == 0 || i < 0 || i > numOfTasks - 1) {
            throw new HelpBuddyException("Invalid task number.\n");
        } else {
            Task t = this.userInput.get(i);
            String status = t.getStatusIcon();
            if (status == " ") {
                throw new HelpBuddyException("Task is not marked as done.\n");
            } else {
                t.updateDone();
                printMessageBlock("OK, I've marked this task as not done yet:\n      " + t + "\n");
            }
        }
    }

    /**
     * A method that prints response for command delete.
     * @param i The value keyed in by user
     * @throws HelpBuddyException If the input value by user is invalid.
     */
    public void printDeleteOutput(int i) throws HelpBuddyException {
        int numOfTasks = userInput.size();
        if (numOfTasks == 0 || i < 0 || i > numOfTasks - 1) {
            throw new HelpBuddyException("Invalid task number.\n");
        } else {
            String output = "Noted. I've removed this task:\n      ";
            Task deletedTask = this.userInput.get(i);
            this.userInput.remove(i);
            numOfTasks--;
            printMessageBlock(output + deletedTask + "\n    Now you have " + numOfTasks + " tasks in this list.\n");
        }
    }

    /**
     * A method that displays error messages from HelpBuddy.
     *
     * @param error A String that contains error details.
     */
    private void printErrorMessage(String error) {
        printMessageBlock(error);
    }

    private void loadData() throws IOException {
        try {
            File data = new File(DATAPATH);
            Scanner fileReader = new Scanner(data);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                readEntry(entry);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            this.createDatabase();
        } catch (HelpBuddyException e) {
            printErrorMessage(e.getMessage());
        }
    }

    private void readEntry(String entry) throws HelpBuddyException {
        String[] fields = entry.split("\\|");
        Task taskToAdd;
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        switch (fields[0]) {
        case "T":
            taskToAdd = new ToDo(fields[2]);
            break;
        case "D":
            taskToAdd = new Deadline(fields[2], LocalDateTime.parse(fields[3], formatter));
            break;
        case "E":
            String[] fromToFields = fields[3].split(" to ");
            taskToAdd = new Event(fields[2],
                    LocalDateTime.parse(fromToFields[0], formatter),
                    LocalDateTime.parse(fromToFields[1], formatter));
            break;
        default:
            throw new HelpBuddyException("Invalid data input in data file!");
        }

        if (Integer.parseInt(fields[1]) == 1) {
            taskToAdd.updateDone();
        }

        this.userInput.add(taskToAdd);
    }

    private void createDatabase() throws IOException {
        File data = new File(DATAPATH);
        File dataDir = new File(DIR);
        dataDir.mkdir();
        data.createNewFile();
    }

    private void saveData() throws IOException {
        FileWriter fw = new FileWriter(DATAPATH);
        userInput.forEach(task -> {
            try {
                fw.write(task.stringifyTask());
                fw.write(System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Error when saving data!");
            }
        });
        fw.close();
    }

    /**
     * A method that displays to users HelpBuddy's response to their input.
     *
     * @param sc A scanner to read user inputs
     * @throws HelpBuddyException If the command entered by user is invalid
     */
    public void outputMessage(Scanner sc)  {
        printMessageBlock(HelpBuddy.printHelloMessage());

        String markPattern = "^mark.*";
        String unmarkPattern = "^unmark.*";
        String toDoPattern = "^todo.*";
        String deadlinePattern = "^deadline.*";
        String eventPattern = "^event.*";
        String deletePattern = "^delete.*";
        String inputMessage = sc.nextLine();

        while(inputMessage != "") {
            try {
                if (inputMessage.matches(markPattern)) {
                    if (inputMessage.matches("mark")) {
                        throw new HelpBuddyException("Please enter a task number.\n");
                    }
                    String taskIndex = inputMessage.replaceAll("mark\\s+", "");
                    if (!taskIndex.isBlank()) {
                        int intTaskValue = Integer.valueOf(taskIndex) - 1;
                        printMarkOutput(intTaskValue);
                    } else {
                        throw new HelpBuddyException("Please enter a task number.\n");
                    }
                } else if (inputMessage.matches(unmarkPattern)) {
                    if (inputMessage.matches("unmark")) {
                        throw new HelpBuddyException("Please enter a task number.\n");
                    }
                    String taskIndex = inputMessage.replaceAll("unmark\\s+", "");
                    if (!taskIndex.isBlank()) {
                        int intTaskValue = Integer.valueOf(taskIndex) - 1;
                        printUnmarkOutput(intTaskValue);
                    } else {
                        throw new HelpBuddyException("Please enter a task number.\n");
                    }
                } else if (inputMessage.matches(deletePattern)) {
                    if (inputMessage.matches("delete")) {
                        throw new HelpBuddyException("Please enter a task number.\n");
                    }
                    String taskIndex = inputMessage.replaceAll("delete\\s+", "");
                    if (!taskIndex.isBlank()) {
                        int intTaskValue = Integer.valueOf(taskIndex) - 1;
                        printDeleteOutput(intTaskValue);
                    } else {
                        throw new HelpBuddyException("Please enter a task number.\n");
                    }
                } else if (inputMessage.equalsIgnoreCase("list")){
                    Object[] inputArray = this.userInput.toArray();
                    String outputMessage = "Here are the tasks in your list:\n";
                    for (int i = 0; i < inputArray.length; i++) {
                        int index = i + 1;
                        Task curr = (Task) inputArray[i];
                        outputMessage +=  "    " + index + "." + curr + "\n";
                    }
                    printMessageBlock(outputMessage);
                } else if (inputMessage.equalsIgnoreCase("bye")) {
                    this.saveData();
                    printMessageBlock(HelpBuddy.printByeMessage());
                    break;
                } else {
                    Task t = null;
                    if (inputMessage.matches(deadlinePattern)) {
                        String taskDetails = inputMessage.replaceAll("deadline", "");
                        if (taskDetails.isBlank()) {
                            t = new Deadline("", null);
                        }
                        String[] taskDetailArray = taskDetails.split("/by");
                        if (taskDetailArray.length == 1) {
                            String[] taskDetailErrorArray = taskDetailArray[0].split("/");
                            if (taskDetailErrorArray.length == 1) {
                                t = new Deadline(taskDetails, null);
                            } else {
                                throw new HelpBuddyException("Please use /by to indicate deadline.\n");
                            }
                        }
                        String by = taskDetailArray[1];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd/MM/yy HH:mm");
                        LocalDateTime deadline = LocalDateTime.parse(by, formatter);
                        t = new Deadline(taskDetailArray[0], deadline);
                    } else if (inputMessage.matches(toDoPattern)) {
                        String taskDetails = inputMessage.replaceAll("todo", "");
                        t = new ToDo(taskDetails);
                    } else if (inputMessage.matches(eventPattern)) {
                        String taskDetails = inputMessage.replaceAll("event", "");
                        if (taskDetails.isBlank()) {
                            t = new Event("", null, null);
                        }
                        String[] taskDetailArray = taskDetails.split("/from");
                        String[] taskDetailErrorArray = taskDetails.split("/");
                        if (taskDetailArray.length == 1) {
                            if (taskDetailErrorArray.length == 1) {
                                t = new Event(taskDetails, null, null);
                            } else {
                                throw new HelpBuddyException("Please enter and use /from for start time first then\n    /to for end time for event.\n");
                            }
                        }
                        String[] timeFromTo = taskDetailArray[1].split(" /to");
                        String from = timeFromTo[0];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd/MM/yy HH:mm");
                        LocalDateTime startTime = LocalDateTime.parse(from, formatter);
                        if (timeFromTo.length == 1) {
                            t = new Event(taskDetails, startTime, null);
                        }
                        String to = timeFromTo[1];
                        LocalDateTime endTime = LocalDateTime.parse(to, formatter);
                        if (startTime.isAfter(endTime)) {
                            throw new HelpBuddyException("End time must be after the start time!\n");
                        } else if  (startTime.isEqual(endTime)) {
                            throw new HelpBuddyException("Both start and end time are the same, please check!\n");
                        }
                        t = new Event(taskDetailArray[0], startTime, endTime);
                    } else {
                        throw new HelpBuddyException("I'm sorry, but I don't know what that means.\n");
                    }
                    String output = "Got it. I've added this task:\n      ";
                    int numOfTasks = userInput.size() + 1;
                    this.userInput.add(t);
                    output += t + "\n    Now you have " + numOfTasks + " tasks in this list.\n";
                    printMessageBlock(output);
                }
            } catch (HelpBuddyException e) {
                printErrorMessage(e.getMessage());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            } catch (DateTimeParseException e) {
                printErrorMessage("Please enter the start/end time in the format of <DD/MM/YY HH:MM>!\n");
            }
            inputMessage = sc.nextLine();
        }
    }
}
