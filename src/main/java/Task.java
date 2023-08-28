import java.util.HashMap;

public class Task {
    boolean completed;
    String name, ogname;

    HashMap<String, String> monthMap;

    public Task(String name) {
        monthMap = new HashMap<>();
        this.completed = false;
        this.name = name;
        monthMap.put("01","Jan");
        monthMap.put("02","Feb");
        monthMap.put("03","Mar");
        monthMap.put("04","Apr");
        monthMap.put("05","May");
        monthMap.put("06","Jun");
        monthMap.put("07","Jul");
        monthMap.put("08","Aug");
        monthMap.put("09","Sep");
        monthMap.put("10","Oct");
        monthMap.put("11","Nov");
        monthMap.put("12","Dec");
    }

    public boolean getComplete() {
        return this.completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markUncompleted() {
        this.completed = false;
    }

    public String convertToFormat(String str) {
        String[] ddmmyyyy = str.split("/");
        if (ddmmyyyy[1].length() < 2) ddmmyyyy[1] = "0" + ddmmyyyy[1];
        if (ddmmyyyy[0].length() < 2) ddmmyyyy[0] = "0" + ddmmyyyy[0];
        String yymmdd = ddmmyyyy[2] + "-" + ddmmyyyy[1] + "-" + ddmmyyyy[0];

        return yymmdd;
    }

    public String convertToWordsTime(String str) {
        String[] sSplit = str.split("-");
        // "2019-10-2"
        String month = monthMap.get(sSplit[1]);
        return month + " " + sSplit[2] + " " + sSplit[0];

    }

    @Override
    public String toString() {
        if (this.completed) {
            return "[X] " + this.name;
        } else {
            return "[ ]" + this.name;
        }

    }



}
