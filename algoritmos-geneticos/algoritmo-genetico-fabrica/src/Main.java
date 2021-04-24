import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import calcado.Calcado;
import genetico.Individuo;
import genetico.Pais;
import genetico.Populacao;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Fábrica de calçados com algoritmo genético");

		Calcado calcado1 = new Calcado("Sandálias", 100.00, 20, 60.00);
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
				System.out.println("Qual a quantidade de indivíduos?");
				System.out.print("Resposta: ");
				qtdInd = scan.nextInt();
				scan.nextLine();
				if (qtdInd <= 1)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				qtdInd = 0;
				scan.nextLine();
			}
		} while (qtdInd <= 1);

		System.out.println();

		Individuo individuo = new Individuo();
		ArrayList<Individuo> individuos = individuo.criaIndividuos(qtdInd);
		// System.out.println("Lista de indivíduos: ");
		// for (Individuo i : individuos)
		// System.out.println(i);

		int tamPop;
		do {
			try {
				System.out.println("Qual o tamanho da população?");
				System.out.print("Resposta: ");
				tamPop = scan.nextInt();
				scan.nextLine();
				if (tamPop <= 1)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				tamPop = 0;
				scan.nextLine();
			}
		} while (tamPop <= 1);

		System.out.println();

		int numPopulacoes = 0;
		do {
			try {
				System.out.println("Quantas populações você terá até alcançar o resultado desejado?");
				System.out.print("Resposta: ");
				numPopulacoes = scan.nextInt();
				scan.nextLine();
				if (numPopulacoes < 1)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				numPopulacoes = 0;
				scan.nextLine();
			}
		} while (numPopulacoes < 1);

		System.out.println();

		Populacao populacao = new Populacao();
		populacao.criaPopulacao(individuos, tamPop);
		// System.out.println("População: ");
		// for (Individuo i : populacao.getIndividuos())
		// System.out.println(i);

		// calcula fitness dos indivíduos da população
		double custoMaximo = 50000.00;
		int tempoMaximo = 10000;
		// penalidade será igual a (60+80+90+50)*100
		// 28000.00
		double penalidade = 28000.00;
		populacao.calculaFitness(calcados, custoMaximo, tempoMaximo, penalidade);

		System.out.println();

		// roleta para encontrar os pais da nova população
		ArrayList<Individuo> pais = populacao.roleta();

		System.out.println();

		// faz reprodução e retorna arraylist para passar para população
		ArrayList<Individuo> filhos = Pais.reproduz(pais, tamPop);

		System.out.println();

		int qtdMutacoes = 0;
		if (tamPop < 100) {
			qtdMutacoes = 1;
		} else {
			qtdMutacoes = (int) (tamPop * 0.02);
		}

		// faz mutações
		filhos = populacao.mutacoes(filhos, qtdMutacoes);

		ArrayList<Individuo> novosIndividuos = new ArrayList<>(tamPop);
		novosIndividuos.addAll(pais);
		novosIndividuos.addAll(filhos);

		populacao.setIndividuos(novosIndividuos);

		System.out.println();

		for (int contPop = 1; contPop <= numPopulacoes; contPop++) {
			populacao.calculaFitness(calcados, custoMaximo, tempoMaximo, penalidade);
			// limpa arraylist de pais antes de descobrir os novos pais
			pais.clear();
			pais = populacao.roleta();
			// limpa arraylist de filhos antes de descobrir os novos filhos
			filhos.clear();
			filhos = Pais.reproduz(pais, tamPop);
			// mutações
			filhos = populacao.mutacoes(filhos, qtdMutacoes);
			novosIndividuos.clear();
			novosIndividuos.addAll(pais);
			novosIndividuos.addAll(filhos);
			populacao.setIndividuos(novosIndividuos);
		}

		System.out.println("Resultados finais: ");
		System.out.println(populacao);

		System.out.println("\n\nFim do programa");

	}
}
