grammar Scheme;

prog: expr+
    ;

expr: '(' '*' expr+ ')'         #Mult
    | '(' '/' expr+ ')'         #Div
    | '(' '+' expr+ ')'         #Plus
    | '(' '-' expr+ ')'         #Minus
    | number=NUMBER             #Number
    | '(' DISPLAY expr ')'      #Display
    ;

DISPLAY: 'display';

NUMBER: ('-')? DIGIT+;
DIGIT: [0-9];
WHITESPACE: [ \t\n\r]+ -> skip;
