package dl;

import java.io.File;

import lexer.Lexer;
import parser.Parser;

public class DL {
	public static void main(String[] args) {
		Lexer l = new Lexer(
				new File("prog.dl"));		
		Parser p = new Parser(l);
		p.parse();
		System.out.println(p.parseTree());
		System.out.println("finalizado");
	}
}
