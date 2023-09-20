# Duke

Duke is a friendly chatbot application for task management. You can interact with Duke through the use of a Graphical User Interface (GUI), and keep track of tasks such as your to-dos, deadlines, and events.

* Setting up in Intellij
* Features
  * Add a to-do: ```todo```
  * Add a deadline: ```deadline```
  * Add an event: ```event```
  * List all tasks: ```list```
  * Mark a task as done: ```mark```
  * Unmark a task: ```unmark```
  * Find a task by keyword: ```find```
  * Delete a specific task: ```delete```
  * Exiting the program: ```exit``` or ```bye```

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

## Features
### Notes about command format
1. Items in ```UPPER_CASE``` are parameters to be supplied by the user.
   e.g. in ```todo TASK_DESCRIPTION```, ```TASK_DESCRIPTION``` is a parameter which can be used as todo homework.
2. Items in square brackets are optional.
   e.g. ```deadline TASK_DESCRIPTION /by DATE [TIME]``` can be used as ```deadline IS1108 Assignment /by 7/11/2023``` or ```deadline IS1108 Assignment /by 7/11/2023 1500```
3. All ```DATE``` parameters can be put in multiple formats.
   e.g. ```deadline TASK_DESCRIPTION /by DATE [TIME]``` can be used as ```deadline IS1108 Assignment /by 2023-09-08```, ```deadline IS1108 Assignment /by 8/9/2023``` or  ```deadline IS1108 Assignment /by Mon```
4. All weekday abbreviations in ```DATE``` parameters refers to the coming weekday
   e.g. ```deadline IS1108 Assignment /by Mon``` refers to next Monday.
5. All ```[TIME]``` parameters can be put as either in 12-Hour clock format or 24-Hour clock format.
   e.g. ```deadline TASK_DESCRIPTION /by DATE [TIME]``` can be used as ```deadline IS1108 Assignment /by Mon 11.59pm```,      
   ```deadline IS1108 Assignment /by Mon 2359``` or ```deadline IS1108 Assignment /by Mon 23:59```
6. Extraneous parameters for commands that do not take in parameters (such as ```list``` and ```clear```) will be ignored.
   e.g. if the command specifies ```list 123```, it will be interpreted as ```list```.
   
### Add a to-do: ```todo```
Adds a to-do task to your task list.
Format: ```todo TASK_DESCRIPTION```
Examples:
```todo update README```
```todo finish CS2100 Assignment```

### Add a deadline: ```deadline```
Adds a deadline to your task list.
Format: ```deadline TASK_DESCRIPTION /from DATE [TIME]```
Examples:
```deadline update README /from Mon 4pm```
```deadline return book /by 2/12/2023 1800```

### Add an event: ```event```
Adds an event to your task list.
Format: ```event TASK_DESCRIPTION /from DATE [TIME] /to DATE [TIME]```
Examples:
```event Sleepover /from Thurs /to Fri```
```event Orientation Camp /from 8/10/2023 /to 11/10/2023```
```event ST2334 Finals /from 27/11/2023 1600 /to 27/11/2023 1800```

### List all tasks: ```list```
List all tasks that have been added.
Format: ```list```

### Mark a task as done: ```mark```
Marks a task as done.
Format: ```mark INDEX```
* Marks a task as done at the specified ```INDEX```
* The index refers to index number shown in the displayed task list.
Examples:
```mark 3```

### Unmark a task: ```unmark```
Unmarks a task that has been previously marked as done.
Format: ```mark INDEX```
* unmarks a task at the specified ```INDEX```
* The index refers to index number shown in the displayed task list.
Examples:
```unmark 3```

### Find a task by keyword: ```find```
Finds a task which contains the given keyword.
Format: ```find KEYWORD```
* Only the task description is searched
* Only full words will be matched e.g. meeting will not match meetings

Examples:
```find meeting```
![<img width="383" alt="find command example" src="https://github.com/Angelyxx/ip/assets/73735276/6b280df6-140d-4b64-aba5-1e33dac4b4be">]

### Delete a specific task: ```delete```
Deletes an existing task.
Format: ```delete INDEX```
* Deletes a task at the specified ```INDEX```
* The index refers to index number shown in the displayed task list.
Examples:
```delete 3```

### Exiting the program: ```exit``` or ```bye```
Exits the duke chatbot
Format: ```exit``` or ```bye```
