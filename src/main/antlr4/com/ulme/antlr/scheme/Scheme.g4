grammar Scheme;

program: expression+
       ;

expression: '(' oprator=arithmeticOperator expression+ ')'  #ArithmeticOperation
          | number=NUMBER                                   #Number
          | varName=IDENTIFIER                              #Identifier
          | '(' DISPLAY expression ')'                      #Display
          | '(' NEWLINE ')'                                 #Newline
          | '(' LIST expression* ')'                        #List
          | '(' DEFINE varName=IDENTIFIER expression ')'    #VariableDefinition
          | '(' DEFINE '(' funcName=IDENTIFIER (paramNames+=IDENTIFIER)* ')' statements=expression+ ')'    #FunctionDefinition
          | '(' funcName=IDENTIFIER arguments+=expression* ')'                #FunctionCall
          ;

arithmeticOperator: '+'|'-'|'*'|'/'
                  ;

//relationalRator: '='|'>'|'<'
//               ;
//
//booleanOperator: 'and'|'or'|'not'
//               ;

DISPLAY: 'display';
NEWLINE: 'newline';
DEFINE: 'define';
LIST: 'list';

/*
SEMICOLON       : ';' -> mode(COMMENT_MODE);
mode COMMENT_MODE;
COMMENT_TEXT: (~('\n' | EOF))*;
END: ('\n' | EOL) -> mode(DEFAULT_MODE);
*/

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
