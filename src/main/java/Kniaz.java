

import java.io.IOException;
import java.util.Scanner;
import java.util.List;

import exceptions.KniazRuntimeException;
import logic.taskhandling.*;
import parser.KniazCommand;
import parser.KniazParser;
import save.KniazSaver;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

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

        KniazSaver kniazSaver = new KniazSaver(); //use default

        KniazCommand nextCommand; //Initialise the input
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


            if (nextCommand.instructionEquals(KniazParser.InstructionType.QUIT)) {

                break; // exit if we are told to quit
            }

            if (nextCommand.instructionEquals(KniazParser.InstructionType.LIST)) {

                System.out.println(taskList.toPrintString());
                // print out if we are asked to list
            } else if (nextCommand.instructionEquals(KniazParser.InstructionType.MARK)) {

                try {

                    String taskString = MarkHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been marked as done.");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }



            } else if (nextCommand.instructionEquals(KniazParser.InstructionType.UNMARK)) {

                try {

                    String taskString = UnmarkHandler.handle(taskList, nextCommand.getArgs());
                    System.out.println("Ah, so you didn't actually finish it. Correcting your mistake.");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            } else if (nextCommand.instructionEquals(KniazParser.InstructionType.DELETE)){

                try {
                    String taskString = DeleteHandler.handle(taskList, nextCommand.getArgs());
                    System.out.println("This task has been erased, mercy on its data :");
                    System.out.println(taskString);
                    System.out.println(String.format("Only %s tasks remain.",taskList.size()));
                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }
            } else if (nextCommand.instructionEquals(KniazParser.InstructionType.TODO)) {

                try {

                    String taskString = ToDoHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been added To-Do : ");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            } else if (nextCommand.instructionEquals(KniazParser.InstructionType.DEADLINE)) {

                try {

                    String taskString = DeadlineHandler.handle(taskList ,nextCommand.getArgs());
                    System.out.println("As you say. The task has been added as a Deadline : ");
                    System.out.println(taskString);

                } catch (KniazRuntimeException e) {
                    System.out.println(e.getUserMessage());
                }

            } else if (nextCommand.instructionEquals(KniazParser.InstructionType.EVENT)) {


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
            } catch (IOException e) {
                System.out.println("Something went wrong trying to save, I won't remember your tasks on reload!");
                System.out.println(e.getMessage());
                break;

            } catch (SecurityException e) {
                System.out.println("I couldn't save because I wasn't allowed!");
                System.out.println(e.getMessage());
                break;
            }


        }


        System.out.println(Kniaz.EXITMESSAGE);
        System.out.println(Kniaz.SEPERATOR);
    }







}
