grammar AntlrDemo;

expr: LPAREN expr RPAREN        # parenExpr
    | expr (MULT | DIV) expr    # multOrDiv
    | expr (PLUS | MINUS) expr  # plusOrMinus
    | factor                    # factorExpr
    ;

factor: INTEGER;

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
LPAREN: '(';
RPAREN: ')';
INTEGER: [-]*[0-9]+;

WS: [ \t\n\r]+ -> skip;
