import java.util.Arrays;

public class Ma478 {

	public static void main(String[] args) {
		double[] temp=new double[37];
		for (int i = 1; i < 38; i++) {
			temp[i-1]=Math.pow(13, i) % 37;
//			System.out.println(Math.pow(18, i) % 37);
		}
		Arrays.sort(temp);
		for (int i = 0; i < 37; i++) {
			System.out.println(temp[i]);
		}
	}

}
