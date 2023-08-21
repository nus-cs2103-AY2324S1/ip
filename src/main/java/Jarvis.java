import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        String name = "Jarvis";
        String logo = "      **     **     *******   **      ** **  ********\n" +
                "     /**    ****   /**////** /**     /**/** **////// \n" +
                "     /**   **//**  /**   /** /**     /**/**/**       \n" +
                "     /**  **  //** /*******  //**    ** /**/*********\n" +
                "     /** **********/**///**   //**  **  /**////////**\n" +
                " **  /**/**//////**/**  //**   //****   /**       /**\n" +
                "//***** /**     /**/**   //**   //**    /** ******** \n" +
                " /////  //      // //     //     //     // ////////  ";
        System.out.println(logo);
        String line = "____________________________________________________________";
        String greeting = "Good day Sir! I'm ";
        String question = "How can I help you today Sir?";
        String signOff = "Good bye Sir!";

        System.out.println(line);
        System.out.println(greeting + name + "!");
        System.out.println(question);
        System.out.println(line);

        while (true) { // any other command other than bye
            Scanner userInput = new Scanner(System.in);
            String request = userInput.nextLine();

            if (!request.equals("bye")) { // if "bye is not entered"
                System.out.println(line);
                System.out.println(request);
                System.out.println(line);
            } else {
                System.out.println(line); // if "bye" is entered
                System.out.println(signOff);
                System.out.println(line);
                break;
            }
        }
    }
}