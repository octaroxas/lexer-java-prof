package parser;

import inter.Node;
import inter.expr.*;
import inter.stmt.*;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Parser {
	private Lexer lexer;
	private Token look;
	private Node root;

	public Parser(Lexer lex) {
		lexer = lex;
		move();
	}

	private void error(String s) {
		System.err.println("linha " 
				+ Lexer.line() 
				+ ": " + s);
		System.exit(0);
	}

	private Token move()  {
		Token save = look;
		look = lexer.nextToken();
		return save;
	}

	private Token match(Tag t) {
		if ( look.tag() == t )
			return move();
		error("'" + look.lexeme() 
				+ "' inesperado");
		return null;
	}

	public void parse() {
		root = program();
	}

	private Program program() {
		match(Tag.PROGRAM);
		Token tokId = match(Tag.ID);
		Stmt b = block();
		match(Tag.DOT);
		match(Tag.EOF);
		return new Program(tokId, (Block)b);

	}

	private Stmt block() {
		Block b = new Block();
		match(Tag.BEGIN);
		while( look.tag() != Tag.END) {
			b.addStmt(stmt());
			match(Tag.SEMI);
		}
		match(Tag.END);
		return b;
	}

	private Stmt stmt() {
		switch ( look.tag() ) {
		case BEGIN: return block();
		case INT: case REAL: 
			case BOOL: return decl();
		case WRITE: return writeStmt();
		case ID: return assign();
		case IF: return ifStmt();
		case WHILE: return whileStmt();
		default: error("comando inválido");
		}
		return null;
	}

	private Stmt decl() {
		Token type = move();
		Token tokId = match(Tag.ID);
		Id id = new Id(tokId, type.tag());
		return new Decl(id);
	}

	private Stmt writeStmt() {
		move();
		match(Tag.LPAREN);
		Token tok = match(Tag.ID);
		Id id = new Id(tok, null);
		match(Tag.RPAREN);
		return new Write(id);
	}

	private Stmt assign() {
		Token tok = match(Tag.ID);
		Id id = new Id(tok, null);
		//match(Tag.ID);
		match(Tag.ASSIGN);
		Expr e = expr();
		return new Assign(id,e);
	}

	private Stmt ifStmt() {
		match(Tag.IF);
		match(Tag.LPAREN);
		Expr e = expr();
		match(Tag.RPAREN);
		Stmt s1 = stmt();
		return new If(e, s1);
	}

	private Stmt whileStmt() {
		match(Tag.WHILE);
		match(Tag.LPAREN);
		Expr e = expr();
		match(Tag.RPAREN);
		Stmt s1 = stmt();
		return new While(e, s1);
	}

	private Expr expr() {
		Expr e = rel();
		while( look.tag() == Tag.OR ) {
			move();  
			e = new Or(e, rel());
		}
		return e;
	}

	private Expr rel() {
		Expr e = arith();
		while ( look.tag() == Tag.LT || 
				look.tag() == Tag.LE ||
				look.tag() == Tag.GT) {
			Token op = move();
			e = new Rel(op, e, arith());
		}
		return e;
	}

	private Expr arith() {
		Expr e = term();
		while(	look.tag() == Tag.SUM || 
				look.tag() == Tag.SUB ) {
			Token op = move();
			e = new Bin(op, e, term());
		}
		return e;
	}

	private Expr term() {
		Expr e = factor();
		while(	look.tag() == Tag.MUL ) {
			Token op = move();
			e = new Bin(op, e, factor());
		}
		return e;
	}

	private Expr factor() {
		Expr e = null;
		switch( look.tag() ) {
		case LPAREN: move(); e = expr();
			match(Tag.RPAREN); break;
		case LIT_INT:
			e = new Literal(move(),Tag.INT);
			break;
		case LIT_REAL:
			e = new Literal(move(),Tag.REAL);
			break;
		case TRUE: case FALSE:
				e = new Literal(move(),Tag.BOOL);
				break;
		case ID:
			Token tok = match(Tag.ID);
			e = new Id(tok, null); break;
		default:
			error("expressão inválida");
		}
		return e;
	}

	public String parseTree() {
		return root.strTree();
	}
}
