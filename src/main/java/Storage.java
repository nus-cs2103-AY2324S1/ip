import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws NoacException {

        ArrayList<Task> returnList = new ArrayList<>();

        try {
            File file = new File(this.filePath);
            file.getParentFile().mkdirs();
            if (!file.createNewFile()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNextLine()){
                    String[] fileLineInput = scanner.nextLine().split("\\|");

                    String taskType = fileLineInput[0];

                    switch (taskType){
                    case "T":
                        if (fileLineInput.length != 3) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        Todo todo = new Todo(fileLineInput[2]);
                        if(fileLineInput[1].equals("1")) {
                            todo.markAsDone();
                        }

                        returnList.add(todo);

                        break;

                    case "D":
                        if (fileLineInput.length != 4) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        Deadline deadline;
                        try {
                            deadline = new Deadline(fileLineInput[2], Parser.parseDate(fileLineInput[3]));
                        } catch (DateTimeParseException e) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        if(fileLineInput[1].equals("1")) {
                            deadline.markAsDone();
                        }

                        returnList.add(deadline);

                        break;

                    case "E":
                        if (fileLineInput.length != 5) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }


                        Event event;
                        try{
                            event = new Event(fileLineInput[2],Parser.parseDate(fileLineInput[3]) , Parser.parseDate(fileLineInput[4]) );

                        } catch (DateTimeParseException e) {
                            throw new NoacException("☹ OOPS!!! Corrupted Save file");
                        }

                        if(fileLineInput[1].equals("1")) {
                            event.markAsDone();
                        }

                        returnList.add(event);

                        break;

                    default:
                        throw new NoacException("☹ OOPS!!! Corrupted Save file");

                    }
                }
            }
        } catch (IOException e) {
            throw new NoacException("☹ OOPS!!! Corrupted Save file");
        }

        return returnList;
    }


    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for(int i = 0; i < taskList.size(); i++) {
                bufferedWriter.write(taskList.getTask(i).printToFile() + "\n");
            }

            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
