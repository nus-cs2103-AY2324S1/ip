public class Tasks {
    SingleTask[] taskarray;
    public Tasks(){
        this.taskarray = new SingleTask[100];
    }

    public void add(String usertask) {
        for(int i = 0; i < taskarray.length; i++) {
            if (taskarray[i] == null) {
                taskarray[i] = new SingleTask(usertask);
                break;
            }
        }
        System.out.println("added for you boy : " + usertask);
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskarray.length; i++) {
            if (taskarray[i] != null) {
                System.out.println(i + 1 + ".[" + taskarray[i].getStatusIcon() +"]" + taskarray[i].description);
            }
        }
    }

    public void mark(int a) {
        taskarray[a].mark();
    }

    public void unmark(int a) {
        taskarray[a].unmark();
    }


}
