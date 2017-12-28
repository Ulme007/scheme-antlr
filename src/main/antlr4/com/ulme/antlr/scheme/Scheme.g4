grammar Scheme;

prog: expr+
    ;

expr: '(' DISPLAY expr ')'      #Display
    | '(' number=NUMBER ')'     #Number
    | '(' '+' expr ')'          #Plus
    ;

DISPLAY: 'display';
NUMBER: [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
