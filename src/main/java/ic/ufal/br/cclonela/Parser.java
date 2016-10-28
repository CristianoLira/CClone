package ic.ufal.br.cclonela;

import java.util.LinkedList;


public class Parser {
	protected Analyzer lexicalAnalyzer;
	private Token token;
	//protected LinkedList<Token> ll;

	public Parser(Analyzer analyzer) {
		this.lexicalAnalyzer = analyzer;
		//this.ll = ll;
	}
	
	void nextToken(){
		token = lexicalAnalyzer.nextToken();
	}

	void start(){
		nextToken();
		program();
	}
	
	void error(int id){
		System.out.println("erro" + id);
		System.exit(id);
	}

	void program(){
		//return this.mainFunc();
		functions();
		mainFunc();

	}

	void functions(){
		returnType();
		
		if(token.categ == Categories.id){
			nextToken();
			param();
			scope();
			functions();
		}
		else return;
		
	}
	
	void mainFunc(){
		returnType();
		
		if(token.categ == Categories.prMain){
			nextToken();
			param();
			scope();
		}
		else error(1);
	
	}
	
	void returnType(){
		if(token.categ == Categories.prVoid){
			nextToken();
		}
		else {
			type();
			//returnType2();
		}

	}

	void param(){
		if(token.categ == Categories.abPar){
			nextToken();
			param2();
		}
	}
	
	void param2(){
		if(token.categ == Categories.prVoid){
			nextToken();
		}
		else {
			paramList();
		}
		if(token.categ != Categories.fcPar){
			nextToken();
			error(-1);
		}
		
	}
	
	void id(){
		if(token.categ == Categories.id){
			nextToken();
			id2();
		}
		
		else error(-1);
	}
	
	void id2(){
		if(token.categ == Categories.abCol){
			nextToken();
			exp();
			if(token.categ == Categories.fcCol)
				nextToken();
			else error(-1);
		}
		else return;
	}
	
	void paramList(){
		type();
		id();
		paramList2();
	}
	
	void paramList2(){
		if(token.categ == Categories.sep){
			nextToken();
			paramList();
		}
		else return;
	}
	
	void type(){
			if(token.categ == Categories.prBool || 
					token.categ == Categories.prChar ||
					token.categ == Categories.prInt ||
					token.categ == Categories.prFloat){
				nextToken();
				type2();
			}
			else error(-1);
	}
	
	void type2(){
		if(token.categ == Categories.abCol){
			nextToken();
			if(token.categ == Categories.fcCol)
				nextToken();
			else error(-1);
		}
		else return;
	}

	void scope(){
		if(token.categ == Categories.abCh){
			nextToken();
			cmd();
			if(token.categ != Categories.fcCh)
				error(-1);
		}
		else error(-1);
	}
	
	void scope2(){
		if(token.categ == Categories.fcCh)
			nextToken();
		else{
			cmd();
			scope2();
		}
	}
	
	void cmd(){
		if(token.categ == Categories.prBool || 
				token.categ == Categories.prChar ||
				token.categ == Categories.prInt ||
				token.categ == Categories.prFloat)
			declarationCmd();
		else if(token.categ == Categories.id)
			cmd2();
		else if(token.categ == Categories.prIf)
			ifelseCmd();
		else if(token.categ == Categories.prWhile)
			whileCmd();
		else if(token.categ == Categories.prFor)
			forCmd();
		else if(token.categ == Categories.prRet)
			returnCmd();
		else error(-1);
	}
	
	void cmd2(){
		if(token.categ == Categories.abPar)
			funccallCmd();
		else{
			id2();
			attrCmd();
		}
	}
	
	void declarationCmd(){
		type();
		declarationCmd2();
	}
	
	void declarationCmd2(){
		if(token.categ == Categories.id){
			nextToken();
			declarationCmd3();
		}
		else if(token.categ == Categories.abCol)
			arrayDec();
		else error(-1);
	}
	
	void declarationCmd3(){
		if(token.categ == Categories.opAtr){
			//atribuição é operador
			attrCmd();
		}
		else return;
	}
	
	void arrayDec(){
		if(token.categ == Categories.abCol){
			nextToken();
			exp();
			if(token.categ == Categories.fcCol)
				nextToken();
			else error(-1);
			
			if(token.categ == Categories.id)
				nextToken();
			else error(-1);
			
			arrayDec2();
		}
		else error(-1);
	}
	
	void arrayDec2(){
		if(token.categ == Categories.opAtr)
			arrayAttrCmd();
		else return;
	}
	
	void attrCmd(){
		if(token.categ == Categories.opAtr)
			exp();
		else error(-1);
	}
	
	void arrayAttrCmd(){
		if(token.categ == Categories.opAtr)
			arrayInit();
		else error(-1);
	}
	
	void arrayInit(){
		if(token.categ == Categories.abCh){
			nextToken();
			elem();
			if(token.categ == Categories.fcCh)
				nextToken();
			else error(-1);
		}
		else error(-1);
	}
	
	void elem(){
		if(token.categ == Categories.id){
			id();
		}
		else exp();
		
		elem2();
	}
	
	void elem2(){
		if(token.categ == Categories.sep){
			nextToken();
			elem();
		}
		else return;
	}
	
	void constant(){
		if(token.categ == Categories.cteCh ||
				token.categ == Categories.cteFloat ||
				token.categ == Categories.cteInt ||
				token.categ == Categories.cteStr){
			nextToken();
		}
		else error(-1);
	}
	
	void funccallCmd(){
		if(token.categ == Categories.abPar){
			nextToken();
			funccallParamList();
			if(token.categ == Categories.fcPar)
				nextToken();
			else error(-1);
		}
		else error(-1);
	}
	
	void funccallParamList(){
		funccallParam();
		funccallParamList2();
	}
	
	void funccallParamList2(){
		if(token.categ == Categories.sep){
			nextToken();
			funccallParamList();
		}
		else return;
	}
	
	void funccallParam(){
		if(token.categ == Categories.id)
			id();
		else exp();
	}
	
	void ifelseCmd(){
		ifCmd();
		elseifCmd();
		elseCmd();
		
	}
	
	void ifCmd(){
		if(token.categ == Categories.prIf){
			nextToken();
			if(token.categ == Categories.abPar){
				nextToken();
				exp();
				if(token.categ == Categories.fcPar){
					nextToken();
					scope();
				}
				else error(-1);
			}
			else error(-1);
		}
		else error(-1);
	}
	
	void elseifCmd(){
		if(token.categ == Categories.prElse){
			nextToken();
			elseifCmd2();
		}
		else error(-1);
	}
	
	void elseifCmd2(){
		if(token.categ == Categories.prIf){
			ifCmd();
			elseifCmd();
		}
		else if(token.categ == Categories.abCh)
			elseCmd();
		else error(-1);
	}
	
	void elseCmd(){
		scope();
	}
	
	void whileCmd(){
		if(token.categ == Categories.prWhile){
			nextToken();
			if(token.categ == Categories.abPar){
				nextToken();
				exp();
				if(token.categ == Categories.fcPar)
					scope();
				else error(-1);
			}
			else error(-1);
		}
		else error(-1);
	}
	
	void forCmd(){
		if(token.categ == Categories.prFor){
			
		}
		else error(-1);
	}
	
	void returnCmd(){
		if(token.categ == Categories.prRet){
			returnCmd2();
		}
		else error(-1);
	}
	
	void returnCmd2(){
		if(token.categ == Categories.id)
			id();
		else exp();
		
		if(token.categ == Categories.term)
			nextToken();
		else error(-1);
	}

}
