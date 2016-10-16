package ic.ufal.br.cclonela;

public class Token {
	Categories categ;
	private String value;
	private int line;
	private int column;
	
	public Token(String value, Categories categ, int line, int column){
		this.value = value;
		this.categ = categ;
		this.line = line;
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "<" + line + "," + column + "> " + categ + " = '" + value + "'";
	}
	
	public String getValue(){
		return this.value;
	}
	
	public int getLine(){
		return this.line;
	}
	
	public int getColumn(){
		return this.column;
	}

	public Categories getCateg() {
		return categ;
	}

	
	
	
}
