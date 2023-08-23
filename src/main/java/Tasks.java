public class Tasks {

    String str;
    boolean status;
    static int count = 0;
    public Tasks(String str) {
        this.str = str;
        this.status = false;
    }
    public void markDone() {
        this.status = true;
    }
    public void markNotDone() {
        this.status = false;
    }

    @Override
    public String toString() {
        String x;
        if (this.status) {
            x = "X";
        } else {
            x = " ";
        }
        String str1 = String.format(" [%s] ", x);
        return str1 + this.str;
    }


}
