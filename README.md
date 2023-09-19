# Snow

Snow is your personal reminder bot. Snow helps you to add task to your task list, delete and update. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output

## Features 

### Listing all tasks : `list`
Shows a list of all tasks.

Format: `list`

### Adding a new task : `todo`, `deadline`, `event`
Adds a task to do into the task list.

1. Todo

   Format: `todo TASK`

   Examples:
   
   `todo homework`
   
   `todo cooking`

2. Deadline
   
   Format: `deadline TASK /by 2023-09-16`

   Examples:
   
   `deadline homework /by 2023-10-01`
   
   `deadline meeting /by 2023-09-26`

3. Event
   
   Format: 'event TASK /from TIME /to TIME'

   Examples:
   'event pilates /from 2pm /to 3pm'
   'event birthday party /from 7pm /to 8pm'

### Marking a task as done : `mark`
Marks a task with X at a specified index. 

Format: `mark INDEX`

### Unmarking a task : 'unmark'
Unmarks an existing task by removing the X at a specified index. 

Format: `unmark INDEX`

### Deleting a task : `delete`
Deletes an existing task at the specified index. 

Format: `delete INDEX`

### Updating an existing task : `update`
Updates an existing task at a specified index. 

Format: `update INDEX [task fields to update]` following the format for creating the task

For example, 
- to update a Todo task: `update 1 knitting`
- to update a Deadline task: `update 2 baking /by 2023-12-11`
- to update an Event task: `update 1 meeting /from 10am /to 12pm`

### Exiting the program : `bye`
Exits the chatbot.

Format: `bye`

### Saving the data
Snow chatbot data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
AddressBook data are saved as a JSON file [JAR file location]/data/addressbook.json. Advanced users are welcome to update data directly by editing that data file.


