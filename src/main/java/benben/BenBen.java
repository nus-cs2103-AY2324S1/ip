package benben;
import java.util.Scanner;

public class BenBen {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public BenBen(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BenBenException e) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void todo(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        for (int i =  1; i < strSplit.length; i++) {
            des = des + " " + strSplit[i];
        }
        des = des.trim();

        if (des.length() == 0) {
            throw new BenBenException("Please follow the format: todo todo details");
        }

        Task t = new Todo(des);
        tasks.add(t);
        storage.write(tasks);
        ui.showAdd(t.toString(), tasks.size());
    }

    public void deadline(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        String ddl = "";
        boolean isDes = true;
        for (int i =  1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/by")) {
                isDes = false;
                continue;
            }

            if (isDes) {
                des = des + " " + strSplit[i];
            } else {
                ddl = ddl + " " + strSplit[i];
            }
        }
        des = des.trim();
        ddl = ddl.trim();

        if (des.length() == 0 || ddl.length() == 0 ) {
            throw new BenBenException("Please follow the format: deadline deadline details /by yyyy/mm/dd");
        }

        Task t = new Deadline(des, ddl);
        //arr[counter] = t;
        tasks.add(t);
        storage.write(tasks);
        ui.showAdd(t.toString(), tasks.size());
    }

    public void event (String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        String des = "";
        String start = "";
        String end = "";
        boolean isDes = true;
        boolean isStart = false;
        for (int i =  1; i < strSplit.length; i++) {
            if (strSplit[i].equals("/from")) {
                isDes = false;
                isStart = true;
                continue;
            }
            if (strSplit[i].equals("/to")) {
                isStart = false;
                continue;
            }


            if (isDes && !isStart) {
                des = des + " " + strSplit[i];
            } else if (!isDes && isStart) {
                start = start + " " + strSplit[i];
            } else {
                end = end + " " + strSplit[i];
            }
        }
        des = des.trim();
        start = start.trim();
        end = end.trim();

        if (des.length() == 0 || start.length() == 0 || end.length() == 0 ) {
            throw new BenBenException("Please follow the format: event event details /from yyyy-MM-dd HH-mm /to yyyy-MM-dd HH-mm");
        }

        Task t = new Event(des, start, end);
        //arr[counter] = t;
        tasks.add(t);
        ui.showAdd(t.toString(), tasks.size());
        storage.write(tasks);
    }

    public void iterList() {
        ui.showList(tasks);
    }

    public void mark(String str) throws BenBenException{
        String[] strSplit = str.split("\\s+");
        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to mark!");
        }

        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to mark!");
        }
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            //arr[x - 1].mark();
            tasks.get(x - 1).mark();
            ui.showMark(tasks.get(x - 1).toString());
            storage.write(tasks);
        } catch(NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch(NullPointerException e) {
            throw new BenBenException("The task you are trying to mark does not exist!");
        }
    }

    public void unmark(String str) throws BenBenException{

        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to unmark!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to unmark!");
        }
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            //[x - 1].unmark();
            tasks.get(x - 1).unmark();
            ui.showUnmark(tasks.get(x - 1).toString());
            storage.write(tasks);
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException e) {
            throw new BenBenException("The task you are trying to unmark does not exist!");
        }
    }

    public void remove(String str) throws BenBenException {
        String[] strSplit = str.split("\\s+");

        if (strSplit.length < 2) {
            throw new BenBenException("Please enter a task to remove!");
        }
        if (strSplit.length > 2) {
            throw new BenBenException("Please only enter one task to remove!");
        }
        Integer x = null;

        try {
            x = Integer.parseInt(strSplit[1]);
            //[x - 1].unmark();
            Task temp = tasks.get(x - 1);
            tasks.remove(x - 1);
            //counter = counter - 1;
            ui.showRemove(temp.toString(), tasks.size());
            storage.write(tasks);
        } catch (NumberFormatException e) {
            throw new BenBenException("Please use an integer value to indicate your task!");
        } catch (NullPointerException e) {
            throw new BenBenException("The task you are trying to remove does not exist!");
        } catch (IndexOutOfBoundsException e) {
            throw new BenBenException("The task you are trying to remove does not exist!");
        }
    }

    public void exit() {
        ui.showExit();
        System.exit(0);
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            try {
                String next = sc.nextLine();
                Parser.parse(this, next);
            } catch (BenBenException e) {
                ui.show(e.toString());
            }
        }
    }

    public static void main(String[] args) throws BenBenException {
        new BenBen("./src/main/java/tasks.txt").run();
    }
}

