import java.util.*;
import java.io.*;

public class Trays1
{
	public static final int MAXCOL = 30;

	public static boolean[][] ok = new boolean[3][MAXCOL+1];
	
	public static long[][] table = new long[7][MAXCOL+1];
	//
	// row 0: x, row1: ., row2: ., row3: x, row4: x, row5: x, row6: .
	// 		  x		   .		x		 .		  x		   .		x
	// 		  x		   x		.		 .		  .		   x		x
	
	public static void initTable()
	{
		table[0][0] = 1;
		for(int i=1; i<=6; i++)
			table[i][0] = 0;
		for(int r=0; r<3; r++)
			ok[r][0] = false;
		for(int c=1; c<=MAXCOL; c++)
			for(int r=0; r<3; r++)
				ok[r][c] = true;
	}
	
	public static void fillTable(int cols)
	{
		for(int c=1; c<=cols; c++) {
			boolean horz0 = ok[0][c] && ok[0][c-1];
			boolean horz1 = ok[1][c] && ok[1][c-1];
			boolean horz2 = ok[2][c] && ok[2][c-1];
			table[0][c] = table[0][c-1];
			if (ok[0][c] && ok[1][c])
				table[0][c] += table[0][c-1];
			if (ok[1][c] && ok[2][c])
				table[0][c] += table[0][c-1];
			if (horz0 && horz1)
				table[0][c] += table[1][c-1];
			if (horz0 && horz2)
				table[0][c] += table[2][c-1];
			if (horz1 && horz2)
				table[0][c] += table[3][c-1];
			if (horz0) {
				table[0][c] += table[6][c-1];
				if (ok[1][c] && ok[2][c])
					table[0][c] += table[6][c-1];
			}
			if (horz1) {
				table[0][c] += table[5][c-1];
			}
			if (horz2) {
				table[0][c] += table[4][c-1];
				if (ok[0][c] && ok[1][c])
					table[0][c] += table[4][c-1];
			}
			if (horz0 && horz1 && horz2)
				table[0][c] += table[0][c-2];
	
			for(int i=1; i<=6; i++)
				table[i][c] = table[0][c-1];
			if (ok[2][c])
				table[1][c] = table[0][c-1];
			if (ok[1][c])
				table[2][c] = table[0][c-1];
			if (ok[0][c])
				table[3][c] = table[0][c-1];
			if (horz2)
				table[1][c] += table[4][c-1];
			if (horz1)
				table[2][c] += table[5][c-1];
			if (horz0)
				table[3][c] += table[6][c-1];
	
			if (ok[0][c] && ok[1][c])
				table[4][c] = table[0][c-1];
			if (ok[0][c] && ok[2][c])
				table[5][c] = table[0][c-1];
			if (ok[1][c] && ok[2][c])
				table[6][c] = table[0][c-1];
			if (ok[0][c] && ok[1][c])
				table[4][c] += table[0][c-1];
			if (horz0)
				table[4][c] += table[6][c-1];
			if (horz1)
				table[4][c] += table[5][c-1];
			if (horz0 && horz1)
				table[4][c] += table[1][c-1];
			if (horz0)
				table[5][c] += table[6][c-1];
			if (horz2)
				table[5][c] += table[4][c-1];
			if (horz0 && horz2)
				table[5][c] += table[2][c-1];
			if (ok[1][c] && ok[2][c])
				table[6][c] += table[0][c-1];
			if (horz1)
				table[6][c] += table[5][c-1];
			if (horz2)
				table[6][c] += table[4][c-1];
			if (horz1 && horz2)
				table[6][c] += table[3][c-1];
		}
	}
	
	public static void main(String [] args) throws IOException
	{
		Scanner in = new Scanner(System.in);
		int cols, n;
		cols = in.nextInt();
		n = in.nextInt();
			initTable();
			int count = 0;
			for(int i=0; i<n; i++) {
				double x, y;
				x = in.nextDouble();
				y = in.nextDouble();
				if (ok[(int)y][(int)x+1]) {
					ok[(int)y][(int)x+1] = false;
					count++;
				}
			}
			if (count == cols*3)
				System.out.println(0);
			else {
				fillTable(cols);
				System.out.println(table[0][cols]);
			}
	}
}
