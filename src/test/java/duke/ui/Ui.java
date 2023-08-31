package duke.ui;

import duke.Duke;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskArray;

import java.util.Scanner;

public class Ui {
    String name;

    /**
     * The Ui class handles interactions with the user interface, displaying messages and receiving inputs.
     */
    public Ui() {

    }
    /**
     * Runs the task processing loop, taking user inputs and performing corresponding actions.
     *
     * @param oriTaskArray The original TaskArray containing tasks.
     * @return The updated TaskArray after processing user inputs.
     */
    public TaskArray runTask(TaskArray oriTaskArray) {
        TaskArray taskArray = oriTaskArray;
        //Requesting Input and Redirect to Parser
        Scanner scanner = new Scanner(System.in);
        Parser input = new Parser(acceptInput(scanner));
        //Repeating Asking User if Not Equals Bye
        String command = input.getCommand();
        while(!command.equals("bye")) {
            switch(command) {
                case "help":
                    helpFunction();
                    break;
                case "list":
                    taskArray.printTaskArrayList();
                    break;
                case "find":
                    taskArray.printFind(input.getExtracted());
                    break;
                case "delete":
                    int deleteIndex = input.processDeleteIndex();
                    if(deleteIndex >= 0) {
                        taskArray.removeTask(deleteIndex - 1);
                    }else{
                        printInvalidArgIndex();
                        continue;
                    }
                    break;

                case "mark":
                    int markIndex = input.processMarkIndex();
                    if(markIndex >= 0) {
                        taskArray.get(markIndex - 1).mark();
                    }else{
                        printInvalidArgIndex();
                        continue;
                    }
                    break;

                case "unmark":
                    int unmarkIndex = input.processUnmarkIndex();
                    if(unmarkIndex >= 0) {
                        taskArray.get(unmarkIndex - 1).unmark();
                    }else{
                        printInvalidArgIndex();
                        continue;
                    }
                    break;

                case "todo":
                    Task toDo = input.processToDo();
                    if(toDo == null) {
                        printInsufficientArgToDo();
                    }else{
                        taskArray.add(toDo);
                    }
                    break;

                case "deadline":
                    Task deadlineTask = input.processDeadline();
                    if(deadlineTask == null) {
                        printInsufficientArgDeadline();
                    }else{
                        taskArray.add(deadlineTask);
                    }
                    break;

                case "event":
                    Task eventTask = input.processEvent();
                    if(eventTask == null) {
                        printInsufficientArgEvent();
                    }else{
                        taskArray.add(eventTask);
                    }
                    break;


                default:
                    printInvalidArg();

            }
            input = new Parser(acceptInput(scanner));
            command = input.getCommand();
        }
        scanner.close();
        byeFunction();
        return taskArray;

    }

    /**
     * Greets the user with a welcome message.
     *
     * @param name The name of the bot.
     */
    public void greetFunction(String name){

        String greetings = Duke.HORIZONTAL_LINE +"\nHello! I'm " + name + "\n"
                + Duke.LOGO
                + "What can I do for you?\n" + Duke.HORIZONTAL_LINE;
        System.out.println(greetings);
    }

    /**
     * Displays the list of available commands to the user.
     */
    public void helpFunction(){
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("You can utilise our functions below : ");
        System.out.println("bye");
        System.out.println("find [task keywords]");
        System.out.println("list");
        System.out.println("todo [task]");
        System.out.println("deadline [task] /by [dd/MM/yyyy]");
        System.out.println("event [task] /from [dd/MM/yyyy] /to [dd/MM/yyyy]");
        System.out.println("Note that all input date format will only accepted in format : \n 02/12/2019 1800 dd/MM/yyyy HHmm");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Accepts user input from the scanner.
     *
     * @param scanner The Scanner object used for input.
     * @return The user's input as a String.
     */

    public String acceptInput(Scanner scanner){
        String input = scanner.nextLine();

        return input;
    }


    /**
     * Displays a goodbye message to the user.
     */
    public static void byeFunction(){

        String byeword = Duke.HORIZONTAL_LINE + "\nBye. Hope to see you again soon\n" + Duke.HORIZONTAL_LINE;
        System.out.println(byeword);
    }

    /**
     * Displays an error message for an invalid index.
     */
    public void printInvalidArgIndex(){
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("OOPS!!! Invalid Index!");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Displays an error message for insufficient arguments for an event task.
     */
    public void printInsufficientArgEvent(){
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("OOPS!!! The argument for the event is insufficient!");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Displays an error message for insufficient arguments for a deadline task.
     */
    public void printInsufficientArgDeadline() {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("OOPS!!! The argument for the deadline is insufficient!");
        System.out.println(Duke.HORIZONTAL_LINE);
    }
    /**
     * Displays an error message for an empty description in a to-do task.
     */


    public void printInsufficientArgToDo() {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("OOPS!!! The description of a deadline cannot be empty.");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Displays an error message for an invalid command.
     */
    public void printInvalidArg(){
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(Duke.HORIZONTAL_LINE);
    }






}
