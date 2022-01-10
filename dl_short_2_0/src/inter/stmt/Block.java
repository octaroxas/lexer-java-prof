package inter.stmt;

import java.util.LinkedList;

public class Block  extends Stmt{
    private LinkedList<Stmt> stmts;
    public Block() {
        stmts = new LinkedList<Stmt>();
    }

    public void addStmt(Stmt stmt) {
        stmts.add(stmt);
        addChild(stmt);
    }

    @Override
    public String toString(){
        return "BLOCK";
    }
}
