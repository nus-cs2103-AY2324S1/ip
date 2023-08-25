import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Barbie {
    public static void main(String[] args) {
            // CONSTANTS
            String line = "______________________________\n";
            Scanner scanner = new Scanner(System.in);
            String logo = " ____             _\n"
                    + "|  _ \\           | |\n"
                    + "| |_| |_____,_ ,_| |,___  _  ___\n"
                    + "|  _ /|  _  | ` _|  __\\ \\| |/ _  \\\n"
                    + "| |_| | |_| | |  | |__/ /| |  ___/\n"
                    + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
            ArrayList<Task> list = new ArrayList<>();

            // Intro
            System.out.println(line
                    + "Hi Barbie! Hi Ken!\n"
                    + "\nI'm\n"
                    + logo
                    + "\nWhat can I do for you?\n" +
                    line);
            System.out.println("[you]:");

            // Reading input
            String input = scanner.nextLine();
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            int indexNumber = 0; // Starting from 1 reduces the need to subtract and add 1 for usability.

            loop:
            while (!Objects.equals(input, "bye")) {
                try {

                    System.out.println("\t" + line
                        + "\t [barbie]:\n");
                    switch (command) {
                        case "mark":
                        case "unmark":
                        case "del":


                            String desc = parts[1];
                            int taskNumber;
                            try {
                                taskNumber = Integer.parseInt(desc) - 1;
                            } catch (NumberFormatException e) {
                                throw new BarbieFormatException("Task to mark or unmark or del is not provided as a digit!\n"
                                        + "use the list command to see the digit of your task and make sure to give "
                                        + "the digit of the task you want to mark/unmark/del. (e.g. mark 2)");
                            }

                            if (Objects.equals(command, "mark")) {

                                // Editing variables
                                list.get(taskNumber).mark();

                                // Output
                                System.out.println("\t Nice! I've marked this task as done:\n"
                                        + "\t " + list.get(taskNumber) + "\n"
                                        + "\t" + line);

                            } else if (Objects.equals(command, "unmark")){

                                // Editing variables
                                taskNumber = Integer.parseInt(desc);
                                list.get(taskNumber).unmark();

                                // Output
                                System.out.println("\t Alright! I've marked this task as not done yet:\n"
                                        + "\t " + list.get(taskNumber) + "\n"
                                        + "\t" + line);
                            } else {

                                // Editing variables
                                list.remove(taskNumber);
                                indexNumber -= 1;

                                // Output
                                System.out.println("\t Deletion success! I've deleted this task off your list.");

                            }
                            break;

                        case "todo":
                        case "deadline":
                        case "party":

                            if (parts.length < 2) {
                                throw new BarbieFormatException("Barbie your item has no description!\n"
                                        + "Remember to add a description after the command 'todo/deadline/party'.");
                            }

                            if (Objects.equals(command, "todo")) {

                                desc = parts[1];
                                list.add(indexNumber, new Todo(desc));

                            } else if (Objects.equals(command, "deadline")) {

                                String[] parts2 = parts[1].split("/");

                                if (parts2.length < 2) {
                                    throw new BarbieFormatException("Barbie your deadline has no deadline!\n"
                                            + "Remember to add a deadline after the description denoted by a '/' luv");
                                }

                                desc = parts2[0];
                                String by = parts2[1];

                                list.add(indexNumber, new Deadlines(desc, by));

                            } else {

                                String[] parts2 = parts[1].split("/");

                                if (parts2.length < 3) {
                                    throw new BarbieFormatException("Barbie your party has the incorrect number of timings!\n"
                                            + "Remember to add a 'start' time and an 'end' time"
                                            + " after the description! denote it by a '/' luv");
                                }

                                desc = parts2[0];
                                String from = parts2[1];
                                String to = parts2[2];

                                list.add(indexNumber, new Party(desc, from, to));

                            }

                            System.out.println("\t Got you barbie! I've added this task to your Barbie list:\n"
                                    + "\t " + list.get(indexNumber));
                            indexNumber += 1;
                            break;


                        case "list":

                            // No variables to edit, only output (refer to listTasks func)
                            listTasks(list, indexNumber);

                            break;


                        case "bye":

                            break loop; // break out of the while loop, not switch statement


                        default:
                            // Editing variables
                            list.add(indexNumber, new Task(input)); // Create a new Task
                            indexNumber += 1; //Incrementing item counter

                            // Output
                            System.out.println("\t Okey Dokey! I've added this task into your list:\n"
                                    + "\t[ ] " + input);


                            break;
                    }
                } catch (BarbieFormatException e) {
                    System.out.println("Barbie Error!! " + e.getMessage());
                } catch (Exception ex) {
                    System.out.println(ex.toString());
                }

                System.out.println("\t" + line);


                // Reset
                System.out.println("[you]:");
                input = scanner.nextLine();
                parts = input.split(" ", 2);
                command = parts[0].toLowerCase();

            }


            // Exit
            System.out.println(line + "Bye Barbie! Bye Ken!\n" + line);



    }

    protected static void listTasks (ArrayList<Task> list,int indexNumber){
        if (indexNumber == 0) {
            System.out.println("Hmm.. your list looks empty. To add items, use the 'todo', 'deadline' or 'party' commands!");
        }
        // "list" command
        for (int i = 0; i < indexNumber; i++) {
            int itemNumber = i + 1;
            System.out.println("\t" + itemNumber + ". " + list.get(i));
        }
    }

}
