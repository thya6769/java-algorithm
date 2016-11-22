import java.util.Scanner;

public class A3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int l;
		int h;
		
		int maxL = scan.nextInt();
		int maxH = scan.nextInt();
		int maxNoJob = 0;
		for(int i = 1; i < n; i++){
			l = scan.nextInt();
			h = scan.nextInt();
			int temp = Math.max(maxL, maxH);
			
			maxH = maxNoJob + h;
			maxNoJob = temp;
			maxL = temp + l;
//			System.out.println(maxL + " " + maxH + " " + maxNoJob);
		}
		System.out.println(Math.max(maxH, maxL));
		
		
	}
}
