import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    enum COMMANDS {BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BY, FROM, TO, UNKNOWN}
    private static ListOfTask taskList = new ListOfTask();
    public static void main(String[] args) {
        greet();
    }

    private static void greet() {
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

    static class Parse {

        private String command;
        private String[] initialParse;
        private String[] phaseParse;
        public Parse(String command) {
            this.command = command;
        }
        public COMMANDS mainCommand() {
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

        public String secondWord() {
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

        public String phaseParse() {
            try {
                this.phaseParse = this.initialParse[1].split("/");
                return phaseParse[0];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        public String phaseTwo() {
            try {
                return this.phaseParse[1];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }

        public String phaseThree() {
            try {
                return this.phaseParse[2];
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                return null;
            }
        }
    }
    public static void nextCommand(String command) {
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
                try {
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
                        System.out.println("Please add the day/date/time the event will start");
                    }
                } catch (NullPointerException e) {
                    System.out.println("Please add the event that is happening");
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
                        taskList.unmark(n);
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

class Task {

    private String taskName;
    private Boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }
    @Override
    public String toString() {
        return ("[" + (this.done ? "X] " : " ] ") + this.taskName);
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public static class ToDos extends Task {
        public ToDos(String taskName) {
            super(taskName);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadlines extends Task {
        private String dayDate;
        public Deadlines(String taskName, String dayDate) {
            super(taskName);
            this.dayDate = dayDate;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by: " + dayDate + ")";
        }
    }

    public static class Event extends Task {
        private String startDayDateTime;
        private String endDayDateTime;
        public Event(String taskName, String startDayDateTime, String endDayDateTime) {
            super(taskName);
            this.endDayDateTime = endDayDateTime;
            this.startDayDateTime = startDayDateTime;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(from: " + startDayDateTime + "to: " + endDayDateTime +")";
        }
    }
}

class ListOfTask {
    private static ArrayList<Task> listOfTask = new ArrayList<>();
    //private static Task[] listOfTask = new Task[100];
    private static int counter = 0;

    public void addTask(String task) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        //listOfTask[counter] = new Task.ToDos(task);
        System.out.println("added: " + temp);
    }

    public void addTask(String task, String dayDate) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        //listOfTask[counter] = new Task.Deadlines(task, dayDate);
        System.out.println("added: " + temp);
        counter++;
    }

    public void addTask(String task, String startDayDateTime, String endDayDateTime) {
        Task temp = new Task.ToDos(task);
        listOfTask.add(temp);
        //listOfTask[counter] = new Task.Event(task, startDayDateTime, endDayDateTime);
        System.out.println("added: " + temp);
        counter++;
    }

    public void listTasks() {
        int[] i = new int[1];
        i[0] = 1;
        listOfTask.forEach(x-> {
            System.out.print(i[0] + ".");
            System.out.println(x);
            i[0]++;
            }
        );
    }

    public void mark(int index) {
        try {
            listOfTask.get(index - 1).mark();
            System.out.println(listOfTask.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void unmark(int index) {
        try {
            listOfTask.get(index - 1).unmark();
            System.out.println(listOfTask.get(index - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }

    public void delete(int index) {
        try {
            Task removed = listOfTask.remove(index - 1);
            System.out.println(removed + " has been removed");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select from index 1 to " + listOfTask.size());
        }
    }
}

class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
