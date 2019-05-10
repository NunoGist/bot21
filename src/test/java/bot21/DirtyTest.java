package bot21;

import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

public class DirtyTest {

	public static void main(String[] args) {
		try {
			DirtyWorker worker = new DirtyWorker();

			TimeUnit.SECONDS.sleep(3);

			// worker.click(3267, 417);
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		}

	}
}
