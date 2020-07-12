//package usaco2018Open;
// 1 hour
// 1,6,8 worked, the rest were either errors or incorrect
//Difficulty 8/10
//At first I thought this problem would be pretty simple but it turned out to be a pain debugging and I couldn't find the problem in the end
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class milkorder
{

	public static void main(String[] args) throws IOException
	{
		Scanner in = new Scanner(new File("milkorder.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));
		
		int N=in.nextInt();
		int M=in.nextInt();
		int K=in.nextInt();
		
		int[]socialOrder=new int[M];
		int[][]certainPositions=new int[K][2];
		
		for(int i=0; i<M; i++) {
			socialOrder[i]=in.nextInt();
		}
		
		for(int i=0; i<K; i++) {
			certainPositions[i][0]=in.nextInt();
			certainPositions[i][1]=in.nextInt();
		}
		
		//-------------------------------------------------------------------------------------------------
		ArrayList<Integer> milkingOrder=new ArrayList<Integer>();
		for(int i=0; i<N; i++) {
			milkingOrder.add(0);
		}
		
		//fill in certain positions
		for(int i=0; i<K; i++) {
			milkingOrder.set(certainPositions[i][1]-1, certainPositions[i][0]);
		}
		
		//fill in positions based on social hierarchy
		int previousCowPosition=0;
		for(int i=M-1; i>=0; i--) {
			
			
			if(milkingOrder.contains(socialOrder[i]))//if the certain cow is already in the array then skip to next cow 
			{
				previousCowPosition=milkingOrder.indexOf(socialOrder[i]);
				continue;
			}
			else 
			{
				for(int j=N-1;j>=0;j--) {
					if(milkingOrder.get(j).equals(0)&&i==M-1) {
						milkingOrder.set(j, socialOrder[i]);
						previousCowPosition=j;
						break;
					}
					if(milkingOrder.get(j).equals(0)&&j<previousCowPosition) {
						milkingOrder.set(j, socialOrder[i]);
						previousCowPosition=j;
						break;
					}
				}
			}
			
		}
		
		//check for the earliest empty time slot
		for(int i=0; i<N; i++) {
			if(milkingOrder.get(i).equals(0)) {
				pw.print(i+1);
				pw.close();
				break;
			}
		}

	}

}
