package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    static ListOfTask taskList = new ListOfTask();
    private static Ui ui = new Ui();
    public static void main(String[] args) {
        greet();

    }

    private static void greet() {
        if (!Storage.load(taskList)) {
            return;
        }
        ui.greet();
        nextCommand(ui.nextInput());
    }

    protected static void nextCommand(String command) {
        Parser cmd = new Parser(command);
        Commands.COMMANDS firstWord = cmd.mainCommand();

        switch (firstWord) {
        case BYE:
            ui.exit();
            return;

        case LIST:
            String secondWord = cmd.secondWord();
            if (secondWord == null) {
                taskList.listTasks();
            }
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
                    Parser parseDayDate = new Parser(dayDate);
                    LocalDateTime date = LocalDateTime.parse(parseDayDate.secondWord().trim(),FORMAT);
                    if (parseDayDate.mainCommand().equals(Commands.COMMANDS.BY)) {
                        taskList.addTask(task, date);
                    } else {
                        System.out.println("The format for the command is: deadline task /by date&time");
                    }
                } catch (NullPointerException e) {
                    System.out.println("Please add the day/date that the task is due by");
                } catch (DateTimeParseException e) {
                    System.out.println("The format for dates&time is 'dd-MM-yyyy hhmm'");
                }
            } catch (NullPointerException e) {
                System.out.println("Please add the task that has to been done");
            }
            break;

        case EVENT:
            String task = cmd.phaseParse();
            try {
                String startDayDateTime = cmd.phaseTwo();
                Parser parseStartDayDateTime = new Parser(startDayDateTime);
                if (parseStartDayDateTime.mainCommand().equals(Commands.COMMANDS.FROM)) {
                    try {
                        LocalDateTime startDate = LocalDateTime.parse(parseStartDayDateTime.secondWord().trim(),FORMAT);
                        String endDayDateTime = cmd.phaseThree();
                        Parser parseEndDayDateTime = new Parser(endDayDateTime);
                        if (parseEndDayDateTime.mainCommand().equals(Commands.COMMANDS.TO)) {
                            LocalDateTime endDate = LocalDateTime.parse(parseEndDayDateTime.secondWord().trim(),FORMAT);
                            taskList.addTask(task, startDate, endDate);
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
            } catch (DateTimeParseException e) {
                System.out.println("The format for dates&time is 'dd-MM-yyyy hhmm'");
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
        nextCommand(ui.nextInput());
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
