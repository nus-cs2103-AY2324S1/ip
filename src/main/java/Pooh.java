import java.util.Scanner;

public class Pooh {
    public static void main(String[] args) {
        String logo = "      .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "      | .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "      | |   ______     | || |     ____     | || |     ____     | || |  ____  ____  | |\n" +
                "      | |  |_   __ \\   | || |   .'    `.   | || |   .'    `.   | || | |_   ||   _| | |\n" +
                "      | |    | |__) |  | || |  /  .--.  \\  | || |  /  .--.  \\  | || |   | |__| |   | |\n" +
                "      | |    |  ___/   | || |  | |    | |  | || |  | |    | |  | || |   |  __  |   | |\n" +
                "      | |   _| |_      | || |  \\  `--'  /  | || |  \\  `--'  /  | || |  _| |  | |_  | |\n" +
                "      | |  |_____|     | || |   `.____.'   | || |   `.____.'   | || | |____||____| | |\n" +
                "      | |              | || |              | || |              | || |              | |\n" +
                "      | '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                "       '----------------'  '----------------'  '----------------'  '----------------' ";
        String horizontalLine = "      _______________________________________________________________________________";
        System.out.println(logo);
        System.out.println(horizontalLine);
        System.out.println("      Hi there! Good to see you! I'm Pooh!\n      What can I do for you?");
        System.out.println(horizontalLine);

        Scanner userInput = new Scanner(System.in);
        while  (userInput.hasNextLine()) {
            String userCmd = userInput.nextLine();
            if (userCmd.equalsIgnoreCase("bye")) {
                System.out.println(horizontalLine);
                System.out.println("      How lucky I am to have something that makes saying goodbye so hard. Bye!");
                System.out.println(horizontalLine);
                userInput.close();
                System.exit(0);
            } else {
                System.out.println(horizontalLine);
                System.out.println("      " + userCmd);
                System.out.println(horizontalLine);
            }
        }
    }
}

