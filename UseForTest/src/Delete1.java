import java.util.*;

public class Delete1
{
	public static final int MAXF = 100;
	public static final int ICONROW = 15;
	public static final int ICONCOL = 9;

	public static int[] rowVals = new int[MAXF+1];
	public static int[] colVals = new int[MAXF+1];
	public static int nrows, ncols;

	public static int[][] grid = new int[MAXF][MAXF];
	public static int[][] wspace = new int[MAXF][MAXF];

	public static pos[] dfiles = new pos[MAXF];
	public static pos[] sfiles = new pos[MAXF];

	public static int insert(int val, int list[], int n)
	{
		int j=0;
		while (j < n) {
			if (list[j] < val)
				j++;
			else if (list[j] == val)
				return n;
			else
				break;
		}
		int tmp = list[j];
		list[j] = val;
		while (j < n) {
			j++;
			int tmp2 = list[j];
			list[j] = tmp;
			tmp = tmp2;
		}
		n++;
		return n;
	}

	public static void insertInGrid(pos p[], int npos, int val)
	{
		for(int k=0; k<npos; k++) {
			int i=0, j=0;
			while (rowVals[i] != p[k].r)
				i++;
			while (colVals[j] != p[k].c)
				j++;
			grid[i][j] = val;		// assumes no two files in same position
		}
	}

	public static int calcMoves(int startr, int startc, int ndel)
	//
	// calculate min number of moves for all rects with upper left at startr,startc
	//
	{
		for(int i=startr; i<nrows; i++)
			for(int j=startc; j<ncols; j++)
				wspace[i][j] = 0;
		wspace[startr][startc] = ndel + grid[startr][startc];
		int minMoves = wspace[startr][startc];
		for(int i=startr+1; i<nrows; i++) {
			int val = wspace[i-1][startc] + grid[i][startc];
			if (val < minMoves)
				minMoves = val;
			wspace[i][startc] = val;
		}
		for(int j=startc+1; j<ncols; j++) {
			int val = wspace[startr][j-1] + grid[startr][j];
			if (val < minMoves)
				minMoves = val;
			wspace[startr][j] = val;
		}
		for(int i=startr+1; i<nrows; i++) {
			for(int j=startc+1; j<ncols; j++) {
				int val = wspace[i-1][j] + wspace[i][j-1] - wspace[i-1][j-1] + grid[i][j];
				if (val < minMoves)
					minMoves = val;
				wspace[i][j] = val;
			}
		}
/*
cout << "Workspace " << startr << ',' << startc << endl;
for(int i=startr; i<nrows; i++) {
for(int j=startc; j<ncols; j++) {
cout << wspace[i][j] << ' ';
}
cout << endl;
}
/**/
		return minMoves;
	}

	public static void main(String [] args)
	{
		int maxr, maxc, ndel, nsav,bobs_adjustment=0;
		Scanner in = new Scanner(System.in);

		for(int i=0; i< MAXF; i++) {
			dfiles[i] = new pos();
			sfiles[i] = new pos();
		}

			maxr = in.nextInt();
			maxc = in.nextInt();
			ndel = in.nextInt();
			nsav = in.nextInt();
			nrows = ncols = 0;
			for(int i=0; i<ndel; i++) {
				dfiles[i].r = in.nextInt();
				dfiles[i].c = in.nextInt();
if (dfiles[i].r > maxr - 8 || dfiles[i].c > maxc - 4) {
  i--;
  ndel--;
  bobs_adjustment++;
  continue;
}
				nrows = insert(dfiles[i].r, rowVals, nrows);
				ncols = insert(dfiles[i].c, colVals, ncols);
			}
			for(int i=0; i<nsav; i++) {
				sfiles[i].r = in.nextInt();
				sfiles[i].c = in.nextInt();
				nrows = insert(sfiles[i].r, rowVals, nrows);
				ncols = insert(sfiles[i].c, colVals, ncols);
			}
			for(int i=0; i<nrows; i++)
				for(int j=0; j<ncols; j++)
					grid[i][j] = 0;
			insertInGrid(dfiles, ndel, -1);
			insertInGrid(sfiles, nsav, 1);
											// now go through all rects
			int minMoves = ndel+nsav;
			for(int i=0; i<nrows; i++) {
				for(int j=0; j<ncols; j++) {
					int moves = calcMoves(i, j, ndel);
					if (moves < minMoves)
						minMoves = moves;
				}
			}
			System.out.println(minMoves+bobs_adjustment);
	}
}

class pos
{
	public int r, c;
}
