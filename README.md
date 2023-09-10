# DukePro
## Usage
> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))
   
DukePro is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, DukePro can get your task management done faster than traditional GUI apps. 
It's
- **text-based**
- **easy to learn**
- ~~FAST~~ SUPER FAST to use

## Quick start

### Running the JAR file

1. Download the JAR file from [here]
2. Open the terminal and navigate to the directory where the JAR file is located
3. Run the JAR file using the command `java -jar ip.jar`

### Start using DukePro
+ Add a task using the `todo`, `deadline` or `event` command 
+ List all tasks using the `list` command 
+ Mark a task as done using the `done` command 
+ Delete a task using the `delete` command 
+ Find a task using the `find` command 
+ Exit the program using the `bye` command

### Features
- [x] Managing Tasks
- [x] Managing Deadlines
- [x] Managing Events
- [ ] Interactive GUI

### Special Features
+ What's more! Your tasks are **auto-saved** after every command.
No more worries about forgetting to save your tasks or if your 
computer crashes 😭😭😭.
__________________________________________________________________________________________

If you are a Java programmer, you can use it to practice Java too! Here's the `main` method:
```java
public class Duke {
    public static void main(String[] args) {
        new Duke("./data", "./data/store.txt").run();
    }
}
```