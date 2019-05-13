package bot21;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PlayerBot {

	private String[][] strat = { {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, };

	// ponto no ecrã, x y
	private final static int[] insuranceButton = { 0, 0 };

	// x, y, width, height
	private final static Rectangle playerScore = new Rectangle(0, 0, 0, 0);

	private static DirtyWorker bot;

	public static void main(String[] args) throws AWTException, InterruptedException {
		/*
		 * 
		 * deixar a aposta feita e as primeiras cartas dadas;
		 * 
		 * a ideia será pôr um sleep no inicio para dar tempo de por a janela em
		 * fullscreen
		 * 
		 * 
		 * 
		 * clicar no sim do seguro;
		 * 
		 * ver se é par(tem o botao de split ativo), se for, esperar até eu jogar
		 * 
		 * 
		 * 
		 * 
		 * ver o score do dealer
		 * 
		 * 
		 * ver o score do player (este sempre que vem uma carta sobe a posiçao, tenho de
		 * aumentar o xy e saber se ja acabou (ver se tem o botao verde de "same bet"))
		 * 
		 * se tiver barra, tabela dos soft plays
		 * 
		 * se não, decidir a jogada com a tabela basic
		 * 
		 *
		 *
		 * 
		 */

		bot = new DirtyWorker();

		TimeUnit.SECONDS.sleep(4);

		// the fucking game
		while (true) {
			bot.click(insuranceButton);

			// wait for cards
			TimeUnit.SECONDS.sleep(2);

			if (isPair()) {
				pause();
				continue;
			}

		}

	}

	private static boolean isPair() {
		bot.getScreenshot(x, y, width, height)
	}

	private static void pause() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		scanner.close();
		TimeUnit.SECONDS.sleep(4);
	}
}