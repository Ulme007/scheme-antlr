# Very simple Scheme interpreter with ANTLR

## Types
- Long
- String
- List

## Functions

Function                    | Semantic                   
--------------------------- | ----------------------------
(display 1)                 | Print '1' without new line 
(newline)                   | Print out a new line       
(define size 2)             | Define the variable `size` with the value 2 (print out with (display size))
(define (square x) (* x x)) | Define the function `square`. Execute this with (square 2)

## Comments
';' Comment to the end of the line

## Development

* Show the parsed Antlr tree of file 'src/main/resources/code.demo'.
  mvn compile -PShowTree
* Print out the parsed tokens of file 'src/main/resources/code.demo'.
  mvn compile -PPrintToken

## ToDo
* Add car, cdr
* Add a REPL
* Read files from command line and from tests

* Add type for a function (e.g. FunctionType)
* `display` should use print not println
* Add **newline** - print out a new line
* Add comments to the grammar
* Add conditions such as: **<,>,<=,>=,and,or,not**
* Define and execute a file for the standard functions such as **abs**, **map** and so on
* Add **list** - define a list
  ```
   (list 1 2 3)
  ```
  The empty list is '()'
- Add **nil** - define the null element
- Add **#t** - defines `true`
- Add **#f** - defines `false`
- Add **cons** - create a list
- Add **quote** - don't eval this
- Add **eval** - evaluate the expression
- Add **car** - get the first element of a list
  ```
    (car list(1 2 3))
  ```
- Add **cdr** - get the rest (without first element) of a list
  ```
    (cdr list(1 2 3))
  ```
- Add **length** - get the length of a list
  ```
    (length (list (1 2 3)))
  ```

* Possible ToDo's
* Add second visitor to collect all function definitions
