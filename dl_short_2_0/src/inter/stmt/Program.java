package inter.stmt;

import lexer.Tag;
import lexer.Token;

public class Program extends Stmt{
    private Token id;
    private Block block;

    public Program(Token i, Block b) {
        id = i;
        block = b;
        addChild(block);
    }

    @Override
    public String toString(){
        return Tag.PROGRAM.toString();
    }
}
