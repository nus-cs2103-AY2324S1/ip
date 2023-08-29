import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;



public class Brotherman {

    public static boolean isValidCommands(String command) throws BrothermanException{
        if (!command.equals("list")) {
            return true;
        }

        if (command.split(" ")[0].equals("mark") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! Something has to be marked!");
        }

        if (command.split(" ")[0].equals("unmark") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! Something has to be unmarked!");
        }

        if (command.split(" ")[0].equals("todo") && command.split(" ").length <= 1) {
            throw new BrothermanException("Error! There must be a description!!!");
        }

        if (command.split(" ")[0].equals("deadline") && command.split(" ")[1].equals("/by")) {
            throw new BrothermanException("Error! There has to be a description!!");
        }

        if (command.split(" ")[0].equals("deadline") && !command.contains("/by")) {
            throw new BrothermanException("Error! There has to be a due date/time!");
        }

        if (command.split(" ")[0].equals("event") && !command.contains("/from")) {
            throw new BrothermanException("Error! There has to be a start date/time!");
        }

        if (command.split(" ")[0].equals("event") && !command.contains("/to")) {
            throw new BrothermanException("Error! There has to be a end date/time!");
        }

        if (command.split(" ")[0].equals("event") && command.split(" ")[1].equals("/from")) {
            throw new BrothermanException("Error! There has to be a description");
        }
        return true;
    }

    public static void saveToFile(ArrayList<Task> list) {

        File folder = new File("./data/");
        if(!folder.exists()) folder.mkdirs();

        try (PrintWriter output = new PrintWriter("./data/brotherman.txt")){
            for (Task listItems : list) {
                output.println(listItems.storeText());
            }
        } catch(Exception e){
            e.getStackTrace();
        }

    }

    public static void readFromFile(ArrayList<Task> array) {

        try (Scanner sc = new Scanner(new File("./data/brotherman.txt"))) {
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                String[] arr = taskString.split("\\|");
                Task.readTaskFromFile(arr, array);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Welcome to Brotherman!");
        }

    }
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        ArrayList<Task> userList = new ArrayList<Task>();


        System.out.println(
                "___________________________________________________________\n"
                + "Hello! I'm Brotherman\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n");

        readFromFile(userList);
        String userCommand = input.nextLine();


        while (!userCommand.equals("bye")) {

            if (userCommand.equals("list")) {
                int start = 1;
                for (Task listItems : userList) {
                    System.out.println(start + ". " + listItems.toString());
                    start++;
                }
            } else if (userCommand.split(" ")[0].equals("mark")) {
                int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;

                if (task < 0 || task >= userList.size()) {
                    System.out.println("Brotherman the value you put in wrong.  Try again.");
                } else {
                    userList.get(task).markAsDone();
                    System.out.println(
                            "___________________________________________________________\n"
                                    + "The task is done Brotherman \n"
                                    + userList.get(task).toString()
                                    + " \n"
                                    + "___________________________________________________________\n"
                    );
                }
            } else if (userCommand.split(" ")[0].equals("unmark")) {
                int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;

                if (task < 0 || task >= userList.size()) {
                    System.out.println("Brotherman the value you put in wrong.  Try again.");
                } else {
                    userList.get(task).unmarkAsDone();
                    System.out.println(
                            "___________________________________________________________\n"
                                    + "The task is now undone Brotherman \n"
                                    + userList.get(task).toString()
                                    + " \n"
                                    + "___________________________________________________________\n"
                    );
                }

            } else if (userCommand.split(" ")[0].equals("delete")) {

                int task = Integer.parseInt(userCommand.split(" ")[1]) - 1;
                if (task < 0 || task >= userList.size()) {
                    System.out.println("Brotherman the value you put in wrong.  Try again.");
                } else {
                    System.out.println(
                            "___________________________________________________________\n"
                                    + "The task is now deleted Brotherman \n"
                                    + userList.get(task).toString()
                                    + " \n"
                                    + "___________________________________________________________\n"
                    );

                    userList.remove(task);
                }

            } else if (userCommand.split(" ")[0].equals("todo")) {

                userList.add(new Todo(userCommand.split("todo")[1]));

                System.out.println(
                        "___________________________________________________________\n"
                        + "Got you Brotherman! New Todo added to the list. \n"
                        + userList.get(userList.size() - 1).toString()
                        + " \n"
                        + "You have " + userList.size() + " tasks in the list! \n"
                        + "___________________________________________________________\n"
                );

            } else if (userCommand.split(" ")[0].equals("deadline")) {

                userList.add(new Deadline(userCommand.split("deadline ")[1]));

                System.out.println(
                        "___________________________________________________________\n"
                        + "Got you Brotherman! New deadline added to the list.\n"
                        + userList.get(userList.size() - 1).toString()
                        + " \n"
                        + "You have " + userList.size() + " tasks in the list!\n"
                        + "___________________________________________________________\n"
                );

            } else if (userCommand.split(" ")[0].equals("event")) {

                userList.add(new Event(userCommand.split("event")[1]));
                System.out.println(
                        "___________________________________________________________\n"
                        + "Got you Brotherman! New event added to the list.\n"
                        + userList.get(userList.size() - 1).toString()
                        + " \n"
                        + "You have " + userList.size() + " tasks in the list!\n"
                        + "___________________________________________________________\n"
                );
            } else {

                System.out.println("brotherman input a todo, event or deadline!");
            }

            userCommand = input.nextLine();

        }

        saveToFile(userList);
        System.out.println(
                "___________________________________________________________\n"
                + "Bye, see you again Brotherman!\n"
                + "___________________________________________________________\n"

        );
    }
}
