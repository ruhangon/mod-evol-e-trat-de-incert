package genetico;

import java.util.ArrayList;
import java.util.Random;

import item.Item;

public class Populacao {
	private ArrayList<Individuo> individuos;

	public Populacao(int tamPop) {
		this.individuos = new ArrayList<>(tamPop);
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

	public void calculaFitness(ArrayList<Item> itens, int capMaxima, int penalidade) {
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
		// se peso do indivíduo exceder capacidade máxima, aplica penalidade
		conferePenalidadeDosIndividuos(capMaxima, penalidade);
	}

	public void conferePenalidadeDosIndividuos(int capMaxima, int penalidade) {
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			if (this.individuos.get(contInd).getPeso() > capMaxima)
				this.individuos.get(contInd).setValor(this.individuos.get(contInd).getValor() - penalidade);
		}
	}

	public void mostraIndividuosDaPopulacao() {
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

	public void roleta() {
		Integer valorMinimo = buscaValorMinimoERemove();
		// agora tendo o valor mínimo subtrai ele de todos os indivíduos da população atual
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			this.individuos.get(contInd).setValor(this.individuos.get(contInd).getValor() - valorMinimo);
		}
		// soma os valores novos e guarda em uma variável
		Integer somaValores=0;
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			somaValores += this.individuos.get(contInd).getValor();
		}
		// aqui
	}

	public Integer buscaValorMinimoERemove() {
		Integer valorMinimo = 1000000; // guarda o menor valor encontrado em um indivíduo da população
		int posIndComValorMinimo = 0; // guarda a posição do indivíduo com menor valor
		for (int contInd = 0; contInd < this.individuos.size(); contInd++) {
			if (this.individuos.get(contInd).getValor() < valorMinimo) {
				valorMinimo = this.individuos.get(contInd).getValor();
				posIndComValorMinimo = contInd;
			}
		}
		this.individuos.remove(posIndComValorMinimo); // remove o indivíduo com o menor valor encontrado
		return valorMinimo;
	}

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

}
