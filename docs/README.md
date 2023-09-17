# Jarvis, Your Personal Task Manager!

![Jarvis](Ui.png)

# User Guide

## Features and Usage

> [!IMPORTANT]
> - Words in UPPER_CASE are the parameters to be supplied by the user. e.g. in ```todo TASKNAME```, ```TASKNAME``` is a parameter which can be used as ```todo Buy iPad```.
> - The parameters can contain whitespaces (e.g. ```Buy iPad```), and must be seperated from the command and the type specifiers (```/by```, ```/from```, ```/to```) by atleast 1 whitespace character from both sides. e.g. ```todoTASKNAME``` and ```deadline TASKNAME/byDATE``` are invalid, but ```deadline TASKNAME /by DATE``` is valid. 
> - Input date for tasks like ```event``` and ```deadline``` must follow the format ```DD/MM/YY HHMM``` with ```HHMM``` following the 24 hours format. e.g. ```23/09/23 1800```.
> - Parameters have to be in the specified order, otherwise it will result in an invalid message. e.g. if the command specifies ```TASKNAME /by DEADLINE```,
>   ```/by DEADLINE TASKNAME``` is invalid.
> - Extraneous parameters for commands that do not take in parameters (such as ```list```, ```bye``` and ```remind```) will result in an invalid message.
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding
>   line-breaks may be omitted when copied over to the application.

### Listing all the tasks ```list```
Shows a list of all the tasks (```event```, ```deadline```, and ```todo```) in the task list.

Format: ```list```

Example of usage: `list`

![list](https://github.com/shishirbychapur/ip/assets/95522842/a11c6b95-4008-4775-beea-7ce87e23452b)

### Adding a task with no time constraints: ```todo```
Adds a ```todo``` task into the list.

Format: ```todo TASKNAME```

Example of usage: `todo Play Basketball`

![todo](https://github.com/shishirbychapur/ip/assets/95522842/c63b201e-e1c7-4fd7-ae65-a220862066f0)

### Adding a task with a deadline: ```deadline```
Adds a ```deadline``` task into the list.

Format: ```deadline TASKNAME /by DEADLINE```

Example of usage: ```deadline Complete CS2100 Assignment /by 18/09/23 1315```

![deadline](https://github.com/shishirbychapur/ip/assets/95522842/054d6b7a-3563-4b51-b96f-aead44570f59)

### Adding a task with a starting and ending time: ```event```
Adds a ```event``` task into the list.

Format: ```event TASKNAME /from FROM /to TO```

Example of usage: ```event CS2103T Final Exam /from 01/12/23 0900 /to 01/12/23 1030```

![event](https://github.com/shishirbychapur/ip/assets/95522842/544bee3a-0169-475b-bfc3-e00c6f41391a)

### Removing a task: ```delete```
Removes the task matching the given index.

Format: ```delete INDEX```

Example of usage: ```delete 2```

![delete](https://github.com/shishirbychapur/ip/assets/95522842/f7507b9e-87ea-4347-a506-b5e01bdbfd7b)

### Completing a task: ```mark```
Marks the task matching the given index as complete.

Format: ```mark INDEX```

Example of usage: ```mark 1```

![mark](https://github.com/shishirbychapur/ip/assets/95522842/6a28eae4-ab66-405a-a834-9f34d0a059cb)

### Removing completion of a task: ```unmark```
Marks the task matching the given index as incomplete.

Format: ```unmark INDEX```

Example of usage: ```unmark 1```

[![unmark](https://github.com/shishirbychapur/ip/assets/95522842/ab678d38-38ea-4dff-9254-072b4ac8e785)

### Search for a task: ```find```
Finds a list of tasks containing the given keyword.

Format: ```find KEYWORD```

Example of usage: ```find Basketball```

![find](https://github.com/shishirbychapur/ip/assets/95522842/92f933be-d9f0-4071-98dd-55a93162deaf)

### Generating a reminder: ```remind```
Creates a reminder containing pending and passed ```event``` and ```deadline``` tasks.
It is also automatically called at the start of execution.

Format: ```remind```

Example of usage: ```remind```

### Exit the application: ```bye```
Exits from the application. An alternate way of exit is by pressing the close button (located at the top right corner of your screen).

Format: ```bye```

Example of usage: `bye`

![remind](https://github.com/shishirbychapur/ip/assets/95522842/df51bf50-e0ed-42df-8965-813fac366f4b)
