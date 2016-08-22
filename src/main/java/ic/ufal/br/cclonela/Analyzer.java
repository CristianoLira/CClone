package ic.ufal.br.cclonela;

import java.util.List;

public class Analyzer {
	
	List<String> input;
	int pos;
	
	public Analyzer(List<String> input){
		this.input = input;
		this.pos = 0;
	}

	public Token nextToken(){
		String token = input.get(pos);
		Token nextToken;
		Categories categ;
		
		if(token.equals("+")){
			categ = Categories.opAd;
		}
		else if(token.equals("­")){
			categ = Categories.opSub;
		}
		else if(token.equals("++") || token.equals("--")){
			categ = Categories.opInc;
		}
		else if(token.equals("*")){
			categ = Categories.opMult;
		}
		else if(token.equals("/")){
			categ = Categories.opDiv;
		}
		else if(token.equals("%")){
			categ = Categories.opMod;
		}
		else if(token.equals("&&")){
			categ = Categories.opConj;
		}
		else if(token.equals("||")){
			categ = Categories.opDisj;
		}
		else if(token.equals("!")){
			categ = Categories.opNeg;
		}
		else if(token.equals(">")){
			categ = Categories.opMa;
		}
		else if(token.equals(">=")){
			categ = Categories.opMai;
		}
		else if(token.equals("<")){
			categ = Categories.opMe;
		}
		else if(token.equals("<=")){
			categ = Categories.opMei;
		}
		else if(token.equals("==")){
			categ = Categories.opIg;
		}
		else if(token.equals("!=")){
			categ = Categories.opDif;
		}
		else if(token.equals("=")){
			categ = Categories.opAtr;
		}
		else if(token.equals("int")){
			categ = Categories.prInt;
		}		
		else if(token.equals("float")){
			categ = Categories.prFloat;
		}
		else if(token.equals("char")){
			categ = Categories.prChar;
		}
		else if(token.equals("bool")){
			categ = Categories.prBool;
		}
		else if(token.equals("true")){
			categ = Categories.prTrue;
		}
		else if(token.equals("false")){
			categ = Categories.prFalse;
		}
		else if(token.equals("if")){
			categ = Categories.prIf;
		}
		else if(token.equals("else")){
			categ = Categories.prElse;
		}
		else if(token.equals("while")){
			categ = Categories.prWhile;
		}
		else if(token.equals("for")){
			categ = Categories.prFor;
		}
		else if(token.equals("main")){
			categ = Categories.prMain;
		}
		else if(token.equals("return")){
			categ = Categories.prRet;
		}		
		else if(token.equals("(")){
			categ = Categories.abPar;
		}
		else if(token.equals(")")){
			categ = Categories.fcPar;
		}
		else if(token.equals("[")){
			categ = Categories.abCol;
		}
		else if(token.equals("]")){
			categ = Categories.fcCol;
		}
		else if(token.equals("{")){
			categ = Categories.abCh;
		}
		else if(token.equals("}")){
			categ = Categories.fcCh;
		}
		else if(token.equals(";") || token.equals(",")){
			categ = Categories.sep;
		}
		else if(token.matches("\\w\\(w|d)*")){
			categ = Categories.id;
		}
		else if(token.matches("-?\\d+")){
			categ = Categories.cteInt;
		}
		else if(token.matches("-\\d+(.\\d+)?")){
			categ = Categories.cteFloat;
		}
		else if(token.matches("\"(\\d|\\w|\\s)?\"")){
			categ = Categories.cteCh;
		}
		else if(token.matches("\"(\\d|\\w|\\s)*\"")){
			categ = Categories.cteStr;
		}
		else{
			System.out.println("Token não reconhecido.");
			categ = null;
		}
			
		nextToken = new Token(token, categ);
			
		return nextToken;
	}
}
