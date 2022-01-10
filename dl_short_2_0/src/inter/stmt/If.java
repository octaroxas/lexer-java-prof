package inter.stmt;

import inter.expr.Expr;
import lexer.Tag;

public class If extends Stmt{
    private Expr expr;
    private Stmt stmt;

    public If(Expr e, Stmt s){
        expr = e;
        stmt = s;
        addChild(expr);
        addChild(stmt);
    }

    @Override
    public String toString(){
        return Tag.IF.toString();
    }
}
