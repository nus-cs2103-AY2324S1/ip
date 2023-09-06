package HelperClass;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String fileName;
    private String dirName;
    private String filePath;
    private Task[] userList;
    private int listPointer;


    //private methods below


    private void BackgroundSetUp() {

        File dir = new File(this.dirName);
        if (!(dir.exists())) {
            if (dir.mkdir()) {
                System.out.println("Directory '" + this.dirName + "' created.");
            } else {
                System.err.println("Failed to create directory '" + this.dirName + "'.");
                return;
            }
        }


        File file = new File(dir, this.fileName);

        if (!(file.exists())) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File '" + this.fileName + "' created in directory '" + this.dirName + "'.");
                } else {
                    System.err.println("Failed to create file '" + this.fileName + "' in directory '" + this.dirName + "'.");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }





    private List<String> ReadLine(String line) {
        List<String> formattedLine = new ArrayList<>();
        Scanner lineScanner = new Scanner(line);
        while (lineScanner.hasNext()) {

            String token = lineScanner.next();
            formattedLine.add(token);

        }
        lineScanner.close();

        return formattedLine;
    }



    private Task[] LoadList() {
        Task[] userList = new Task[100];
        int positionPointer = 0;


        Path path = Paths.get(this.filePath);
        try {
            Scanner fileScanner = new Scanner(path);
            while(fileScanner.hasNextLine()){

                // Record format: "Type | Status | Name | StartTime(optional) | EndTime(optional)"
                // example: "D | 0 | return book | 2023-09-04"
                // "0" for not done and "1" for done

                String line = fileScanner.nextLine();

                List<String> formattedLine = ReadLine(line);



                List<String> attributes = new ArrayList<>();
                StringBuilder attributeName = new StringBuilder();

                for (Object element : formattedLine) {
                    if (element.equals("|")) {
                        attributes.add(attributeName.toString());
                        attributeName = new StringBuilder();
                    } else {
                        if (attributeName.length() == 0) {
                            attributeName.append(element);
                        } else {
                            attributeName.append(" ").append(element);
                        }


                    }
                }

                attributes.add(attributeName.toString());
                boolean isDone = attributes.get(1).equals("1");

                switch (attributes.get(0)) {
                    case "T": {
                        Task task = new Task(attributes.get(2), 1, "Null", "Null", isDone);
                        userList[positionPointer] = task;

                        break;
                    }
                    case "D": {
                        Task task = new Task(attributes.get(2), 2, "Null", attributes.get(3), isDone);
                        userList[positionPointer] = task;

                        break;
                    }
                    case "E": {
                        Task task = new Task(attributes.get(2), 3, attributes.get(3), attributes.get(4), isDone);
                        userList[positionPointer] = task;

                        break;


                    }
                    default:
                        throw new IllegalStateException("Unexpected value: " + attributes.get(0));
                }

                positionPointer++;


            }
            fileScanner.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.listPointer = positionPointer;


        return userList;
    }





    //public methods below

    public Storage (String fileName, String dirName) {
        this.fileName = fileName;
        this.dirName = dirName;
        this.filePath = this.dirName + "/" + this.fileName;

        BackgroundSetUp();
        this.userList = LoadList();

    }

    public void SaveList(Task[] userList, int numberOfElements) {

        try (FileWriter writer = new FileWriter(filePath)) {
            for (int i = 0; i < numberOfElements; i++) {
                writer.write(userList[i].ForRecordingInTextFile());
                writer.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Task[] getUserList() {
        return this.userList;
    }

    public int getListPointer() {
        return listPointer;
    }








}
