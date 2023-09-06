package qi.storage;

import qi.qiexception.QiException;
import qi.task.Deadline;
import qi.task.Event;
import qi.task.Todo;
import qi.tasklist.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
        File directory = file.getParentFile();

        // Create directory to the file if it does not exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Cannot create file!");
        }
    }

    public void load(TaskList list) throws QiException {
        try {
            Scanner sc = new Scanner(this.file);

            String task;

            while (sc.hasNext()) {
                task = sc.nextLine();
                if (task.charAt(1) == 'T') {
                    list.addTask(new Todo(task.substring(7)));
                    if (task.charAt(4) == 'X') {
                        list.mark(list.size(), true);
                    }
                } else if (task.charAt(1) == 'D') {
                    int idx = 0;
                    while (task.charAt(idx) != ':') {
                        idx++;
                    }
                    list.addTask(new Deadline(task.substring(7, idx - 4),
                            LocalDate.parse(task.substring(idx + 2, task.length() - 1),
                                    DateTimeFormatter.ofPattern("MMM dd yyyy"))));
                    if (task.charAt(4) == 'X') {
                        list.mark(list.size(), true);
                    }
                } else {
                    int idx1 = 0;
                    while (task.charAt(idx1) != ':') {
                        idx1++;
                    }

                    int idx2 = idx1 + 1;
                    while (task.charAt(idx2) != ':') {
                        idx2++;
                    }

                    list.addTask(new Event(task.substring(7, idx1 - 6),
                            task.substring(idx1 + 2, idx2 - 3),
                            task.substring(idx2 + 2, task.length() - 1)));
                    if (task.charAt(4) == 'X') {
                        list.mark(list.size(), true);
                    }
                }
            }
        } catch (IOException e) {
            throw new QiException("Cannot read file!");
        }
    }

    public void update(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(this.file.getAbsolutePath());
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            content.append(list.showTask(i + 1));
            if (i < list.size() - 1) {
                content.append('\n');
            }
        }
        fw.write(content.toString());
        fw.close();
    }
}
