package inter.expr;

import lexer.Tag;
import lexer.Token;

public class Rel extends Expr {
    protected Expr expr1;
    protected Expr expr2;

    public Rel(Token op, Expr e1, Expr e2){
        super(op, Tag.BOOL);
        expr1 = e1;
        expr2 = e2;
        addChild(expr1);
        addChild(expr2);
    }
}
