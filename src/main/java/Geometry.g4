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
    : LET FUNCTION ID FROM '(' (param (',' param)*)? ')' RETURNS type funcProgram
    ;

showStm
    : SHOW expr ';'
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
    : functionCall
    | ID
    | literal
    | '(' expr ')'
    ;

functionCall
    : ID '(' (expr (',' expr)*)? ')'
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
    | SEGMENT
    | LINE
    | QUAD
    | RHOMB
    | TRAPEZ
    | PARAL
    | RECT
    | SQUARE
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
SEGMENT     : 'ВІДРІЗОК' ;
LINE        : 'ПРЯМА' ;
QUAD        : 'ЧОТИРИКУТНИК' ;
RHOMB       : 'РОМБ' ;
TRAPEZ      : 'ТРАПЕЦІЯ' ;
PARAL       : 'ПАРАЛЕЛОГРАМ' ;
RECT        : 'ПРЯМОКУТНИК' ;
SQUARE      : 'КВАДРАТ' ;

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