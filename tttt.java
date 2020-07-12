//package usaco2018Open;
//90 min
//1,6,7,8,9 works, 2,3,4,5,10 don't work
//Difficulty 5/10
//At first I wrote the code with no problem allowing the first case to work, however after testing out the code and getting more than
//half of it wrong I realized that I missed a really important case all because I misread a sentence in the problem. So from now on I won't just read the problem once but carefully several times.
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class tttt
{

	public static void main(String[] args) throws IOException
	{
		Scanner in = new Scanner(new File("tttt.in"));
		
		String [][] board= new String[3][3];
		for(int i=0; i<3; i++) {
			String inputRow=in.next();
			for(int j=0; j<3; j++) {
				board[i][j]=inputRow.substring(j, j+1);
			}
		}
		
		//-----------------------------------------------------------------------------------------------------------------
		int individualWins=0;
		int teamWins=0;
		
		//-----------------------------------------------------------------------------------------------------------------
		//check rows
		for(int i=0; i<3; i++) {
			if(board[i][0].equals(board[i][1])||board[i][0].equals(board[i][2])||board[i][2].equals(board[i][1])) {

				if(board[i][0].equals(board[i][1])&&board[i][0].equals(board[i][2])) {
					individualWins++;
				}
				else {
					teamWins++;
				}
				
			}
		}
		//check columns
		for(int i=0; i<3; i++) {
			if(board[0][i].equals(board[1][i])||board[0][i].equals(board[2][i])||board[2][i].equals(board[1][i])) {
				if(board[0][i].equals(board[1][i])&&board[0][i].equals(board[2][i])) {
					individualWins++;
				}
				else {
					teamWins++;
				}	
			}
		}
		//check diagonal 1
		if(board[0][0].equals(board[1][1])||board[0][0].equals(board[2][2])||board[2][2].equals(board[1][1])) {
			if(board[0][0].equals(board[1][1])&&board[0][0].equals(board[2][2])) {
				individualWins++;
			}
			else {
				teamWins++;
			}	
		}
		//check diagonal 2
		if(board[0][2].equals(board[1][1])||board[0][2].equals(board[2][0])||board[2][0].equals(board[1][1])) {
			if(board[0][2].equals(board[1][1])&&board[0][2].equals(board[2][0])) {
				individualWins++;
			}
			else {
				teamWins++;
			}	
		}
		//-----------------------------------------------------------------------------------------------------------------------------------
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("tttt.out")));
		pw.println(individualWins);
		pw.println(teamWins);
		pw.close();

	}

}
