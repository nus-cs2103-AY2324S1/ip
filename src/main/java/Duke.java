import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    // list exists so that duke.txt file does not have to be loaded so many times for mark,
    // unmark and delete operations
//    public static ArrayList<Task> list = loadDataFromFile();
    enum Day {
        Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday
    }

    enum Month {
        Jan, Feb, Mar, Apr, May, June, July, Aug, Sept, Oct, Nov, Dec
    }
    public static void main(String[] args) {
        printIntro();

//        ArrayList<Task> throwExceptionIfFileFormatIncorrect = loadDataFromFile();

        Scanner sc = new Scanner(System.in);
        // Get user command
        String cmd = sc.nextLine();

        // Exit if command is "bye"
        while (!cmd.equals("bye")) {
            try {
                String type = cmd.split(" ", 2)[0];

                // If cmd is "list", list items and wait for next command
                if (cmd.equals("list")) {
                    printListItems();
                } else if (type.equals("todo")) {
                    // Check if description is empty
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("todo");
                    }
                    String taskName = cmd.split(" ", 2)[1];
                    Task todo = new ToDo(taskName);
//                    list.add(todo);
                    appendTaskToFile(todo);
                    printAddTaskMessage(todo);
                } else if (type.equals("deadline")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("deadline");
                    }
                    String taskWithDeadline = cmd.split(" ", 2)[1];

                    if (hasNoDeadline(taskWithDeadline)) {
                        throw new NoDeadlineException();
                    }

                    String taskName = taskWithDeadline.split("/", 2)[0];
                    String deadlineDescription = taskWithDeadline.split("/", 2)[1];

                    try {
                        Task deadline = new Deadline(taskName, checkDeadline(deadlineDescription));
                        appendTaskToFile(deadline);
                        printAddTaskMessage(deadline);
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equals("event")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("event");
                    }
                    String taskWithDuration = cmd.split(" ", 2)[1];
                    String[] time = taskWithDuration.split("/");

                    // Check if there is a valid duration
                    if (time.length != 3) {
                        throw new IncompleteDurationException();
                    }
                    String taskName = time[0];
                    String starting = time[1];
                    String ending = time[2];
//                    String duration = starting + ending;
//
//                    int result = validStart(starting);
//                    if (result == -1) {
//                        throw new InvalidDurationException(duration);
//                    }
//                    if (!isValidEnd(ending, result)) {
//                        throw new InvalidDurationException(duration);
//                    }

// Assumes that starting and ending both start with "from" and "to" respectively
                    Task event = new Event(taskName, starting.split(" ", 2)[1].trim(), ending.split(" ", 2)[1].trim());
//                    list.add(event);
                    appendTaskToFile(event);
                    printAddTaskMessage(event);
                } else if (type.equals("delete")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("delete");
                    }

                    int taskNumber = -1;
                    String integer = cmd.split(" ", 2)[1];
                    try {
                        taskNumber = Integer.parseInt(integer);
                    } catch (Exception e) {
                        throw new InvalidIntegerException();
                    }

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }
//                    Task task = list.get(taskNumber - 1);
//                    list.remove(task);
                    Task task = markOrDeleteDataToFile(taskNumber - 1, "delete");
                    printDeleteTaskMessage(task);
                } else if (type.equals("mark")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("mark");
                    }

                    int taskNumber = -1;
                    String integer = cmd.split(" ", 2)[1];
                    try {
                        taskNumber = Integer.parseInt(integer);
                    } catch (Exception e) {
                        throw new InvalidIntegerException();
                    }

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

