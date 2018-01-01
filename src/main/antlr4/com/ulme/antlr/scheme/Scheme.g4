grammar Scheme;

program: expression+
       ;

expression: OPEN_BRACE oprator=arithmeticOperator expression+ CLOSE_BRACE  #ArithmeticOperation
          | number=NUMBER                                   #Number
          | varName=IDENTIFIER                              #Identifier
          | OPEN_BRACE DISPLAY expression CLOSE_BRACE                      #Display
          | OPEN_BRACE NEWLINE CLOSE_BRACE                                 #Newline
          | OPEN_BRACE LIST expression* CLOSE_BRACE                        #List
          | OPEN_BRACE DEFINE varName=IDENTIFIER expression CLOSE_BRACE    #VariableDefinition
          | OPEN_BRACE DEFINE OPEN_BRACE funcName=IDENTIFIER (paramNames+=IDENTIFIER)* CLOSE_BRACE statements=expression+ CLOSE_BRACE    #FunctionDefinition
          | OPEN_BRACE funcName=IDENTIFIER arguments+=expression* CLOSE_BRACE                #FunctionCall
          ;

arithmeticOperator: '+'|'-'|'*'|'/'
                  ;

relationalOperator: '='|'<'|'<='|'>'|'>='
                  ;

booleanOperator: 'and'|'or'|'not'
               ;

DISPLAY: 'display';
NEWLINE: 'newline';
DEFINE: 'define';
LIST: 'list';
TRUE: '#t';
FALSE: '#f';

/*
SEMICOLON       : ';' -> mode(COMMENT_MODE);
mode COMMENT_MODE;
COMMENT_TEXT: (~('\n' | EOF))*;
END: ('\n' | EOL) -> mode(DEFAULT_MODE);
*/

OPEN_BRACE: '(';
CLOSE_BRACE: ')';
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
