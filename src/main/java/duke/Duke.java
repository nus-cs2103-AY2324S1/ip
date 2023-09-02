package duke;

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.time.*;
import java.time.format.*;

public class Duke {
    private static StringBuilder tempData = new StringBuilder();
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    private static List<Task> list = new ArrayList<Task>();

    private static void refreshData() {
        String zenithData = "src/main/java/data/zenith.txt";
        try {
            FileChannel fileChannel = FileChannel.open(Paths.get(zenithData),
                    StandardOpenOption.WRITE);
            fileChannel.truncate(0);
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task task : list) {
            String done = "";
            String by = "";
            String from = "";
            String to = "";
            String description = task.getDescription();
            String type = "";

            if (task.isDone == true) {
                done = " /1";
            }// Perform your task processing here
            else {
                done = " /0";
            }
            if (task instanceof Event) {
                from = "/from " + ((Event) task).getFrom();
                to = "/to " + ((Event) task).getBy();
                type = "event ";
            }

            if (task instanceof Deadline) {
                by = "/by " + ((Deadline) task).getBy();
                type = "deadline ";
            }
            if (task instanceof Todo) {
                type = "todo ";
            }
            String string = type + description + from + by + to + done;
            try {
                appendToFile(zenithData, string);
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        }
    }


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

    private static void loadFileStringContents(String filePath) throws FileNotFoundException{
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                StringToArray(line);
            }
            s.close();

            // Handle the exception here, such as creating the file if needed
    }

    private static void StringToArray(String str) { // might need to add corrupted file exception
        String arr[] = str.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        if (arr[0].equals("event")) {
            String firstFrom = arr[1].substring(arr[1].indexOf("/from") + 6); //
            String secondFrom = firstFrom.substring(0, firstFrom.indexOf("/to"));
            String firstTo = arr[1].substring(arr[1].indexOf("/to")+ 4);
            String secondTo = firstTo.substring(0, firstTo.indexOf("|"));
            String content = arr[1].substring(0, arr[1].indexOf("/from "));
            String done = firstTo.substring(firstTo.indexOf("|") + 1);
            String fromDate = secondFrom.substring(0, secondFrom.indexOf("/"));
            String firstFromMonth = secondFrom.substring(secondFrom.indexOf("/") + 1);
            String fromMonth = firstFromMonth.substring(0, firstFromMonth.indexOf("/"));
            String fromYear = firstFromMonth.substring(firstFromMonth.indexOf("/") + 1);

            String toDate = secondTo.substring(0, secondTo.indexOf("/"));
            String firstToMonth = secondTo.substring(secondTo.indexOf("/") + 1);
            String toMonth = firstToMonth.substring(0, firstToMonth.indexOf("/"));
            String toYear = firstToMonth.substring(firstToMonth.indexOf("/") + 1);
            toYear = toYear.substring(0, toYear.indexOf(" "));
            fromYear = fromYear.substring(0, fromYear.indexOf(" "));
            LocalDate to = LocalDate.of(Integer.parseInt(toYear), Integer.parseInt(toMonth), Integer.parseInt(toDate));
            LocalDate from = LocalDate.of(Integer.parseInt(fromYear), Integer.parseInt(fromMonth), Integer.parseInt(fromDate));
            String formattedTo = to.format(formatter);
            String formattedFrom = from.format(formatter);

            if (done == "1") {
                Event event = new Event(content, formattedFrom, formattedTo);
                event.finish();
                list.add(event);
            } else if (done == "0"){
                Event event = new Event(content, formattedFrom, formattedTo);
                list.add(event);
            }
        }
        else if (arr[0].equals("deadline")) {
            String firstBy = arr[1].substring(arr[1].indexOf("/by") + 4);
            String secondBy = firstBy.substring(0, firstBy.indexOf("|"));
            String done = firstBy.substring(firstBy.indexOf("|") + 1);
            String byDate = secondBy.substring(0, secondBy.indexOf("/"));
            String firstByMonth = secondBy.substring(secondBy.indexOf("/") + 1);
            String byMonth = firstByMonth.substring(0, firstByMonth.indexOf("/"));
            String byYear = firstByMonth.substring(firstByMonth.indexOf("/") + 1);
            byYear = byYear.substring(0, byYear.indexOf(" "));
            LocalDate by = LocalDate.of(Integer.parseInt(byYear), Integer.parseInt(byMonth), Integer.parseInt(byDate));
            String formattedTo = by.format(formatter);

            Deadline deadline = new Deadline(arr[1].substring(0, arr[1].indexOf("/by ")), formattedTo);


            if (done == "1") {
                deadline.finish();
                list.add(deadline);
            } else {
                list.add(deadline);
            }
        }
        else if (arr[0].equals("todo")){
            try {
                String firstTodo = arr[1];
                String secondToDo = firstTodo.substring(0, firstTodo.indexOf("|"));
                String done = firstTodo.substring(firstTodo.indexOf("|") + 1);
                Todo todo = new Todo(secondToDo);
                if (done == "1") {
                    todo.finish();
                    list.add(todo);
                } else {
                    list.add(todo);

                }

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("Please specify the content of the todo list");
            }
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("\n" + textToAppend);
        fw.close();
    }




    public void Answer() throws Exception{ // just a reader for additional files inputted by the users.
        // my plan is to make sure every line inputted by the user, it is saved to the zenith.txt file directly.
        // But to show the users the list, need to first load the data to a class storage containing the copied
        // data of zenith.txt
        String zenithData = "src/main/java/data/zenith.txt";
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
                refreshData();
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
                refreshData();
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
                refreshData();
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
            appendToFile(zenithData, str);
            refreshData();
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
            appendToFile(zenithData, str);
            refreshData();
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
                appendToFile(zenithData, str);
                refreshData();
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
        String zenithData = "src/main/java/data/zenith.txt";
        try {
            loadFileStringContents(zenithData);
            System.out.println("____________________________________________________________");
            System.out.println("Hello I'm Zenith" );
            System.out.println("What can I do for you?");
            System.out.println("____________________________________________________________");
            Duke d = new Duke();
            d.Answer();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
