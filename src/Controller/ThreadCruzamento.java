package Controller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;


public class ThreadCruzamento extends Thread {
	private int id;
	private Semaphore sem;
	private String direcao;
	double tempini, tempofim, tempotot;

	public ThreadCruzamento(int idcarro, Semaphore semaforo, String direcao) {

		this.id = idcarro;
		this.sem = semaforo;
		this.direcao = direcao;
	}

	public void run() {
		CarroAndando();
		try {
			sem.acquire();
			
			Carroparado();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sem.release();
		}
		cruzamento();

	}

	private void CarroAndando() {
		int tempo = 1000;
		int DistanciaPercorrida = 0;
		while (DistanciaPercorrida < 100) {
			DistanciaPercorrida += ((int) (Math.random() * 6) + 4);
			while (DistanciaPercorrida > 100) {
				if (DistanciaPercorrida > 100) {
					DistanciaPercorrida -= 1;
				}
				if (DistanciaPercorrida < 100) {
					try {
						Thread.sleep(tempo);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			}
			System.out.println("Carro " + id + " andou " + DistanciaPercorrida + " metros");
		}
	}

	public void Carroparado() {
		System.out.println("Carro " + id + " parou no semaforo");
		tempini = System.nanoTime();
	}

	public void cruzamento() {
		DecimalFormat df = new DecimalFormat("#.#");
		tempofim = System.nanoTime();
		tempotot = tempofim - tempini;
		tempotot = tempotot / Math.pow(10, 9);
		
		System.out.println("Carro " + id + " ficou aguardando no cruzamento: " + df.format(tempotot) + " segundos");
		System.out.println("Carro " + id + " atravesou o cruzamento na direção " + direcao);
	}
}
