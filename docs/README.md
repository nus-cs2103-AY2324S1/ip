# Veda User Guide
Welcome to Veda System user guide. 

Veda is a task management companion system that aids you in keeping tracking of your different tasks.

You can refer to this user guide if
* You are new to the system
* You have questions regarding certain features
* You got problems that need troubleshooting

Through this guide, you will be able to:
* Learn what features Veda has to offer
* Learn the commands to use these features

Let's dive into the Veda user guide!

## Getting started
### Setting up
**Note: Veda can only be run on a computer.**

Firstly, you will need to ensure that your computer is operating `java 11`.
You can check that your computer is on the right version by opening your command prompt and typing `java -version` .


After that, head over to this website and download and install the file **Veda.jar**.

### Using Veda for the first time
To run Veda, you will have to use your computer command prompt.

First, open up your command prompt and use `cd` to access the directory that Veda.jar is stored in.

E.g. If Veda.jar is in your downloads/ directory, use `cd Downloads/`.

Upon accessing the directory that Veda.jar file is in, type in your command prompt `java -jar Veda.jar`.

**Note:** This may lead to some scary lines appearing, but it is normal. The GUI for Veda will open within moments.



## Features 

### View task

`list`
Views all the tasks currently stored.

### Update task

Update todo 
`update [task index] [new description]`

Update deadline
`update [task index] [new description] /by [dd/MM/yyyy HHmm]`

Update event
`update [task index] [new description] /from [dd/MM/yyyy HHmm] / by [dd/MM/yyyy HHmm]`

### Delete task

Delete task at index
`delete [task index]`

### Add task

Add todo
`todo [new description]`

Add deadline
`deadline [new description] /by [dd/MM/yyyy HHmm]`

Add event
`event [new description] /from [dd/MM/yyyy HHmm] / by [dd/MM/yyyy HHmm]`

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```

## Glossary
