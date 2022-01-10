package inter.stmt;

import inter.expr.Expr;
import inter.expr.Id;
import lexer.Tag;

public class Assign extends Stmt {
    protected Id id;
    protected Expr expr;

    public Assign(Id i, Expr e) {
        id = i;
        expr = e;
        addChild(id);
        addChild(expr);
    }

    @Override
    public String toString(){
        return Tag.ASSIGN.toString();
    }
}
