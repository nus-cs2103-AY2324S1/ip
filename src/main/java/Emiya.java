import java.util.Scanner;

public class Emiya {

    public static void main(String[] args) {
        int arrayPointer = 0;
        String[] listArray = new String[100];
        String welcomeMessage = "-----------------------------------------\n"
                + "Hello! I'm Emiya\n"
                + "What can I do for you?\n"
                + "-----------------------------------------\n";
        String exitMessage = "-----------------------------------------\n"
                + "Bye. Hope to see you again soon!\n"
                + "-----------------------------------------\n";
        Scanner myScannerObj = new Scanner(System.in);

        System.out.println(welcomeMessage);

        String userCommand = "";

        while (true) {
            // nextLine is blocking, so can have this here
            userCommand = myScannerObj.nextLine();
            int listPointer = 1;

            if (userCommand.equals("bye")) {
                break;
            }

            if (userCommand.equals("I am the bone of my sword")) {
                System.out.println("-----------------------------------------\n"
                        +"Unknown to death nor known to life"+"\n"
                        + "-----------------------------------------\n");
                continue;
            }

            if (userCommand.equals("dead")) {
                System.out.println("-----------------------------------------\n"
                        +"People die if they are killed!"+"\n"
                        + "-----------------------------------------\n");
                continue;
            }


            if (!userCommand.equals("list")) {
                String outputMessage = "-----------------------------------------\n" +
                        "added: " + userCommand+"\n"
                        +"-----------------------------------------\n";
                System.out.println(outputMessage);
                listArray[arrayPointer] = userCommand;
                arrayPointer++;
                continue;
            }

            StringBuilder listString = new StringBuilder("-----------------------------------------\n");

            for (String action: listArray) {
                if (action == null) {
                    if (listPointer == 1) {
                        listString.append("The list is empty! Add items to the list!\n");
                    }
                    break;
                }
                String listItem = listPointer + ". " + action + "\n";
                listPointer++;
                listString.append(listItem);
            }

            listString.append("-----------------------------------------\n");

            System.out.println(listString);

        }

        System.out.println(exitMessage);

        myScannerObj.close();
    }
}
