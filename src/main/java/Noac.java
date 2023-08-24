
import java.util.ArrayList;
import java.util.Scanner;


public class Noac {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();


        String logo =  " _   _  ___    _    ____\n" +
                "| \\ | |/ _ \\  / \\  / ___|\n" +
                "|  \\| | | | |/ _ \\| |\n" +
                "| |\\  | |_| / ___ \\ |___\n" +
                "|_| \\_|\\___/_/   \\_\\____|\n";
        System.out.println("Hello from\n" + logo);


        String welcomeMessage = "    ____________________________________________________________\n" +
                "     Hello! I'm NOAC\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(welcomeMessage);


        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println("    " + i + "." + list.get(i-1).toString());
                }
                System.out.println("    ____________________________________________________________");
            } else if (userInput.split(" ")[0].equals("mark") || (userInput.split(" ")[0].equals("unmark"))) {

                String[] temp = userInput.split(" ");

                if(!checkValidMarkInput(userInput, list.size())){


                } else {
                    int taskNo = Integer.parseInt(temp[1]);

                    System.out.println("    ____________________________________________________________");

                    if (temp[0].equals("mark")) {
                        list.get(taskNo - 1).markAsDone();

                        System.out.println("     Nice! I've marked this task as done:");

                    } else {
                        list.get(taskNo - 1).unmarkAsDone();

                        System.out.println("     OK, I've marked this task as not done yet:");

                    }
                    System.out.println("       " + list.get(taskNo-1).toString());
                    System.out.println("    ____________________________________________________________");


                }

            }
            else {
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + userInput);
                System.out.println("    ____________________________________________________________");

                list.add(new Task(userInput));

            }

            userInput = scanner.nextLine();

        }

        String byeMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        System.out.println(byeMessage);
    }


    private static boolean checkValidMarkInput(String input, int listSize) {
        String[] temp = input.split(" ");

        if(temp.length > 2) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Invalid Input! \n     Please enter in the format mark [TASK_NUMBER] e.g. mark 1");
            System.out.println("    ____________________________________________________________");
            return false;
        } else if (!temp[1].matches("\\d+")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Invalid Input! \n     Please enter in the format mark [TASK_NUMBER] e.g. mark 1");
            System.out.println("    ____________________________________________________________");
            return  false;
        } else if (Integer.parseInt(temp[1]) > listSize) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Invalid Input! \n     Please enter a task in your list!");
            System.out.println("    ____________________________________________________________");
            return false;
        }

        return true;
    }
}



