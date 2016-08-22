package ic.ufal.br.cclonela;

import java.io.IOException;

public class Test 
{
	
    public static void main(String[] args) throws IOException {
      	
    	Analyzer lexicalAnalyzer = new Analyzer("C:\\Backup\\dev\\code\\cclonela\\teste.txt");
    	lexicalAnalyzer.readFile();
    	
    	Token token = lexicalAnalyzer.nextToken();    	
    	while(token != null){
    		System.out.println(token.toString());
        	token = lexicalAnalyzer.nextToken();
    	}
    	
    }

    	
}
