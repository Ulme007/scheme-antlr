grammar Scheme;

prog: expr+
    ;

/*
(define (sum x1 x2)
(+ x1 x2))

(define (prozedurname attribut1 attribut2)
(Anweisung1)
(Anweisung2))
*/

expr: '(' '*' expr+ ')'                         #Mult
    | '(' '/' expr+ ')'                         #Div
    | '(' '+' expr+ ')'                         #Plus
    | '(' '-' expr+ ')'                         #Minus
    | number=NUMBER                             #Number
    | varName=IDENTIFIER                        #Identifier
    | '(' DISPLAY expr ')'                      #Display
    | '(' DEFINE varName=IDENTIFIER expr ')'    #VariableDefinition
    | '(' DEFINE '(' funcName=IDENTIFIER (paramNames+=IDENTIFIER)* ')' expr+ ')'  #FunctionDefinition
    | '(' funcName=IDENTIFIER '(' (paramNames+=NUMBER)* ')' ')'               #FunctionCallExpression
    ;

DISPLAY: 'display';

DEFINE: 'define';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
