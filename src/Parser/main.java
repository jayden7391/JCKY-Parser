package Parser;

import java.io.*;


public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
		String GrammerFileName = "src/IO+Files/grammar.txt";
		String InputFileName = "src/IO+Files/input.txt";
		
		Grammer CFG = new Grammer();
		
		CFG.InsertGrammer(GrammerFileName);
		
		BufferedReader in = new BufferedReader(new FileReader(InputFileName));
		String line = null;
		
		while((line=in.readLine())!=null){
			
			CKY ckyparser = new CKY(CFG);
			ckyparser.CKYStart(line);
			ckyparser.PrintParseTree();
		}
		
		
	}

}
