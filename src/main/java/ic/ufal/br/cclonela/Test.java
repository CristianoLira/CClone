package ic.ufal.br.cclonela;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * Hello world!
 *
 */
public class Test 
{
    public static void main( String[] args )
    {
    	InputStream inputstream;
    	PushbackInputStream pis;
    	
        try {
               inputstream = new FileInputStream("C:\\Backup\\dev\\code\\cclonela\\teste.txt");

               char data = (char) inputstream.read();
               System.out.println(data);
               
               pis = new PushbackInputStream(inputstream);
               
               pis.unread((int) '(');
               
               System.out.println((char) pis.read());
               
               System.out.println((char) inputstream.read());
               
               inputstream.close();
        } catch (FileNotFoundException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
        } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
        }
    	
    }
}
