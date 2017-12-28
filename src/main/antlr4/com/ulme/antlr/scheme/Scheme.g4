grammar Scheme;

prog: expr+
    ;

expr: '(' '*' expr+ ')'                         #Mult
    | '(' '/' expr+ ')'                         #Div
    | '(' '+' expr+ ')'                         #Plus
    | '(' '-' expr+ ')'                         #Minus
    | number=NUMBER                             #Number
    | varName=IDENTIFIER                        #Identifier
    | '(' DISPLAY expr ')'                      #Display
    | '(' DEFINE varName=IDENTIFIER expr ')'    #VariableDefinition
    ;

DISPLAY: 'display';

DEFINE: 'define';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
