import java.util.*;

public class BGrille1 {
  public static int n;
  public static String en,de,grille;
  public static Scanner in;
  public static int count[];

  public static void main(String[] args) {
    in = new Scanner(System.in);
      n = in.nextInt();
      count = new int[n*n];
      Arrays.fill(count,0);
      grille = "";
      for (int i = 0; i < n; i++)
        grille += in.next();
      en = in.next();
      process(); 
  }

  public static void process() {
    char de1[] = new char[n*n];
    int total = 0;
    boolean invalid = false;
    for (int k = 0; k < 4; k++) {
      rotate();
      String segment = en.substring(n*(3-k)*n/4,n*n*(4-k)/4);
      int j = 0;
      for (int i = 0; i < n*n; i++) {
        if (grille.charAt(i) == '.') {
//          if (j < n*n)
          if (j < segment.length())
            de1[i] = segment.charAt(j++);
          if (count[i] > 0) {
            invalid = true;
            break;
          }
          count[i]++;
          total++;
        }
      }
    }
    if (invalid || total != n*n) {
      System.out.println("invalid grille");
    }
    else {
      de = "";
      for (int i = 0; i < de1.length; i++)
        de += de1[i];
      System.out.println(de);
    }
  }

  public static void rotate() {
    String temp = "";
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        char c = grille.charAt(col*n + n-1-row);
        temp += c;
      }
    }
    grille = temp;
  }
}
