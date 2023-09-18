cd src/main/java/duchess/
javac command/*.java Deadline.java Duchess.java DuchessException.java Event.java Parser.java Storage.java Task.java TaskList.java TaskStatus.java ToDo.java Ui.java Utility.java
cd ../../../..
java -cp src/main/java/ duchess/Duchess
rm src/main/java/duchess/command/*.class
rm src/main/java/duchess/*.class
