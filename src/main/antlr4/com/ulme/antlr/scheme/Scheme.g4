grammar Scheme;

prog: expr+
    ;

expr: '(' PRINT expr ')'        #Print
    | '(' number=NUMBER ')'     #Number
    | '(' '+' expr ')'          #Plus
    ;

PRINT: 'print';
NUMBER: [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
