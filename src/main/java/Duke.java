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
        String[] commandArraycommand = command.split(" ");
        switch(commandArraycommand[0]) {

            case ("bye"):
                exit();
                break;

            case ("list"):
                taskList.listTasks();
                break;

            default:
                taskList.addTask(command);
        }
        Scanner scanObj = new Scanner(System.in);
        String newCommand = scanObj.nextLine();
        nextCommand(newCommand);
    }
}

class ListOfTask {
    private static String[] listOfTask = new String[100];
    private static int counter = 0;

    public void addTask(String task) {
        listOfTask[counter] = task;
        counter++;
        System.out.println(counter);
        System.out.println("added: " + task);
    }

    public void listTasks() {
        for(int i = 0; i < counter; i++) {
            System.out.println(i+1 + ". " + listOfTask[i]);
        }
    }
}