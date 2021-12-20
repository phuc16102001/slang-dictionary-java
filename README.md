# Slang dictionary (java)
A small dictionary project to store slangs. 

## Features
Firstly, this project allow you create a dictionary where a slang word is associated with one or many definition(s). I provide you some features as described below.

### Searching by slang
Obviously, you can search by a slang word by clicking on `Search by slang` button on `Dictionary menu` window. In the `Slang window` you can view, add or even delete definitions.
> The slang word must be exactly matched with the data in your dictionary

### Searching by definition
Secondly, you can search by a definition by clicking on `Search by definition` button. 
> The searching by definition is a full-text search, which means you do not need to type the complete definition

### Add/remove a new slang
Although the default dictionary is stored in `slang.txt`, you are able to add or remove a slang word. In `add` feature, if the slang word already existed, you will be asked to choose whether overwrite it or append a new definition.

### Show a random slang
For learning purpose, I provide a feature that can random a new slang each time with corresponding definitions.

### Reset with the default dictionary
After playing with your dictionary, you can reset it to default as you wish by clicking on `Reset dictionary` button.

### View search history
It is very easy to revise what you have search by using the history feature. Futhermore, when view history, you can double click on the slang word to view its definitions.

### Quizzes
Lastly, I also have a feature that is quizzes. There are two types of quiz:
- Quiz with slang question
- Quiz with definition question
Each quiz has 4 answers.

## Compile and usage
To compile, run file `compile.bat` (optional). After compile, you can run with `run.bat` file or:
```bat
java -jar jar/output.jar
```

## Contributors
Project is developed by [phuc16102001](https://github.com/phuc16102001) that was a submission of lab 3 in Java Programming course. Please **do not copy** in any case.