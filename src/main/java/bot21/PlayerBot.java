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

	private static Scanner scanner = new Scanner(System.in);;

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

		/*
		 * TODO: ligar o jogo, ver onde está o retangulo dos meus pontos
		 * 
		 * ha muitos momentos inesperados portanto: ~
		 * 
		 * - o isPair() tem de tentar umas vezes
		 * 
		 * - descobrir como vou ver se o botao "mesma aposta está up" e faze-lo sempre
		 * que carrego em algo
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
				pauseFor(4);
				continue;
			}

		}

	}

	private static boolean isPair() {
		bot.getScreenshot(x, y, width, height)
	}

	private static void pauseFor(int seconds) throws InterruptedException {
		scanner.nextLine();
		TimeUnit.SECONDS.sleep(seconds);
	}
}