package genetico;

import java.util.ArrayList;
import java.util.Random;

import calcado.Calcado;
import util.LimitesDoIndividuo;

public class Populacao {
	private ArrayList<Individuo> individuos;

	public Populacao() {
		this.individuos = new ArrayList<>();
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

	public void calculaFitness(ArrayList<Calcado> calcados, double custoMaximo, int tempoMaximo, double penalidade) {
		System.out.println("Realiza calculo do fitness");
		/*
		 * caso custo, tempo e lucro dos indiv�duos estejam diferentes de 0, faz ficarem
		 * com 0
		 */
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			this.individuos.get(contInd).setCusto(0.0);
			this.individuos.get(contInd).setTempo(0);
			this.individuos.get(contInd).setLucro(0.0);
		}
		// soma custo, tempo e lucro de cada indiv�duo da popula��o
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			for (int contCrom = 0; contCrom < this.individuos.get(0).getCromossomo().size(); contCrom++) {
				double custoCalcadoAtual = calcados.get(contCrom).getCusto()
						* this.individuos.get(contInd).getCromossomo().get(contCrom);
				this.individuos.get(contInd).setCusto(this.individuos.get(contInd).getCusto() + custoCalcadoAtual);
				int tempoCalcadoAtual = calcados.get(contCrom).getTempo()
						* this.individuos.get(contInd).getCromossomo().get(contCrom);
				this.individuos.get(contInd).setTempo(this.individuos.get(contInd).getTempo() + tempoCalcadoAtual);
				double lucroCalcadoAtual = calcados.get(contCrom).getLucro()
						* this.individuos.get(contInd).getCromossomo().get(contCrom);
				this.individuos.get(contInd).setLucro(this.individuos.get(contInd).getLucro() + lucroCalcadoAtual);
			}
		}
		/*
		 * se indiv�duo exceder custo m�ximo, ou tempo m�ximo, ou ambos ao mesmo tempo,
		 * aplica penalidade
		 */
		conferePenalidadeDosIndividuos(custoMaximo, tempoMaximo, penalidade);
		System.out.println();
		System.out.println("Essa � a popula��o ap�s o calculo do fitness dos indiv�duos atuais: ");
		this.mostraIndividuosDaPopulacao();
	}

	public void conferePenalidadeDosIndividuos(double custoMaximo, int tempoMaximo, double penalidade) {
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			if ((this.individuos.get(contInd).getCusto() > custoMaximo)
					&& (this.individuos.get(contInd).getTempo() <= tempoMaximo)) {
				this.individuos.get(contInd).setLucro(this.individuos.get(contInd).getLucro() - penalidade);
			} else if ((this.individuos.get(contInd).getCusto() <= custoMaximo)
					&& (this.individuos.get(contInd).getTempo() > tempoMaximo)) {
				this.individuos.get(contInd).setLucro(this.individuos.get(contInd).getLucro() - penalidade);
			} else if ((this.individuos.get(contInd).getCusto() > custoMaximo)
					&& (this.individuos.get(contInd).getTempo() > tempoMaximo)) {
				this.individuos.get(contInd).setLucro(this.individuos.get(contInd).getLucro() - (penalidade * 2));
			}
		}
	}

	/*
	 * mostra cromossomo e lucro de cada indiv�duo da popula��o
	 */
	public void mostraIndividuosDaPopulacao() {
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			System.out.println(this.individuos.get(contInd));
			System.out.println("Custo: " + this.individuos.get(contInd).getCusto());
			System.out.println("Tempo: " + this.individuos.get(contInd).getTempo());
			System.out.println("Lucro: " + this.individuos.get(contInd).getLucro());
		}
	}

	/*
	 * m�todo que usa roleta para encontrar os pais que ser�o usados para gerar os
	 * novos filhos
	 */
	public ArrayList<Individuo> roleta() {
		System.out.println("Usa a roleta");
		double lucroMinimo = buscaLucroMinimoERemove();
		/*
		 * agora tendo o lucro m�nimo subtrai ele de todos os indiv�duos da popula��o
		 * atual
		 */
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			this.individuos.get(contInd).setLucro(this.individuos.get(contInd).getLucro() - lucroMinimo);
		}
		// soma os lucros novos e guarda em uma vari�vel
		int somaLucros = 0;
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			somaLucros += (int) (this.individuos.get(contInd).getLucro().doubleValue());
		}
		// cria limites de cada indiv�duo para serem inseridos na roleta
		LimitesDoIndividuo l = new LimitesDoIndividuo();
		ArrayList<LimitesDoIndividuo> limites = l.preencheLimites(this.individuos);
		// cria ArrayList para colocar os novos pais escolhidos pela roleta
		ArrayList<Individuo> pais = new ArrayList<>();
		Random random = new Random();
		int numAleat = random.nextInt(somaLucros); // escolhe o �ndice de um dos indiv�duos da roleta
		Individuo i = l.encontraIndividuo(limites, numAleat);
		pais.add(i);
		System.out.println("O indiv�duo referente a posi��o " + numAleat + " da roleta foi adicionado");
		do {
			numAleat = random.nextInt(somaLucros); // escolhe o �ndice de um dos indiv�duos da roleta
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
	 * busca indiv�duo com menor lucro, remove ele e retorna o seu lucro
	 */
	public double buscaLucroMinimoERemove() {
		double lucroMinimo = 10000000.00; // guarda o menor valor encontrado em um indiv�duo da popula��o
		int posIndComLucroMinimo = 0; // guarda a posi��o do indiv�duo com menor lucro
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			if (this.individuos.get(contInd).getLucro() < lucroMinimo) {
				lucroMinimo = this.individuos.get(contInd).getLucro();
				posIndComLucroMinimo = contInd;
			}
		}
		this.individuos.remove(posIndComLucroMinimo);
		return lucroMinimo;
	}

	public ArrayList<Individuo> mutacoes(ArrayList<Individuo> filhos, int qtdMut) {
		System.out.println("Muta��es");
		Random random = new Random();
		// escolhe uma posi��o aleat�ria de cromossomo, que ser� invertida com a posi��o
		// a sua direita
		int cromAleat = random.nextInt(filhos.get(0).getCromossomo().size());
		// escolhe um indiv�duo aleatoriamente
		int indAleat = random.nextInt(filhos.size());
		int contMut = 0;
		do {
			System.out.println("Indiv�duo antes da muta��o: " + filhos.get(indAleat));
			if (cromAleat != 3) {
				int vlAux = filhos.get(indAleat).getCromossomo().get(cromAleat).intValue();
				filhos.get(indAleat).getCromossomo().set(cromAleat,
						filhos.get(indAleat).getCromossomo().get(cromAleat + 1));
				filhos.get(indAleat).getCromossomo().set(cromAleat + 1, vlAux);
			} else {
				int vlAux = filhos.get(indAleat).getCromossomo().get(cromAleat).intValue();
				filhos.get(indAleat).getCromossomo().set(cromAleat, filhos.get(indAleat).getCromossomo().get(0));
				filhos.get(indAleat).getCromossomo().set(0, vlAux);
			}
			System.out.println("Indiv�duo depois da muta��o: " + filhos.get(indAleat));
			contMut++;
			if (contMut >= qtdMut)
				break;
			cromAleat = random.nextInt(filhos.get(0).getCromossomo().size());
			indAleat = random.nextInt(filhos.size());
		} while (true);
		return filhos;
	}

	@Override
	public String toString() {
		String pop = "";
		for (Individuo ind : this.individuos) {
			pop = pop.concat("" + ind.getCromossomo().get(0) + " - " + ind.getCromossomo().get(1) + " - "
					+ ind.getCromossomo().get(2) + " - " + ind.getCromossomo().get(3) + "\n");
		}
		return pop;
	}

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

}
