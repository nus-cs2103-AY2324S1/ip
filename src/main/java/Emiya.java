import java.util.Scanner;

public class Emiya {

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static void main(String[] args) {

        // pointer to determine where to add objects to array
        int arrayPointer = 0;

        // represents the list as an array of Task objects
        Task[] taskArray = new Task[100];
        String welcomeMessage = "-----------------------------------------\n"
                + "Hello! I'm Emiya\n"
                + "What can I do for you?\n"
                + "-----------------------------------------\n";
        String exitMessage = "-----------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-----------------------------------------\n";
        Scanner myScannerObj = new Scanner(System.in);

        System.out.println(welcomeMessage);

        while (true) {
            // nextLine is blocking, so can have this here
            String input = myScannerObj.nextLine();
            // handles numbering for the list
            int listPointer = 1;
//            Pattern regexPattern = Pattern.compile("(\\D+)(.*)");
//            Matcher regexMatcher = regexPattern.matcher(input);
            String[] partsOfInput = input.split("\\s+",2);

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("I am the bone of my sword")) {
                System.out.println("-----------------------------------------\n"
                        +"Unknown to death nor known to life"+"\n"
                        + "-----------------------------------------\n");
                continue;
            }

            if (input.equals("dead")) {
                System.out.println("-----------------------------------------\n"
                        +"People die if they are killed!"+"\n"
                        + "-----------------------------------------\n");
                continue;
            }

            if (input.equals("list")) {
                StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
                        "Here are the tasks in your list:\n");
                for (Task task: taskArray) {
                    if (task == null) {
                        if (listPointer == 1) {
                            listString.append("The list is empty! Add items to the list!\n");
                        }
                        break;
                    }
                    String listItem = listPointer + "." + task + "\n";
                    listPointer++;
                    listString.append(listItem);
                }
                listString.append("-----------------------------------------\n");
                System.out.println(listString);
                continue;
            }

            if (partsOfInput != null) {
//                String typeOfTask = regexMatcher.group(1).trim();
//                String taskDetails = regexMatcher.group(2).trim();
                String typeOfTask = partsOfInput[0];
                String taskDetails = partsOfInput[1];
                Integer position = null;
                String notCommand = null;

                if (isNumeric(taskDetails)) {
                    position = Integer.parseInt(taskDetails);
                } else {
                    notCommand = taskDetails;
                }

                switch (typeOfTask) {
                    case "mark":
                        if (position != null) {
                            // handle mark list
                            taskArray[position-1].setMarked();
                            System.out.println("-----------------------------------------\n" +
                                    "Nice job! I have marked this task as done:\n" + taskArray[position-1] +"\n"
                                    +"-----------------------------------------\n");
                            break;
                        }
                        // if not a specific mark typeOfTask, continue
                    case "unmark":
                        if (position != null) {
                            taskArray[position-1].setUnmarked();
                            System.out.println("-----------------------------------------\n" +
                                    "Oof, alright I have set this task as unmarked:\n" + taskArray[position-1] +"\n"
                                    +"-----------------------------------------\n");
                            break;
                        }
                        // if not a specific unmark typeOfTask, continue
//                    case "list": // shift list out into its own thing for input
//                        StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
//                                "Here are the tasks in your list:\n");
//                        for (Task task: taskArray) {
//                            if (task == null) {
//                                if (listPointer == 1) {
//                                    listString.append("The list is empty! Add items to the list!\n");
//                                }
//                                break;
//                            }
//                            String listItem = listPointer + "." + task + "\n";
//                            listPointer++;
//                            listString.append(listItem);
//                        }
//                        listString.append("-----------------------------------------\n");
//                        System.out.println(listString);
//                        break;
                    case "todo":
                        // need to be able to go through the rest of the string and add it inside
                        taskArray[arrayPointer] = new ToDo(taskDetails);
                        arrayPointer++;
                        String todoOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + taskArray[arrayPointer-1] +"\n"
                                +"Now you have " + arrayPointer + " tasks in your list!\n"
                                +"-----------------------------------------\n";
                        System.out.println(todoOutputMessage);
                        break;
                    case "deadline": // go through taskDetails and find /by
                        String[] deadlineDetails = taskDetails.split("/by");
                        taskArray[arrayPointer] = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        arrayPointer++;
                        String deadlineOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + taskArray[arrayPointer-1] +"\n"
                                +"Now you have " + arrayPointer + " tasks in your list!\n"
                                +"-----------------------------------------\n";
                        System.out.println(deadlineOutputMessage);
                        break;
                    case "event": // need to go through taskDetails and find /from and /to
                        String[] eventDetails = taskDetails.split("/from ");
                        String[] eventDurationDetails = eventDetails[1].split("/to ");
                        taskArray[arrayPointer] = new Event(eventDetails[0], eventDurationDetails[0], eventDurationDetails[1]);
                        arrayPointer++;
                        String eventOutputMessage = "-----------------------------------------\n" +
                                "Sure! I have added this task to the list:\n" + taskArray[arrayPointer-1] +"\n"
                                +"Now you have " + arrayPointer + " tasks in your list!\n"
                                +"-----------------------------------------\n";
                        System.out.println(eventOutputMessage);
                        break;
                    default:
//                        String outputMessage = "-----------------------------------------\n" +
//                                "Sure! I have added this task to the list:\n" + taskArray[arrayPointer-1] +"\n"
//                                +"Now you have " + arrayPointer + " tasks in your list!\n"
//                                +"-----------------------------------------\n";
//                        System.out.println(outputMessage);
                }

            }

        }

        System.out.println(exitMessage);

        myScannerObj.close();
    }
}
