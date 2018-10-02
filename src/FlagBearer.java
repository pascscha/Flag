import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Opens Swissflag and displays animation created by the {@link Flag} class
 * 
 * @author Yannick Andreas Müller
 *
 */
public class FlagBearer {

	public static void main(String[] args) {
		try {
			// Load Flag
			Flag swissFlag = new Flag("src/swiss_flag.txt", 1, 4, 0.1);
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

}
