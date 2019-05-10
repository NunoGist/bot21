package bot21;

import static org.bytedeco.leptonica.global.lept.pixDestroy;
import static org.bytedeco.leptonica.global.lept.pixRead;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.leptonica.PIX;
import org.bytedeco.tesseract.TessBaseAPI;

public class BasicExample {
	public static void main(String[] args) {
		// invertImage("C:\\Users\\Nuno Gomes\\Documents\\Lightshot\\Screenshot_1.png");
		reconImage(args);
	}

	private static void reconImage(String[] args) {
		BytePointer outText;

		TessBaseAPI api = new TessBaseAPI();
		// Initialize tesseract-ocr with English, without specifying tessdata path
		if (api.Init(null, "eng") != 0) {
			System.err.println("Could not initialize tesseract.");
			System.exit(1);
		}

		// Open input image with leptonica library
		PIX image = pixRead(args.length > 0 ? args[0] : "C:\\Users\\Nuno Gomes\\Documents\\Lightshot\\Screenshot_3.png");
		api.SetImage(image);
		// Get OCR result
		outText = api.GetUTF8Text();
		System.out.println("OCR output:\n" + outText.getString());

		// Destroy used object and release memory
		api.End();
		api.close();
		outText.deallocate();
		pixDestroy(image);
	}

	public static void invertImage(String imageName) {
		BufferedImage inputFile = null;
		try {
			inputFile = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int x = 0; x < inputFile.getWidth(); x++) {
			for (int y = 0; y < inputFile.getHeight(); y++) {
				int rgba = inputFile.getRGB(x, y);
				Color col = new Color(rgba, true);
				int MONO_THRESHOLD = 700;
				if (col.getRed() + col.getGreen() + col.getBlue() < MONO_THRESHOLD)
					col = new Color(0, 0, 0);
				// else
				// col = new Color(0, 0, 0);
				// col = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 -
				// col.getBlue());
				inputFile.setRGB(x, y, col.getRGB());
			}
		}

		try {
			File outputFile = new File("C:\\Users\\Nuno Gomes\\Documents\\Lightshot\\invert2.png");
			ImageIO.write(inputFile, "png", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}