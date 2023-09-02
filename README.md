# CS2103T Individual Project (iP)

This is a project log for my individual project
   ```
   Welcome to
    ___ _  _ ___
   | _ \ || |_ _|
   |  _/ __ || | 
   |_| |_||_|___|
   PHI (Programmable Human Interface)
   ```
---
## Main Increments

### Level 0. Rename, Greet, Exit
- Chatbot was created and given the name Phi
- Greets the user and exits the program

### Level 1. Echo
- PHI now echoes commands entered by the user
- Exits when the user types the command _bye_

### Level 2. Add, List
- Text entered by the user will be saved as a task
- Tasks are displayed in a list upon command _list_

### Level 3. Mark as Done
- Tasks can now be marked as done (and undone)

### Level 4. ToDos, Events, Deadlines
- Tasks are now classified under one of three types:
  - ToDos: Tasks without any date/time
  - Deadlines: Tasks to be done before a specified deadline
  - Events: Tasks that start and end at a specified time
  
### Level 5. Handle Errors
- PHI can deal with basic-level errors (incorrect inputs) entered by the user

### Level 6. Delete
- Tasks can now be deleted from the list

### Level 7. Save
- Tasks are saved in storage whenever any changes are made to the task list
- Data from the tasklist is loaded from the storage when PHI starts (_./data/tasklist.txt_)

### Level 8. Dates and Times
- Deadline/Event dates can now be understood in yyyy-MM-dd format, and are displayed back as MMM dd yyyy
  - Non-recognised formats will continue to be saved as a String

## Other Increments

### Automated Text UI Testing
- Testing of PHI is semi-automated through I/O redirection (text-ui-test)

### Enumerations
- Enumerations used for the Tasks data-type

### More OOP
- Extraction of code as classes for more Object-Oriented-Programming (OOP)
  - Creating/Refining the following classes (Ui, Storage, Parser, TaskList)

### Packages
- Classes in PHI are organised into suitable java packages
  - Currently, all classes are in one package (_phi_)
  
### Gradle
- Support for Gradle added to PHI, allowing automation of some build/run tasks

### JUnit Tests
- JUnit tests created to test code behaviour

### JAR File
- **phi.jar v1.0.0** released
  - PHI packaged into an executable JAR file (built using Gradle) for easier distribution
  - 
### JavaDoc
- JavaDoc header comments added to all classes and all non-trivial methods















