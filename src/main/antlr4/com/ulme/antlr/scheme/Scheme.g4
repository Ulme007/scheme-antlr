grammar Scheme;

program: expression+
       ;

expression: '(' '*' expression+ ')'                         #Mult
          | '(' '/' expression+ ')'                         #Div
          | '(' '+' expression+ ')'                         #Plus
          | '(' '-' expression+ ')'                         #Minus
          | number=NUMBER                             #Number
          | varName=IDENTIFIER                        #Identifier
          | '(' DISPLAY expression ')'                      #Display
          | '(' DEFINE varName=IDENTIFIER expression ')'    #VariableDefinition
          | '(' DEFINE '(' funcName=IDENTIFIER (paramNames+=IDENTIFIER)* ')' expression+ ')'    #FunctionDefinition
          | '(' funcName=IDENTIFIER (paramNames+=(NUMBER|IDENTIFIER))* ')'                #FunctionCall
          ;

DISPLAY: 'display';

DEFINE: 'define';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
