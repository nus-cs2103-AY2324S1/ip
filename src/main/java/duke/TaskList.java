package duke;

import duke.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;

public class TaskList {
    String task;
    private static List<Task> list = new ArrayList<Task>();
    public static String trimString(String input) {
        if (input == null) {
            return null; // Handle null input if needed
        }
        return input.trim();
    }


    private static void StringToArray(String str) {
        // might need to add corrupted file exception
        String arr[] = str.split(" ", 2);
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
            LocalDate to = LocalDate.of(Integer.parseInt(trimString(toYear)), Integer.parseInt(toMonth), Integer.parseInt(toDate));
            LocalDate from = LocalDate.of(Integer.parseInt(trimString(fromYear)), Integer.parseInt(fromMonth), Integer.parseInt(fromDate));

            if (done.equals("1")) {
                Event event = new Event(content, from, to);
                event.finish();

                list.add(event);
            } else {
                Event event = new Event(content, from, to);
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


            Deadline deadline = new Deadline(arr[1].substring(0, arr[1].indexOf("/by ")), by);


            if (done.equals("1")) {
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
                if (done.equals("1")) {
                    todo.finish();
                    list.add(todo);
                } else {
                    list.add(todo);

                }

            } catch (ArrayIndexOutOfBoundsException e){
                Ui specify = new Ui();
                specify.specify();

            }
        }
    }

