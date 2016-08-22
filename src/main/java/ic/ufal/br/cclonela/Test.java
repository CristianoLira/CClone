package ic.ufal.br.cclonela;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Test 
{
	
	public static List<String> readFile(String path) throws IOException{
		List<String> tokens = new ArrayList<>();
    	for (String line : Files.readAllLines(Paths.get(path))) {
    	    for (String token : line.split("\\s+")) {
    	        tokens.add(token);
    	    }
    	}
    	
    	return tokens;
	}
	
    public static void main(String[] args) throws IOException {
    	List<String> tokens = new ArrayList<>();
    	
    	tokens = readFile("C:\\Backup\\dev\\code\\cclonela\\teste.txt");
    	
    	for(int i = 0; i < tokens.size(); i++){
    		System.out.println("\"" + tokens.get(i) + "\"");
    	}
    	
    }

    	
}
