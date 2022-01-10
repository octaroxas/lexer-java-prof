package inter.stmt;

import inter.expr.Expr;
import lexer.Tag;

public class While extends Stmt {
    private Expr expr;
    private Stmt stmt;

    public While(Expr e, Stmt s) {
        expr = e;
        stmt = s;
        addChild(expr);
        addChild(stmt);
    }

    @Override
    public String toString(){
        return Tag.WHILE.toString();
    }

}
