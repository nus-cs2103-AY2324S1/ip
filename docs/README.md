# User Guide to the Richie Application

## What is Richie?

Richie is a chat bot that allows you to store a list of tasks that you have to do so as to help you 
remember your upcoming responsibilities. Tasks are categorised into Todo tasks, Deadline tasks, 
and Event tasks. Todo tasks are tasks that only have a description. (For example, do homework). 
Deadline tasks are tasks that have a deadline (For example, do dishes by 2 feb 2023 4pm). Lastly, 
Event tasks are events that occur within a period of time (For example, project meeting from 2 feb 2023 4pm 
to 2 feb 2023 5pm). Tasks can be marked as done once done. Obsolete tasks are tasks that are added by mistake can be deleted.
You can also find the task that you interested in by searching for its description. Have fun using the app! :grinning:

## Usage

### Listing all tasks : 'list'

> Shows a list of all tasks in the task list

Format : list


### Adding a Todo task : 'todo'

> Adds a Todo task to the task list

Format : todo TASK_DESCRIPTION

Examples:
- todo homework
- todo house chores


### Adding a Deadline task : 'deadline'

> Adds a Deadline task to the task list

Format : deadline TASK_DESCRIPTION /by TASK_DEADLINE (TASK_DEADLINE must in the format of : 'dd/mm/yyyy hhmmm')

Examples:
- deadline house chores /by 2/11/2023 1500
- deadline finish homework /by 12/3/2023 1700


### Adding an Event task : 'event'

> Adds a Event task to the task list

Format : event TASK_DESCRIPTION /from TASK_START_TIME /to TASK_END_TIME (TASK_START_TIME and TASK_END_TIME must in the format of : 'dd/mm/yyyy hhmmm')

Examples:
- event project meeting /form 2/11/2023 1500 /to 2/11/2023 1700
- event career fair /from 12/3/2023 1700 /to 12/3/2023 1900


### Marking a task as done : 'mark'

> Marks a task in the task list as done by refering to the number that corresponds to the task in the task list

Format : mark TASK_NUMBER (TASK_NUMBER must be a non negative integer and it cannot be more than the number of tasks that exist in the task list currently)

Examples:
- mark 2


### Deleting a task : 'delete'  

> Deletes a task from the task list by refering to the number that corresponds to the task in the task list

Format : delete TASK_NUMBER (TASK_NUMBER must be a non negative integer and it cannot be more than the number of tasks that exist in the task list currently)

Examples:
- delete 3


### Finding a task : 'find'

> Shows the tasks in the task list whose descriptions match the keyword that is entered as a parameter.

Format : find KEYWORD 

Examples:
-  find homework
-  find project


### Terminating the programme : 'bye'

> Exits the programme. Don't worry tasks in the existing task list are still stored so you can access them when you rerun the programme

Format : bye

Examples:
-bye

### Entering a command 

> To enter a command just type any of the supported commands in the text field below and either click enter on your keyboard or click on the 'send' button
