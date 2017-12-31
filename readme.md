# Very simple Scheme interpreter with ANTLR


# Development

* Show the parsed Antlr tree of file 'src/main/resources/code.demo'.
  mvn compile -PShowTree
* Print out the parsed tokens of file 'src/main/resources/code.demo'.
  mvn compile -PPrintToken

# ToDo
* `display` should use print not println
* Add **newline** - print out a new line
* Add comments to the grammar
* Add conditions su as: **<,>,<=,>=,not,and**
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
