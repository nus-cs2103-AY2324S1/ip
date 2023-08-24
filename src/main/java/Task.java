/*
     * Abstract class representing a Task.
     */
   public abstract class Task {
        private String label;
        private boolean isDone;

        /*
         * Constructor that takes in a label
         * @param String label label for the task.
         */
        Task(String label) {
            this.label = label;
            this.isDone = false;
        }

        /*
         * Marks task as isDone
         * 
         * @return void
         */
        public void done() {
            isDone = true;
        }

        /*
         * Marks task as undone
         * 
         * @return void
         */
        public void undone() {
            isDone = false;
        }

        /*
         * String representation of task. Shows whether it is isDone or not.
         * 
         * @return String String representation of task.
         */
        @Override
        public String toString() {
            if (isDone) {
                return "[X] " + label;
            }
            return "[ ] " + label;
        }
    }
