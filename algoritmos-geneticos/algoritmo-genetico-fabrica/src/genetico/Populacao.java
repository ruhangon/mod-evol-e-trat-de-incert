package genetico;

import java.util.ArrayList;
import java.util.Random;

import calcado.Calcado;

public class Populacao {
	private ArrayList<Individuo> individuos;

	public Populacao() {
		this.individuos = new ArrayList<>();
	}

	public void criaPopulacao(ArrayList<Individuo> todosOsIndividuos, int tamPop) {
		Random random = new Random();
		/*
		 * pega um número aleatório que é a posição de um indivíduo na arraylist passada
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
		 * caso custo, tempo e lucro dos indivíduos estejam diferentes de 0, faz ficarem
		 * com 0
		 */
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			this.individuos.get(contInd).setCusto(0.0);
			this.individuos.get(contInd).setTempo(0);
			this.individuos.get(contInd).setLucro(0.0);
		}
		// soma custo, tempo e lucro de cada indivíduo da população
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
		 * se indivíduo exceder custo máximo, ou tempo máximo, ou ambos ao mesmo tempo,
		 * aplica penalidade
		 */
		conferePenalidadeDosIndividuos(custoMaximo, tempoMaximo, penalidade);
		System.out.println();
		System.out.println("Essa é a população após o calculo do fitness dos indivíduos atuais: ");
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
	 * mostra cromossomo e lucro de cada indivíduo da população
	 */
	public void mostraIndividuosDaPopulacao() {
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			System.out.println(this.individuos.get(contInd));
			System.out.println("Custo: " + this.individuos.get(contInd).getCusto());
			System.out.println("Tempo: " + this.individuos.get(contInd).getTempo());
			System.out.println("Lucro: " + this.individuos.get(contInd).getLucro());
		}
	}

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

}
