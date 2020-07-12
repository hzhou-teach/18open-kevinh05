//package usaco2018Open;
//50 min
//All test cases worked except for 7 which was wrong
//Difficulty 6/10
//I thought this one was pretty simple, all I needed to do was find the number in the array that was the most to the right of its position in the sorted array
//I took advantage or the sort and search methods in the java library to do this
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class sort
{

	public static void main(String[] args) throws IOException
	{
		Scanner in = new Scanner(new File("sort.in"));
		
		
		int N=in.nextInt();
		int[] A=new int [N];
		for(int i=0; i<N; i++) {
			A[i]=in.nextInt();
		}
		
		int[] sortedA=A.clone();
		Arrays.sort(sortedA);
		
		int mostOutOfPlace=0;
		int i=N-1;
		
		while(i>=0||!(mostOutOfPlace>i)) { //cycles if pointer is greater than zero and if the distance of the most misplaced integer to where it should be is greater than the index
			
			int indexInSortedA=Arrays.binarySearch(sortedA, A[i]);
			while((indexInSortedA<N-1)&&(sortedA[indexInSortedA]==sortedA[indexInSortedA+1])) { //if there are repeated elements in the sorted array make the index the right most
				indexInSortedA++;
			}
			
			if(i-indexInSortedA>mostOutOfPlace) {
				mostOutOfPlace=i-Arrays.binarySearch(sortedA, A[i]);
			}
			i--;
		}
		
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		pw.print(mostOutOfPlace+1);
		pw.close();
	}

}
