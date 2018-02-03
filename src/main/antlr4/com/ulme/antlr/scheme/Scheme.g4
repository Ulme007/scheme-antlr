grammar Scheme;

program: expression+
       ;

expression: LPAREN oprator=arithmeticOperator expression+ RPAREN  #ArithmeticOperation
          | number=NUMBER                                   #Number
          | varName=IDENTIFIER                              #Identifier
          | LPAREN DISPLAY expression RPAREN                      #Display
          | LPAREN NEWLINE RPAREN                                 #Newline
          | LPAREN LIST expression* RPAREN                        #List
          | LPAREN DEFINE varName=IDENTIFIER expression RPAREN    #VariableDefinition
          | LPAREN DEFINE LPAREN funcName=IDENTIFIER (paramNames+=IDENTIFIER)* RPAREN statements=expression+ RPAREN    #FunctionDefinition
          | LPAREN funcName=IDENTIFIER arguments+=expression* RPAREN                #FunctionCall
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
NIL: 'nil';

LPAREN: '(';
RPAREN: ')';
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;

WHITESPACE: [ \t\n\r]+ -> skip;
COMMENT: ';' ~[\r\n]* -> skip;
