import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    public final static String horiLine = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        greetFunction("Jack");
        String folderPath = "./data";  // Relative or absolute path to the folder
        String filePath = "./data/data.txt"; // Relative or absolute path to the file

        TaskArray taskArrayList = retrieveData(folderPath,filePath);
        System.out.println("Note that all input date format will only accepted in format : \n 02/12/2019 1800 dd/MM/yyyy HHmm");
        System.out.println(horiLine);

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] inputArray= input.split(" ");
        String firstInput = inputArray[0];

        while(!firstInput.equals("bye")) {

            if (firstInput.equals("list")) {
                taskArrayList.printTaskArrayList();

            } else if (firstInput.equals("delete")){
                if(inputArray.length != 2){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! Invalid Index!");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];
                    continue;
                }
                int index = Integer.parseInt(inputArray[1]);
                taskArrayList.removeTask(index - 1);

            } else if (firstInput.equals("mark")){
                int index = Integer.parseInt(inputArray[1]);
                taskArrayList.get(index - 1).mark();

            } else if (firstInput.equals("unmark")){
                int index = Integer.parseInt(inputArray[1]);
                taskArrayList.get(index - 1).unmark();

            }else if(firstInput.equals("todo")) {
                if(inputArray.length == 1){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];

                    continue;
                }

                String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));
                Task newTask = new ToDo(extractedTask);
                taskArrayList.add(newTask);


            }else if(firstInput.equals("deadline")) {

                if(inputArray.length == 1){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];
                    continue;
                }

                Task newTask = createDeadline(inputArray);
                taskArrayList.add(newTask);

            }else if(firstInput.equals("event")) {
                if(inputArray.length == 1){
                    System.out.println(horiLine);
                    System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                    System.out.println(horiLine);

                    input = scanner.nextLine();
                    inputArray= input.split(" ");
                    firstInput = inputArray[0];

                    continue;
                }

                Task newTask = createEvent(inputArray);
                taskArrayList.add(newTask);

            }else{
                System.out.println(horiLine);
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(horiLine);
            }

            input = scanner.nextLine();
            inputArray= input.split(" ");
            firstInput = inputArray[0];
        }

        ArrayList<String> formattedData = formatData(taskArrayList);
        inputFile(formattedData,filePath);
        byeFunction();
        scanner.close();


    }

    public static void greetFunction(String name){
        String greetings = horiLine +"\nHello! I'm " + name + "\n"
                + "What can I do for you?\n" + horiLine;
        System.out.println(greetings);
    }
    public static void byeFunction(){

        String byeword = horiLine + "\nBye. Hope to see you again soon\n" + horiLine;
        System.out.println(byeword);
    }
    public static void repeatFunction(String text){
        String byeword = horiLine +"\n" + text + "\n" + horiLine;
        System.out.println(byeword);
    }

    public static TaskArray retrieveData(String folderPath, String filePath){


        // Create the folder/file if it doesn't exist
        boolean createdFolder = createFolder(folderPath);
        boolean createdFile =createFile(filePath);

        ArrayList<String> result = new ArrayList();
        ArrayList<String> tobeProcessedArray = scanFile(filePath);



        return parseData(tobeProcessedArray);

    }

    public static boolean createFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("File created successfully.");

            return false;
        } else {
            System.out.println("File already exists.");
            return true;
        }
    }
    public static boolean createFolder(String folderPath){
        File folder = new File(folderPath);
        if (!folder.exists()) {
            boolean folderCreated = folder.mkdirs();
            if (folderCreated) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create folder.");
            }
            return false;

        } else {
            System.out.println("Folder already exists.");
            return true;

        }
    }

    public static ArrayList<String> scanFile(String fileName){

        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static TaskArray parseData(ArrayList<String> inputList){

        ArrayList<Task> arrayTask = new ArrayList<>();

        for (String input : inputList) {
            String[] parts = input.split(";");
            String type = parts[0];
            String text = parts[1];
            boolean checked = false;

            if(parts[2].equals("true")){
                checked = true;
            }

            Task newTask;

            switch(type) {
                case "T":
                    newTask = new ToDo(text,checked);
                    arrayTask.add(newTask);
                    break;

                case "E":
                    LocalDateTime startDate = LocalDateTime.parse(parts[3]);
                    LocalDateTime endDate = LocalDateTime.parse(parts[4]);
                    newTask = new Event(text,startDate,endDate);
                    arrayTask.add(newTask);
                    break;

                case "D":
                    LocalDateTime dueDate = LocalDateTime.parse(parts[3]);
                    newTask = new Deadline(text,dueDate,checked);
                    arrayTask.add(newTask);
                    break;
            }



        }
        return new TaskArray(arrayTask);
    }

    public static ArrayList<String> formatData(TaskArray taskArray){
        ArrayList<Task> taskArrayList = taskArray.getTaskArrayList();

        ArrayList<String> output = new ArrayList<>();

        for (Task task : taskArrayList) {
            String input = task.getParsed();
            output.add(input);
        }
        return output;

    }
    public static void inputFile(ArrayList<String> inputArray, String filePath){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : inputArray) {
                writer.write(line);
                writer.newLine(); // Add a newline after each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static LocalDateTime convertDateTime(String input){
        //Using format given by textbook dd-mm-yyyy HHmm example :"02/12/2019 1800"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(input, formatter);

        return dateTime;

    }

    public static Task createEvent(String[] inputArray){

        Integer startIndex = -1;
        Integer endIndex = -1;
        String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

        String endDate ="";
        String startDate = "";
        for(int i = 0; i < inputArray.length; i++){
            if(inputArray[i].equals("/from") && startIndex == -1){
                startIndex = i;
                extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, i ));
            }else if(inputArray[i].equals("/to") && startIndex != -1){
                endDate = String.join(" ", Arrays.copyOfRange(inputArray, i+1, inputArray.length));
                startDate = String.join(" ", Arrays.copyOfRange(inputArray, startIndex + 1, i));

            }
        }

        Task newTask = new Event(extractedTask,convertDateTime(startDate),convertDateTime(endDate));
        return newTask;
    }


    public static Task createDeadline(String[] inputArray){
        String dueDate = "";
        String extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, inputArray.length));

                    for(int i = 0; i < inputArray.length; i++){
            if(inputArray[i].equals("/by")){
                dueDate = String.join(" ", Arrays.copyOfRange(inputArray, i+1, inputArray.length));
                extractedTask = String.join(" ", Arrays.copyOfRange(inputArray, 1, i));

                break;
            }
        }

        Task newTask = new Deadline(extractedTask,convertDateTime(dueDate));
        return newTask;
    }


}
