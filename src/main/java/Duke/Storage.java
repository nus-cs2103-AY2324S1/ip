package Duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to read and write to text file.
 */
public class Storage {
    private String file;

    /**
     * Constructor taking in file name.
     * @param file
     */
    public Storage(String file){
        this.file=file;
    }

    /**
     * Method to read from text file to load to ArrayList.
     * @return
     */
    public ArrayList<Task> load(){
        ArrayList<Task> list =new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] out = st.split(",");
                switch (out[0]) {
                case "T":
                    Todo e = new Todo(out[2]);
                    if (out[1].equals("1")) {
                        e.setDone();
                    }
                    list.add(e);
                    break;
                case "E":
                    Event f = new Event(out[2], out[3], out[4]);
                    if (out[1].equals("1")) {
                        f.setDone();
                    }
                    list.add(f);
                    break;
                case "D":
                    Deadline g = new Deadline(out[2], out[3]);
                    if (out[1].equals("1")) {
                        g.setDone();
                    }
                    list.add(g);
                    break;

                }
            }
        }
        catch (Exception e) {
            return new ArrayList<Task>();
        }
        return list;
    }


    /**
     * Adds text to file.
     * @param text
     */
    public void addToFile(String text){

        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f, true);
            fw.append(text + "\n");
            fw.close();
        }
        catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Updates a particular line in the text file to store if that entry
     * should be marked.
     * @param num line number
     * @param val whether the entry should be marked
     */
    public void updateFile(int num,boolean val){

        File file = new File(this.file);
        try {
            Scanner sc = new Scanner(file);
            String line = "";
            String out = "";
            int count = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                count += 1;
                if (count != num) {
                    out += line + "\n";
                } else {
                    if (val) {
                        out += line.substring(0, 2) + "1" + line.substring(3) + "\n";
                    } else {
                        out += line.substring(0, 2) + "0" + line.substring(3) + "\n";
                    }
                }
            }
            FileWriter fw = new FileWriter(file,false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**
     * Delete a particular line from the file.
     * @param num
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
                    out += line+"\n";
            }
            FileWriter fw = new FileWriter(file,false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();

        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }


    }
}