//                    Task task = list.get(taskNumber - 1);
//                    task.markTask();
                    Task task = markOrDeleteDataToFile(taskNumber - 1, "mark");
                    printMarkedTaskMessage(task);
                } else if (type.equals("unmark")) {
                    if (descriptionIsEmpty(cmd)) {
                        throw new InvalidDescriptionException("unmark");
                    }

                    int taskNumber = -1;
                    String integer = cmd.split(" ", 2)[1];
                    try {
                        taskNumber = Integer.parseInt(integer);
                    } catch (Exception e) {
                        throw new InvalidIntegerException();
                    }

                    if (!isValidTaskNumber(taskNumber)) {
                        throw new InvalidTaskNumberException(taskNumber);
                    }

//                    Task task = list.get(taskNumber - 1);
//                    task.unMarkTask();
                    Task task = markOrDeleteDataToFile(taskNumber - 1, "unmark");
                    printUnmarkedTaskMessage(task);
                } else {  // If the inputted command is not valid, throw TaskTypeException
                    throw new TaskTypeException();
                }
            } catch (TaskTypeException e) {
                System.out.println(e.getMessage());
            } catch (InvalidDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (NoDeadlineException e) {
                System.out.println(e.getMessage());
            }
//            catch (InvalidDeadlineException e) {
//                System.out.println(e.getMessage());
//            }
            catch (InvalidTaskNumberException e) {
                System.out.println(e.getMessage());
            } catch (IncompleteDurationException e) {
                System.out.println(e.getMessage());
            } catch (InvalidIntegerException e) {
                System.out.println(e.getMessage());
            }
//            catch (InvalidDurationException e) {
//                System.out.println(e.getMessage());
//            }
            finally {
                cmd = sc.nextLine();
            }
        }

        printExit();
    }

    // Obtains the tasks from the duke.txt file and prints them one by one
    public static void printListItems() {
        ArrayList<Task> tasks = loadDataFromFile();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i+1 + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name +
                    " " + task.getTimeInfo());
        }
    }

