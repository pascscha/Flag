import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Loads a flag from a textfile and adds waves to it
 * 
 * @author Yannick Andreas Müller
 *
 */
public class Flag {

	// Stores the flag content
	ArrayList<String> flag;

	// The dimensions of the Flag
	int height;
	int width;

	// Animation parameters
	int amplitude;
	double frequency;
	double speed;
	int time = 0;

	/**
	 * Basic Constructor
	 * 
	 * @param fileName
	 *            The name of the file which stores the flag
	 * @param frequency
	 *            The frequency (in Waves per Flagwith)
	 * @param amplitude
	 *            The amplitude of the wave (in characters)
	 * @param speed
	 *            The speed of the wave
	 * @throws IOException
	 *             if the file does not exist, is empty or not rectangular
	 */
	public Flag(String fileName, double frequency, int amplitude, double speed) throws IOException {
		readFlagFile(fileName);
		this.amplitude = amplitude;
		this.frequency = frequency;
		this.speed = speed;
		this.time = 0;
	}

	// Getter & Setter Functions
	public void setAmplitude(int amplitude) {
		this.amplitude = amplitude;
	}

	public void setFrequency(double frequency) {
		this.frequency = frequency;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getAmplitude(int amplitude) {
		return this.amplitude;
	}

	public double getFrequency(double frequency) {
		return this.frequency;
	}

	public double getSpeed(double speed) {
		return this.speed;
	}

	/**
	 * Reads Flag from textfile
	 * 
	 * @param fileName
	 *            Filename of textfile
	 * @throws IOException
	 *             if the file does not exist, is empty or not rectangular
	 */
	void readFlagFile(String fileName) throws IOException {
		FileInputStream fis = new FileInputStream(fileName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

		flag = new ArrayList<String>();

		// Read in first Line and get width
		String firstLine = reader.readLine();
		if (firstLine != null) {
			width = firstLine.length();
		} else {
			reader.close();
			throw new IOException("File is empty!");
		}
		flag.add(firstLine);

		// Read in following Lines and check if it stays rectangular
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.length() == width) {
				flag.add(line);
			} else {
				reader.close();
				throw new IOException("Flag is not a rectangle! (Sorry Nepal)");
			}
		}
		reader.close();

		height = flag.size();
		width = flag.get(0).length();
	}

	/**
	 * Returns a String representation of the flag at the current time
	 */
	public String toString() {
		StringBuffer out = new StringBuffer();
		for (int i = -amplitude / 2; i < height + amplitude / 2; i++) {
			out.append(getLine(i));
			out.append("\n");
		}
		tick();
		return out.toString();
	}

	/**
	 * Advances Animation to next frame
	 */
	void tick() {
		this.time++;
	}

	/**
	 * Returns a single Line of the flag
	 * 
	 * @param line
	 *            The Index of the desired line
	 * @return The current Line with the wave effect applied to it
	 */
	String getLine(int line) {
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < width; i++) {
			int offset = (int) Math.round(Math.sin(this.speed * this.time + i * frequency / width * Math.PI * 2));
			out.append(getChar(i, line + offset));
		}
		return out.toString();
	}

	/**
	 * Returns the character of a specific Coordinate on the Flag. If its out of
	 * bounds this returns zero.
	 * 
	 * @param x
	 *            The x-coordinate of the Position
	 * @param y
	 *            The y-coordinate of the Position
	 * @return The character at the desired position.
	 */
	char getChar(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return this.flag.get(y).charAt(x);
		}
		return ' ';
	}

}
