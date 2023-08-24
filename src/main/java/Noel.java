import java.util.Scanner;

public class Noel {

    static String helloMsg = " Hello! I'm Noel!\nWhat can I do for you?";
    static String byeMsg = " Bye. Hope to see you again soon!";

    static int maxSize = 100;
    static Task[] taskList = new Task[maxSize];

    // headPointer points to the first item in the taskList
    static int headPointer = 0;

    // tailPointer points to the next free space in the taskList
    static int tailPointer = 0;

    static int sizeOfList = 0;

    static String addedMessageStart = "Got it. I've added this task:";


    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    public static boolean checkFull() {
        return sizeOfList == maxSize;
    }

    public static boolean checkEmpty() {
        return sizeOfList == 0;
    }

    public static void addToDo(String task) {

        Task taskToAdd = new ToDos(task);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList[tailPointer] = taskToAdd;

        tailPointer = (tailPointer + 1) % maxSize;
        sizeOfList++;

        String addedMessageEnd = "Now you have " + sizeOfList + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);

    }

    public static void addDeadline(String task, String endDate) {

        Task taskToAdd = new Deadlines(task, endDate);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList[tailPointer] = taskToAdd;

        tailPointer = (tailPointer + 1) % maxSize;
        sizeOfList++;

        String addedMessageEnd = "Now you have " + sizeOfList + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);

    }

    public static void addEvent(String task, String startDate, String endDate) {

        Task taskToAdd = new Events(task, startDate, endDate);

        if (checkFull()) {
            System.out.println("Array is full!");
        }

        taskList[tailPointer] = taskToAdd;

        tailPointer = (tailPointer + 1) % maxSize;
        sizeOfList++;

        String addedMessageEnd = "Now you have " + sizeOfList + " tasks in the list.";
        String updateAdd = addedMessageStart + "\n" +  taskToAdd + "\n" + addedMessageEnd;
        printFunction(updateAdd);

    }

    public static void printTaskList(){
        if (checkEmpty()) {
            System.out.println("List is empty!");
        } else {
            String filler = "____________________________________________________________";
            System.out.println(filler);
            int j = 1;
            for (int i = headPointer; i != tailPointer; i = (i + 1) % maxSize) {
                System.out.println(j + ". " + taskList[i]);
                j++;
            }
            System.out.println(filler);
        }
    }

    public static void main(String[] args) {

        printFunction(helloMsg);

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
                        taskNum = ((headPointer + taskNum) - 1) % maxSize;
                        taskList[taskNum].markAsDone();
                        break;
                    }
                    case "unmark": {
                        int taskNum = Integer.parseInt(result[1]);
                        taskNum = ((headPointer + taskNum) - 1) % maxSize;
                        taskList[taskNum].unMark();
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

                    default:
                        System.out.println("Invalid Option!");
                }
            } else {
                System.out.println("Invalid Option!");
            }
            nextLine = in.nextLine();
        }

        printFunction(byeMsg);
    }
}

