public class TaskList {
    private String[] taskList = new String[100];
    private int index = 0;

    public void add(String description) {
        taskList[index] = description;
        index ++;
        System.out.println("added: " + description);
    }
    public void list() {
        String msg = "";
        int num = 1;
        for (String task : taskList) {
            if (task != null) {
                msg = msg + String.valueOf(num) + ": " + task + "\n";
                num ++;
            } else {
                break;
            }
        }
        System.out.println(msg);
    }
}
