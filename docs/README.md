# MYBOT's User Guide
MYBot is a friendly chatbot buddy for keeping track and managing your tasks!

## Features 

### Adding tasks
Tasks can be easily added by simply telling the bot the type of task you want to add and the details to the task.

There are three types of task users can add: `todo`, `deadline`, `event`.

### Managing tasks
Tasks can be simply managed by telling the bot what you wish to do and the specific number of that task. 

There are several command users can use to manage their tasks: `mark`, `unmark`, `delete`,`find`


### Storage
MYBot saves users' tasks and users' can easily find the tasks their previously stored everytime they relaunch the Bot.

Users may view their list of task with `list`

### Detect duplicates
MYBot also checks through the storage everytime users add and task and reminds users in any case there is a repetition
of tasks.

If users still wish to add the task after the reminder, users can type `proceed`


## Usage

### Adding tasks
#### 1. `todo`
Adds a **todo** task

Example input:`todo assignment`

#### 2. `deadline`
Adds a **deadline** task

Example input:

    deadline iP /by 13.10.2023
    deadline iP /by 13.10.2023,Thu
    deadline iP /by 13.10.2023,Thu,6PM

#### 3. `event`
Adds an **event** task

Example input:

    event tP Meeting /from 13.10.2023 /to 13.11.2023
    event tP Meeting /from 13.10.2023,Thu /to 13.11.2023,Monday 
    event tP Meeting /from 13.10.2023,Thu,6PM /to 13.11.2023,Monday,10PM

**To note:**

For MyBot to correctly recognise the duedate and period for events and deadline, the input should take note of the following.
1. Please do not leave a space after `,`
2. There should at least be a date after `/by`,`from` and `to` but the day and time is not necessary 
3. Day and Month of the Date input should always be double digit like `09.09.2023`
3. Day should be input as`Thu` or `Thursday`, inputs like `Thurs` will not be recognise
4. Time should be input as `6PM` or `630PM` or `6.30PM`, inputs like `6:30PM` or `6:30pm` will not be recognised


### Managing tasks
#### 1. `mark`
Indicates Xth task of the list as completed. 

Expected input: `mark 3` 

#### 2. `unmark`
Indicates Xth task of the list as not completed.

Expected input: `unmark 3`

#### 3. `delete`
Removes Xth task from the list of tasks

Expected input: `delete 3`

#### 4. `find`
Find all the tasks with a certain input

Expected input:`find iP`

Expected output: The list of all the tasks that has the words `iP` in it


### Storage
#### 1. `list`
Shows the list of all the items in your list

Expected input:`list`

### Detect duplicates
#### 1. `duplicates`
If you have a very similar inputs, MYBot would remind you that there are very smilar inputs in your list.If you still
wish to add in this task just type `proceed` and MYBot will add the task into the task list.
