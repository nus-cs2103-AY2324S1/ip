package duke;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void testShowList() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            String allText = "     1.[T][ ] Apply for internships\n";
            allText += "     2.[D][X] Finish reading (by: Oct 15 2023 1800)\n";
            allText += "     3.[E][ ] Resume clinic (from: Sep 1 2023 0000 to: Sep 29 2023 2359)";

            TaskList tasks = new TaskList(file);
            assertEquals(allText, tasks.showList());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testFileList() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            String allText = "T | 0 | Apply for internships\r\n";
            allText += "D | 1 | Finish reading | Oct 15 2023 1800\r\n";
            allText += "E | 0 | Resume clinic | Sep 1 2023 0000 -> Sep 29 2023 2359";

            TaskList tasks = new TaskList(file);
            assertEquals(allText, tasks.fileList());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testAdd() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            String allText = "T | 0 | Apply for internships\r\n";
            allText += "D | 1 | Finish reading | Oct 15 2023 1800\r\n";
            allText += "E | 0 | Resume clinic | Sep 1 2023 0000 -> Sep 29 2023 2359\r\n";
            allText += "T | 0 | Go to bed";

            TaskList tasks = new TaskList(file);
            tasks.add(new ToDo("Go to bed"));
            assertEquals(allText, tasks.fileList());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testDelete() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            String allText = "D | 1 | Finish reading | Oct 15 2023 1800\r\n";
            allText += "E | 0 | Resume clinic | Sep 1 2023 0000 -> Sep 29 2023 2359";

            TaskList tasks = new TaskList(file);
            tasks.delete(0);
            assertEquals(allText, tasks.fileList());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testGet() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            Task task = new ToDo("Apply for internships");

            TaskList tasks = new TaskList(file);
            assertEquals(task.toString(), tasks.get(0).toString());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void testSize() {
        try {
            File file = new File("./testFile.txt");
            file.createNewFile();

            FileWriter fw = new FileWriter("./testFile.txt");
            fw.write("T | 0 | Apply for internships\r\n");
            fw.write("D | 1 | Finish reading | Oct 15 2023 1800\r\n");
            fw.write("E | 0 | Resume clinic | Sep 01 2023 0000 -> Sep 29 2023 2359");
            fw.close();

            TaskList tasks = new TaskList(file);
            assertEquals(3, tasks.size());
        } catch (IOException e) {
            fail();
        }
    }
}
