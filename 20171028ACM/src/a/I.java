package a;

import java.util.Scanner;
import java.util.TreeMap;

public class I {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TreeMap<Double,String> map = new TreeMap<Double,String>();
		int n = scanner.nextInt();
		for(int i=0;i<n;i++){
			if(scanner.hasNextInt()){
				double r = scanner.nextDouble()/2;
				map.put(r, scanner.next());
			}else{
				String str = scanner.next();
				map.put(scanner.nextDouble(), str);	
			}
		}
		for(double t:map.keySet()){
			System.out.println(map.get(t));
		}

		scanner.close();
	}

}
