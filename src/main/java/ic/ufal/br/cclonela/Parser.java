package ic.ufal.br.cclonela;

import java.util.LinkedList;

public class Parser {
	protected Analyzer lexicalAnalyzer;
	protected LinkedList<Token> ll;
	
	public Parser(Analyzer analyzer, LinkedList<Token> ll) {
		this.lexicalAnalyzer = analyzer;
		this.ll = ll;
	}
	
	public boolean syntaxAnalisys(){
		return program();
	}
	
	public boolean program(){
		
			return this.mainFunc();
		
		
		
	}
	
	public boolean functions(){
		
		if(returnType()){
			if(ll.getFirst().getCateg() == Categories.id){
				Token token;
				token = ll.getFirst();
				ll.removeFirst();
				
				if(param()){
					if(scope()){
						if(functions()){
							return true;
						}
					}
					
				}else{
					ll.addFirst(token);
				}
				
			}
		}
		
		return true;
		
		
	}
	public boolean mainFunc(){
		if(returnType()){
			if(ll.getFirst().getCateg() == Categories.prMain){
				ll.removeFirst();
				if(param()){
					if(scope()){
						return true;
						
					}else{
						System.out.println("1");
						return false;
						
					}
					
				}else{
					System.out.println("2");
					return false;
				}
				
			}else{
				System.out.println(ll.getFirst().getCateg());
				System.out.println("3");
				return false;
			}
		}else{
			System.out.println("4");
			return false;
		}
		
		
	}
	public boolean returnType(){
		
		if(ll.getFirst().getCateg() == Categories.prVoid){
			ll.removeFirst();
			return true;
		}
		if(type()){
			return true;
		}else{
			System.out.println("5");
			return false;
		}
		
	}
	public boolean type(){
		if(ll.getFirst().getCateg() == Categories.prBool || 
				ll.getFirst().getCateg() == Categories.prChar ||
				ll.getFirst().getCateg() == Categories.prInt ||
				ll.getFirst().getCateg() == Categories.prFloat){
			ll.removeFirst();
			return true;
		}
		else{
			System.out.println("6");
			return false;
		}
	}
	
	public boolean param(){
		if(ll.getFirst().getCateg() == Categories.abPar){
			ll.removeFirst();
			if(param2()){
				return true;
			}else{
				System.out.println("7");
				return false;
			}
		}else{
			System.out.println("8");
			return false;
		}
	}
	public boolean param2(){
		if(ll.getFirst().getCateg() == Categories.prVoid){
			
			ll.removeFirst();
			if(ll.getFirst().getCateg() == Categories.fcPar){
				ll.removeFirst();
				return true;
			}else{
				System.out.println("9");
				return false;
			}
		}
		System.out.println(ll.getFirst().toString());
		System.out.println("10");
		return false;
	}
	public boolean scope(){
		if(ll.getFirst().getCateg() == Categories.abCh){
			ll.removeFirst();
			if(ll.getFirst().getCateg() == Categories.fcCh){
				return true;
			}else{
				System.out.println("11");
				return false;
			}
		}else{
			System.out.println("12");
			return false;
		}
	}
	
	
	
}


