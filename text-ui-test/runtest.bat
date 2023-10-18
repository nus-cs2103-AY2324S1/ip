@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin
set "BIN_PATH=..\bin"

REM set compile path
set "SOURCE_DIR=..\src\main\java"

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist save del save

REM Loop through all .java files in the directory and subdirectories
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin duke.Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
