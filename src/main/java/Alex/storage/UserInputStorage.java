package Alex.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Alex.AlexException;
import Alex.tasks.*;


/**
 * A class that is used to deal with user data storage. For example, storing all the task(s) in TaskList into
 * the file Alex.txt after every termination of the chatbot execution. Similarly, restore all the task(s) in
 * Alex.txt into TaskList every time when the user run the chatbot.
 */
public class UserInputStorage {

    /**
     * A constant that is used to represent time format throughout this program. The time format is yyyy-MM-dd HHmm.
     */
    public static final DateTimeFormatter TIMEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * A static method that is used to store all the task(s) in TaskList to the file Alex.txt
     * after every termination of the Alex bot execution.
     */
    public static String storeToFile() {
        try {
            FileWriter fw = new FileWriter("data/Alex.txt");
            int numberOfElements = TaskList.getNumberOfElements();
            assert numberOfElements >= 0 : "TaskList.getNumberOfElements should return non-negative integer";
            for (int i = 1; i <= numberOfElements; i++) {
                Task task = TaskList.getTaskByIndex(i);
                String taskInfo = "";
                if (task instanceof ToDos) {
                    taskInfo = "T " + task.getDescription() + (task.isDone() ? " 1" : " 0");
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    taskInfo = "D " + deadline.getDescription() + " /by " + deadline.getBy()
                            + (task.isDone() ? " 1" : " 0");
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    taskInfo = "E " + event.getDescription() + " /from " + event.getFromTime() + " /to "
                            + event.getToTime() + (task.isDone() ? " 1" : " 0");
                }

                if (i == numberOfElements) {
                    fw.write(taskInfo);
                } else {
                    fw.write(taskInfo + "\n");
                }
            }
            fw.close();
            return "User data is successfully stored";
        } catch (IOException | AlexException e) {
            return "Something went wrong when saving users data to Alex.txt: " + e.getMessage();
        }
    }

