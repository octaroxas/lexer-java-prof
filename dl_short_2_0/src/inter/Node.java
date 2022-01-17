package inter;

import lexer.Lexer;

import java.util.LinkedList;

public abstract class Node {
    private LinkedList<Node> children = new LinkedList<Node>();

    protected void addChild(Node n ) {
        children.add(n);
    }

    protected LinkedList<Node> children() {
        return children;
    }

    public String strTree(){
        return strTree("");
    }

    private String strTree(String ident) {
        StringBuffer sb = new StringBuffer();
        sb.append(toString());
        for(Node n:children()) {
            sb.append("\n"+ident +"|--> ");
            sb.append(n.strTree(ident+"        "));
        }
        return sb.toString();
    }

    public static void error(String s) {
        System.err.println("Linha "+ Lexer.line()+ ": "+ s);
        System.exit(0);
    }
}
