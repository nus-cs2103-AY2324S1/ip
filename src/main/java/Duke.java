import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /***
     * Checks user inputs, if invalid throws DukeException
     * @param str User input split by each word
     * @param task type of task - todo,event,deadline,mark/unmark
     * @throws DukeException
     */
    public static void inputChecker(String[] str, String task) throws DukeException{

        if (str.length <2 ) {
            throw new DukeException("☹ OOPS!!! The description of a " +task+ " cannot be empty.");
        }
    }
    public static void main(String[] args) throws DukeException {


        System.out.println("Hello! I'm Mikhil" + '\n' + "What can I do for you");
        boolean flag = true;
        ArrayList<Task> tasks = TasksFile.readTasks();
        Scanner userInput = new Scanner(System.in);

        while (flag == true) {
            String input = userInput.nextLine();
            String[] splitStr = input.trim().split("\\s+");

            if (input.equals("bye")) {
                flag = false;
                System.out.println("Bye. Hope to see you again soon!");
            }

            //list out each task from tasks ArrayList
            else if (input.equals("list")) {
                System.out.println("  Here are the tasks in your list:");
                for (int i = 0; i < Task.getSize(); i++) {
                    int index = i+1;
                    System.out.println("  " + index + "." + tasks.get(i).toString()) ;

                }
            }
            //create Todo object
            else if (splitStr[0].equals("todo")){
                try{
                    inputChecker(splitStr, "todo");
                    Todo t = new Todo(input.substring(5));
                    tasks.add(t);
                    TasksFile.saveTasks(tasks);
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }

            }
            //create deadline object, splitting the due date by "/" and stripping off the by:
            else if (splitStr[0].equals("deadline")){
                try{
                    inputChecker(splitStr, "deadline");
                    String[] deadline = input.split("/");
                    inputChecker(deadline, "deadline");
                    Deadline d = new Deadline(deadline[0].substring(9), deadline[1].substring(3));
                    tasks.add(d);
                    TasksFile.saveTasks(tasks);
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }
            }

            //create event object, splitting the due date by "/" and stripping off the to: and from:
            else if (splitStr[0].equals("event")){
                try{
                    inputChecker(splitStr, "event");
                    String[] event = input.split("/");
                    inputChecker(event, "deadline");

                    Event e = new Event(event[0].substring(6),event[1].substring(5), event[2].substring(3));
                    tasks.add(e);
                    TasksFile.saveTasks(tasks);
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }
            }

            //mark or unmark an existing task
            else if(splitStr[0].equals("mark") || splitStr[0].equals("unmark")){
                try {
                    inputChecker(splitStr, "mark/unmark");
                    int index = Integer.parseInt(splitStr[1]);
                    Task item = tasks.get(index - 1);
                    item.setAction(splitStr[0]);
                    TasksFile.saveTasks(tasks);
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }

            }

            //delete task from tasks ArrayList
            else if(splitStr[0].equals("delete")){
                try {
                    inputChecker(splitStr, "delete");
                    int index = Integer.parseInt(splitStr[1]);
                    Task item = tasks.remove(index - 1);
                    item.delete();
                    TasksFile.saveTasks(tasks);
                }
                catch (DukeException e){
                    System.out.println(e.getMessage());
                }
            }

            //unknown command
            else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println("----------------------------------------");
        }
    }
}
