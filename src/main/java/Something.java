public class Something {
    static int instances = 0;
    Something(){
        instances += 1;
    }

    public static int getInstances() {
        return instances;
    }


}
