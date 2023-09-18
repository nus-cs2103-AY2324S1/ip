package duke.ui;

public class Exit {
    Thread thread;
    public Exit() {
        thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
    }
    public void start() {
        thread.start();
    }
}
