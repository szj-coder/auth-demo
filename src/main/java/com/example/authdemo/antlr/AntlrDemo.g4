grammar AntlrDemo;

expr: LPAREN expr RPAREN        # parenExpr
    | expr (MULT | DIV) expr    # multOrDiv
    | expr (PLUS | MINUS) expr  # plusOrMinus
    | expr '==' expr            # operatorExpr
    | expr '?' expr ':' expr    # ternaryOperator
    | factor                    # factorExpr
    ;

factor: INTEGER                 # objFactory
    | BOOLEAN                   # objFactory
    ;

PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
LPAREN: '(';
RPAREN: ')';
OPENCURLY: '{';
CLOSECURLY: '}';
INTEGER: [-]?[0-9]+;
BOOLEAN: T R U E
       | F A L S E
       | [0]
       | [1];

IF: I F;
ELSE: E L S E;

// 表示用A代表a和A，这样就可以不区分大小写了，以下同理
A: [aA];
B: [bB];
C: [cC];
D: [dD];
E: [eE];
F: [fF];
G: [gG];
H: [hH];
I: [iI];
J: [jJ];
K: [kK];
L: [lL];
M: [mM];
N: [nN];
O: [oO];
P: [pP];
Q: [qQ];
R: [rR];
S: [sS];
T: [tT];
U: [uU];
V: [vV];
W: [wW];
X: [xX];
Y: [yY];
Z: [zZ];


WS: [ \t\n\r]+ -> skip;
