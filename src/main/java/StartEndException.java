public class StartEndException extends Exception{
        public StartEndException() {
            super("_________________________________________________\n"
                    + " OOPS!! Ensure that your event has a start and end!\n"
                    + "_________________________________________________\n");
        }
}
