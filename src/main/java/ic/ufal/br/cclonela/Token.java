package ic.ufal.br.cclonela;


public class Token {
	Categories categ;
	String value;
	
	public Token(String value, Categories categ){
		this.value = value;
		this.categ = categ;
	}
}
