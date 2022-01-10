package inter.stmt;

import inter.expr.Id;

public class Decl extends Stmt {
    private Id id;
    public Decl(Id i){
        id = i;
        addChild(id);
    }
    @Override
    public String toString(){
        return "DECL";
    }
}
