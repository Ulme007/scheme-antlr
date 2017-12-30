grammar Scheme;

program: expression+
       ;

expression: '(' oprator=arithmeticOperator expression+ ')'  #ArithmeticOperation
          | number=NUMBER                                   #Number
          | varName=IDENTIFIER                              #Identifier
          | '(' DISPLAY expression ')'                      #Display
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
DEFINE: 'define';
LIST: 'list';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
NUMBER: ('-')? [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
