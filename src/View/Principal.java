package View;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

import Controller.ThreadCruzamento;

public class Principal {

	public static void main(String[] args) {
			
		String[] direcao = { "De origem norte para sul", "De origem leste para o oeste", "De origem oeste para o leste", "De origem sul para o norte" };
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for (int idcarro = 1; idcarro < 5; idcarro++ ) {
			Thread carros = new ThreadCruzamento(idcarro, semaforo, direcao[idcarro - 1]);
			carros.start();
		}

	}

}
