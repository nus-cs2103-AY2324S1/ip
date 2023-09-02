import tasks.TaskAbstract;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;
import dukeexceptions.CorruptDataException;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;


enum TaskTypes {
    todo,
    deadline,
    event
}

enum DataTaskTypes {
    t,
    d,
    e
}

public class Duke {
    static final String logo = "\n   _____ _    _          _____   _____ _____ _______ \n" +
            "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n" +
            " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n" +
            " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n" +
            " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n" +
            "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";
    static final String horizontal = "----------------------------------------------------------------------------" +
            "-----------";
    static List<TaskAbstract> taskList = new ArrayList<TaskAbstract>();
    static int taskCounter = 0;
    static boolean isReadFileSuccessful = false;
    enum validCommands {
        bye,
        list,
        mark,
        unmark,
        deadline,
        event,
        todo,
        delete
    };

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontal + logo + horizontal);

        readFile();

        while (!isReadFileSuccessful) {
            System.out.print("User: ");
            String nextLine = sc.nextLine();
            if (nextLine.equals("exit")) {
                sc.close();
                System.exit(0);
            } else {
                readFile();
            }
        }

        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + horizontal);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            String nextLine = sc.nextLine();
            if (isValidCommand(nextLine)) {
                passCommand(nextLine);
            }
            System.out.print(horizontal + "\nUser: ");
        }
        sc.close();

        writeToFile();
        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
    }

    private static void passCommand(String nextLine) {
        String[] strArray = nextLine.split(" ");
        String command = strArray[0];
        if (command.equals("list")) {
            System.out.println("ChadGPT: Here are your tasks: ");
            int counter = 0;
            for (TaskAbstract t : taskList) {
                System.out.print("    " + ++counter + ". ");
                t.printStatus();
            }
        } else if (command.equals("mark")) {
            int index = Integer.parseInt(strArray[1]) - 1;
            if (taskList.get(index).isComplete()) {
                System.out.println("ChadGPT: Oops, the task is already complete.");
            } else {
                taskList.get(index).completeTask();
                System.out.println("ChadGPT: Nice! I'll mark the task as done: ");
                taskList.get(index).printStatus();
            }
        } else if (command.equals("unmark")) {
            int index = Integer.parseInt(strArray[1]) - 1;
            if (!taskList.get(index).isComplete()) {
                System.out.println("ChadGPT: Oops, the task is currently incomplete.");
            } else {
                taskList.get(index).undo();
                System.out.println("ChadGPT: No problem, I'll mark this task as not done yet: ");
                taskList.get(index).printStatus();
            }
        } else if (command.equals("delete")) {
            int index = Integer.parseInt(strArray[1]) - 1;
            System.out.println("ChadGPT: No problem, I'll remove the task from your task list: ");
            taskList.remove(index).printStatus();
            taskCounter--;
        } else {
            TaskAbstract newTask = createTask(nextLine, strArray);
            System.out.println("ChadGPT: added task '" + newTask.toString() + "'");
            System.out.println("You now have " + taskCounter + " tasks in the list.");
            taskList.add(newTask);
        }
    }

    private static TaskAbstract createTask(String nextLine, String[] strArr) {
        String[] delimited = nextLine.split("/");
        switch(strArr[0].toLowerCase()) {
            case "todo":
                taskCounter++;
                return new Todo(nextLine.substring(5));
            case "deadline":
                taskCounter++;
                return new Deadline(delimited[0].substring(9, delimited[0].length() - 1),
                        delimited[1].substring(3));
            case "event":
                taskCounter++;
                return new Event(delimited[0].substring(6, delimited[0].length() - 1),
                        delimited[1].substring(5, delimited[1].length() - 1),
                        delimited[2].substring(3));
        }
        throw new IllegalArgumentException("Invalid task type");
   }

    private static boolean isValidCommand(String nextLine) {
        String[] delimitedBySpace = nextLine.split(" ");
        String[] delimitedBySlash = nextLine.split("/");

        try {
            String command = delimitedBySpace[0].toLowerCase();
            validCommands.valueOf(command);
        } catch (IndexOutOfBoundsException indexExcept) {
            System.out.println("ChadGPT: Please input a valid command.");
            return false;
        } catch (IllegalArgumentException argExcept) {
            System.out.println("ChadGPT: Sorry I don't understand this command :-(");
            return false;
        }

        switch (delimitedBySpace[0].toLowerCase()) {
            case "mark":
            case "delete":
            case "unmark":
                try {
                    int location = Integer.parseInt(delimitedBySpace[1]);
                    if (location > taskCounter) {
                        System.out.println(taskCounter > 0 ? "ChadGPT: Please input a " +
                                "valid task index. You may use the command \"list\" to get " +
                                "the task indexes." : "ChadGPT: Please add a task first.");
                        return false;
                    }
                } catch (IndexOutOfBoundsException indexExcept) {
                    System.out.println("ChadGPT: Please input index of task you would like to alter.");
                    return false;
                } catch (IllegalArgumentException argExcept) {
                    System.out.println("ChadGPT: Please input an integer for the index of the task you would like to alter.");
                    return false;
                }
                return true;
            case "todo":
                try {
                    String information = delimitedBySpace[1];
                } catch (IndexOutOfBoundsException indexExcept) {
                    System.out.println("ChadGPT: Please include information about the task you would like to add.");
                    return false;
                }
                return true;
            case "deadline":
                try {
                    String information = delimitedBySlash[0].split(" ")[1];
                } catch (IndexOutOfBoundsException indexExcept) {
                    System.out.println("ChadGPT: Please include information about the task you would like to add.");
                    return false;
                }
                try {
                    String endDate = delimitedBySlash[1].substring(3);
                } catch (StringIndexOutOfBoundsException stringExcept) {
                    System.out.println("ChadGPT: Please ensure your deadline date is included.");
                    return false;
                } catch (IndexOutOfBoundsException indexExcept) {
                    System.out.println("ChadGPT: Please include the deadline date of your task after \"/by\" command.");
                    return false;
                }

                try {
                    String[] dates = nextLine.split("/by ")[1].split(" ");
                    String endDate = dates[0];
                    String year = endDate.substring(0, 4);
                    String month = endDate.substring(5, 7);
                    String day = endDate.substring(8, 10);
                    LocalDate endDateObj = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
                    if (dates.length > 1) {
                        Integer.parseInt(dates[1]);
                        LocalTime timeObj = LocalTime.parse(dates[1].substring(0, 2) + ":" + dates[1].substring(2));
                    }
                } catch (NumberFormatException numberExcept) {
                    System.out.println("ChadGPT: Please ensure the time of your deadline is in numerical format.");
                    return false;
                } catch (IndexOutOfBoundsException | IllegalArgumentException formatExcept) {
                    System.out.println("ChadGPT: Ensure that deadline date follows the following format: yyyy-mm-dd.");
                    return false;
                }
                return true;

            case "event":
                try {
                    String information = delimitedBySlash[0].split(" ")[1];
                } catch (IndexOutOfBoundsException indexExcept) {
                    System.out.println("ChadGPT: Please include information about the task you would like to add.");
                    return false;
                }

                try {
                    String startDate = delimitedBySlash[1].substring(5, delimitedBySlash[1].length() - 1);
                    String endDate = delimitedBySlash[2].substring(3);
                } catch (StringIndexOutOfBoundsException stringExcept) {
                    System.out.println("ChadGPT: Please ensure that you have included the start and end dates.");
                    return false;
                } catch (IndexOutOfBoundsException indexExcept) {
                    System.out.println("ChadGPT: Please verify you have included the start date after /from and " +
                            "end date after /to commands");
                    return false;
                }

                try {
                    String dates = nextLine.split(" /from ")[1];
                    String[] startDateArr = dates.split(" /to ")[0].split(" ");
                    String startDate = startDateArr[0];
                    String startDateYear = startDate.substring(0, 4);
                    String startDateMonth = startDate.substring(5, 7);
                    String startDateDay = startDate.substring(8, 10);
                    LocalDate startDateObj = LocalDate.parse(String.format("%s-%s-%s", startDateYear,
                            startDateMonth, startDateDay));
                    if (startDateArr.length > 1) {
                        Integer.parseInt(startDateArr[1]);
                        LocalTime startTimeObj = LocalTime.parse(startDateArr[1].substring(0, 2) + ":" +
                                startDateArr[1].substring(2));
                    }
                    String[] endDateArr = dates.split(" /to ")[1].split(" ");
                    String endDate = endDateArr[0];
                    String endDateYear = endDate.substring(0, 4);
                    String endDateMonth = endDate.substring(5, 7);
                    String endDateDay = endDate.substring(8, 10);
                    LocalDate endDateObj = LocalDate.parse(String.format("%s-%s-%s", endDateYear,
                            endDateMonth, endDateDay));
                    if (endDateArr.length > 1) {
                        Integer.parseInt(endDateArr[1]);
                        LocalTime endDateTime = LocalTime.parse(endDateArr[1].substring(0, 2) + ":" +
                                endDateArr[1].substring(2));
                    }
                } catch (NumberFormatException numberExcept) {
                    System.out.println("ChadGPT: Please ensure the time of your deadline is in numerical format.");
                    return false;
                } catch (IndexOutOfBoundsException | IllegalArgumentException indexExcept) {
                    System.out.println("ChadGPT: Ensure that deadline date follows the following format: yyyy-mm-dd.");
                    return false;
                }
        }
        return true;
   }

    private static TaskAbstract createTaskStartup(String nextLine) throws CorruptDataException {
        String[] delimited = nextLine.split(" \\| ");
        switch (delimited[0].toLowerCase()){
            case "t":
                Todo todo = new Todo(delimited[2]);
                if (delimited[1].equals("1")) {
                    todo.completeTask();
                }
                return todo;
            case "d":
                Deadline deadline = new Deadline(delimited[2], delimited[3]);
                if (delimited[1].equals("1")) {
                    deadline.completeTask();
                }
                return deadline;
            case "e":
                String[] dateArray = delimited[3].split(" - ");
                Event event = new Event(delimited[2], dateArray[0], dateArray[1]);
                if (delimited[1].equals("1")) {
                    event.completeTask();
                }
                return event;
        }
        throw new CorruptDataException(nextLine);
    }

    private static boolean isDataValid(String nextLine) throws CorruptDataException {
        String[] delimited = nextLine.split(" \\| ");
        if (delimited.length == 3 || delimited.length == 4) {
            String taskType = delimited[0].toLowerCase();
            try {
                DataTaskTypes.valueOf(taskType);
                TaskAbstract newTask = createTaskStartup(nextLine);
                return true;
            } catch (IndexOutOfBoundsException indexExcept) {
            } catch (IllegalArgumentException illegalArgExcept) {
            }
        }
        throw new CorruptDataException(nextLine);
    }

    private static void readFile() throws IOException {
        try {
            File myFile = new File("./src/main/java/data/duke.txt");
            myFile.createNewFile();
            Scanner sc = new Scanner(myFile);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if (isDataValid(nextLine)) {
                    TaskAbstract newTask = createTaskStartup(nextLine);
                    taskList.add(newTask);
                    taskCounter++;
                }
            }
            isReadFileSuccessful = true;
        } catch (IOException IOExcept) {
            throw new IOException();
        } catch (CorruptDataException corruptDataExcept) {
            System.out.println("ChadGPT: Data is corrupt at: \"" + corruptDataExcept.getCorruptLine() +
                    "\". Please fix and press enter to proceed, or type the command \"exit\" to quit program");
            taskList.clear();
            taskCounter = 0;
        }
   }

    private static void writeToFile() throws IOException {
        try {
            File myFile = new File("./src/main/java/data/duke.txt");
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);
            for (TaskAbstract t : taskList) {
                pw.println(t.saveToTextFormat());
            }
            pw.close();
        } catch (IOException IOExcept) {
            throw new IOException();
        }
    }
}