//    public static void writeItemToFile(Task task) {
//        String data =  list.size() + "." + task.getTaskType() + task.getStatusIcon() + " " + task.name + "\n";
//        // Get the path to the duke.txt file
//        Path filePath = Paths.get("./data/duke.txt");
//        try {
//            // Append data to the duke.txt file
//            Files.write(filePath, data.getBytes(), StandardOpenOption.APPEND);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    // Removes all data from the duke.txt file
    // Takes the task arraylist argument, serializes it and stores it in the duke.txt file
    public static void saveDataToFile(ArrayList<Task> data) {
        // Delete all data from duke.txt file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/duke.txt", false))) {
            outputStream.writeObject(data);
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Takes the task argument, serializes it and adds it to the end of the duke.txt file
    public static void appendTaskToFile(Task task) {
        ArrayList<Task> tasks = loadDataFromFile();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./data/duke.txt"))) {
            tasks.add(task);
            saveDataToFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads the data in the duke.txt file, unserializes it and returns an arraylist representing
    // the saved tasks
    public static ArrayList<Task> loadDataFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        String folderPath = "./data/";
        String filePath = "./data/duke.txt";

        try {
            // Check if the folder exists, create if not
            Path folder = Paths.get(folderPath);
            if (!Files.exists(folder)) {
                Files.createDirectories(folder);
                System.out.println("Folder created: " + folderPath);
            }
            // Check if the file exists, create if not
            Path file = Paths.get(filePath);
            if (!Files.exists(file)) {
                Files.createFile(file);
                System.out.println("File created: " + filePath);
            }

            // If the duke.txt file is empty, return an empty task arraylist
            if (Files.size(file) == 0) {
                return tasks;
            }

            // Throws StreamCorruptedException when data format is unserializable
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
            tasks = (ArrayList<Task>) inputStream.readObject();

            // Throws exception if data in duke.txt is not an ArrayList<Task>
            if (tasks instanceof ArrayList<?>) {
                tasks = (ArrayList<Task>) tasks;
            } else {
                throw new InvalidDataFormatException();
            }
        } catch (InvalidDataFormatException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tasks;
    }

    // Reads the duke.txt file, unserializes it and obtain the arraylist representing saved tasks.
    // Depending on the purpose, information in the arraylist is updated, and the whole arraylist is
    // serialized and saved again in the duke.txt file
    public static Task markOrDeleteDataToFile(int index, String purpose) {
        ArrayList<Task> tasks = loadDataFromFile();

        if (purpose.equals("mark")) {
            tasks.get(index).markTask();
            saveDataToFile(tasks);
            return tasks.get(index);
        } else if (purpose.equals("unmark")) {
            tasks.get(index).unMarkTask();
            saveDataToFile(tasks);
            return tasks.get(index);
        } else {
            Task deletedTask = tasks.get(index);
            tasks.remove(index);
            saveDataToFile(tasks);
            return deletedTask;
        }
    }

    public static void printIntro() {
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Dookie\n" +
                " What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(intro);
    }

    public static void printAddTaskMessage(Task task) {
        int listSize = loadDataFromFile().size();
        String message = "____________________________________________________________\n" +
                " Got it. I've added this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + listSize + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printDeleteTaskMessage(Task task){
        int listSize = loadDataFromFile().size();

        String message = "____________________________________________________________\n" +
                " Noted. I've removed this task: \n   " +
                task.getTaskType() + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                " Now you have " + listSize + " tasks in the list.\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printExit() {
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(exitMessage);
    }

    public static void printMarkedTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static void printUnmarkedTaskMessage(Task task) {
        String message = "____________________________________________________________\n" +
                " OK. I've marked this task as not done yet:\n" +
                "   " + task.getStatusIcon() + " " + task.name + task.getTimeInfo() + "\n" +
                "____________________________________________________________";
        System.out.println(message);
    }

    public static boolean descriptionIsEmpty(String cmd) {
        return cmd.split(" ").length == 1;
    }

//    public static String checkedDeadline(String deadline) {
//        if (deadline.split(" ").length == 1) {
//            System.out.println("There is only one term in the inputted deadline");
//            return "";
//        }
//        String by = deadline.split(" ", 2)[0];
//
//        // Check if the first word of the string is "by"
//        if (!by.equals("by")) {
//            System.out.println("First word of input is by!");
//            return "";
//        }
//
//        String deadlineDescription = deadline.split(" ", 2)[1];
//        System.out.println("Zooming in on description, details are: " + deadlineDescription);
//        if (deadlineDescription.split(" ").length == 1) {
//            System.out.println("Deadline description has 1 term: " + deadlineDescription.split(" ")[0]);
//            String capitalisedDay = deadlineDescription.substring(0, 1).toUpperCase() + deadlineDescription.substring(1).toLowerCase().trim();
//            System.out.println("Capitalised day is: " + capitalisedDay);
//            return isValidDay(capitalisedDay) ? capitalisedDay : "";
//        } else if (deadlineDescription.split(" ").length == 2) {
//            System.out.println("Deadline description has 2 terms: " + deadlineDescription);
//            boolean isDayAndTime = false;
//            String day = deadlineDescription.split(" ")[0].substring(0, 1).toUpperCase() +
//                            deadlineDescription.split( " ")[0].substring(1).toLowerCase().trim();
//            System.out.println("Day is: " + day);
//            String time = deadlineDescription.split(" ")[1];
//            System.out.println("Time is: " + time);
//            isDayAndTime = isValidDay(day) && isValidTime(time);
//            System.out.println("Is it day and time? " + isDayAndTime);
//            if (isDayAndTime) {
//                return deadlineDescription;
//            }
//
//            boolean isDate = false;
//            String month = day;
//            System.out.println("Month is: " + month);
//            int date = -1;
//            try {
//                date = Integer.parseInt(time.substring(0, time.length() - 2));
//                System.out.println("Date is: " + date);
//            } catch (Exception e) {
//                System.out.println("There was an error parsing the date");
//                return "";
//            }
//            isDate = isValidDate(month, date);
//            System.out.println("Is it a date? " + isDate);
//            if (isDayAndTime) {
//                System.out.println("It is a day and time");
//                return deadlineDescription;
//            } else if (isDate) {
//                System.out.println("It is a date");
//                return getDate(month, date);
//            }
//            return "";
//        } else if (deadlineDescription.split(" ").length == 3) {
//            System.out.println("Deadline description has 3 terms: " + deadlineDescription);
//            String capitalisedMonth = deadlineDescription.split(" ")[0];

//            System.out.println("The capitalised month is: " + capitalisedMonth);
//            int date = -1;
//            try {
//                date = Integer.parseInt(deadlineDescription.split(" ")[1].split("")[0]);
//                System.out.println("The date is: " + date);
//            } catch (Exception e) {
//                System.out.println("There was an error parsing the date");
//                return "";
//            }
//            String time = deadlineDescription.split(" ")[2];
//            System.out.println("The time is: " + time);
//
//            return (isValidDate(capitalisedMonth, date) && isValidTime(time))
//                    ? deadlineDescription
//                    : "";
//        }
//        return "";
//    }
//
//    public static int validStart(String starting) {
//        String from = starting.split(" ", 2)[0];
//        String duration = starting.split(" ", 2)[1];
//
//        if (!from.equals(from)) {
//            return -1;
//        }
//
//        if (duration.split(" ").length == 2) {
//            String day = duration.split(" ")[0];
//            String time = duration.split(" ")[1];
//            if (isValidDay(day) && isValidTime(time)) {
//                return 0;
//            }
//            return -1;
//        } else if (duration.split(" ").length == 3) {
//
//        }
//        return -1;
//    }
//
//    public static boolean isValidEnd(String ending, int result) {
//
//    }
    // Checks if the string inputted is a valid time. Note that the timing is only in hours
//    public static boolean isValidTime(String time) {
//        if (time.split("").length != 3) {
//            return false;
//        }
//        String integer = time.split("")[0];
//        String timeOfDay = time.split("", 2)[1];
//
//        int hour = -1;
//        try {
//            hour = Integer.parseInt(integer);
//        } catch (Exception e) {
//            return false;
//        }
//
//        return timeOfDay.equals("pm")  timeOfDay.equals("am");
//    }
//
//    // Checks if the inputted string matches any value in the Day Enum
//    public static boolean isValidDay(String day) {
//        for (Day dayy : Day.values()) {
//            if (dayy.name().equals(day)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Checks if the inputted month and day form a valid date. Note that February assumes there are only 28 days
//    public static boolean isValidDate(String month, int day) {
//        if (month.equals("Jan")  month.equals("March")  month.equals("May")  month.equals("July")
//                 month.equals("August")  month.equals("October")  month.equals("December")) {
//            return day >= 1 && day <= 31;
//        } else if (month.equals("Apr")  month.equals("June")  month.equals("Sept")  month.equals("Nov")) {
//            return day >=1 && day <= 31;
//        } else if (month.equals("Feb")) {
//            return day >= 1 && day <= 28;
//        } else {
//            return false;
//        }
//    }
//
//    // Takes the inputted month and day and returns a prefixed date in the form of (month)(day)
//    public static String getDate(String month, int day) {
//        String prefix = "";
//        int ones = -1;
//        if (day % 10 == 0) {
//            ones = day;
//        } else {
//            ones = day % 10;
//        }
//        if (ones == 1) {
//            prefix = "st";
//        } else if (ones == 2) {
//            prefix = "nd";
//        } else if (ones == 3) {
//            prefix = "rd";
//        } else {
//            prefix = "th";
//        }
//
//        return month + " " + day + prefix;
//    }

    public static boolean hasNoDeadline(String taskWithDeadline) {
        return taskWithDeadline.split("/").length == 1;
    }

    public static LocalDateTime checkDeadline(String deadline) throws InvalidDeadlineException {
        String[] parts = deadline.split(" ");
        // If there isn't exactly three components in the deadline, return false
        if (parts.length != 3) {
            throw new InvalidDeadlineException(deadline);
        }
        String by = parts[0];
        String date = parts[1];
        String time = parts[2];

        // If the first word is not "by", return false
        if (!by.equals("by")) {
            throw new InvalidDeadlineException(deadline);
        }

        try {
            String[] dateParts = date.split("/");
            // If the date does not have three components, return false
            if (dateParts.length != 3) {
                throw new InvalidDeadlineException(deadline);
            }
            int day = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // If time is not a four digit number, return false
            if (time.length() != 4) {
                throw new InvalidDeadlineException(deadline);
            }

            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));

            return LocalDateTime.of(year, month, day, hour, min);
        } catch (Exception e) {
            throw new InvalidDeadlineException(deadline);
        }
    }

    public static boolean isValidTaskNumber(int number) {
        int listSize = loadDataFromFile().size();
        return number > 0 && number <= listSize;
    }

//    public static LocalDateTime createDateAndTime(String dateAndTime) {
//        System.out.println(dateAndTime);
//        String date = dateAndTime.split(" ")[0];
//        String time = dateAndTime.split(" ")[1];
//        String[] dates = date.split("/");
//
//        int day = Integer.parseInt(dates[0]);
//        int month = Integer.parseInt(dates[1]);
//        int year = Integer.parseInt(dates[2]);
//        int hour = Integer.parseInt(time.substring(0, 2));
//        int min = Integer.parseInt(time.substring(2, 4));
//
//        return LocalDateTime.of(year, month, day, hour, min);
//    }
}