    public void Answer() throws Exception{ // just a reader for additional files inputted by the users.
        // my plan is to make sure every line inputted by the user, it is saved to the zenith.txt file directly.
        // But to show the users the list, need to first load the data to a class storage containing the copied
        // data of zenith.txt
        String zenithData = "src/main/java/data/zenith.txt";
        Parser parser = new Parser(list);
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String str = reader.readLine();
        //String arr[] = str.split(" ", 2);
        String arr[];
        String onetwo = list.size() > 1? " tasks": " task";
        Ui ui = new Ui();


        if (parser.getStr().equals("bye")) {
            Ui bye = new Ui();
            bye.bye();
        }
        else if (parser.getStr().equals("list")) {
            List();
            ui.currentlist((list.size()), onetwo);

            Answer();
        } else if (parser.getArr()[0].equals("delete")) {
            try {
                arr = parser.getArr();
                list.get(Integer.parseInt(arr[1]) - 1).getDescription();
                ui.remove(list.get(Integer.parseInt(arr[1]) - 1).toString());
                list.remove(Integer.parseInt(arr[1]) - 1);
                ui.currentlist((list.size()), onetwo);
                refreshData();
                Answer();
            }
            catch(NumberFormatException e) {
                ui.numExc();
                Answer();
            }
            catch(IndexOutOfBoundsException e) {
                ui.indexOut();
                Answer();
            }
        }
        else if (parser.getArr()[0].equals("mark")) {
            //arr[1].length() == 1 && isNumeric(arr[1])
            try {
                arr = parser.getArr();
                ui.mark(list.size(), onetwo, list.get(Integer.parseInt(arr[1]) - 1).getDescription());
                list.get(Integer.parseInt(arr[1]) - 1).getDescription();
                //System.out.println("Noted. I've marked this task: ");
                //System.out.println("    [X] " + list.get(Integer.parseInt(arr[1]) - 1).getDescription());
                list.get(Integer.parseInt(arr[1]) - 1).finish();
                //System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                refreshData();
                Answer();
            }
            catch(NumberFormatException e) {
                ui.numExc();
                Answer();
            }
            catch(IndexOutOfBoundsException e) {
                ui.indexOut();
                Answer();
            }

        } else if (parser.getArr()[0].equals("unmark")) {
            try {
                arr = parser.getArr();
                list.get(Integer.parseInt(arr[1]) - 1).getDescription();
                ui.unmark(list.size(), onetwo, list.get(Integer.parseInt(arr[1]) - 1).getDescription());
                //System.out.println("OK, I've marked this task as not done yet: ");
                //System.out.println("    [] " + list.get(Integer.parseInt(arr[1]) - 1).getDescription());
                list.get(Integer.parseInt(arr[1]) - 1).unfinish();
                //System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                Answer();
                refreshData();
            }
            catch(NumberFormatException e) {
                ui.numExc();
                Answer();
            }
            catch(IndexOutOfBoundsException e) {
                ui.indexOut();
                Answer();
            }
        }

        else if (parser.getArr()[0].equals("find")) {
            String subString = parser.getArr()[1];
            List<Task> matchingList = new ArrayList<>();
            for (Task task: list) {
                if (task.getDescription().contains(subString)) {
                    matchingList.add(task);
                }
            }
            ui.matchingList(matchingList);
            Answer();

        }
        else if (parser.getArr()[0].equals("event")) {
            arr = parser.getArr();
            String firstFrom = arr[1].substring(arr[1].indexOf("/from") + 6);
            String secondFrom = firstFrom.substring(0, firstFrom.indexOf("/to"));
            String to = arr[1].substring(arr[1].indexOf("/to")+ 4);
            //System.out.println("____________________________________________________________");
            //System.out.println("Got it. I've added this task:");
            String content = arr[1].substring(0, arr[1].indexOf("/from "));
            String toDate = to.substring(0, to.indexOf("/"));
            String firstToMonth = to.substring(to.indexOf("/") + 1);
            String toMonth = firstToMonth.substring(0, firstToMonth.indexOf("/"));
            String toYear = firstToMonth.substring(firstToMonth.indexOf("/") + 1);
            LocalDate secondTo = LocalDate.of(Integer.parseInt(toYear), Integer.parseInt(toMonth), Integer.parseInt(toDate));
            String fromDate = secondFrom.substring(0, secondFrom.indexOf("/"));
            String firstFromMonth = secondFrom.substring(secondFrom.indexOf("/") + 1);
            String fromMonth = firstFromMonth.substring(0, firstFromMonth.indexOf("/"));
            String fromYear = firstFromMonth.substring(firstFromMonth.indexOf("/") + 1);
            fromYear = fromYear.substring(0, fromYear.indexOf(" "));
            LocalDate from = LocalDate.of(Integer.parseInt(fromYear), Integer.parseInt(fromMonth), Integer.parseInt(fromDate));
            Event event = new Event(content, from, secondTo);
            //System.out.println(event);
            ui.add(event, list.size(), onetwo);
            list.add(event);
            //System.out.println("Now you have " + list.size() + onetwo +  " in the list");
            //System.out.println("____________________________________________________________");
            appendToFile(zenithData, parser.getStr());
            refreshData();
            Answer();

        } else if (parser.getArr()[0].equals("deadline")) {
            arr = parser.getArr();
            String by = arr[1].substring(arr[1].indexOf("/by") + 4);
            //System.out.println("____________________________________________________________");
            //System.out.println("Got it. I've added this task:");
            String byDate = by.substring(0, by.indexOf("/"));
            String firstByMonth = by.substring(by.indexOf("/") + 1);
            String byMonth = firstByMonth.substring(0, firstByMonth.indexOf("/"));
            String byYear = firstByMonth.substring(firstByMonth.indexOf("/") + 1);


            LocalDate secondBy = LocalDate.of(Integer.parseInt(byYear), Integer.parseInt(byMonth), Integer.parseInt(byDate));

            Deadline deadline = new Deadline(arr[1].substring(0, arr[1].indexOf("/by ")), secondBy);
            //System.out.println(deadline);
            ui.add(deadline, list.size(), onetwo);
            list.add(deadline);
            //System.out.println("Now you have " + list.size() + onetwo +  " in the list");
            //System.out.println("____________________________________________________________");
            //appendToFile(zenithData, str);
            refreshData();
            Answer();
        }
        else if (parser.getArr()[0].equals("todo")){
            try {
                arr = parser.getArr();
                Todo todo = new Todo(arr[1]);
                //System.out.println("____________________________________________________________");
                //System.out.println("Got it. I've added this task:");//  no following words after
                //System.out.println(todo);
                list.add(todo);
                ui.add(todo, list.size(), onetwo);
                //System.out.println("Now you have " + list.size() + onetwo +  " in the list");
                //System.out.println("____________________________________________________________");
                appendToFile(zenithData, parser.getStr());
                refreshData();
                Answer();
            } catch (ArrayIndexOutOfBoundsException e){
                ui.specify();
                Answer();
            }
        }
        else if(parser.getArr()[0].isEmpty()) {
            try { // if empty string
                arr = parser.getArr();
                System.out.println(arr[1]);
            } catch(ArrayIndexOutOfBoundsException e) {
                ui.blank();
                //System.out.println("Don't just input blank space");
                Answer();
            }
        } else { // not the correct format
            try {
                throw new DukeException("");
            }
            catch (DukeException e) {
                ui.format();
                //System.out.println("Please input the correct format");
                Answer();
            }
        }


    }
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("\n" + textToAppend);
        fw.close();
    }
    public void List() {
        Ui ui = new Ui();
        ui.list(list);
    }

    public static String convertDateFormat(String inputDateStr) {
        // Split the input date string by the '-' delimiter
        String[] parts = inputDateStr.split("-");

        // Ensure that there are three parts (year, month, day)
        if (parts.length != 3) {
            return "Invalid date format";
        }

        // Reorder the parts to form the desired output format
        String day = parts[2];
        String month = parts[1];
        String year = parts[0];

        // Concatenate the parts with '/' separator
        String outputDateStr = day + "/" + month + "/" + year;

        return outputDateStr;
    }
    private static void refreshData() {
        String zenithData = "src/main/java/data/zenith.txt";
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
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
                done = " |1";
            }// Perform your task processing here
            else {
                done = " |0";
            }
            if (task instanceof Event) {
                from = "/from " + convertDateFormat(((Event) task).getFrom().toString());
                to = "/to " + convertDateFormat(((Event) task).getBy().toString());
                type = "event ";
                String string = type + description + from + to  + done;
                try {
                    appendToFile(zenithData, string);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (task instanceof Deadline) {

                by = "/by " + convertDateFormat(((Deadline) task).getBy().toString());
                type = "deadline ";
                String string = type + description + by + done;
                try {
                    appendToFile(zenithData, string);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (task instanceof Todo) {
                type = "todo ";
                String string = type + description + done;
                try {
                    appendToFile(zenithData, string);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public TaskList(String task) {
        this.task = task;

    }

    public TaskList() {

    }

    public String getTask() {
        return this.task;
    }

    public void load() {
        StringToArray(getTask());
    }
}
