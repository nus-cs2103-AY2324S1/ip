package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, UNKNOWN}
    private static ListOfTask taskList = new ListOfTask();
    public static void main(String[] args) {
        greet();
    }

    private static void greet() {
        File saveData = new File("./src/data/duke.txt");
        try {
            Scanner readData = new Scanner(saveData);
            while (readData.hasNextLine()) {
                quickLoad(readData.nextLine());
            }
            readData.close();
        } catch (FileNotFoundException e) {
            try {
                saveData.createNewFile();
            } catch (IOException f) {
                if (clarify() == 0) {
                    exit();
                    return;
                }
            }
        }
        System.out.println(
                "Hello. I am Luxion. \n" +
                "What can I do for you?");

        Scanner scanObj = new Scanner(System.in);
        String command = scanObj.nextLine();
        nextCommand(command);
    }

    private static void exit() {
        System.out.println("Bye. See you soon.\n");
    }

    private static int clarify() {
        System.out.println("You do not have access to create a save file");
        System.out.println("Do you wish to continue? (yes/no) None of your data will be saved.");
        Scanner scan = new Scanner(System.in);
        String respond = scan.nextLine();
        if (respond.equals("yes")) {
            return 1;
        } else if (respond.equals("no")) {
            return 0;
        } else {
            return clarify();
        }
    }

    protected static void quickLoad(String command) {
        Parse cmd = new Parse(command);
        COMMANDS firstWord = cmd.mainCommand();

        switch (firstWord) {
        case TODO:
            if (cmd.secondWord() != null) {
                taskList.loadTask(cmd.secondWord());
            } else {
                System.out.println("ToDo line corrupted: " + command);
            }
            return;

        case DEADLINE:
            try {
                String task = cmd.phaseParse();
                String dayDate = cmd.phaseTwo();
                Parse parseDayDate = new Parse(dayDate);
                if (parseDayDate.mainCommand().equals(COMMANDS.BY) && parseDayDate.secondWord() != null) {
                    taskList.loadTask(task, parseDayDate.secondWord());
                }
            } catch (NullPointerException e) {
                System.out.println("Deadline line corrupted: " + command);
            }
            return;

        case EVENT:
            try {
                String task2 = cmd.phaseParse();
                String startDayDateTime = cmd.phaseTwo();
                Parse parseStartDayDateTime = new Parse(startDayDateTime);
                String endDayDateTime = cmd.phaseThree();
                Parse parseEndDayDateTime = new Parse(endDayDateTime);
                taskList.loadTask(task2, parseStartDayDateTime.secondWord(), parseEndDayDateTime.secondWord());
            } catch (NullPointerException e) {
                System.out.println("Event line corrupted: " + command);
            }
            return;

        case MARK:
            int i = taskList.size();
            taskList.loadMark(i);
            return;

        default:
            System.out.println("line corrupted: " + command);
        }
    }

    static class Parse {

        private String command;
        private String[] initialParse;
        private String[] phaseParse;
        protected Parse(String command) {
            this.command = command;
        }
        protected COMMANDS mainCommand() {
            this.initialParse = command.split(" ",2);
            switch (initialParse[0]) {
            case ("bye"):
                return COMMANDS.BYE;
            case ("list"):
                return COMMANDS.LIST;
            case ("todo"):
                return COMMANDS.TODO;
            case ("deadline"):
                return COMMANDS.DEADLINE;
            case ("event"):
                return COMMANDS.EVENT;
            case ("mark"):
                return COMMANDS.MARK;
            case ("unmark"):
                return COMMANDS.UNMARK;
            case ("delete"):
                return COMMANDS.DELETE;
            case ("by"):
                return COMMANDS.BY;
            case ("from"):
                return COMMANDS.FROM;
            case ("to"):
                return COMMANDS.TO;
            default:
                return COMMANDS.UNKNOWN;
            }
        }

        protected String secondWord() {
            try {
                if (this.initialParse[1].equals("")) {
                    return null;
                } else {
                    return this.initialParse[1];
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        protected String phaseParse() {
            try {
                this.phaseParse = this.initialParse[1].split("/");
                return phaseParse[0];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        protected String phaseTwo() {
            try {
                return this.phaseParse[1];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        protected String phaseThree() {
            try {
                return this.phaseParse[2];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }
    }
    protected static void nextCommand(String command) {
        Parse cmd = new Parse(command);
        COMMANDS firstWord = cmd.mainCommand();

        switch (firstWord) {
        case BYE:
            exit();
            return;

        case LIST:
            taskList.listTasks();
            break;

        case TODO:
            if (cmd.secondWord() != null) {
                taskList.addTask(cmd.secondWord());
            } else {
                System.out.println("Please add a task for ToDo");
            }
            break;

        case DEADLINE:
            try {
                String task = cmd.phaseParse();
                try {
                    String dayDate = cmd.phaseTwo();
                    Parse parseDayDate = new Parse(dayDate);
                    if (parseDayDate.mainCommand().equals(COMMANDS.BY) && parseDayDate.secondWord() != null) {
                        taskList.addTask(task, parseDayDate.secondWord());
                    } else {
                        System.out.println("The format for the command is: deadline task /by DayOrDate");
                    }
                } catch (NullPointerException e) {
                    System.out.println("Please add the day/date that the task is due by");
                }
            } catch (NullPointerException e) {
                System.out.println("Please add the task that has to been done");
            }
            break;

        case EVENT:
            String task = cmd.phaseParse();
            try {
                String startDayDateTime = cmd.phaseTwo();
                Parse parseStartDayDateTime = new Parse(startDayDateTime);
                if (parseStartDayDateTime.mainCommand().equals(COMMANDS.FROM) && parseStartDayDateTime.secondWord() != null) {
                    try {
                        String endDayDateTime = cmd.phaseThree();
                        Parse parseEndDayDateTime = new Parse(endDayDateTime);
                        if (parseEndDayDateTime.mainCommand().equals(COMMANDS.TO) && parseEndDayDateTime.secondWord() != null) {
                            taskList.addTask(task, parseStartDayDateTime.secondWord(), parseEndDayDateTime.secondWord());
                        } else {
                            System.out.println("The format for the command is: event task /from startDayDateTime /to endDayDateTime");
                        }
                    } catch (NullPointerException e) {
                        System.out.println("Please add the day/date/time the event will end");
                    }
                } else {
                    System.out.println("The format for the command is: event task /from startDayDateTime /to endDayDateTime");
                }
            } catch (NullPointerException e) {
                System.out.println("Please add the event, start of the event and end of the event");
            }
            break;

        case MARK:
            if (cmd.secondWord() != null) {
                try {
                    int n = Integer.parseInt(cmd.secondWord());
                    taskList.mark(n);
                } catch (NumberFormatException e) {
                    System.out.println("Please input only 1 number after mark");
                }
            } else {
                System.out.println("Please input only 1 number after mark");
            }
            break;

        case UNMARK:
            if (cmd.secondWord() != null) {
                try {
                    int n = Integer.parseInt(cmd.secondWord());
                    taskList.unMark(n);
                } catch (NumberFormatException e) {
                    System.out.println("Please input only 1 number after unmark");
                }
            } else {
                System.out.println("Please input only 1 number after unmark");
            }
            break;

        case DELETE:
            if (cmd.secondWord() != null) {
                try {
                    int n = Integer.parseInt(cmd.secondWord());
                    taskList.delete(n);
                } catch (NumberFormatException e) {
                    System.out.println("Please input only 1 number after unmark");
                }
            } else {
                System.out.println("Please input only 1 number after unmark");
            }
            break;

        default:
            System.out.println("Unknown command");
        }
        Scanner scanObj = new Scanner(System.in);
        String newCommand = scanObj.nextLine();
        nextCommand(newCommand);
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
