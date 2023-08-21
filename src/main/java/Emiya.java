import java.util.Scanner;

public class Emiya {

    public static void main(String[] args) {
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

            String outputMessage = "-----------------------------------------\n" +
                    userCommand+"\n"
                    +"-----------------------------------------\n";
            System.out.println(outputMessage);

        }

        System.out.println(exitMessage);

        myScannerObj.close();
    }
}
