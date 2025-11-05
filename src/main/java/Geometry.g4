grammar Geometry;

program
    : stm+ EOF
    ;

stm
    : type ID ASSIGN expr SEMI
    | func_def
    | SHOW expr SEMI
    ;

type
    : INT_T
    | DOUBLE_T
    | POINT_T
    | SEGMENT_T
    | LINE_T
    | QUAD_T
    | PARAL_T
    | TRAPEZ_T
    | RHOMB_T
    | RECT_T
    | SQUARE_T
    ;

param
    : type ID
    ;

params
    : param (COMMA param)*
    ;

args
    : expr (COMMA expr)*
    ;

return_stm
    : RETURN expr SEMI
    ;

func_def
    : ID LPAREN params? RPAREN LBRACE stm+ return_stm RBRACE
    ;

expr
    : ID
    | CONST_INT
    | CONST_DOUBLE
    | ID LPAREN args? RPAREN
    | POINT_T LPAREN params RPAREN
    | SEGMENT_T LPAREN params RPAREN
    | LINE_T LPAREN params RPAREN
    | QUAD_T LPAREN params RPAREN
    | PARAL_T LPAREN params RPAREN
    | TRAPEZ_T LPAREN params RPAREN
    | RHOMB_T LPAREN params RPAREN
    | RECT_T LPAREN params RPAREN
    | SQUARE_T LPAREN params RPAREN
    ;

ASSIGN      : '=' ;
SEMI        : ';' ;
COMMA       : ',' ;
LPAREN      : '(' ;
RPAREN      : ')' ;
LBRACE      : '{' ;
RBRACE      : '}' ;

SHOW        : 'show' ;
RETURN      : 'return' ;

INT_T       : 'Int' ;
DOUBLE_T    : 'Double' ;
POINT_T     : 'Point' ;
SEGMENT_T   : 'Segment' ;
LINE_T      : 'Line' ;
QUAD_T      : 'Quad' ;
PARAL_T     : 'Paral' ;
TRAPEZ_T    : 'Trapez' ;
RHOMB_T     : 'Rhomb' ;
RECT_T      : 'Rect' ;
SQUARE_T    : 'Square' ;

CONST_DOUBLE
    : DIGITS '.' DIGITS ( [eE] [+-]? DIGITS )?
    | DIGITS [eE] [+-]? DIGITS
    ;

CONST_INT
    : DIGITS
    ;

ID
    : [a-zA-Z_] [a-zA-Z_0-9]*
    ;

fragment DIGITS : [0-9]+ ;

WS
    : [ \t\r\n]+ -> skip
    ;

LINE_COMMENT
    : '//' ~[\r\n]* -> skip
    ;

BLOCK_COMMENT
    : '/*' .*? '*/' -> skip
    ;
