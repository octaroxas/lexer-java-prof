package inter.expr;

import lexer.Token;

public class Bin extends Expr {
    protected Expr expr1;
    protected Expr expr2;

    public Bin(Token op, Expr e1, Expr e2){
        super(op,null);
        expr1 = e1;
        expr2 = e2;

        addChild(expr1);
        addChild(expr2);

    }
}
