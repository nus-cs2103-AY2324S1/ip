# YONG User Guide
**A chatbot for you to manage your upcoming tasks.**

## Quick Start
1. Ensure you have _Java 11_ or above installed in your computer.
2. Download the latest `Yong-1.0.0.jar` file from the `build/libs` directory
3. Copy the file to the folder you want to use as the _home folder_ for your chatbot
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar Yong-1.0.0 .jar` command 
to run the application.
5. Type any of the commands listed below and press Enter to execute it. _e.g. typing `list` and pressing `enter` will
list all the tasks that has been keyed in.
6. Refer to the **Features** below for the details of each command.

## Features 
The list of features include:
1. Greeting
2. Listing all tasks
3. Finding specific tasks
4. Marking/Un-marking tasks as complete
5. Deleting tasks from the list
6. Adding tasks to the list
7. Sorting tasks in the list

### Greeting

Command: `Hi`

Yong will respond with a greeting message.

### Listing all tasks

Command: `List`

Yong will list all tasks, including the following details:
1. Type of task
2. Whether the task is completed
3. Description of the task
4. Additional information of the task

### Finding specific tasks

Command: `Find {Keyword}`

Yong will list all tasks that includes the specified keyword in the description

### Marking/Un-marking tasks as complete

Command: `Mark {Task No.}` / `Unmark {Task No.}`

Yong will mark/unmark the specified task

### Deleting tasks from the list

Command: `Delete {Task No.}`

Yong will delete the specified task

### Adding tasks to the list

Command:
1. Todo tasks: `Todo {Task Description}`
2. Deadline tasks: `Deadline {Task Description} /by {YYYY-MM-DD HHMM}`
3. Event tasks: `Event {Task Description} /from {YYYY-MM-DD HHMM} /to {YYYY-MM-DD HHMM}`

Yong will add the task to the end of the list of tasks.

`Todo` Tasks: Only contains the task description

`Deadline` Tasks: Contains the task description and a mandatory deadline

`Event` Tasks: Contains the task description and a mandatory from and to datetime.

### Sorting tasks in the list

Command: `Sort {Task Type} {Sort Method}`

Task type includes:
1. `Todo`
2. `Deadline`
3. `Event`
4. `All`

Sort method includes:
1. `Chronologically`
2. `Alphabetically`

Yong will sort the list of tasks according to the specified task type and method.
If users choose to only sort `Todo` tasks, the sorted `Todo` tasks will be added to the start of the list.



## FAQ

_Will the tasks be saved if I exit the chatbot?_

All entered task into the list will be saved when you exit the chatbot,
and will be loaded whenever you re-open the file.