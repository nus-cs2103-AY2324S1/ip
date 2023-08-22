import java.util.Scanner;
import java.util.ArrayList;

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

        ArrayList<String> taskList = new ArrayList<String>(); // list of tasks

        while (true) {
            Scanner userInput = new Scanner(System.in);
            String task = userInput.nextLine();

            if (task.equals("list")) { // if "list" is entered
                System.out.println(line);
                for (int i = 0; i < taskList.size(); i++) {
                    int count = i + 1;
                    System.out.println(count + ". " + taskList.get(i));
                }
                System.out.println(line);
            } else if (task.equals("bye")) { // if "bye" is entered
                System.out.println(line);
                System.out.println(signOff);
                System.out.println(line);
                break;
            } else { // if any other request
                taskList.add(task); // add new task to the end of task list
                System.out.println(line);
                System.out.println("added: " + task);
                System.out.println(line);
            }
        }
    }
}