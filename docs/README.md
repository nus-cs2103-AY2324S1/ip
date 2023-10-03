# User Guide

CodeBuddy is a command-line task management application that allows users to manage tasks,
optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, CodeBuddy can get your task management done faster than traditional GUI apps.


## Features 

### Adding a Deadline: ```deadline```

Adds a deadline task.

Format: ```deadline [taskname] /by [date]```

Examples:
- ```deadline return book /by 2021-11-12 1800```
- ```deadline submit task /by 2011-09-12```

### Adding an Event: ```event```

Adds an event task.

Format: ```event [taskname] /from [date] /to [date]```

Examples:
- ```event project meeting /from 2019-12-11 1800 /to 2020-01-21 2130```

### Adding a Todo: ```todo```

Adds a todo task.

Format: ```todo [taskname]```

Examples:
- ```todo borrow book```


### Listing all tasks: ```list```

Shows a list of all tasks.

Format: ```list```

### Marking a task: ```mark```

Marks a task as done.

Format: ```mark [index]```

Examples:
- ```mark 2```

### Unmarking a task: ```unmark```

Marks a task as not done.

Format: ```unmark [index]```

Examples:
- ```unmark 2```

### Finding a task: ```find```

Search for tasks that match a given keyword.

Format: ```find [keyword]```

Examples:
- ```find book```

### Delete a task: ```delete```

Delete a task from the list.

Format: ```delete [index]```

Examples:
- ```delete 2```

### Update a task: ```update```

Updates a task's details.

Format: ```update [index] /[field] [values]```

Examples:
- ```update 2 /by 2021-11-18 1800 /description hello```
- ```update 3 /from 2021-11-18 1800 /to 2021-12-18```

### Exiting the program: ```bye```

Exits the program

Format: ```bye```

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


