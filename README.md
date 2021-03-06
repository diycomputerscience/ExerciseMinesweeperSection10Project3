<h1>Section 10 Project 3</h1>

<h2>Overview</h2>

You meet the team lead and another developer at the water cooler !

 - _**[team lead]** I was once building some software which had problems with parsing complex data. We decided to use [regular expressions](http://www.zytrax.com/tech/web/regex.htm) to fix those problems.
Up until  now, in our logic to save and load the state of a Board, we have been manually parsing the data to recreate the squares. Even though it works, this is not the best way to parse text data. It is far better to use regular expressions._ 
 - _**[developer]** cool, did that work out ?_
 - _**[team lead]** Hah! Before using regular expressions we had one problem, and after using them we had two :-)_
 - _**[developer]** HasAPuzzledLookOnFace_
 - _**[team lead]** I was kidding !, Regular expressions are something which every developer should know about. But just be careful when using them, and be sure to use them properly._

Suddenly you realize that the Minesweeper project could also use regular expressions for parsing the board data which we saved in a file. Excited with the prospect of learning something new, let's incorporate regular expressions in Minesweeper's code in this project.
 
For this project, we have modified ```FilePersistenceStrategy``` to enable the use of regular expressions. We have created two constants, as shown in Code Snippet 1, which represent the format  to save data, and the corresponding regular expression to read the data back. In the previous line we have used the term format in the spirit of - _format which can be used with String.format(...)_.

    public static final String SQUARE_FORMAT = ""; 
    public static final String SQUARE_LOAD_REGEX = "";

_**Code Snippet 1**_

You have to give proper values to both these constants and implement the empty methods ```dataForSquare()``` and ```load()```. 
In the method ```dataForSquare()```, you have to format data using ```SQUARE_FORMAT```, so it can be saved to a file, and in the second method ```load()```, you have to use the regular expression defined in ```SQUARE_LOAD_REGEX```, to parse lines of data read from the file, and create ```Square``` objects. These ```Square``` objects are then to be used to create a ```Board``` object.

Run ```AllTests```, and verify that 62 tests are run. There is 1 error and 2 failures. Implement code as specified above, and get the tests to pass.
