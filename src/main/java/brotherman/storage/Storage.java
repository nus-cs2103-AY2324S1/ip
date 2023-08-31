package brotherman.storage;

import brotherman.tasks.Task;
import brotherman.tasks.TaskList;

import brotherman.ui.Ui;

import brotherman.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File folder;

    public Storage(String filePath) {
        this.folder = new File(filePath);
        if(!folder.exists()) folder.mkdirs();
    }

    public TaskList readFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("./data/brotherman.txt"))) {
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                String[] task = taskString.split("\\|");
                if (task[0].equals("T")) {

                    Task task1 = Parser.parseTodo(task[2].trim());

                    task1.markedChecker(task[1]);
                    taskList.add(task1);

                } else if (task[0].equals("D")) {
                    String[] parts = task[2].split("/by");
                    Task task1 = Parser.parseDeadline(parts[0].trim(), parts[1].trim());
                    task1.markedChecker(task[1]);
                    taskList.add(task1);
                } else if (task[0].equals("E")) {
                    String[] parts = task[2].trim().split("\\s*/from\\s*|\\s*/to\\s*");
                    Task task1 = Parser.parseEvent(parts[0], parts[1], parts[2]);
                    task1.markedChecker(task[1]);
                    taskList.add(task1);
                } else {
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Welcome to Brotherman!");
        }
        return new TaskList(taskList);
    }

    public void saveToFile(ArrayList<Task> list) {

        try (PrintWriter output = new PrintWriter("./data/brotherman.txt")){
            for (Task listItems : list) {
                output.println(listItems.storeText());
            }
        } catch(Exception e){
            e.getStackTrace();
        }

    }
}
