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
			System.out.println((char) 27 + "[31m"); // Makes text red (Only works in console)

			// Load Flag
			Flag swissFlag = new Flag("src/swiss_flag.txt", 1, 4, 0.1);
			while (true) {
				try {
					TimeUnit.MILLISECONDS.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(swissFlag);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
