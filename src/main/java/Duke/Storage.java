package Duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to read and write to a text file for task storage.
 */
public class Storage {
    private String file;

    /**
     * Constructor for the Storage class.
     *
     * @param file The name of the text file to read and write task data.
     */
    public Storage(String file) {
        this.file = file;
    }

    /**
     * Reads task data from the text file and loads it into an ArrayList of tasks.
     *
     * @return An ArrayList containing tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] out = st.split(",");
                assert out[0].equals("T") || out[0].equals("E") || out[0].equals("D");
                switch (out[0]) {
                case "T":
                    Todo e = new Todo(out[2]);
                    if (out[1].equals("1"))
                        e.setDone();
                    list.add(e);
                    break;
                case "E":
                    Event f = new Event(out[2], out[3], out[4]);
                    if (out[1].equals("1"))
                        f.setDone();
                    list.add(f);
                    break;
                case "D":
                    Deadline g = new Deadline(out[2], out[3]);
                    if (out[1].equals("1"))
                        g.setDone();
                    list.add(g);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + out[0]);
                }
            }
        } catch (Exception e) {
            return new ArrayList<Task>();
        }
        return list;
    }

    /**
     * Adds text to the end of the text file.
     *
     * @param text The text to be added to the file.
     */
    public void addToFile(String text) {
        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f, true);
            fw.append(text + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Updates a particular line in the text file to store whether that entry should be marked.
     *
     * @param num The line number to be updated.
     * @param val Whether the entry should be marked (true) or unmarked (false).
     */
    public void updateFile(int num, boolean val) {
        File file = new File(this.file);
        try {
            Scanner sc = new Scanner(file);
            String line = "";
            String out = "";
            int count = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                count += 1;
                if (count != num)
                    out += line + "\n";
                else {
                    if (val)
                        out += line.substring(0, 2) + "1" + line.substring(3) + "\n";
                    else
                        out += line.substring(0, 2) + "0" + line.substring(3) + "\n";
                }
            }
            FileWriter fw = new FileWriter(file, false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Deletes a particular line from the text file.
     *
     * @param num The line number to be deleted.
     */
    public void deleteFromFile(int num) {
        File f = new File(file);
        try {
            Scanner sc = new Scanner(f);
            String line = "";
            String out = "";
            int count = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                count += 1;
                if (count != num)
                    out += line + "\n";
            }
            FileWriter fw = new FileWriter(file, false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
