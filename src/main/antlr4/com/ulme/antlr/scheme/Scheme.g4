grammar Scheme;

prog: expr+
    ;

expr: '(' '+' expr+ ')'         #Plus
    | number=NUMBER             #Number
    | '(' DISPLAY expr ')'      #Display
    ;

DISPLAY: 'display';

NUMBER: ('-')? DIGIT+;
DIGIT: [0-9];
WHITESPACE: [ \t\n\r]+ -> skip;
