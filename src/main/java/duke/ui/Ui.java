package duke.ui;

import duke.Duke;
import duke.task.*;
import duke.parser.*;

import java.util.Scanner;

public class Ui {
    String name;

    public Ui(){

    }
    public void greetFunction(String name){

        String greetings = Duke.horiLine +"\nHello! I'm " + name + "\n"
                + Duke.logo
                + "What can I do for you?\n" + Duke.horiLine;
        System.out.println(greetings);
    }

    public void helpFunction(){
        System.out.println(Duke.horiLine);
        System.out.println("You can utilise our functions below : ");
        System.out.println("bye");
        System.out.println("list");
        System.out.println("todo [task]");
        System.out.println("deadline [task] /by [dd/MM/yyyy]");
        System.out.println("event [task] /from [dd/MM/yyyy] /to [dd/MM/yyyy]");
        System.out.println("Note that all input date format will only accepted in format : \n 02/12/2019 1800 dd/MM/yyyy HHmm");
        System.out.println(Duke.horiLine);
    }

    public String acceptInput(Scanner scanner){
        String input = scanner.nextLine();

        return input;
    }

    public TaskArray runTask(TaskArray oriTaskArray){
        TaskArray taskArray = oriTaskArray;
        //Requesting Input and Redirect to Parser
        Scanner scanner = new Scanner(System.in);
        Parser input = new Parser(acceptInput(scanner));
        //Repeating Asking User if Not Equals Bye
        String command = input.getCommand();
        while(!command.equals("bye")){
            switch(command){
                case "help":
                    helpFunction();
                    break;
                case "list":
                    taskArray.printTaskArrayList();
                    break;
                case "delete":
                    int deleteIndex = input.processMarkIndex();
                    if(deleteIndex >= 0) {
                        taskArray.removeTask(deleteIndex - 1);
                    }else{
                        continue;
                    }
                    break;

                case "mark":
                    int markIndex = input.processMarkIndex();
                    if(markIndex >= 0) {
                        taskArray.get(markIndex - 1).mark();
                    }else{
                        continue;
                    }
                    break;

                case "unmark":
                    int unmarkIndex = input.processUnmarkIndex();
                    if(unmarkIndex >= 0) {
                        taskArray.get(unmarkIndex - 1).unmark();
                    }else{
                        continue;
                    }
                    break;

                case "todo":
                    Task toDo = input.processToDo();
                    if(toDo == null){
                        printInsufficientArgToDo();
                    }else{
                        taskArray.add(toDo);
                    }
                    break;

                case "deadline":
                    Task deadlineTask = input.processDeadline();
                    if(deadlineTask == null){
                        printInsufficientArgDeadline();
                    }else{
                        taskArray.add(deadlineTask);
                    }
                    break;

                case "event":
                    Task eventTask = input.processEvent();
                    if(eventTask == null){
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
    public static void byeFunction(){

        String byeword = Duke.horiLine + "\nBye. Hope to see you again soon\n" + Duke.horiLine;
        System.out.println(byeword);
    }

    public void printInsufficientArgEvent(){
        System.out.println(Duke.horiLine);
        System.out.println("☹ OOPS!!! The argument for the event is insufficient!");
        System.out.println(Duke.horiLine);
    }
    public void printInsufficientArgDeadline(){
        System.out.println(Duke.horiLine);
        System.out.println("☹ OOPS!!! The argument for the deadline is insufficient!");
        System.out.println(Duke.horiLine);
    }

    public void printInsufficientArgToDo(){
        System.out.println(Duke.horiLine);
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        System.out.println(Duke.horiLine);
    }

    public void printInvalidArg(){
        System.out.println(Duke.horiLine);
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(Duke.horiLine);
    }






}
