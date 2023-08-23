public class Task {
        protected String description;
        protected boolean isDone;

        protected boolean isSplit;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
            this.isSplit = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] ");
        }

        public void markStatus() {
            this.isDone = true;
        }

        public void unmarkStatus() {
            this.isDone = false;
        }

        public void splitDescription() {
            if (!isSplit) {
                String[] splitDesc = description.split("\\s+", 2);
                this.description = splitDesc[1];
                isSplit = true;
            }
        }


        @Override
        public String toString() {
            splitDescription();
            return getStatusIcon() + this.description;
        }

}
