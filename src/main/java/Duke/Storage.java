package Duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String file;

    public Storage(String file){
        this.file=file;
    }

    public ArrayList<Task> load(){
        ArrayList<Task> list =new ArrayList<Task>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null){
                String[] out=st.split(",");
                switch (out[0]){
                    case "T":
                        Todo e =new Todo(out[2]);
                        if(out[1].equals("1"))
                            e.setDone();
                        list.add(e);
                        break;
                    case "E":
                        Event f =new Event(out[2],out[3],out[4]);
                        if(out[1].equals("1"))
                            f.setDone();
                        list.add(f);
                        break;
                    case "D":
                        Deadline g =new Deadline(out[2],out[3]);
                        if(out[1].equals("1"))
                            g.setDone();
                        list.add(g);
                        break;

                }
            }
        }
        catch (Exception e){
            return new ArrayList<Task>();
        }
        return list;
    }
    public void addToFile(String text){
        try {
            File f = new File(file);
            FileWriter fw = new FileWriter(f, true);
            fw.append(text);
            fw.close();
        }
        catch (Exception e){
            System.out.println("Error:" + e.getMessage());
        }
    }
    public void updateFile(int num,boolean val){
        File file = new File(this.file);
        try {
            Scanner sc = new Scanner(file);
            String line="";
            String out="";
            int count=0;
            while(sc.hasNextLine()){
                line=sc.nextLine();
                count+=1;
                if(count!=num)
                    out+=line+"\n";
                else{
                    if(val)
                        out+=line.substring(0,2)+"1"+line.substring(3)+"\n";
                    else
                        out+=line.substring(0,2)+"0"+line.substring(3)+"\n";
                }
            }
            FileWriter fw=new FileWriter(file,false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();

        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }
    public void deleteFromFile(int num) {
        File f = new File(file);
        try {
            Scanner sc = new Scanner(f);
            String line="";
            String out="";
            int count=0;
            while(sc.hasNextLine()){
                line=sc.nextLine();
                count+=1;
                if(count!=num)
                    out+=line+"\n";
            }
            FileWriter fw=new FileWriter(file,false);
            fw.append(out);
            fw.flush();
            fw.close();
            sc.close();

        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }


    }
}
