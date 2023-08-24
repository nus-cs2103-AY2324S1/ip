
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

            try{
                String[] userInputArr = userInput.split(" ");
                String command = userInputArr[0];

                switch(command) {
                    case "list":
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 1; i <= list.size(); i++) {
                            System.out.println("     " + i + "." + list.get(i-1).toString());
                        }
                        System.out.println("    ____________________________________________________________");

                        break;

                    case "mark": case "unmark":
                        String[] temp = userInput.split(" ");

                        if(checkValidMarkInput(userInput, list.size())){

                            int taskNo = Integer.parseInt(temp[1]);

                            System.out.println("    ____________________________________________________________");

                            if (command.equals("mark")) {
                                list.get(taskNo - 1).markAsDone();

                                System.out.println("     Nice! I've marked this task as done:");

                            } else {
                                list.get(taskNo - 1).unmarkAsDone();

                                System.out.println("     OK, I've marked this task as not done yet:");

                            }
                            System.out.println("       " + list.get(taskNo-1).toString());
                            System.out.println("    ____________________________________________________________");


                        }

                        break;

                    case "todo":
                        if (userInputArr.length > 1) {
                            String description = "";

                            for(int i = 1; i < userInputArr.length; i++) {
                                description += userInputArr[i] + " ";
                            }

                            description = description.substring(0, description.length() - 1);

                            Todo t = new Todo(description);

                            list.add(t);

                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + t.toString());
                            System.out.println("     Now you have " + list.size() + " tasks in the list.");
                            System.out.println("    ____________________________________________________________");



                        } else {

                            throw new NoacException("☹ OOPS!!! The description of a todo cannot be empty.");

                        }
                        break;

                    case "deadline":


                        String description = "";
                        String by = "";

                        boolean afterBy = false;

                        for(int i = 1; i < userInputArr.length; i++) {
                            if (userInputArr[i].equals("/by")){
                                afterBy = true;
                                continue;
                            }
                            if (afterBy) {
                                by += userInputArr[i] + " ";
                            } else {
                                description += userInputArr[i] + " ";
                            }

                        }

                        if (!afterBy) {

                            throw new NoacException("☹ OOPS!!! The input must contain the command /by");

                        }

                        if(by.length() == 0 || description.length() == 0) {

                            throw new NoacException("☹ OOPS!!! The description and by of a deadline cannot \n     be empty");

                        }

                        by = by.substring(0, by.length() - 1);
                        description = description.substring(0, description.length() - 1);

                        Deadline d = new Deadline(description, by);

                        list.add(d);

                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + d.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");


                        break;


                    case "event":

                        String descript = "";
                        String from = "";
                        String to = "";

                        String status = "event";


                        for(int i = 1; i < userInputArr.length; i++) {
                            if (userInputArr[i].equals("/from")){
                                status = "from";
                                continue;
                            }
                            if (userInputArr[i].equals("/to")){
                                status = "to";
                                continue;
                            }

                            if (status.equals("event")) {
                                descript += userInputArr[i] + " ";
                            } else if (status.equals("from")) {
                                from += userInputArr[i] + " ";
                            } else if (status.equals("to")) {
                                to += userInputArr[i] + " ";
                            }

                        }

                        if (!status.equals("to")) {

                            throw new NoacException("☹ OOPS!!! The input must contain the command /from and /to \n     in this order");                        }

                        if(descript.length() == 0 || from.length() == 0 || to.length() == 0) {

                            throw new NoacException("☹ OOPS!!! The description, from and to of a event cannot \n     be empty!");

                        }

                        from = from.substring(0, from.length() - 1);
                        to = to.substring(0, to.length() - 1);
                        descript = descript.substring(0, descript.length() - 1);

                        Event e = new Event(descript, from, to);

                        list.add(e);

                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + e.toString());
                        System.out.println("     Now you have " + list.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");



                        break;


                    case "delete":

                        if(checkValidDeleteInput(userInput, list.size())) {

                            int taskNo = Integer.parseInt(userInputArr[1]);

                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + list.get(taskNo-1).toString());
                            list.remove(taskNo-1);
                            System.out.println("     Now you have " + list.size() + " tasks in the list.");
                            System.out.println("    ____________________________________________________________");


                        }
                        break;


                    default:

                        throw new NoacException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }


            } catch (NoacException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println("    " + e.getMessage());
                System.out.println("    ____________________________________________________________");

            }



            userInput = scanner.nextLine();

        }

        String byeMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        System.out.println(byeMessage);
    }


    private static boolean checkValidMarkInput(String input, int listSize) throws NoacException {
        String[] temp = input.split(" ");

        if(temp.length > 2 || temp.length <= 1) {
            throw new NoacException("☹ OOPS!!! Please enter in the format mark [TASK_NUMBER] e.g. mark 1");

        } else if (!temp[1].matches("\\d+")) {

            throw new NoacException("☹ OOPS!!! Please enter in the format mark [TASK_NUMBER] e.g. mark 1");
        } else if (Integer.parseInt(temp[1]) > listSize) {

            throw new NoacException("☹ OOPS!!! Please enter a task in your list!");

        }

        return true;
    }

    private static boolean checkValidDeleteInput(String input, int listSize) throws NoacException {
        String[] temp = input.split(" ");

        if(temp.length > 2 || temp.length <= 1) {
            throw new NoacException("☹ OOPS!!! Please enter in the format delete [TASK_NUMBER] e.g. delete 1");

        } else if (!temp[1].matches("\\d+")) {

            throw new NoacException("☹ OOPS!!! Please enter in the format delete [TASK_NUMBER] e.g. delete 1");
        } else if (Integer.parseInt(temp[1]) > listSize) {

            throw new NoacException("☹ OOPS!!! Please enter a task in your list!");

        }

        return true;
    }



}



