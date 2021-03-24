package genetico;

import java.util.ArrayList;
import java.util.Random;

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

	public void mostraIndividuosDaPopulacao() {
		int contInd = 0;
		do {
			for (int contCrom = 0; contCrom < this.individuos.get(0).getCromossomo().size(); contCrom++) {
				System.out.print(this.individuos.get(contInd).getCromossomo().get(contCrom));
			}
			System.out.println();
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
