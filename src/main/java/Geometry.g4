grammar Geometry;

program
    : stm* EOF
    ;

stm
    : varDecl
    | funcDecl
    | showStm
    ;

varDecl
    : LET type ID '=' expr ';'
    ;

funcDecl
    : LET FUNCTION ID FROM '(' paramList? ')' RETURNS type funcProgram
    ;

showStm
    : SHOW expr ';'
    ;

paramList
    : param (',' param)*
    ;

param
    : type ID
    ;

funcProgram
    : '{' stm* returnStm '}'
    ;

returnStm
    : RETURN expr ';'
    ;

expr
    : constructorCall
    | functionCall
    | ID
    | literal
    | '(' expr ')'
    ;

constructorCall
    : geometricType '(' argList? ')'
    ;

functionCall
    : ID '(' argList? ')'
    ;

argList
    : expr (',' expr)*
    ;

type
    : numericType
    | geometricType
    ;

numericType
    : INT
    | DOUBLE
    ;

geometricType
    : POINT
    | SQUARE
    | SEGMENT
    | LINE
    | RECT
    | RHOMB
    | TRAPEZ
    | PARAL
    ;

literal
    : INT_CONST
    | DOUBLE_CONST
    ;

LET         : 'НЕХАЙ' ;
FUNCTION    : 'ФУНКЦІЯ' ;
FROM        : 'ВІД' ;
RETURNS     : 'ПОВЕРТАЄ' ;
RETURN      : 'ПОВЕРНУТИ' ;
SHOW        : 'ПОКАЗАТИ' ;

INT         : 'ЦІЛЕ' ;
DOUBLE      : 'ДІЙСНЕ' ;
POINT       : 'ТОЧКА' ;
SQUARE      : 'КВАДРАТ' ;
SEGMENT     : 'ВІДРІЗОК' ;
LINE        : 'ЛІНІЯ' ;
RECT        : 'ПРЯМОКУТНИК' ;
RHOMB       : 'РОМБ' ;
TRAPEZ      : 'ТРАПЕЦІЯ' ;
PARAL       : 'ПАРАЛЕЛОГРАМ' ;

ID
    : [A-Za-z\u0400-\u04FF_] [A-Za-z0-9\u0400-\u04FF_]*
    ;

INT_CONST
    : DIGIT+
    ;

DOUBLE_CONST
    : DIGIT+ ('.' DIGIT+)?
    ;

fragment DIGIT : [0-9] ;

WS  : [ \t\r\n\u000C]+ -> skip ;
LINE_COMMENT : '//' ~[\r\n]* -> skip ;
BLOCK_COMMENT : '/*' .*? '*/' -> skip ;