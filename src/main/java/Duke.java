import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke{

    public static void saveTasksToFile(ArrayList<Task> tasks, String dir){
        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        try {
            File outputFile = new File(dir);
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
            fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter((fileWriter));
            for (Task t: tasks) {
                bufferedWriter.write(t.toSaveString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e){
            System.out.println("An error occured" + e.getMessage());
        }
    }
    public static ArrayList<Task> loadSaveFile(String dir){
        FileReader fileReader;
        BufferedReader bufferedReader;
        ArrayList<Task> input = new ArrayList<Task>();
        try {
            File outputFile = new File(dir);
            outputFile.getParentFile().mkdirs();
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            fileReader = new FileReader(dir);
            bufferedReader = new BufferedReader((fileReader));
            String nextLine;
            nextLine = bufferedReader.readLine();
            while (nextLine != null) {
                String[] splitted = nextLine.split(" \\| ");
                switch (splitted[0]) {
                case "T":
                    input.add(new ToDo(splitted[2], (splitted[1] == "1")));
                    break;
                case "E":
                    input.add(new Event(splitted[2], splitted[3], splitted[4], (splitted[1] == "1")));
                    break;
                case "D":
                    int key = Integer.valueOf(splitted[4]);
                    if (key == 2){
                        input.add(new Deadline(splitted[2], LocalDateTime.parse(splitted[3]), (splitted[1] == "1")));
                    } else if (key == 1) {
                        input.add(new Deadline(splitted[2], LocalDate.parse(splitted[3]), (splitted[1] == "1")));
                    } else {
                        System.out.println("Unrecognisable date/time deadline loaded");
                    }
                    break;
                }
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e){
            System.out.println("An error occured");
        }

        return input;
    }

    public static void main(String[] args) {

        String logo =
                "___________  __________  __________  ||   \n"
                +"|         | |         | |         |  ||      \n"
                +"-----------  ---------- ----------   ||  \n"
                + "    ||         ||          ||        ||       \n"
                + "    ||         ||          ||        ||           \n"
                + "    ||         ||          ||        ||           \n"
                + "    ||         ||          ||        ||       \n"
                + "    ||      __________  __________   ||      \n"
                + "    ||      |         | |         |  ______ \n"
                + "    ||      ----------  ----------   ______    \n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner myScanner = new Scanner(System.in);
        ArrayList<Task> myList = new ArrayList<Task>(); // Create an ArrayList object
        String saveFileDir = "./data/duke.txt";
        myList = loadSaveFile(saveFileDir);

        while(myScanner.hasNext()){
            String inValue = myScanner.next();
            Task item;
            char derived_prefix;
            switch(inValue) {
                case "bye":
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    return;

                case "list":
                    System.out.println("____________________________________________________________");
                    int i = 1;
                    System.out.println("Here are the tasks in your list:");

                    for (Task t: myList){
                        System.out.println(String.valueOf(i) + "." + t.toString());
                        i++;
                    }
                    System.out.println("____________________________________________________________");
                    break;

                case "mark":
                    int number = myScanner.nextInt();
                    item = myList.get(number-1);
                    item.set();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(item.toString());
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    break;

                case "unmark":
                    int numero = myScanner.nextInt();
                    item = myList.get(numero-1);
                    item.unset();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(item.toString());
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    break;

                case "delete":
                    int numbero = myScanner.nextInt();
                    item = myList.get(numbero - 1);
                    myList.remove(numbero-1);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(item.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    break;

                case "todo":
                    inValue = myScanner.nextLine();
                    if (inValue.length() != 0){
                        inValue = inValue.substring(1);
                    } else {
                        //throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("____________________________________________________________");
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                        System.out.println("____________________________________________________________");
                        break;
                    }
                    ToDo t =  new ToDo(inValue);
                    myList.add(t);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(t.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    break;
                case "deadline":
                    inValue = myScanner.nextLine();
                    if (inValue.length() != 0){
                        inValue = inValue.substring(1);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        System.out.println("____________________________________________________________");
                        break;
                        //throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");

                    }
                    String[] toBeSplit = inValue.split(" /by ");
                    Deadline d;
                    if (toBeSplit[1].contains (" ")){
                        //date + time is present
                        d = new Deadline(toBeSplit[0], LocalDateTime.parse(toBeSplit[1], DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")));
                    }
                    else{
                        d = new Deadline(toBeSplit[0], LocalDate.parse(toBeSplit[1], DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                    }
                    myList.add(d);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    break;
                case "event":
                    inValue = myScanner.nextLine();
                    if (inValue.length() != 0){
                        inValue = inValue.substring(1);
                    } else {
                        System.out.println("____________________________________________________________");
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        System.out.println("____________________________________________________________");
                        break;
                        //throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] to_Split = inValue.split(" /from ");
                    String[] second_Split = to_Split[1].split(" /to ");
                    Event e = new Event(to_Split[0], second_Split[0], second_Split[1]);
                    myList.add(e);
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e.toString());
                    System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(myList, saveFileDir);
                    break;

                default:
                    inValue += myScanner.nextLine();
                    System.out.println("____________________________________________________________");
                    System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println("____________________________________________________________");
                    break;
                    
            }
        }
    }
}

