package bot21;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;

public class DirtyWorker {

	private static final String TESSERACT_DATA_FILES_PATH = "C:\\Users\\Nuno Gomes\\Downloads\\OCR";
	private static final String INVERTED_IMAGE_PATH = "C:\\Users\\Nuno Gomes\\Documents\\invert2.png";
	private Robot robot;
	private TessBaseAPI tesseract;

	public DirtyWorker() throws AWTException {
		robot = new Robot();
		tesseract = new TessBaseAPI();

		// Initialize tesseract-ocr with English, specifying tessdata path
		if (tesseract.Init(TESSERACT_DATA_FILES_PATH, "eng") != 0) {
			System.err.println("Could not initialize tesseract.");
			System.exit(1);
		}
	}

	public BufferedImage getScreenshot(Rectangle area) throws AWTException {
		BufferedImage screenshot = robot.createScreenCapture(area);
		return screenshot;
	}

	// for each px, if its color is less than "almost white", paint it black
	private void invertImage(BufferedImage image) {
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int rgba = image.getRGB(x, y);
				Color col = new Color(rgba, true);
				int MONO_THRESHOLD = 700;
				if (col.getRed() + col.getGreen() + col.getBlue() < MONO_THRESHOLD)
					col = new Color(0, 0, 0);
				// else
				// col = new Color(0, 0, 0);
				// col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 -
				// col.getBlue());
				image.setRGB(x, y, col.getRGB());
			}
		}
		try {
			File outputFile = new File(INVERTED_IMAGE_PATH);
			ImageIO.write(image, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String reconImage(BufferedImage image) {

		// make the screenshot monocoloured
		invertImage(image);

		// Open input image with leptonica library
		// "C:\\Users\\Nuno Gomes\\Documents\\Lightshot\\Screenshot_3.png"
		PIX pixImage = pixRead(INVERTED_IMAGE_PATH);
		tesseract.SetImage(pixImage);

		// Get OCR result
		BytePointer outText = tesseract.GetUTF8Text();
		String score = outText.getString();
		System.out.println("OCR output:\n" + score);

		// Destroy used object and release memory
		outText.deallocate();
		pixDestroy(pixImage);

		return score;
	}

	public void click(int[] button) throws AWTException {
		robot.mouseMove(button[0], button[1]);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
}