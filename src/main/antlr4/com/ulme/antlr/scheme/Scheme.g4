grammar Scheme;

programm: expression+
        ;

expression: '(' PRINT expression ')'    #Print
          | '(' number=NUMBER ')'       #Number
          | '(' '+' expression ')'      #Plus
          ;

PRINT: 'print';
NUMBER: [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
