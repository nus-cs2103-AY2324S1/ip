package duke;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Storage {
    protected String filepath;
    protected BufferedReader reader;
    protected BufferedWriter writer;
    protected BufferedWriter saveWriter;
    protected TaskList tasks;
    public Storage(String filepath, TaskList tasks) {
        this.filepath = filepath;
        this.tasks = tasks;
    }

    public void startStorage() {
        try {
            reader = new BufferedReader(new FileReader(filepath));
            String fileLine;

            while((fileLine = reader.readLine()) != null) {
                String eventType = Character.toString(fileLine.charAt(1));
                String isDone = Character.toString(fileLine.charAt(4));
                String extractedSubstring = fileLine.substring(7, fileLine.length());
                switch (eventType) {
                    case "T":
                        tasks.toDoHandler(extractedSubstring, isDone.equals("X"), true);
                        break;
                    case "D": {
                        //System.out.println(extractedSubstring);
                        String description = Parser.convertDeadlineFormat(extractedSubstring);
                        tasks.deadlineHandler(description, isDone.equals("X"), true);
                        break;
                    }
                    case "E": {
                        String description = Parser.convertEventFormat(extractedSubstring);
                        tasks.eventHandler(description, isDone.equals("X"), true);
                        break;
                    }
                }
            }
            reader.close();
        } catch (IOException e ) {
            e.printStackTrace();
            System.out.println("The file named duke.txt does not exist.");
        } catch (EmptyDescriptionException e) {
            //do something
        }
    }

    public void writeToStorage() {
        try {
            writer = new BufferedWriter(new FileWriter(filepath, true));
            saveWriter = new BufferedWriter(new FileWriter(filepath));

            for (Task task : this.tasks.getTaskList()) {
                saveWriter.write(task.toString() + "\n");
            }
            writer.close();
            saveWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
