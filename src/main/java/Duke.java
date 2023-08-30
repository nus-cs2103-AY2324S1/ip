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
            Ui.Error(e);
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
                        Ui.badDateLoaded();
                    }
                    break;
                }
                nextLine = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e){
            Ui.Error(e);
        }
        return input;
    }

    public static void main(String[] args) {

        Ui Ui = new Ui();
        Ui.hello();
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
                    Ui.goodbye();
                    saveTasksToFile(myList, saveFileDir);
                    return;

                case "list":
                    Ui.tasksInList(myList);
                    break;

                case "mark":
                    int number = myScanner.nextInt();
                    item = myList.get(number-1);
                    item.set();
                    Ui.taskDone(item);
                    saveTasksToFile(myList, saveFileDir);
                    break;

                case "unmark":
                    int numero = myScanner.nextInt();
                    item = myList.get(numero-1);
                    item.unset();
                    Ui.taskUndone(item);
                    saveTasksToFile(myList, saveFileDir);
                    break;

                case "delete":
                    int numbero = myScanner.nextInt();
                    item = myList.get(numbero - 1);
                    myList.remove(numbero-1);
                    Ui.taskDelete(item, myList);
                    saveTasksToFile(myList, saveFileDir);
                    break;

                case "todo":
                    inValue = myScanner.nextLine();
                    if (inValue.length() != 0){
                        inValue = inValue.substring(1);
                    } else {
                        //throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        Ui.showError("todo");
                        break;
                    }
                    ToDo t =  new ToDo(inValue);
                    myList.add(t);
                    Ui.taskAdd(t, myList);
                    saveTasksToFile(myList, saveFileDir);
                    break;
                case "deadline":
                    inValue = myScanner.nextLine();
                    if (inValue.length() != 0){
                        inValue = inValue.substring(1);
                    } else {
                        Ui.showError("deadline");
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
                    Ui.taskAdd(d, myList);
                    saveTasksToFile(myList, saveFileDir);
                    break;
                case "event":
                    inValue = myScanner.nextLine();
                    if (inValue.length() != 0){
                        inValue = inValue.substring(1);
                    } else {
                        Ui.showError("event");
                        break;
                        //throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] to_Split = inValue.split(" /from ");
                    String[] second_Split = to_Split[1].split(" /to ");
                    Event e = new Event(to_Split[0], second_Split[0], second_Split[1]);
                    myList.add(e);
                    Ui.taskAdd(e, myList);
                    saveTasksToFile(myList, saveFileDir);
                    break;

                default:
                    inValue += myScanner.nextLine();
                    Ui.unrecognisedCommand();
                    break;
                    
            }
        }
    }
}

