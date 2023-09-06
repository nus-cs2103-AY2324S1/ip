import java.io.File;
<<<<<<< HEAD
=======
import java.io.FileNotFoundException;
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String path;

    public Storage(String txt) {
        path = txt;
    }

    public void saveTask(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (Task t : tasks) {
<<<<<<< HEAD
            writer.write(t.toSave() + "\n");
=======
            writer.write(t.toString() + "\n");
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
        }
        writer.close();
    }

    public ArrayList<Task> loadTask() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(path);

        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
<<<<<<< HEAD
=======
                    // System.out.println(scanner.nextLine());
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
                    tasks.add(Task.savedParse(scanner.nextLine()));
                }
                scanner.close();
            } catch (IOException e) {
                // e.printStackTrace();
                System.out.println("no file");
            }
        }
        return tasks;
    }
}
<<<<<<< HEAD

=======
>>>>>>> 667cac354c5e48fb2e525cddd1a0324068a81297
