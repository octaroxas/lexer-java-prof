package inter.expr;

import lexer.Tag;
import lexer.Token;

public class Bin extends Expr {
    protected Expr expr1;
    protected Expr expr2;

    public Bin(Token op, Expr e1, Expr e2){
        super(op,null);
        type = maxType(e1.type(), e2.type());
        if(this.type == null){
            error("Tipos inconpativeis!");
        }
        
        expr1 = e1;
        expr2 = e2;
        addChild(expr1);
        addChild(expr2);

    }

    private static Tag maxType(Tag t1, Tag t2){
        if(!t1.isNum() || !t2.isNum()) {
            return null;
        } else if(t1.isReal() || t2.isReal()) {
            return Tag.REAL;
        } else  {
            return Tag.INT;
        }
    }
}
