# Bocchi 🎸
### Quick Start
1. Ensure you have Java `11` or above installed.
2. Download the latest `bocchi.jar` from [here](https://github.com/junhonglow/ip/releases/tag/A-Release).
3. Copy the file to a folder you want to use as the home folder for your application.
4. Ensure that you create a subdirectory within the folder labeled as `data`. This will be used to save your data.
5. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar bocchi.jar` command to run the application.
A GUI similar to the below should appear in a few seconds.
![image](https://github.com/junhonglow/ip/assets/95668366/4dd1cf43-fcaf-4a9b-ac69-6eed04b26bfb)

## Features 
- Adding a task - todo / deadline / event
- Deleting a task - delete
- Viewing tasks - list
- Marking a task - mark
- Unmarking a task - unmark
- Exit the program - bye
  
### Adding a task
1. Add a task with no stipulated duration.

    > Format: todo {task name}
    
    `todo Sleep`

2. Add a task with a deadline.

    > Format: deadline {task name} /by {deadline}
    
    `deadline English homework /by 2023-11-22 23:59:59`

3. Add a task with a duration.

    > Format: event {task name} /from {start} /to {end}

    `event Badminton /from 2023-11-23 10:00:00 /to 2023-11-23 12:00:00`

### Deleting a task
Delete a task at a specified index.

> Format: delete {index}`

`delete 1`

### Viewing your task list
View your current tasks.

> Format: list

`list`

### Marking a task
Mark a task at a specified index as done.

> Format: mark {index}

`mark 1`

### Unmarking a task
Mark a task at a specified index as not done.

> Format: unmark {index}

`unmark 1`

### Exit the program

> Format: bye

`bye`
