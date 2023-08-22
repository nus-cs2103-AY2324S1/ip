import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        String input = "";

        while (true) {
            // nextLine is blocking, so can have this here
            input = myScannerObj.nextLine();
            // handles numbering for the list
            int listPointer = 1;
            Pattern regexPattern = Pattern.compile("(\\D+)(.*)");
            Matcher regexMatcher = regexPattern.matcher(input);

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

            if (regexMatcher.find()) {
                String command = regexMatcher.group(1).trim();
                String optional = regexMatcher.group(2).trim();
                Integer position = null;
                String notCommand = null;

                if (isNumeric(optional)) {
                    position = Integer.parseInt(optional);
                } else {
                    notCommand = optional;
                }

                switch (command) {
                    case "mark":
                        if (position != null) {
                            // handle mark list
                            taskArray[position-1].setMarked();
                            System.out.println("-----------------------------------------\n" +
                                    "Nice job! I have marked this task as done:\n" + taskArray[position-1] +"\n"
                                    +"-----------------------------------------\n");
                            break;
                        }
                        // if not a specific mark command, continue
                    case "unmark":
                        if (position != null) {
                            taskArray[position-1].setUnmarked();
                            System.out.println("-----------------------------------------\n" +
                                    "Oof, alright I have set this task as unmarked:\n" + taskArray[position-1] +"\n"
                                    +"-----------------------------------------\n");
                            break;
                        }
                        // if not a specific unmark command, continue
                    case "list":
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
                        break;
                    default:
                        String outputMessage = "-----------------------------------------\n" +
                                "added: " + input +"\n"
                                +"-----------------------------------------\n";
                        System.out.println(outputMessage);
                        taskArray[arrayPointer] = new Task(input);
                        arrayPointer++;
                }

            }

//            // handles input that is not the list command; will add to Task array
//            // i think this part should be added into the
//            if (!input.equals("list")) {
//                String outputMessage = "-----------------------------------------\n" +
//                        "added: " + input +"\n"
//                        +"-----------------------------------------\n";
//                System.out.println(outputMessage);
//                taskArray[arrayPointer] = new Task(input);
//                arrayPointer++;
//                continue;
//            }
//
//            // code that handles the list command; will take return list to the user
//            StringBuilder listString = new StringBuilder("-----------------------------------------\n" +
//                    "Here are the tasks in your list:\n");
//
//            for (Task task: taskArray) {
//                if (task == null) {
//                    if (listPointer == 1) {
//                        listString.append("The list is empty! Add items to the list!\n");
//                    }
//                    break;
//                }
//                String listItem = listPointer + "." + task + "\n";
//                listPointer++;
//                listString.append(listItem);
//            }
//
//            listString.append("-----------------------------------------\n");
//
//            System.out.println(listString);

        }

        System.out.println(exitMessage);

        myScannerObj.close();
    }
}
