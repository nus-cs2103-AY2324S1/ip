# Jarvis User Guide

> "Simplicity is the ultimate sophistication." - Leonardo da Vinci [(source)](https://www.theorderexpert.com/11-inspirational-quotes-about-organization/)

Experience organization and productivity like never before with **Jarvis** – your ultimate task manager. Simplify your life, one task at a time.

## Installation

- Download the latest  ```.jar``` file from [here.](https://github.com/shishirbychapur/ip/releases)
- Navigate to the directory of installation, and open Terminal.
- Run ```java -jar jarvis.jar``` and enjoy the luxury of having your own manager!

## Features

1. Allows various types of tasks (Events, Deadlines, Todo)
2. Stores and retrieves data directly to/from a designated .txt file
3. Enables marking/unmarking of tasks on completion
4. Grants users the ability to search for their respective task
5. Reminders users on overdue and upcoming events and deadlines.

## Special Attributes
- [x]  **Easy** to learn
- [x]  **Available** _24/7_ at your service
- [x]  Created by ~~Shishir~~ **Tony Stark** himself
- [x]  **Free** of charge

## Usage

> **❗Important:**
> - Words in UPPER_CASE are the parameters to be supplied by the user. e.g. in ```todo TASKNAME```, ```TASKNAME``` is a parameter which can be used as ```todo Buy iPad```.
> - The parameters can contain whitespaces (e.g. ```Buy iPad```), and must be seperated from the command and the type specifiers (```/by```, ```/from```, ```/to```) by atleast 1 whitespace character from both sides. e.g. ```todoTASKNAME``` and ```deadline TASKNAME/byDATE``` are invalid, but ```deadline TASKNAME /by DATE``` is valid. 
> - Input date for tasks like ```event``` and ```deadline``` must follow the format ```DD/MM/YY HHMM``` with ```HHMM``` following the 24 hours format. e.g. ```23/09/23 1800```.
> - Parameters have to be in the specified order, otherwise it will result in an invalid message. e.g. if the command specifies ```TASKNAME /by DEADLINE```,
>   ```/by DEADLINE TASKNAME``` is invalid.
> - Extraneous parameters for commands that do not take in parameters (such as ```list```, ```bye``` and ```remind```) will result in an invalid message.
> - If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding
>   line-breaks may be omitted when copied over to the application.

### Listing all tasks: ```list```
Shows a list of all the tasks (```event```, ```deadline```, and ```todo```) in the task list.

Format: ```list```

Example of usage: `list`

Expected output:
```
   Tasks displayed. Your guidance is requested.
   1. 📝 ☐ Play Basketball
```                 

### Adding a task with no time constraints: ```todo```
Adds a ```todo``` task into the list.

Format: ```todo TASKNAME```

Example of usage: `todo Play Basketball`

Expected output:
```
   Added the following task to the list.
   1. 📝 ☐ Play Basketball
   You currently have 1 tasks in your list.
```   

### Adding a task with a deadline: ```deadline```
Adds a ```deadline``` task into the list.

Format: ```deadline TASKNAME /by DEADLINE```

Example of usage: ```deadline Complete CS2100 Assignment /by 18/09/23 1315```

Expected output:
```
   Added the following task to the list.
   2. ⏰ ☐ Complete CS2100 Assignment (by: Sep 18 2023 01:15 pm)
   You currently have 2 tasks in your list.
```   

### Adding a task with a starting and ending time: ```event```
Adds a ```event``` task into the list.

Format: ```event TASKNAME /from FROM /to TO```

Example of usage: ```event CS2103T Final Exam /from 01/12/23 0900 /to 01/12/23 1030```

Expected output:
```
   Added the following task to the list.
   3. 🗓️ ☐ CS2103T Final Exam (from: Dec 1 2023 09:00 am to: Dec 1 2023 10:30 am)
   You currently have 3 tasks in your list.
```   

### Removing a task: ```delete```
Removes the task matching the given index.

Format: ```delete INDEX```

Example of usage: ```delete 3```

Expected output:
```
   The following task has been removed.
   3. 🗓️ ☐ CS2103T Final Exam (from: Dec 1 2023 09:00 am to: Dec 1 2023 10:30 am)
   Is there anything else I can assist you with?
```   

### Completing a task: ```mark```
Marks the task matching the given index as complete.

Format: ```mark INDEX```

Example of usage: ```mark 1```

Expected output:
```
   The following task is marked as complete:
   1. 🗓️ ☑ Play Basketball 
   Is there anything else I can assist you with?
```  

### Removing completion of a task: ```unmark```
Marks the task matching the given index as incomplete.

Format: ```unmark INDEX```

Example of usage: ```unmark 1```

Expected output:
```
   The following task has been umarked:
   1. 🗓️ ☐ Play Basketball 
   Is there anything else I can assist you with?
``` 

### Search for a task: ```find```
Finds a list of tasks containing the given keyword.

Format: ```find KEYWORD```

Example of usage: ```find Basketball```

Expected output:
```
   The following tasks match the entered keyword:
   1. 🗓️ ☐ Play Basketball 
```

### Generating a reminder: ```remind```
Creates a reminder containing pending and passed ```event``` and ```deadline``` tasks.
It is also automatically called at the start of execution.

Format: ```remind```

Example of usage: ```remind```

Expected output:
```
   Just a friendly reminder, the following tasks are pending completion!
   1. ⏰ ☐ Complete CS2100 Assignment (by: Sept 18 2023 01:15 pm)
```

### Exit the application: ```bye```
Exits from the application. An alternate way of exit is by pressing the close button (located at the top right corner of your screen).

Format: ```bye```

Example of usage: `bye`

Expected output:
```
    I shall now take my leave. Farewell!
```
## Command Summary

| Action              | Format                                      | Examples                                              |
| ------------------- | ------------------------------------------- | ----------------------------------------------------- |
| Add Todo Task       | `todo TASKNAME`                             | `todo Play Basketball`                                |
| Add Deadline Task   | `deadline TASKNAME /by DEADLINE`            | `deadline Complete CS2100 Assignment /by 18/09/23 1315` |
| Add Event Task      | `event TASKNAME /from FROM /to TO`          | `event CS2103T Final Exam /from 01/12/23 0900 /to 01/12/23 1030` |
| List                | `list`                                      | `list`                                                |
| Delete              | `delete INDEX`                              | `delete 3`                                            |
| Mark                | `mark INDEX`                                | `mark 1`                                              |
| Unmark              | `unmark INDEX`                              | `unmark 1`                                            |
| Find                | `find KEYWORD`                              | `find Basketball`                                     |
| Remind              | `remind`                                    | `remind`                                              |
| Exit                | `bye`                                       | `bye`                                                 |




