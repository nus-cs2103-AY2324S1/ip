import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Noac {


    private static final String FILE_PATH = "./data/noac.txt";


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();

        String logo =  " _   _  ___    _    ____\n" +
                "| \\ | |/ _ \\  / \\  / ___|\n" +
                "|  \\| | | | |/ _ \\| |\n" +
                "| |\\  | |_| / ___ \\ |___\n" +
                "|_| \\_|\\___/_/   \\_\\____|\n";
        System.out.println("Hello from\n" + logo);


        try {
            tasks = Noac.loadFromFile();

        } catch (NoacException e) {
            System.out.println("    ____________________________________________________________");
            System.out.println("    " + e.getMessage());
            System.out.println("    ____________________________________________________________");

        }


        String welcomeMessage = "    ____________________________________________________________\n" +
                "     Hello! I'm NOAC\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(welcomeMessage);

        String userInput = scanner.nextLine();

        boolean didListChange = false;

        while (!userInput.equals("bye")) {

            try{
                String[] userInputArr = userInput.split(" ");
                String command = userInputArr[0];

                switch(command) {
                    case "list":
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 1; i <= tasks.size(); i++) {
                            System.out.println("     " + i + "." + tasks.get(i-1).toString());
                        }
                        System.out.println("    ____________________________________________________________");

                        break;

                    case "mark": case "unmark":
                        String[] temp = userInput.split(" ");

                        if(checkValidMarkInput(userInput, tasks.size())){

                            int taskNo = Integer.parseInt(temp[1]);

                            System.out.println("    ____________________________________________________________");

                            if (command.equals("mark")) {
                                tasks.get(taskNo - 1).markAsDone();
                                didListChange = true;

                                System.out.println("     Nice! I've marked this task as done:");

                            } else {
                                tasks.get(taskNo - 1).unmarkAsDone();
                                didListChange = true;

                                System.out.println("     OK, I've marked this task as not done yet:");

                            }
                            System.out.println("       " + tasks.get(taskNo-1).toString());
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

                            tasks.add(t);
                            didListChange = true;


                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + t.toString());
                            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
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

                        tasks.add(d);
                        didListChange = true;


                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + d.toString());
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
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

                        tasks.add(e);
                        didListChange = true;


                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + e.toString());
                        System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("    ____________________________________________________________");



                        break;


                    case "delete":

                        if(checkValidDeleteInput(userInput, tasks.size())) {

                            int taskNo = Integer.parseInt(userInputArr[1]);

                            System.out.println("    ____________________________________________________________");
                            System.out.println("     Noted. I've removed this task:");
                            System.out.println("       " + tasks.get(taskNo-1).toString());

                            tasks.remove(taskNo-1);
                            didListChange = true;

                            System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
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


            if(didListChange) {
                printToFile(tasks);
            }

            didListChange = false;


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


    private static void printToFile(ArrayList<Task> tasks){
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0; i < tasks.size(); i++) {
                bufferedWriter.write(tasks.get(i).printToFile() + "\n");

            }

            bufferedWriter.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private static ArrayList<Task> loadFromFile() throws NoacException {

        ArrayList<Task> returnList = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    String[] fileLineInput = scanner.nextLine().split("\\|");

                    String taskType = fileLineInput[0];

                    switch (taskType){
                    case "T":
                        if (fileLineInput.length != 3) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        Todo todo = new Todo(fileLineInput[2]);
                        if(fileLineInput[1].equals("1")) {
                            todo.markAsDone();
                        }

                        returnList.add(todo);

                        break;

                    case "D":
                        if (fileLineInput.length != 4) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        Deadline deadline = new Deadline(fileLineInput[2], fileLineInput[3]);
                        if(fileLineInput[1].equals("1")) {
                            deadline.markAsDone();
                        }

                        returnList.add(deadline);

                        break;

                    case "E":
                        if (fileLineInput.length != 5) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        Event event = new Event(fileLineInput[2], fileLineInput[3], fileLineInput[4]);
                        if(fileLineInput[1].equals("1")) {
                            event.markAsDone();
                        }

                        returnList.add(event);

                        break;

                    default:
                        throw new NoacException("☹ OOPS!!! Corrupted Save file");


                    }



                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return returnList;

    }


}



