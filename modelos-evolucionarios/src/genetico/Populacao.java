package genetico;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import item.Item;
import util.LimitesDoIndividuo;

public class Populacao {
	private ArrayList<Individuo> individuos;

	public Populacao(int tamPop) {
		this.individuos = new ArrayList<>(tamPop);
	}

	public void criaPopulacao(ArrayList<Individuo> todosOsIndividuos, int tamPop) {
		Random random = new Random();
		/*
		 * pega um n�mero aleat�rio que � a posi��o de um indiv�duo na arraylist passada
		 */
		int numAleat = random.nextInt(todosOsIndividuos.size());
		this.individuos.add(todosOsIndividuos.get(numAleat));
		do {
			numAleat = random.nextInt(todosOsIndividuos.size());
			if (!Individuo.existeNaPopulacao(todosOsIndividuos.get(numAleat), this.individuos))
				this.individuos.add(todosOsIndividuos.get(numAleat));
		} while (this.individuos.size() < tamPop);
	}

	public void calculaFitness(ArrayList<Item> itens, int capMaxima, int penalidade) {
		// caso peso e valor dos indiv�duos estejam diferentes de 0, faz ficarem com 0
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			this.individuos.get(contInd).setPeso(0);
			this.individuos.get(contInd).setValor(0);
		}
		// soma peso e valor de cada indiv�duo da popula��o
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			for (int contCrom = 0; contCrom < this.individuos.get(0).getCromossomo().size(); contCrom++) {
				if (this.individuos.get(contInd).getCromossomo().get(contCrom) == 1) {
					this.individuos.get(contInd)
							.setPeso(this.individuos.get(contInd).getPeso() + itens.get(contCrom).getPeso());
					this.individuos.get(contInd)
							.setValor(this.individuos.get(contInd).getValor() + itens.get(contCrom).getValor());
				}
			}
		}
		// se peso do indiv�duo exceder capacidade m�xima, aplica penalidade
		conferePenalidadeDosIndividuos(capMaxima, penalidade);
	}

	public void conferePenalidadeDosIndividuos(int capMaxima, int penalidade) {
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			if (this.individuos.get(contInd).getPeso() > capMaxima)
				this.individuos.get(contInd).setValor(this.individuos.get(contInd).getValor() - penalidade);
		}
	}

	/*
	 * mostra cromossomo e valor de cada indiv�duo da popula��o
	 */
	public void mostraIndividuosDaPopulacao() {
		System.out.println("Esses s�o os indiv�duos da popula��o");
		int contInd = 0;
		do {
			for (int contCrom = 0; contCrom < this.individuos.get(0).getCromossomo().size(); contCrom++) {
				System.out.print(this.individuos.get(contInd).getCromossomo().get(contCrom));
			}
			System.out.println();
			System.out.println("Valor: " + this.individuos.get(contInd).getValor());
			contInd++;
		} while (contInd < this.individuos.size());
	}

	/*
	 * m�todo que usa roleta para encontrar os pais que ser�o usados para gerar os
	 * novos filhos
	 */
	public ArrayList<Individuo> roleta() {
		System.out.println("Usa a roleta");
		int valorMinimo = buscaValorMinimoERemove();
		/*
		 * agora tendo o valor m�nimo subtrai ele de todos os indiv�duos da popula��o
		 * atual
		 */
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			this.individuos.get(contInd).setValor(this.individuos.get(contInd).getValor() - valorMinimo);
		}
		// soma os valores novos e guarda em uma vari�vel
		int somaValores = 0;
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			somaValores += this.individuos.get(contInd).getValor();
		}
		// cria limites de cada indiv�duo para serem inseridos na roleta
		LimitesDoIndividuo l = new LimitesDoIndividuo();
		ArrayList<LimitesDoIndividuo> limites = l.preencheLimites(this.individuos);
		// cria ArrayList para colocar os novos pais escolhidos pela roleta
		ArrayList<Individuo> pais = new ArrayList<>();
		Random random = new Random();
		int numAleat = random.nextInt(somaValores); // escolhe o �ndice de um dos indiv�duos da roleta
		Individuo i = l.encontraIndividuo(limites, numAleat);
		pais.add(i);
		System.out.println("O indiv�duo referente a posi��o " + numAleat + " da roleta foi adicionado");
		do {
			numAleat = random.nextInt(somaValores); // escolhe o �ndice de um dos indiv�duos da roleta
			i = l.encontraIndividuo(limites, numAleat);
			if (!Individuo.existeNaPopulacao(i, pais)) {
				pais.add(i);
				System.out.println("O indiv�duo referente a posi��o " + numAleat + " da roleta foi adicionado");
			}
		} while (pais.size() < (this.individuos.size() / 2));
		System.out.println("A roleta selecionou " + pais.size() + " pais para a popula��o");
		return pais;
	}

	/*
	 * busca indiv�duo com menor valor, remove ele e retorna o seu valor
	 */
	public int buscaValorMinimoERemove() {
		int valorMinimo = 1000000; // guarda o menor valor encontrado em um indiv�duo da popula��o
		int posIndComValorMinimo = 0; // guarda a posi��o do indiv�duo com menor valor
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			if (this.individuos.get(contInd).getValor() < valorMinimo) {
				valorMinimo = this.individuos.get(contInd).getValor();
				posIndComValorMinimo = contInd;
			}
		}
		this.individuos.remove(posIndComValorMinimo);
		return valorMinimo;
	}

	public void insereMutacoes(int qtdMut) {
		System.out.println("Muta��o");
		Random random = new Random();
		// escolhe uma posi��o aleat�ria de cromossomo para alterar
		int cromAleat = random.nextInt(this.individuos.get(0).getCromossomo().size());
		// escolhe um indiv�duo aleatoriamente
		int indAleat = random.nextInt(this.individuos.size());
		Individuo novoIndividuo = null;
		int contMut = 0;
		do {
			novoIndividuo = new Individuo(this.individuos.get(indAleat));
			// System.out.println(novoIndividuo);
			if (novoIndividuo.getCromossomo().get(cromAleat) == 1) {
				novoIndividuo.getCromossomo().set(cromAleat, 0);
			} else {
				novoIndividuo.getCromossomo().set(cromAleat, 1);
			}
			// System.out.println(this.individuos.get(indAleat));
			// System.out.println(novoIndividuo);
			if (!Individuo.existeNaPopulacao(novoIndividuo, this.individuos)) {
				this.individuos.add(novoIndividuo);
				System.out.println("Indiv�duo adicionado: " + novoIndividuo);
				contMut++;
				if (contMut >= qtdMut)
					break;
			}
			cromAleat = random.nextInt(this.individuos.get(0).getCromossomo().size());
			indAleat = random.nextInt(this.individuos.size());
		} while (true);
	}

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

}
