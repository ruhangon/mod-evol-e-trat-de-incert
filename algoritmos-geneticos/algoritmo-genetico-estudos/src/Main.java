import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import genetico.Individuo;
import genetico.Pais;
import genetico.Populacao;
import item.Item;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Teste de algoritmo evolucion�rio");
		System.out.println();

		// perguntar para usu�rio quantos itens tem dispon�vel
		int qtdItens;
		do {
			try {
				System.out.println("Quantos itens h� dispon�vel?");
				System.out.print("Resposta: ");
				qtdItens = scan.nextInt();
				scan.nextLine();
				if (qtdItens <= 0)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				qtdItens = 0;
				scan.nextLine();
			}
		} while (qtdItens <= 0);

		System.out.println();

		/*
		 * cadastro de itens. Pergunta nome, valor e peso. O nome serve para facilitar a
		 * identifica��o do item
		 */
		Item item = new Item();
		ArrayList<Item> itens = item.cadastraItens(scan, qtdItens);

		System.out.println();

		// pede para o usu�rio qual ser� capacidade da mochila
		int capMochila;
		do {
			try {
				System.out.println("Qual a capacidade da mochila, o peso m�ximo que ela suporta?");
				System.out.print("Resposta: ");
				capMochila = scan.nextInt();
				scan.nextLine();
				if (capMochila <= 0)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				capMochila = 0;
				scan.nextLine();
			}
		} while (capMochila <= 0);

		System.out.println();

		// int qtdItens = 4;
		// Item item1 = new Item("taco", 1, 10);
		// Item item2 = new Item("bola", 2, 20);
		// Item item3 = new Item("raquete", 3, 30);
		// Item item4 = new Item("rob�", 4, 40);
		// ArrayList<Item> itens = new ArrayList<>(4);
		// itens.add(item1);
		// itens.add(item2);
		// itens.add(item3);
		// itens.add(item4);

		// cria os indiv�duos
		Individuo ind = new Individuo(qtdItens);
		ArrayList<Individuo> individuos = ind.criaIndividuos(qtdItens);

		// pergunta a usu�rio tamanho da popula��o
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

		Populacao populacao = new Populacao(tamPop);

		// cria a popula��o com os indiv�duos
		populacao.criaPopulacao(individuos, tamPop);

		// calcula fitness dos indiv�duos da popula��o
		populacao.calculaFitness(itens, capMochila, 150);

		// mostra os indiv�duos da popula��o ap�s calculo do fitness
		populacao.mostraIndividuosDaPopulacao();

		System.out.println();

		// roda a roleta para selecionar os pais
		ArrayList<Individuo> pais = populacao.roleta();

		System.out.println();

		// faz reprodu��o e retorna arraylist para passar para popula��o
		ArrayList<Individuo> filhos = Pais.reproduz(pais, tamPop);

		System.out.println();

		int qtdMutacoes = 0;
		if (tamPop < 100) {
			qtdMutacoes = 1;
		} else {
			qtdMutacoes = (int) (tamPop * 0.02);
		}

		// faz muta��es
		filhos = populacao.mutacoes(filhos, qtdMutacoes);

		ArrayList<Individuo> novosIndividuos = new ArrayList<>(tamPop);
		novosIndividuos.addAll(pais);
		novosIndividuos.addAll(filhos);

		populacao.setIndividuos(novosIndividuos);

		System.out.println();

		// System.out.println("Ap�s fazer crossover e muta��o");
		// populacao.mostraIndividuosDaPopulacao();

		/*
		 * passa por novas popula��es, crit�rio de parada � n�mero de popula��es passado
		 * pelo usu�rio
		 */
		int numPopulacoes = 0;
		do {
			try {
				System.out.println("Quantas popula��es voc� ter� at� alcan�ar o resultado desejado?");
				System.out.println("Observa��o = Uma popula��o obrigat�riamente j� passou por crossover e muta��o");
				System.out.print("Resposta: ");
				numPopulacoes = scan.nextInt();
				scan.nextLine();
				if (numPopulacoes < 1)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				numPopulacoes = 0;
				scan.nextLine();
			}
		} while (numPopulacoes < 1);

		System.out.println();

		int totalPopulacoes = numPopulacoes + 1;

		for (int contPop = 1; contPop <= numPopulacoes; contPop++) {
			populacao.calculaFitness(itens, capMochila, 150);
			// limpa arraylist de pais antes de descobrir os novos pais
			pais.clear();
			pais = populacao.roleta();
			// limpa arraylist de filhos antes de descobrir os novos filhos
			filhos.clear();
			filhos = Pais.reproduz(pais, tamPop);
			// muta��es
			filhos = populacao.mutacoes(filhos, qtdMutacoes);
			novosIndividuos.clear();
			novosIndividuos.addAll(pais);
			novosIndividuos.addAll(filhos);
			populacao.setIndividuos(novosIndividuos);
		}

		System.out.println();

		System.out.println("Total de popula��es do programa: " + totalPopulacoes);

		System.out.println();

		System.out.println("Abaixo est�o os indiv�duos selecionados na �ltima popula��o");
		populacao.mostraIndividuosDaPopulacao();

		System.out.println("\n\nFim do programa");

	}
}
