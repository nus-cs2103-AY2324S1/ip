package duke.storage;

import duke.task.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }


    public TaskArray load(){

        // Create the folder/file if it doesn't exist
        boolean createdFolder = createFolder(this.filePath);
        boolean createdFile =createFile(this.filePath);

        ArrayList<String> result = new ArrayList();
        ArrayList<String> tobeProcessedArray = scanFile(this.filePath);



        return parseData(tobeProcessedArray);

    }

    public boolean createFile(String filePath) {
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
    public boolean createFolder(String folderPath){
        File folder = new File(folderPath).getParentFile();

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

    public ArrayList<String> scanFile(String fileName){

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

    public TaskArray parseData(ArrayList<String> inputList){

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

    public ArrayList<String> formatData(TaskArray taskArray){
        ArrayList<Task> taskArrayList = taskArray.getTaskArrayList();

        ArrayList<String> output = new ArrayList<>();

        for (Task task : taskArrayList) {
            String input = task.getParsed();
            output.add(input);
        }
        return output;

    }
    public void inputFile(ArrayList<String> inputArray, String filePath){

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : inputArray) {
                writer.write(line);
                writer.newLine(); // Add a newline after each line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(TaskArray taskArray){
        inputFile(formatData(taskArray),this.filePath);
    }
}
