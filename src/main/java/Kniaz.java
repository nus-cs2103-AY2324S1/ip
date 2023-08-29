

import java.io.IOException;
import java.util.Scanner;

import exceptions.KniazRuntimeException;
import commandhandling.*;
import commandhandling.commandhandling.*;
import ui.inputparser.InstructionType;
import ui.inputparser.commands.CommandFactory;
import ui.inputparser.KniazParser;
import storage.save.KniazLoader;
import storage.save.KniazSaver;
import storage.TaskList;

public class Kniaz {

    /**
     * Seperator to make things a bit prettier
     */
    private static final String SEPERATOR = "_".repeat(20) + "\n";

    /**
     * Logo to use to represent this chatbot in the command line
     */
    private static final String LOGO = "KNIAZ";
    // Placeholder for now, intend to make prettier later
    // Kniaz is a rough equivalent for Duke in eastern europe - get it?

    /**
     * Message to print when exiting.
     */
    private static final String EXITMESSAGE = "Bye. Hope to see you again soon!";



    /**
     * All tasks stored by Kniaz.
     */
    private static TaskList taskList = new TaskList();


    /**
     * Main function of Kniaz, that is intended as entry point into the program.
     * @param args arguments to be entered into Kniaz when it is called from command line
     */
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner input = new Scanner(System.in);
        // initialise a scanner object to read from input

        System.out.println("Hello from\n" + Kniaz.LOGO);
        System.out.println(Kniaz.SEPERATOR);

        System.out.println("What can I do for you?");
        System.out.println(Kniaz.SEPERATOR);

        KniazSaver kniazSaver = new KniazSaver(); // use default
        KniazLoader kniazLoader = new KniazLoader(); // use default
        try { //wrap this in a try-catch because loading has many runtime exceptions that might occur
            taskList = kniazLoader.load();
        } catch (IOException e) { // For IOExceptions in general
            System.out.println("Could not load previous.");
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) { // For when the data is corrupted
            System.out.println("Data did not align to a class");
            System.out.println(e.getMessage());
        }

        CommandFactory nextCommand; //Initialise the input
        while (true) { // I find this a bit icky but we rely on guard clauses to break instead

            try {
                nextCommand = KniazParser.parseCommand(input.nextLine());
                // We try to parse...

            } catch (KniazRuntimeException e) {
                // And if there's an issue in parsing, we feedback user input!
                System.out.println(e.getUserMessage());
                System.out.println(Kniaz.SEPERATOR);
                // this needs to be here, because we skip the rest.
                continue;
            }


            if (nextCommand.instructionEquals(InstructionType.QUIT)) {

                break; // exit if we are told to quit
            }

            if (nextCommand.instructionEquals(InstructionType.LIST)) {

                System.out.println(taskList.toPrintString());
                // print out if we are asked to list
            } else if (nextCommand.instructionEquals(InstructionType.MARK)) {

                try {

                    String taskString = MarkHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been marked as done.");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }



            } else if (nextCommand.instructionEquals(InstructionType.UNMARK)) {

                try {

                    String taskString = UnmarkHandler.handle(taskList, nextCommand.getArgs());
                    System.out.println("Ah, so you didn't actually finish it. Correcting your mistake.");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            } else if (nextCommand.instructionEquals(InstructionType.DELETE)){

                try {
                    String taskString = DeleteHandler.handle(taskList, nextCommand.getArgs());
                    System.out.println("This task has been erased, mercy on its data :");
                    System.out.println(taskString);
                    System.out.println(String.format("Only %s tasks remain.",taskList.size()));
                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }
            } else if (nextCommand.instructionEquals(InstructionType.TODO)) {

                try {

                    String taskString = ToDoHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been added To-Do : ");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            } else if (nextCommand.instructionEquals(InstructionType.DEADLINE)) {

                try {

                    String taskString = DeadlineHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been added as a Deadline : ");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            } else if (nextCommand.instructionEquals(InstructionType.EVENT)) {


                try {

                    String taskString = EventHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been added To-Do : ");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            }

            // Each command input will invariably result in a seperator line being printed
            // Helps to keep it looking nice
            System.out.println((Kniaz.SEPERATOR));


            try {
                kniazSaver.save(taskList);
                // Every time a command is entered, storage.save.
                // This is because the list can only ever be updated via command.
            } catch (IOException e) {
                // When something goes wrong in trying to storage.save with regards to IO
                // Should not happen in usual operation
                System.out.println("Something went wrong trying to storage.save, I won't remember your tasks on reload!");
                System.out.println(e.getMessage());
                break;

            } catch (SecurityException e) {
                // When the security manager doesn't let us storage.save
                // Complain back to the user
                System.out.println("I couldn't storage.save because I wasn't allowed!");
                System.out.println(e.getMessage());
                break;
            }


        }


        System.out.println(Kniaz.EXITMESSAGE);
        System.out.println(Kniaz.SEPERATOR);
    }







}
