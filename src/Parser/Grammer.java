package Parser;
import java.util.*;
import java.io.*;

public class Grammer {

	public List RuleList = new ArrayList();
	
	
	public Grammer(){
		
	}
	public void AddRule(Rule r){
		RuleList.add(r);
	}
	
	public void InsertGrammer(String filename) throws IOException{
		
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String line = null;
		
		while((line=in.readLine())!=null){
			
			String[] tmp = line.split("->");
			if(tmp.length < 2)
				continue;
			Rule r = new Rule();
			r.MakeRule(tmp[0], tmp[1]);
			RuleList.add(r);
		}
		
		
	}
	
	public String FindRule(ArrayList<Integer> idx1, String s){
		
		String LR = "";
		int temp = 0;
		String[] AS = s.split(":");
		for(int j = 0; j<AS.length; j++){
			String s1 = AS[j];
			s1 = s1.split(">")[0];
			s1 = s1.replace(" ", "");
			for(int i = 0; i<RuleList.size(); i++){
				Rule tempR = (Rule) RuleList.get(i);
				
				if(s1.equals((String)tempR.RightSide.get(0))){
					
					System.out.print(tempR.LeftSide + "-> ");
					for(int k=0; k<tempR.RightSide.size(); k++)
						System.out.print(tempR.RightSide.get(k));
					System.out.println("");
					
					
					
					if(temp == 1)
						LR += ":";
					LR += (String)tempR.LeftSide + ">";
					for(int k=0; k<tempR.RightSide.size(); k++)
						LR += (String)tempR.RightSide.get(k) + "!" + String.valueOf(idx1.get(0)) + "," + String.valueOf(idx1.get(1)) + "!";
					temp = 1;
				}
				
			}
	
		}
		
		return LR;
		
	}
	
	public String FindRule2(ArrayList<Integer> idx1, ArrayList<Integer> idx2, String s1, String s2){
		String LR = "";
		
		int temp = 0;
		String st1 = s1.replaceAll(" ", "");
		String st2 = s2.replaceAll(" ", "");
		String[] s1A = st1.split(":");
		String[] s2A = st2.split(":");

			
			
		for(int k=0; k<s1A.length; k++){
			String s1s = s1A[k];
			s1s = s1s.split(">")[0];
			s1s = s1s.replace(" ", "");
			for(int j=0; j<s2A.length; j++){
				String s2s = s2A[j];
				s2s = s2s.split(">")[0];
				s2s = s2s.replace(" ", "");
				for(int i = 0; i<RuleList.size(); i++){
						
					Rule tempR = (Rule) RuleList.get(i);
					if(tempR.RightSide.size() < 2)
						continue;
					if(s1s.equals((String)tempR.RightSide.get(0)) &&
							s2s.equals((String)tempR.RightSide.get(1))){
						
						System.out.print(tempR.LeftSide + "-> ");
						
						for(int s=0; s<tempR.RightSide.size(); s++)
							System.out.print(tempR.RightSide.get(s) + " ");
						
						System.out.println("");
						
						
						if(temp == 1)
							LR += ":";
						LR += (String)tempR.LeftSide + ">";
						for(int c=0; c<tempR.RightSide.size(); c++){
							if(c == 0)
								LR += (String)tempR.RightSide.get(c) + "!" + String.valueOf(idx1.get(0)) + "," + String.valueOf(idx1.get(1)) + "!";
							else
								LR += "/" + (String)tempR.RightSide.get(c) + "!" + String.valueOf(idx2.get(0)) + "," + String.valueOf(idx2.get(1)) + "!";
						}
						
						temp = 1;
						
					}
					
				}
			}
			
			
			
		}
		if(LR.equals(""))
			LR = "-";
		return LR;
	}
	
}
