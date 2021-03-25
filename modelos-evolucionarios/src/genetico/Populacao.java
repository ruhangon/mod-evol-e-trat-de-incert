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

	public ArrayList<Individuo> getIndividuos() {
		return individuos;
	}

	public void setIndividuos(ArrayList<Individuo> individuos) {
		this.individuos = individuos;
	}

}
