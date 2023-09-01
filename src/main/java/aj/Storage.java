package aj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Storage {
    Parser parser;
    final String FILEPATH;

    public List<Task> initialiseData() { // get data from file, create necessary task objects and returns an array of task
//        String localDir = System.getProperty("user.dir");
//        File file = new File(localDir + "/src/main/data/actualData.txt");
        File file = new File(this.FILEPATH);

        List<Task> taskList = new ArrayList<>();

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return null;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parsedValues = line.split(",");
                Scanner strScanner = new Scanner(parsedValues[0]); // original command
                String command = strScanner.next().toLowerCase();
                String remaining = strScanner.nextLine();
                boolean isMark = Boolean.parseBoolean(parsedValues[1]);
                Task task;
                if (command.equals("todo")) {
//                    task = new aj.Todo(remaining.substring(1), isMark);
                    task = this.parser.getTodoTask(remaining, isMark);
                } else if (command.equals("deadline")) {
                    task = this.parser.getDeadlineTask(remaining, isMark);
                } else if (command.equals("event")) {
                    task = this.parser.getEventTask(remaining, isMark);
                } else {
                    task = null;
                }
                taskList.add(task);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    public void updateData(int idx, boolean isMarked) throws IOException { // linenumber refers to index from 0

//        String localDir = System.getProperty("user.dir");
//        Path myPath = Paths.get(localDir + "/src/main/data/actualData.txt");
        Path myPath = Paths.get(this.FILEPATH);

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == idx) {
                String[] parsedValues = fileContent.get(i).split(",");
                String newLineContent = parsedValues[0] + "," + Boolean.toString(isMarked);
                fileContent.set(i, newLineContent);
                break;
            }
        }
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    public void deleteData(int idx) throws IOException { // get linenumber and delete that entry
//        String localDir = System.getProperty("user.dir");
//        Path myPath = Paths.get(localDir + "/src/main/data/actualData.txt");
        Path myPath = Paths.get(this.FILEPATH);

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));

        for (int i = 0; i < fileContent.size(); i++) {
            if (i == idx) {
                fileContent.remove(i);
                break;
            }
        }
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    public void addData(String str) throws IOException { // get linenumber and delete that entry
//        String localDir = System.getProperty("user.dir");
//        Path myPath = Paths.get(localDir + "/src/main/data/actualData.txt");
        Path myPath = Paths.get(this.FILEPATH);

        List<String> fileContent = new ArrayList<>(Files.readAllLines(myPath, StandardCharsets.UTF_8));
        fileContent.add(str);
        Files.write(myPath, fileContent, StandardCharsets.UTF_8);
    }

    Storage(Parser parser, String filePath) {
        this.parser = parser;
        this.FILEPATH = filePath;
    }
}
