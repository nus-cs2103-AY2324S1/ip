# TaskBuddy User Guide
TaskBuddy is a chatbot that helps to maange tasks which you can communicate with using a command line interface(CLI) like GUI
All you need is java 11 installed in your device and your good to go!

## Features 

### Listing all tasks: ```list```
Lists all tasks in your task list.  
Format: ```list```

### Adding a todo task: ```todo```
Adds a todo task to your task list.  
Format: ```todo TASK_NAME```  
Examples:
* ```todo sweeping floor```  
* ```todo shower```

### Adding an event task: ```event```
Adds a event to your task list.  
Format: ```event EVENT_NAME /from START_DATE /to END_DATE ```  
Examples:
* ```event project meeting /from Mon 2pm /to 4pm```  
* ```event project meeting /from 12/2/2023 1500 /to 14/3/2023```

### Adding a deadline task: ```deadline```
Adds a deadline task to your task list.  
Format: ```deadline TASK_NAME /by DEADLINE```  
Examples:
* ```deadline do homework /by no idea :-p``` 
* ```deadline wash dishes /by asap```

### Delete an existing task: ```delete```  
Deletes a task from your task list. Use ```list``` to obtain the index of the task  
Format: ```delete TASK_INDEX```  
Examples:
* ```delete 1```
* ```delete 2```

### Exiting the program: ```bye```
Exit the program. Tasks are automatically saved!
Format: ```bye```
Examples:
* ```bye```

## Usage

### `todo` - Creates a todo task
Adds a todo task to your task list.  
Example of usage:   
```todo sweep the floor```

Expected outcome:
```
Got it. I've added this task:
  [T][] sweep the floor
Now you have 3 tasks in the list.
```

  
### `event` - Creates an event task
Adds a event to your task list.  
Example of usage:  
```event project meeting /from 12/2/2023 1500 /to 14/3/2023```  
Expected outcome:
```
Got it. I've added this task.
  [E][] project meeting (from: 12/2/2023 1500 to: 14/3/2023)
Now you have 4 tasks in the list.
```

### `deadline` - Creates a deadline task
Adds a deadline task to your task list.  
Example of usage:
```deadline TASK_NAME /by DEADLINE```    
Expected outcome:
```
Got it. I've added this task:
  [D][] wash dishes (by: asap)
Now you have 5 tasks in the list.
```

### `list` - Lists all tasks in your list
Displays all your task in your list.  
Can be used to show the task index required for deleting or marking a task  

Expected outcome:  
List of all tasks you have at the moment  
```
Here are the tasks in your list:
1.[T][] Wipe the bookshelf
2.[T][X] Do homework
3.[E][] project meeting (from: 12/2/2023 1500 to: 14/3/2023)
4.[D][] wash dishes (by: asap)
```

### `delete` - Deletes an existing task in the list
Deletes a task from your task list. Use ```list``` to obtain the index of the task  
Example of usage:  
```delete TASK_INDEX```  
Expected outcome:
```
Noted. I've removed this task:
  [T][] sweep the floor
Now you have 4 tasks in the list.
```

### `mark` - Marks a task as complete in the list
Marks a task in your task list as complete. Use ```list``` to obtain the index of the task  
Example of usage:  
```mark 3```  
Expected outcome:  
The third task in the list is marked
```
nice! I've marked this task as done:
[E][X] project meeting (from: 12/2/2023 1500 to: 14/3/2023)
```

### `unmark` - Marks a task as incomplete in the list
Marks a task in your task list as incomplete.    
Use ```list``` to obtain the index of the task  
Example of usage:  
```unmark 3```  
Expected outcome:  
The third task in the list become unmarked
```
OK, I've marked this task as not done yet:
[E][] project meeting (from: 12/2/2023 1500 to: 14/3/2023)
```

### `bye` - Exits the program
Exit the program. Tasks are automatically saved!  
Example of usage:  
```bye```  
Expected outcome:  
Displays a goodbye message and closes the application after a delay
* ```Bye. Hope to see you again soon!```