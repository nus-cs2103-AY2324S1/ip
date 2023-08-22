public class Tasks {
    String[] taskarray;
    public Tasks(){
        this.taskarray = new String[100];
    }

    public void add(String usertask) {
        for(int i = 0; i < taskarray.length; i++) {
            if (taskarray[i] == null) {
                taskarray[i] = usertask;
                break;
            }
        }
        System.out.println("added: " + usertask);
    }

    public void list() {
        for(int i = 0; i < taskarray.length; i++) {
            if (taskarray[i] != null) {
                System.out.println(i + 1 + ". " +taskarray[i]);
            }
        }
    }

}
