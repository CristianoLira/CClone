package ic.ufal.br.cclonela;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Analyzer {
	
	private List<String> linesList;
	private int currentLine, currentColumn;
	private String line;
	private String filePath;
	
	private final char LINE_BREAK = '\n';
	
	public Analyzer(String filePath){
		linesList = new ArrayList<>();
		this.filePath = filePath;
		this.currentLine = 0;
		this.currentColumn = 0;
	}
	
	public void readFile() throws IOException{
    	for (String line : Files.readAllLines(Paths.get(filePath))) {
    		linesList.add(line);
    	}
	}
	
	private Character nextChar(){
		line = linesList.get(currentLine);
		currentColumn++;
		
		if (currentColumn < line.length()) {
			return line.charAt(currentColumn);
		}
		else if(currentLine < linesList.size()){
			currentColumn = 0;
			currentLine++;
			return LINE_BREAK;
		}
		else{
			return '\0';
		}
	}

	public Token nextToken(){
		char currentChar;
		String token = "";
		Token nextToken;
		Categories categ;
		
		do{
			currentChar = nextChar();
			token += currentChar;
			categ = tokenCateg(token);
		}
		while(categ == Categories.unknown && currentChar !='\0');
			
		nextToken = new Token(token, categ, currentLine, currentColumn);
		
		if(currentChar == '\0'){
			nextToken = null;
		}
			
		return nextToken;
	}
	
	// TODO: Fazer reconhecer os tokens juntos e consertar as regex
	private Categories tokenCateg(String token){
		
		if(token.equals("+")){
			return Categories.opAd;
		}
		else if(token.equals("­")){
			return Categories.opSub;
		}
		else if(token.equals("++") || token.equals("--")){
			return Categories.opInc;
		}
		else if(token.equals("*")){
			return Categories.opMult;
		}
		else if(token.equals("/")){
			return Categories.opDiv;
		}
		else if(token.equals("%")){
			return Categories.opMod;
		}
		else if(token.equals("&&")){
			return Categories.opConj;
		}
		else if(token.equals("||")){
			return Categories.opDisj;
		}
		else if(token.equals("!")){
			return Categories.opNeg;
		}
		else if(token.equals(">")){
			return Categories.opMa;
		}
		else if(token.equals(">=")){
			return Categories.opMai;
		}
		else if(token.equals("<")){
			return Categories.opMe;
		}
		else if(token.equals("<=")){
			return Categories.opMei;
		}
		else if(token.equals("==")){
			return Categories.opIg;
		}
		else if(token.equals("!=")){
			return Categories.opDif;
		}
		else if(token.equals("=")){
			return Categories.opAtr;
		}
		else if(token.equals("int")){
			return Categories.prInt;
		}		
		else if(token.equals("float")){
			return Categories.prFloat;
		}
		else if(token.equals("char")){
			return Categories.prChar;
		}
		else if(token.equals("bool")){
			return Categories.prBool;
		}
		else if(token.equals("true")){
			return Categories.prTrue;
		}
		else if(token.equals("false")){
			return Categories.prFalse;
		}
		else if(token.equals("if")){
			return Categories.prIf;
		}
		else if(token.equals("else")){
			return Categories.prElse;
		}
		else if(token.equals("while")){
			return Categories.prWhile;
		}
		else if(token.equals("for")){
			return Categories.prFor;
		}
		else if(token.equals("main")){
			return Categories.prMain;
		}
		else if(token.equals("return")){
			return Categories.prRet;
		}		
		else if(token.equals("(")){
			return Categories.abPar;
		}
		else if(token.equals(")")){
			return Categories.fcPar;
		}
		else if(token.equals("[")){
			return Categories.abCol;
		}
		else if(token.equals("]")){
			return Categories.fcCol;
		}
		else if(token.equals("{")){
			return Categories.abCh;
		}
		else if(token.equals("}")){
			return Categories.fcCh;
		}
		else if(token.equals(";") || token.equals(",")){
			return Categories.sep;
		}
		else if(token.matches("\\w\\(w|d)*")){
			return Categories.id;
		}
		else if(token.matches("-?\\d+")){
			return Categories.cteInt;
		}
		else if(token.matches("-\\d+(.\\d+)?")){
			return Categories.cteFloat;
		}
		else if(token.matches("\"(\\d|\\w|\\s)?\"")){
			return Categories.cteCh;
		}
		else if(token.matches("\"(\\d|\\w|\\s)*\"")){
			return Categories.cteStr;
		}
		else{
			//System.out.println("Token não reconhecido.");
			return Categories.unknown;
		}
	}
	
}
