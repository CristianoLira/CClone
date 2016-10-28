package ic.ufal.br.cclonela;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class Test 
{
	
    public static void main(String[] args) throws IOException {
      	
    	Analyzer lexicalAnalyzer = new Analyzer("teste.txt");
    	lexicalAnalyzer.readFile();
    	LinkedList<Token> ll = new LinkedList<Token>();
    	Parser parser = new Parser(lexicalAnalyzer);
    	Token token = lexicalAnalyzer.nextToken();
    	
//    	while(token != null){
//    		ll.add(token);
//        	token = lexicalAnalyzer.nextToken();
//    	}
//    	if(parser.syntaxAnalisys()){
//    		System.out.println("Tudo ok");
//    	}else{
//    		System.out.println("erro");
//    	}
    	
    	parser.start();
    	
    }

    	
}
