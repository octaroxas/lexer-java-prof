package inter.stmt;

import inter.expr.Id;
import lexer.Tag;

public class Write extends Stmt{
    private Id id;
    public Write(Id i ){
        id = i;
        addChild(id);
    }

    @Override
    public String toString(){
        return Tag.WRITE.toString();
    }
}
