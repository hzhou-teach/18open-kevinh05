//package usaco2018Open;
//3 hours
//All 15 cases work except for 11 and 13
//Difficulty 9/10
//This problem was quite difficult and I have never had experience with this type of problem.
//In the end I figured out all I needed was to find the distance from each cow to the highest ancestor and if the two cows were driectly related, and print accordingly

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class family
{
	static int N;
	static String BESSIE;
	static String ELSIE;
	static String [][] relationships;
	
	public static String findMother(String kid) {
		String mother="";
		for(int i=0; i<N; i++) {
			if(relationships[i][1].equals(kid)) {
				mother=relationships[i][0];
			}
		}
		return mother;
	}

	public static void main(String[] args) throws IOException
	{
		Scanner in = new Scanner(new File("family.in"));
		N=in.nextInt();
		BESSIE=in.next();
		ELSIE=in.next();
		relationships=new String[N][2];
		for(int i=0; i<N; i++) {
			relationships[i][0]=in.next();
			relationships[i][1]=in.next();
		}
		
		//------------------------------------------------------------------------------------------------------
		//find highest ancestor
		String highestAncestor="";
		String mother=relationships[0][0];
		while(!mother.equals("")) {
			highestAncestor=mother;
			mother=findMother(mother);
		}
		
		//--------------------------------------------------------------------------------------------------------------
		boolean related=true;
		//Find distance to highest ancestor
		int BessieRelationshipToHighestAncestor=0;
		int ElsieRelationshipToHighestAncestor=0;
		mother=BESSIE;
		while(!mother.equals(highestAncestor)) {
			mother=findMother(mother);
			if(mother.equals("")) {
				related=false;
				break;
			}
			BessieRelationshipToHighestAncestor++;
		}
		mother=ELSIE;
		while(!mother.equals(highestAncestor)) {
			if(mother.equals("")) {
				related=false;
				break;
			}
			mother=findMother(mother);
			ElsieRelationshipToHighestAncestor++;
		}
	
		//----------------------------------------------------------------------------------------------------------------
		boolean directDescendant=false;
		if(BessieRelationshipToHighestAncestor<ElsieRelationshipToHighestAncestor) {
			mother=ELSIE;
			for(int i=0; i<ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor; i++) {
				mother=findMother(mother);
				if(mother.equals(BESSIE)) {
					directDescendant=true;
					break;
				}
			}
		}
		else if(BessieRelationshipToHighestAncestor>ElsieRelationshipToHighestAncestor) {
			mother=BESSIE;
			for(int i=0; i<BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor; i++) {
				mother=findMother(mother);
				if(mother.equals(ELSIE)) {
					directDescendant=true;
					break;
				}
			}
		}
		else if(BessieRelationshipToHighestAncestor==ElsieRelationshipToHighestAncestor) {
			if(findMother(BESSIE).equals(findMother(ELSIE))) {
				directDescendant=true;
			}
		}
		//-----------------------------------------------------------------------------------------------------------------
		//print possible options according to variables
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("family.out")));
		
		if(BessieRelationshipToHighestAncestor==ElsieRelationshipToHighestAncestor&&directDescendant) {
			pw.print("SIBLINGS");
		}
		else if(BessieRelationshipToHighestAncestor==ElsieRelationshipToHighestAncestor&&!directDescendant) {
			pw.print("COUSINS");
		}
		else if(!related) {
			pw.print("NOT RELATED");
		}
		else if(BessieRelationshipToHighestAncestor<ElsieRelationshipToHighestAncestor&&directDescendant) {
			String ancestor="mother";
			if(ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor==1) {
				pw.print(BESSIE+ " is the mother of "+ELSIE);
			}
			if(ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor==2) {
				pw.print(BESSIE+ " is the grand-mother of "+ELSIE);
			}
			if(ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor>1) {
				ancestor="grand-mother";
				for(int i=0; i<ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor-2; i++) {
					ancestor="great-"+ancestor;
				}
				pw.print(BESSIE+ " is the "+ancestor+" of "+ELSIE);
			}
		}
		else if(BessieRelationshipToHighestAncestor<ElsieRelationshipToHighestAncestor&&!directDescendant) {
			String ancestor="aunt";
			if(ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor==1) {
				pw.print(BESSIE+ " is the aunt of "+ELSIE);
			}
			if(ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor>1) {
				ancestor="great-aunt";
				for(int i=0; i<ElsieRelationshipToHighestAncestor-BessieRelationshipToHighestAncestor-2; i++) {
					ancestor="great-"+ancestor;
				}
				pw.print(BESSIE+ " is the "+ancestor+" of "+ELSIE);
			}
		}
		else if(BessieRelationshipToHighestAncestor>ElsieRelationshipToHighestAncestor&&directDescendant) {
			String ancestor="mother";
			if(BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor==1) {
				pw.print(ELSIE+ " is the mother of "+BESSIE);
			}
			if(BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor==2) {
				pw.print(ELSIE+ " is the grand-mother of "+BESSIE);
			}
			if(BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor>1) {
				ancestor="grand-mother";
				for(int i=0; i<BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor-2; i++) {
					ancestor="great-"+ancestor;
				}
				pw.print(ELSIE+ " is the "+ancestor+" of "+BESSIE);
			}
		}
		else if(BessieRelationshipToHighestAncestor>ElsieRelationshipToHighestAncestor&&!directDescendant) {
			String ancestor="aunt";
			if(BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor==1) {
				pw.print(ELSIE+ " is the aunt of "+BESSIE);
			}
			if(BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor>1) {
				ancestor="great-aunt";
				for(int i=0; i<BessieRelationshipToHighestAncestor-ElsieRelationshipToHighestAncestor-2; i++) {
					ancestor="great-"+ancestor;
				}
				pw.print(ELSIE+ " is the "+ancestor+" of "+BESSIE);
			}
		}
		pw.close();
		
		
		
		

	}

}
