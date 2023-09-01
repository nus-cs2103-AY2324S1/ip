import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String path){
        this.file = new File(path);

        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static void saveToFile(ArrayList<Task> inputs) {
        try {
            File f = new File("./data/duke.txt");

            FileWriter fw = new FileWriter(f);
            for (Task t : inputs) {
                fw.write(t.store() + "\n");
            }
            System.out.println(inputs);
            fw.close();
        } catch (IOException e) {
            System.err.println("An error occurred while saving to the file: " + e.getMessage());
        }
    }

    public static TaskList readFromFile(){
        ArrayList<Task> taskList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("./data/duke.txt"))) {
            while (scanner.hasNext()) {
                String[] type = scanner.nextLine().substring(4).split(" ");
                String[] description = scanner.nextLine().substring(4).split("\\|");

                if (type[0].equals("todo")) {
                    Todo todo = new Todo(description[1]);
                    taskList.add(todo);
                } else if (type[0].equals("deadline")) {
                    Deadline deadline = new Deadline(description[1], description[2]);
                    taskList.add(deadline);
                } else if (type[0].equals("event")) {
                    String date[] = scanner.nextLine().substring(4).split("-");
                    Event event = new Event(description[1], description[2], date[1]);
                    taskList.add(event);
                } else {
                    System.out.println("Invalid Input");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File Not Found!");
        }

        return new TaskList(taskList);
    }
}
