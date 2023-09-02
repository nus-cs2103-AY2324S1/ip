import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    File storedTasks;

    public Storage() {

    }

    void loadSavedTaskList(TaskList taskList) {
        File data = new File("./data");
        if (!data.exists()) {
            try {
                data.mkdirs();
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            }
        }

        File storedTasks = new File("./data/storedTasks.txt");
        if (!storedTasks.exists()) {
            try {
                storedTasks.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.storedTasks = storedTasks;
        StringBuilder savedTasks = new StringBuilder();
        int currChar;
        try {
            FileReader fr = new FileReader(storedTasks);
            while ((currChar = fr.read()) != -1) {
                savedTasks.append((char) currChar);
            }

            String[] allTaskInputs = savedTasks.toString(). split("\n");
            for (int i = 0; i < allTaskInputs.length; i++) {
                String[] taskInputs = allTaskInputs[i].split("\\|");
                if (taskInputs[0].equals("T")) {
                    taskList.addTask(TodoTask.makeTodo(taskInputs));
                } else if (taskInputs[0].equals("D")) {
                    taskList.addTask(DeadlineTask.makeDeadline(taskInputs));
                    // enum: done not done to prevent invalid input
                } else if (taskInputs[0].equals("E")) {
                    taskList.addTask(EventTask.makeEvent(taskInputs));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void saveNewTask(Task newTask) {
        try {
            FileWriter fw = new FileWriter(storedTasks, true);
            fw.write(newTask.toSavedString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void saveAllTasks(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(storedTasks);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toSavedString());
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
