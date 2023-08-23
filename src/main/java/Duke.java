import java.util.Date;
import java.util.Scanner;

public class Duke {
    private static ListOfTask taskList = new ListOfTask();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
    }

    private static void greet() {
        System.out.println("_______________________________\n" +
                "Hello. I am Luxion. \n" +
                "What can I do for you?\n" +
                "_______________________________\n");
        Scanner scanObj = new Scanner(System.in);
        String command = scanObj.nextLine();
        nextCommand(command);
    }

    private static void exit() {
        System.out.println("_______________________________\n" +
                "Bye. See you soon.\n" +
                "_______________________________\n");
    }

    private static void echo(String command) {
        System.out.println("_______________________________\n" +
                command+
                "\n_______________________________\n");
    }

    private static void nextCommand(String command) {
        String[] commandArraycommand = command.split(" ", 2);
        if (commandArraycommand.length == 1) {
            switch (commandArraycommand[0]) {
                case ("bye"):
                    exit();
                    break;

                case ("list"):
                    taskList.listTasks();
                    break;
            }
            Scanner scanObj = new Scanner(System.in);
            String newCommand = scanObj.nextLine();
            nextCommand(newCommand);
            return;
        }
        String[] commandArraycommandfirst = commandArraycommand[1].split("/");
        switch(commandArraycommand[0]) {

            case ("mark"):
                taskList.mark(Integer.valueOf(commandArraycommand[1]));
                break;

            case ("unmark"):
                taskList.unmark(Integer.valueOf(commandArraycommand[1]));
                break;

            case ("todo"):
                taskList.addTask(commandArraycommandfirst[0]);
                break;

            case ("deadline"):
                String[] commandArraycommandsecond = commandArraycommandfirst[1].split(" ",2);
                taskList.addTask(commandArraycommandfirst[0],commandArraycommandsecond[1]);
                break;

            case ("event"):
                String[] commandArraycommandthird = commandArraycommandfirst[1].split(" ",2);
                String[] commandArraycommandforth = commandArraycommandfirst[2].split(" ", 2);
                taskList.addTask(commandArraycommandfirst[0],commandArraycommandthird[1], commandArraycommandforth[1]);
                break;
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
            return "[E]" + super.toString() + "(from: " + startDayDateTime + " to: " + endDayDateTime +")";
        }
    }
}

class ListOfTask {
    private static Task[] listOfTask = new Task[100];
    private static int counter = 0;

    public void addTask(String task) {
        listOfTask[counter] = new Task.ToDos(task);
        System.out.println("added: " + listOfTask[counter]);
        counter++;
    }

    public void addTask(String task, String dayDate) {
        listOfTask[counter] = new Task.Deadlines(task, dayDate);
        counter++;
        System.out.println("added: " + task);
    }

    public void addTask(String task, String startDayDateTime, String endDayDateTime) {
        listOfTask[counter] = new Task.Event(task, startDayDateTime, endDayDateTime);
        counter++;
        System.out.println("added: " + task);
    }

    public void listTasks() {
        for(int i = 0; i < counter; i++) {
            System.out.println(i+1 + "." + listOfTask[i].toString());
        }
    }

    public void mark(int index) {
        listOfTask[index - 1].mark();
        System.out.println(listOfTask[index - 1].toString());
    }

    public void unmark(int index) {
        listOfTask[index - 1].unmark();
        System.out.println(listOfTask[index - 1].toString());
    }
}