import java.util.Objects;
import java.util.Scanner;


public class Barbie {
    public static void main(String[] args) {
        // CONSTANTS
        String line = "______________________________\n";
        Scanner scanner = new Scanner(System.in);
        String logo = " ____             _        \n"
                + "|  _ \\           | | \n"
                + "| |_| |_____,_ ,_| |,___  _  ___\n"
                + "|  _ /|  _  | ` _|  __\\ \\| |/ _  \\\n"
                + "| |_| | |_| | |  | |__/ /| |  ___/  \n"
                + "|____/ \\__,_|_|  |_|\\__/ |_|\\___/\n";
        Task[] list = new Task[100];

        // Intro
        System.out.println(line
                + "Hi Barbie! Hi Ken!\n"
                + "\nI'm\n"
                + logo
                + "\nWhat can I do for you?\n" +
                line);
        System.out.println("[you]: ");

        // Reading input
        String input = scanner.nextLine();
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();

        int itemNumber = 1; // Starting from 1 reduces the need to subtract and add 1 for usability.

        loop:
        while (!Objects.equals(input, "bye")) {
            System.out.println("\t" + line
                    + "\t [barbie]: \n");
            switch (command) {
                case "mark":
                case "unmark":

                    String desc = parts[1];
                    int taskNumber = Integer.parseInt(desc);

                    if (Objects.equals(command, "mark")) {

                        // Editing variables
                        list[taskNumber].mark();

                        // Output
                        System.out.println("\t Nice! I've marked this task as done:\n"
                                + "\t " + list[taskNumber] + "\n"
                                + "\t" + line);

                    } else {

                        // Editing variables
                        taskNumber = Integer.parseInt(desc);
                        list[taskNumber].unmark();

                        // Output
                        System.out.println("\t Alright! I've marked this task as not done yet:\n"
                                + "\t " + list[taskNumber] + "\n"
                                + "\t" + line);
                    }
                    break;

                case "todo":
                case "deadline":
                case "party":
                    try {

                        if (Objects.equals(command, "todo")) {

                            desc = parts[1];

                            list[itemNumber] = new Todo(desc);

                        } else if (Objects.equals(command, "deadline")) {

                            String[] parts2 = parts[1].split("/");
                            desc = parts2[0];
                            String by = parts2[1];

                            list[itemNumber] = new Deadlines(desc, by);

                        } else {

                            String[] parts2 = parts[1].split("/");
                            desc = parts2[0];
                            String from = parts2[1];
                            String to = parts2[2];

                            list[itemNumber] = new Party(desc, from, to);

                        }

                        System.out.println("\t Got you barbie! I've added this task to your Barbie list:\n"
                                + "\t " + list[itemNumber]);
                        itemNumber += 1;
                        break;
                    } catch (Exception e) {
                        System.out.println("\tOops.. there was an error in the formatting! \n"
                                + "\tRemember:\n"
                                + "\tdeadlines have a deadline denoted by '/',\n"
                                + "\tand parties have a start and an end both also denoted by '/'!");
                    }






                case "list":

                    // No variables to edit, only output (refer to listTasks func)
                    listTasks(list, itemNumber);

                    break;


                case "bye":

                    break loop; // break out of the while loop, not switch statement


                default:
                    // Editing variables
                    list[itemNumber] = new Task(input); // Create a new Task
                    itemNumber += 1; //Incrementing item counter

                    // Output
                    System.out.println("\t Okey Dokey! I've added this task into your list:\n"
                            + "\t[ ] " + input);


                    break;
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

    // Function listTasks for readability.
    protected static void listTasks(Task[] list, int itemNumber) {
            // "list" command
            for (int i = 1; i < itemNumber; i++) {
                System.out.println("\t" + i + ". " + list[i]);
            }
    }






}