    /**
     * A static method that is used to load all the user data (task) from the file Alex.txt into TaskList
     * every time the user start running Alex bot.
     */
    public static void loadUserDateFromFile() {
        try {
            File userDataFile = new File("data/Alex.txt");
            if (!userDataFile.getParentFile().exists()) {
                userDataFile.getParentFile().mkdir();
            }
            Scanner userDataScanner = new Scanner(userDataFile);

            while (userDataScanner.hasNextLine()) {
                String userData = userDataScanner.nextLine();
                userData = userData.stripTrailing();
                String taskType = userData.substring(0, 1);
                int dataLength = userData.length();
                Task taskToBeStored = new Task("temp");
                int isDoneSymbol = Integer.parseInt(userData.substring(dataLength - 1, dataLength));
                assert (isDoneSymbol == 1 || isDoneSymbol == 0) : "isDoneSymbol in file should be either 1 or 0";
                boolean isDone = isDoneSymbol == 1 ? true : false;
                if (!userData.substring(1, 2).equals(" ") || (isDoneSymbol != 1 && isDoneSymbol != 0)) {
                    throw new AlexException("");
                }

                if (taskType.equals("T")) {
                    try {
                        String description = userData.substring(2, dataLength - 2);
                        taskToBeStored = new ToDos(description);
                        TaskList.store(taskToBeStored, false);
                    } catch (AlexException | IndexOutOfBoundsException e) {
                        String message = "OOPS!!! The program terminates because the data format at line "
                                + (TaskList.getNumberOfElements() + 1)
                                + "is wrong" + "\n"
                                + "The correct data format for a todo task should be: \n"
                                + "   "
                                + "T (description) (1/0) where 1 indicates done and 0 indicates undone";
                        System.out.println(message);
                        System.exit(0);
                    }
                } else if (taskType.equals("D")) {
                    try {
                        String descriptionWithTime = userData.substring(0, dataLength - 2);
                        String regex = "\\b /by \\b";
                        Pattern pattern = Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(descriptionWithTime);
                        if (!matcher.find()) {
                            throw new AlexException("");
                        }
                        int startIndex = matcher.start();
                        int endIndex = matcher.end();
                        String description = startIndex > 2 ? userData.substring(2, startIndex) : "";
                        String by = userData.substring(endIndex, dataLength - 2);
                        taskToBeStored = new Deadline(description, by);
                        TaskList.store(taskToBeStored, false);
                    } catch (AlexException | IndexOutOfBoundsException | DateTimeParseException e) {
                        String message = "OOPS!!! The program terminates because the data format at line "
                                + (TaskList.getNumberOfElements() + 1)
                                + " is wrong" + "\n"
                                + "The correct data format for a deadline task should be: \n"
                                + "   "
                                + "D (description) /by yyyy-MM-dd HHmm (1/0) where 1 indicates done and"
                                + " 0 indicates undone";
                        System.out.println(message);
                        System.exit(0);
                    }
                } else if (taskType.equals("E")) {
                    try {
                        String descriptionWithTime = userData.substring(0, dataLength - 2);
                        String regex = "\\b /from \\b";
                        Pattern pattern1 = Pattern.compile(regex);
                        Matcher matcher1 = pattern1.matcher(descriptionWithTime);
                        if (!matcher1.find()) {
                            throw new AlexException("");
                        }
                        int firstStart = matcher1.start();
                        int firstEnd = matcher1.end();

                        String regex2 = "\\b /to \\b";
                        Pattern pattern2 = Pattern.compile(regex2);
                        Matcher matcher2 = pattern2.matcher(descriptionWithTime);
                        if (!matcher2.find()) {
                            throw new AlexException("");
                        }
                        int secondStart = matcher2.start();
                        int secondEnd = matcher2.end();

                        String description = firstStart > 2 ? userData.substring(2, firstStart) : "";
                        String fromTime = userData.substring(firstEnd, secondStart);
                        String toTime = userData.substring(secondEnd, dataLength - 2);

                        taskToBeStored = new Event(description, fromTime, toTime);
                        TaskList.store(taskToBeStored, false);
                    } catch (AlexException | IndexOutOfBoundsException | DateTimeParseException e) {
                        String message = "OOPS!!! The program terminates because the data format at line "
                                + (TaskList.getNumberOfElements() + 1)
                                + "is wrong" + "\n"
                                + "The correct data format for an event task should be: \n"
                                + "   "
                                + "E (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm (1/0) "
                                + "where 1 indicates done and 0 indicates undone";
                        System.out.println(message);
                        System.exit(0);
                    }
                } else {
                    throw new AlexException("");
                }

                if (isDone) {
                    taskToBeStored.mark(false);
                }
            }

        } catch (FileNotFoundException e) {
            File userDataFile = new File("data/Alex.txt");
            try {
                userDataFile.createNewFile();
            } catch (IOException e2) {
                System.err.println("The file Alex.txt does not exits and there is an error creating the file: "
                        + e2.getMessage());
                System.exit(0);
            }
        } catch (AlexException | NumberFormatException | IndexOutOfBoundsException e) {
            String message = "OOPS!!! The program terminates because the data format at line "
                    + (TaskList.getNumberOfElements() + 1)
                    + "is wrong" + "\n"
                    + "The correct data format for a todo task should be: \n"
                    + "   "
                    + "T (description) (1/0) where 1 indicates done and 0 indicates undone\n"
                    + "The correct data format for a deadline task should be: \n"
                    + "   "
                    + "D (description) /by yyyy-MM-dd HHmm (1/0) where 1 indicates done and 0 indicates undone\n"
                    + "The correct data format for a event task should be: \n"
                    + "   "
                    + "E (description) /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm (1/0)"
                    + "where 1 indicates done and 0 indicates undone";
            System.out.println(message);
            System.exit(0);
        }
    }
}
