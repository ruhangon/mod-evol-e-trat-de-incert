import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import calcado.Calcado;
import genetico.Individuo;
import genetico.Populacao;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("F�brica de cal�ados com algoritmo gen�tico");

		Calcado calcado1 = new Calcado("Sand�lias", 100.00, 20, 60.00);
		Calcado calcado2 = new Calcado("Sapatos masculinos", 120.00, 30, 80.00);
		Calcado calcado3 = new Calcado("Botas femininas", 150.00, 25, 90.00);
		Calcado calcado4 = new Calcado("Sapatos femininos", 120.00, 28, 50.00);
		ArrayList<Calcado> calcados = new ArrayList<>();
		calcados.add(calcado1);
		calcados.add(calcado2);
		calcados.add(calcado3);
		calcados.add(calcado4);

		System.out.println();

		int qtdInd;
		do {
			try {
				System.out.println("Qual a quantidade de indiv�duos?");
				System.out.print("Resposta: ");
				qtdInd = scan.nextInt();
				scan.nextLine();
				if (qtdInd <= 1)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				qtdInd = 0;
				scan.nextLine();
			}
		} while (qtdInd <= 1);

		System.out.println();

		Individuo individuo = new Individuo();
		ArrayList<Individuo> individuos = individuo.criaIndividuos(qtdInd);
		// System.out.println("Lista de indiv�duos: ");
		// for (Individuo i : individuos)
		// System.out.println(i);

		int tamPop;
		do {
			try {
				System.out.println("Qual o tamanho da popula��o?");
				System.out.print("Resposta: ");
				tamPop = scan.nextInt();
				scan.nextLine();
				if (tamPop <= 1)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				tamPop = 0;
				scan.nextLine();
			}
		} while (tamPop <= 1);

		System.out.println();

		Populacao populacao = new Populacao();
		populacao.criaPopulacao(individuos, tamPop);
		// System.out.println("Popula��o: ");
		// for (Individuo i : populacao.getIndividuos())
		// System.out.println(i);

		// calcula fitness dos indiv�duos da popula��o
		double custoMaximo = 50000.00;
		int tempoMaximo = 10000;
		// penalidade ser� igual a (60+80+90+50)*100
		// 28000.00
		double penalidade = 28000.00;
		populacao.calculaFitness(calcados, custoMaximo, tempoMaximo, penalidade);

		System.out.println();

		System.out.println("\n\nFim do programa");

	}
}
