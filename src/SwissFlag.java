import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Opens SwissFlag and displays animation created by the {@link FlagAnimation} class
 * 
 * @author Yannick Müller
 *
 */

public class SwissFlag {
	static int flag_size;
	public static void main(String[] args) {
		input_flag_size();
		create_flag();
		
		try {
			// Load Flag
			FlagAnimation swissFlag = new FlagAnimation("src/swiss_flag.txt", 1, 2, 0.1);
			while (true) {
				TimeUnit.MILLISECONDS.sleep(20);
				System.err.println(swissFlag);
			}
		} catch (IOException e) {
			System.err.println("Error while reading the file:");
			e.printStackTrace();
		} catch (InterruptedException e) {
			System.out.println("Good Bye :)");
		}
	}
	
	public static void input_flag_size() {
		flag_size = 5;
		Scanner console = new Scanner(System.in);
		System.out.println("What flag size would you like, enter an integer between 1 and 256");
		try {
			flag_size = console.nextInt();
		} catch (Exception e) {
			System.out.println("You are a very bad person. You will only get a little flag.");
		;}
		console.close();
		System.out.println("The SwissFlag with the size "+flag_size+" x "+flag_size+" will be created. Loading flag...");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			
		}
	}
	
	public static void create_flag() {
		ArrayList<String> SwissFlag;
		SwissFlag = new ArrayList<String>();
		String line;
		double under_cross_border = 0.2*flag_size;
		double upper_cross_border = 0.8*flag_size;
		double under_middle_cross_border = 0.4*flag_size;
		double upper_middle_cross_border = 0.6*flag_size;
		for(int i=1; i <= flag_size; i++) {
			line = "";
			for(int j=1; j <= flag_size; j++) {
				if(i>under_cross_border & i<=upper_cross_border & j>under_middle_cross_border & j<=upper_middle_cross_border) {
					line+=" ";
				} else if(j>under_cross_border & j<=upper_cross_border & i>under_middle_cross_border & i<=upper_middle_cross_border) {
					line+=" ";
				} else {
					if (j%4==1) {
						line+="E";
					} else if (j%4==2) {
						line+="T";
					} else if (j%4==3) {
						line+="H";
					} else {
						line+="Z";
					}	
				}
			}
			SwissFlag.add(line);
		}
		System.err.println(SwissFlag);
		try {
			TimeUnit.SECONDS.sleep(60);
		} catch (InterruptedException e) {
			
		}
	}
}
