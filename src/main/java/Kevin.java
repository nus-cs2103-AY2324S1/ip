import TaskList.TaskList;
import java.util.Scanner;

public class Kevin {
    public static final String HORIZONTAL_LINE = "_".repeat(70);

    public static final String BOT_NAME = "\t \n" +
            "\t" + " _   __ _____ _   _ _____ _   _ \n" +
            "\t" +"| | / /|  ___| | | |_   _| \\ | |\n" +
            "\t" +"| |/ / | |__ | | | | | | |  \\| |\n" +
            "\t" +"|    \\ |  __|| | | | | | | . ` |\n" +
            "\t" +"| |\\  \\| |___\\ \\_/ /_| |_| |\\  |\n" +
            "\t" +"\\_| \\_/\\____/ \\___/ \\___/\\_| \\_/\n";

    public static void wrapInHorizontalLines(String str) {
        System.out.println("\t" + HORIZONTAL_LINE);
        System.out.println("\t" + str);
        System.out.println("\t" + HORIZONTAL_LINE);
    }

    public static void echo(String command) {
        wrapInHorizontalLines(command);
    }

    public static void hello() {
        String welcomeMessage = "Hello! I'm" + BOT_NAME + "\n\t" + "What can I do for you?";
        wrapInHorizontalLines(welcomeMessage);
    }

    public static void bye() {
        String goodbyeMessage = "Bye. Hope to see you again soon!";
        wrapInHorizontalLines(goodbyeMessage);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        hello();

        while (true) {
            String fullCommand = scanner.nextLine();
            String command = fullCommand.split(" ")[0];
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                wrapInHorizontalLines(taskList.list());
            } else if (command.equals("mark")) {
                int toDoIndex = Integer.parseInt(fullCommand.split(" ")[1]);
                wrapInHorizontalLines(taskList.mark(toDoIndex));
            } else if (command.equals("unmark")) {
                int toDoIndex = Integer.parseInt(fullCommand.split(" ")[1]);
                wrapInHorizontalLines(taskList.unmark(toDoIndex));
            } else if (command.equals("todo")) {
                String name = fullCommand.split(" ", 2)[1];
                wrapInHorizontalLines(taskList.addToDo(name));
            } else if (command.equals("event")) {
                String eventInfo = fullCommand.split(" ", 2)[1];
                String[] splitEventInfo = eventInfo.split(" /");
                String name = splitEventInfo[0];
                String startTime = splitEventInfo[1].replace("from ", "");
                String endTime = splitEventInfo[2].replace("to ", "");
                wrapInHorizontalLines(taskList.addEvent(name, startTime, endTime));
            } else if (command.equals("deadline")) {
                String deadlineInfo = fullCommand.split(" ", 2)[1];
                String[] splitDeadlineInfo = deadlineInfo.split(" /");
                String name = splitDeadlineInfo[0];
                String deadline = splitDeadlineInfo[1].replace("by", "");
                wrapInHorizontalLines(taskList.addDeadline(name, deadline));
            } else {
                wrapInHorizontalLines(taskList.addList(fullCommand));
            }
        }

        bye();
    }
}



