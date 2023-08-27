import java.sql.SQLOutput;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Duke {
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    List<Task> list = new ArrayList<Task>();
    public void List() {
        System.out.println("Here are the tasks in your list:");
        int index = 1;
        System.out.println("____________________________________________________________");
        for (Task thing: list) {

            System.out.println(index +". " + thing.toString());

            index++;
        }
        System.out.println("____________________________________________________________");
    }
    public void Answer() throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        String arr[] = str.split(" ", 2);
        String onetwo = list.size() > 1? " task": " tasks";


        if (str.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");


        }
        else if (str.equals("list")) {
            List();
            System.out.println("Now you have " + list.size() + onetwo +  " in the list");
            Answer();

        } else if (arr[0].equals("delete")) {
            try {
                list.get(Integer.parseInt(arr[1]) - 1).getDescription();
                System.out.println("Noted. I've removed this task:");
                System.out.println(list.get(Integer.parseInt(arr[1]) - 1).toString());
                list.remove(Integer.parseInt(arr[1]) - 1);
                System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                Answer();
            }
            catch(NumberFormatException e) {
                System.out.println("to pick which task to do, please input an integer");
                Answer();
            }
            catch(IndexOutOfBoundsException e) {
                System.out.println("currently, you task list does not the task with the index you just inputted");
                Answer();
            }
        }
        else if (arr[0].equals("mark")) {
            //arr[1].length() == 1 && isNumeric(arr[1])
            try {
                list.get(Integer.parseInt(arr[1]) - 1).getDescription();
                System.out.println("Noted. I've removed this task: ");
                System.out.println("    [X] " + list.get(Integer.parseInt(arr[1]) - 1).getDescription());
                list.get(Integer.parseInt(arr[1]) - 1).finish();
                System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                Answer();
            }
            catch(NumberFormatException e) {
                System.out.println("to pick which task to do, please input an integer");
                Answer();
            }
            catch(IndexOutOfBoundsException e) {
                System.out.println("currently, you task list does not the task with the index you just inputted");
                Answer();
            }

        } else if (arr[0].equals("unmark")) {
            try {
                list.get(Integer.parseInt(arr[1]) - 1).getDescription();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println("    [] " + list.get(Integer.parseInt(arr[1]) - 1).getDescription());
                list.get(Integer.parseInt(arr[1]) - 1).unfinish();
                System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                Answer();
            }
            catch(NumberFormatException e) {
                System.out.println("to pick which task to undo, please input an integer");
                Answer();
            }
            catch(IndexOutOfBoundsException e) {
                System.out.println("currently, you task list does not the task with the index you just inputted");
                Answer();
            }

        } else if (arr[0].equals("event")) {
            String firstFrom = arr[1].substring(arr[1].indexOf("/from") + 6);
            System.out.println(firstFrom);
            String secondFrom = firstFrom.substring(0, firstFrom.indexOf("/to"));
            String to = arr[1].substring(arr[1].indexOf("/to")+ 4);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            String content = arr[1].substring(0, arr[1].indexOf("/from "));
            Event event = new Event(content, secondFrom, to);
            System.out.println(event);
            list.add(event);
            System.out.println("Now you have " + list.size() + onetwo +  " in the list");
            System.out.println("____________________________________________________________");
            Answer();

        } else if (arr[0].equals("deadline")) {
            String by = arr[1].substring(arr[1].indexOf("/by") + 4);
            System.out.println("____________________________________________________________");
            System.out.println("Got it. I've added this task:");
            Deadline deadline = new Deadline(arr[1].substring(0, arr[1].indexOf("/by ")), by);
            System.out.println(deadline);
            list.add(deadline);
            System.out.println("Now you have " + list.size() + onetwo +  " in the list");
            System.out.println("____________________________________________________________");
            Answer();
        }
        else if (arr[0].equals("todo")){
            try {
                Todo todo = new Todo(arr[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");//  no following words after
                System.out.println(todo);
                list.add(todo);
                System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                System.out.println("____________________________________________________________");
                Answer();
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please specify the content of the todo list");
                Answer();

            }
        }
        else if(arr[0].isEmpty()) {
            try { // if empty string
                System.out.println(arr[1]);
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Don't just input blank space");
                Answer();
            }
        } else { // not the correct format
            try {
                throw new DukeException("");
            }
            catch (DukeException e) {
                System.out.println("Please input the correct format");
                Answer();
            }
        }


    }
    public static void main(String[] args) throws Exception {
        String logo = "Zenith";
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Duke d = new Duke();
        d.Answer();

    }
}
