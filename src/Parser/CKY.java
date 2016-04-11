package Parser;
import java.util.*;
import java.io.*;

public class CKY {

	public Grammer G;
	public int len = 0;
	public String[][] Table;
	
	public CKY(){
		
	}
	
	public CKY(Grammer g){
		
		G = g;
		
	}
	
	public void PrintBack(String s){
		
		
		if(s.contains(">")){
		
			String sL = s.split(">")[0];
			System.out.print(sL + "(");
			String sR = s.split(">")[1];
			String[] sRA = sR.split("/");
		
			for(int i=0; i<sRA.length; i++){
				
				String s1 = sRA[i];
				String sR1 = s1.split("!")[0];
				String sR2 = s1.split("!")[1];
				String nextloc = sR2.replace("!", "");
				nextloc = nextloc.replace("!", "");
				String[] locS = nextloc.split(",");
				PrintBack(Table[Integer.parseInt(locS[0])][Integer.parseInt(locS[1])]);
				
				System.out.print(" ");
			}
			System.out.print(")");
		}
		
		else{
			System.out.print(s);

			//System.out.print(")");
		}
		
		
		
	}
	
	public void PrintParseTree(){
		
		String s = Table[1][len-1];
		
		String[] sA = s.split(":");
		for(int i=0; i<sA.length; i++){
			
			String s1 = sA[i];
			s1.replace(" ", "");
			
			String s1Check = s1.split(">")[0];
			if(s1Check.equals("S")){
				PrintBack(s1);
			}
			
			System.out.println();
		}
		
		
	}
	
	public void CKYStart(String s){
		
		s = s.replace(".","");
		s.trim();
		String tmp[];
		tmp = s.split(" ");
		
		len = tmp.length;
		Table = new String[len+1][len];
		
		for(int i=0; i<len; i++){
			Table[0][i] = tmp[i];
		}
		
		for(int i=0; i<len; i++){
			ArrayList<Integer> id = new ArrayList<Integer>();
			id.add(0);
			id.add(i);
			Table[i+1][i] = G.FindRule(id, Table[0][i]);
			id.clear();
			id.add(i+1);
			id.add(i);
			String result = G.FindRule(id, Table[i+1][i]);
			Table[i+1][i] += ":"+result;
		}
		
		for(int i=0; i<len-1; i++){
			for(int k = 1; k<len-i; k++){
			
//				구하고자 하는 것은 Table[k][k+i]
			
				String temp1 = Table[k][k-1];
				String temp2 = Table[k+1][k+i];
				String temp3 = Table[k][k];
				String temp4 = "";
				
				if(k < len-1)
					temp4 = Table[k+2][k+i];

				
				if(temp3 == null)
					temp3 = "";
				if(temp4 == null)
					temp4 = "";
				
				temp1 = temp1.replaceAll(" ","");
				temp2 = temp2.replaceAll(" ","");
				temp3 = temp3.replaceAll(" ","");
				temp4 = temp4.replaceAll(" ","");
				
				String[] temp1A = temp1.split(":");
				String[] temp2A = temp2.split(":");
				String[] temp3A = temp3.split(":");
				String[] temp4A = temp4.split(":");
				
				ArrayList<String>Input = new ArrayList<String>();
				String result = "";
				int check = 0;
				
				ArrayList<Integer> id1 = new ArrayList<Integer>();
				id1.add(k);
				id1.add(k-1);
				ArrayList<Integer> id2 = new ArrayList<Integer>();
				id2.add(k+1);
				id2.add(k+i);
				
				for(int a=0; a<temp1A.length; a++){
					for(int b=0; b<temp2A.length; b++){
						if(check == 1)
							result += ":";
						
						result += G.FindRule2(id1, id2, temp1A[a],temp2A[b]);
						if(check == 0)
							check = 1;
							
					}
				}
				if(temp3 != "" && temp4 != ""){
					id1.clear();
					id2.clear();
					id1.add(k);
					id1.add(k);
					id2.add(k+2);
					id2.add(k+i);
					for(int a=0; a<temp3A.length; a++){
						for(int b=0; b<temp4A.length; b++){
							if(check == 1)
								result += ":";
							
							result += G.FindRule2(id1, id2, temp3A[a],temp4A[b]);
							if(check == 0)
								check = 1;
								
						}
					}
				}
				result = result.replaceAll(" ", "");
				String[] StringTemp = result.split(":");
				String finalR = "";
				int co = 0;
				int count = 0;
				for(int p = 0; p<StringTemp.length; p++){
					
					if(StringTemp[p].equals("-"))
						count ++;
					else{
						if(co == 0)
							co = 1;
						else
							finalR += ":";
						
						if(!finalR.contains(StringTemp[p]))
							finalR += StringTemp[p];
					}
				}
				
				
				
				if(co == 0)
					finalR = "-";
				
				
				Table[k][k+i] = finalR;
			}
		}

		
	}
	
}
