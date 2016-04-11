package Parser;
import java.util.*;
public class Rule {

	public String LeftSide;
	public List RightSide = new ArrayList();
	
	
	public Rule(){
		
	}
	
	public void MakeRule(String left, String right){
		
		LeftSide = left;
		String tmp1 = right.trim();
		String[] tmp = tmp1.split(" ");
		
		for(int i=0; i<tmp.length; i++){
			if(tmp[i] == "")
				continue;
			RightSide.add(tmp[i]);
		}
		
	}
	
	
}
