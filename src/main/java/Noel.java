import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Noel {

    static String FILEPATH = "./data/noel.txt";
    static String HELLO_MSG = " Hello! I'm Noel!\nWhat can I do for you?";
    static String BYE_MSG = " Bye. Hope to see you again soon!";

    static boolean fileInserted = false;
    static int maxSize = 100;
    static ArrayList<Task> taskList = new ArrayList<>();

    static String addedMessageStart = "Got it. I've added this task:";


    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    public static boolean checkFull() {
        return taskList.size() == maxSize;
    }

    public static boolean checkEmpty() {
        return taskList.size() == 0;
    }

    public static void addToDo(String task) {

        Task taskToAdd = new ToDos(task);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);
        updateFile();
    }

    public static void addDeadline(String task, String endDate) {

        LocalDate date = dateFormat(endDate);
        if (date == null) {
            System.out.println("Invalid date!");
            return;
        }

        LocalTime time = timeFormat(endDate);
        if (time == null) {
            System.out.println("Invalid date!");
            return;
        }

        Task taskToAdd = new Deadlines(task, date, time);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);
        updateFile();

    }

    public static LocalTime timeFormat(String date) {
        LocalTime time = null;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            time = LocalTime.parse(date, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                time = LocalTime.parse(date, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid Time Format");
            }
        }
        return time;
    }

    public static LocalDate dateFormat(String endDate) {
        LocalDate date = null;
        System.out.println(endDate);
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            date = LocalDate.parse(endDate, formatter1);

            return date;
        } catch (DateTimeParseException e1) {
            try {
                date = LocalDate.parse(endDate, formatter2);
                return date;
            } catch (DateTimeParseException e2) {
                System.out.println("Invalid date format");
            }
        }
        return date;
    }

    public static void addEvent(String task, String startDate, String endDate) {

        LocalDate startDateFormat = dateFormat(startDate);
        LocalDate endDateFormat = dateFormat(endDate);
        LocalTime startTimeFormat = timeFormat(startDate);
        LocalTime endTimeFormat = timeFormat(endDate);

        if (startDateFormat == null || endDateFormat == null) {
            System.out.println("Invalid format for start/end date!");
            return;
        }

        if (startTimeFormat == null || endTimeFormat == null) {
            System.out.println("Invalid format for start/end time!");
            return;
        }

        Task taskToAdd = new Events(task, startDateFormat, startTimeFormat, endDateFormat, endTimeFormat);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList.add(taskToAdd);

        String addedMessageEnd = "Now you have " + taskList.size() + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);
        updateFile();
    }

    public static void printTaskList(){
        if (checkEmpty()) {
            System.out.println("List is empty!");
        } else {
            String filler = "____________________________________________________________";
            System.out.println(filler);
            int maxLength = taskList.size();
            for (int i = 0; i < maxLength; i++) {
                System.out.println(i + 1 + ". " + taskList.get(i));
            }
            System.out.println(filler);
        }
    }

    public static void checkFile() {
        Path filePath = Paths.get(FILEPATH);
        if (Files.exists(filePath)) {
            try {
                String content = new String(Files.readAllBytes(filePath));
                updateTaskList(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist");
            System.out.println("Creating file now!");
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                System.out.println("File and Directories created!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateTaskList(String content) {
        String[] listOfStrings = content.split("\n");
        System.out.println(Arrays.toString(listOfStrings));
        for (String line : listOfStrings) {
            String[] values = line.split(" \\| ");

            if (values.length != 1) {

                if (Objects.equals(values[0], "E")) {
                    if (values.length == 4) {

                        // (from: 02 02 2023 06:00 to: 03 02 2023 06:00)
                        String[] dates = values[3].split("to:");
                        String[] startDateArray = dates[0].split("\\(from: ")[1].split(" ");
                        String startDate = startDateArray[0] + " " + startDateArray[1];
                        String[] endDateArray = dates[1].split("\\)")[0].split(" ");
                        String endDate = endDateArray[1] + " " + endDateArray[2];

                        if (dates.length == 2) {
                            addEvent(values[2], startDate, endDate);
                        } else {
                            System.out.println("Invalid line! Skipping line...");
                        }
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                    }

                } else if (Objects.equals(values[0], "T")) {
                    if (values.length == 3) {
                        addToDo(values[2]);
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                    }
                } else if (Objects.equals(values[0], "D")) {
                    if (values.length == 4) {
                        addDeadline(values[2], values[3]);
                    } else {
                        System.out.println("Invalid line! Skipping line...");
                    }
                } else {
                    System.out.println("Invalid line! Skipping line...");
                }

                if (Objects.equals(values[1], "1")) {
                    taskList.get(taskList.size() - 1).markAsDone();
                }
            } else {
                System.out.println("No value in file at " + FILEPATH);
            }
        }
    }

    public static List<String> getTaskAsList() {
        List<String> linesToAppend = new ArrayList<>();
        for (Task t:taskList) {
            linesToAppend.add(t.toFileString());
        }
        return linesToAppend.subList(0, linesToAppend.size());
    }

    public static void updateFile() {
        if (fileInserted) {
            try {
                List<String> linesToAppend = getTaskAsList();
                Files.write(Paths.get(FILEPATH), linesToAppend);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        checkFile();
        printFunction(HELLO_MSG);
        fileInserted = true;

        Scanner in = new Scanner(System.in);
        String nextLine = in.nextLine();
        String command;
        String[] deadlineHelper;
        String[] eventsHelper;

        while (!nextLine.equals("bye")){

            if (nextLine.equals("list")){
                printTaskList();
            } else if (nextLine.contains(" ")) {

                // multiple values
                String[] result = nextLine.split(" ");

                command = result[0];

                switch (command) {

                    case "mark": {
                        int taskNum = Integer.parseInt(result[1]);
                        taskNum = taskNum - 1;
                        taskList.get(taskNum).markAsDone();
                        updateFile();
                        break;
                    }
                    case "unmark": {
                        int taskNum = Integer.parseInt(result[1]);
                        taskList.get(taskNum).unMark();
                        updateFile();
                        break;
                    }
                    case "todo":
                        result = nextLine.split("todo ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a todo cannot be empty.");
                        } else {
                            addToDo(result[1]);
                        }
                        break;

                    case "deadline":
                        result = nextLine.split("deadline ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                        } else {
                            deadlineHelper = result[1].split(" /by ");
                            if (deadlineHelper.length == 2) {
                                addDeadline(deadlineHelper[0], deadlineHelper[1]);
                            } else {
                                System.out.println("OOPS!!! Remember to add the date/description");
                            }
                        }
                        break;

                    case "event":
                        result = nextLine.split("event ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a event cannot be empty.");
                        } else {
                            eventsHelper = result[1].split(" /from ");
                            command = eventsHelper[0];
                            if (eventsHelper.length == 2) {
                                eventsHelper = eventsHelper[1].split(" /to ");
                                if (eventsHelper.length == 2) {
                                    addEvent(command, eventsHelper[0], eventsHelper[1]);
                                } else {
                                    System.out.println("Insufficient commands provided!");
                                }
                            } else {
                                System.out.println("Insufficient commands provided!");
                            }
                        }
                        break;

                    case "delete":
                        result = nextLine.split("delete ");
                        if (result.length == 0) {
                            System.out.println("OOPS!!! The description of a delete cannot be empty.");
                        } else {
                            int intToRemove = Integer.parseInt(result[1]) - 1;
                            Task taskToDel = taskList.get(intToRemove);
                            taskList.remove(intToRemove);

                            String delStart = "Noted. I've removed this task:\n";
                            String delEnd = "Now you have " + taskList.size() + " tasks in the list.";
                            printFunction(delStart + taskToDel + "\n" + delEnd);
                            updateFile();
                            break;
                        }

                    default:
                        System.out.println("Invalid Option!");
                }
            } else {
                System.out.println("Invalid Option!");
            }
            nextLine = in.nextLine();
        }

        printFunction(BYE_MSG);
    }
}

