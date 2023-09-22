# User Guide
Yolo is a Personal Assistant Chatbot that helps keeping track of different things.

## Features
- To add a task, use `todo`.  
- To add a task with a deadline, use `deadline`.  
- To add a task with a certain period, use `event`.
- To view every task in the list, use `list`.  
- To mark a task as completed, use `mark`.  
- To mark a task as uncompleted, use `unmark`.
- To find a task by a keyword, use `find`.
- To sort the tasks in the task list, use `sort`.
- To delete a task from the list, use `delete`.  
- To exit the chatbot, use `bye`.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest Duke.jar from [here](https://github.com/tiongMax/ip/releases).

3. Copy the file to the folder you want to use as the home folder for the chatbot.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Duke.jar command to run the application.

Refer to the Features below for details of each command.

### Adding a task: `todo`
Create a Todo task and add it into a list of tasks.   
Format: `todo TASK`
* If nothing is behind `todo`, no task will be added and an error message will appear.

Examples:
* `todo study`

### Adds a task with a deadline: `deadline`
Create a Deadline task and add it into a list of tasks.    
Format: `deadline TASKNAME /by DD-MM-YYYY TTTT`
* The task will not be added into the list and an error message will appear if:
    - Nothing is entered after the command deadline.
    - Insufficient argument is entered.
    - The dates entered are not in the given format.

Examples:
* `deadline return books /by 23-9-2023 1800`

### Adds a task with a duration: `event`
Create a Event task and add it into a list of tasks.   
Format: `event TASKNAME /from DD/MM/YYYY TTTT /to DD/MM/YYYY TTTT`
* The task will not be added into the list and an error message will appear if:
    - Nothing is entered after the command event.
    - Insufficient argument is entered.
    - The dates entered are not in the given format.
    - The start date should not be later than or the same as the end date. 

Examples:
* `event party /from 1/12/2012 1800 /to 1/12/2012 2000`

### Views all tasks: `list`
Lists out all the tasks in the list.  
Format: `list`

### Marks a task as completed: `mark`
Marks a task as completed.   
Format:  `mark INTEGER`
* No task will be marked and an error message will appear if:
    - There is no number provided. 
    - The number provided is greater than the amount of tasks in the list. 

Examples:
* `mark 1`

### Marks a task as incomplete: `unmark`
Marks a task as incomplete.   
Format:  `unmark INTEGER`
* No task will be unmarked and an error message will appear if:
    - There is no number provided.
    - The number provided is greater than the amount of tasks in the list.

Examples:
* `unmark 1`

### Deletes a task: `delete`
Deletes a task from the task list.  
Format: `delete INTEGER`
* No task will be deleted and an error message will appear if:
    - There is no number provided.
    - The number provided is greater than the amount of tasks in the list.

Examples:
* `delete 0`

### Finds a task by a keyword: `find`
Finds for a task that includes or matches with the keyword entered.   
Format: `find KEYWORD`
* If there is nothing after the find command, no tasks will be returned and an error message will appear.

Examples:
* `find clean room`

### Sorts the tasks in: `sort`
Sorts all the tasks in the list by alphabetical or reverse alphabetical order.
Format: `sort a/ra`
* The task will not be sorted and an error message will appear if:
    - The deadline argument must follow the same format as the /by specifier when creating a Deadline task. 
    - The duration argument must follow the same format as the /from and /to specifiers when creating an Event task. 
    - The deadline/duration arguments must comply with the task at index INTEGER specified. 
    - If the argument is not a / ra or there is no argument after the command the task list will not be sorted and an error message will appear.

Examples:
* `sort a`
* `sort ra`