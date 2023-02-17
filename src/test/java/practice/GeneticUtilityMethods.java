package practice;

public class GeneticUtilityMethods {

	public static void main(String[] args) {   //caller function
       // int sum = add(5, 8);
       // System.out.println(sum);
        int multiplication = sum(5, 6);
        System.out.println(multiplication);
		
	}
	public  static int add(int a, int b) {   // called function
		int c = a+b;
		System.out.println(c);
		return c;
	}
	public static int sum(int a, int b) {
		int c = a*b;
		System.out.println(c);
		return c;
	}

}
