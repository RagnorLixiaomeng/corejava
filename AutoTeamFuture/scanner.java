import java.io.Console;

public class scanner{
	public static void main(String[] args){
	
		Console cons = System.console();
		String username = cons.readLine("User name: ");
		char[] password = cons.readPassword("Password: ");	
	}
}

