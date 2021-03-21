package genetico;

import java.util.ArrayList;
import java.util.Random;

import item.Item;

public class Individuo {
	private ArrayList<Integer> cromossomo;
	private Integer peso;
	private Double valor;

	public Individuo(int qtdItens) {
		this.cromossomo = new ArrayList<>(qtdItens);
		this.peso = 0;
		this.valor = 0.0;
	}

	public ArrayList<Individuo> criaIndividuos(int qtdItens, ArrayList<Item> itens) {
		int qtdIndividuos = qtdItens * 5;
		ArrayList<Individuo> individuos = new ArrayList<>(qtdIndividuos);
		int contInd = 0;
		do {
			Individuo novoInd = new Individuo(qtdItens);
			for (int contItens = 0; contItens < qtdItens; contItens++) {
				Random random = new Random();
				int numAleat = random.nextInt(2); // pega um número aleatório entre 0 e 1
				// System.out.println(numAleat);
				novoInd.cromossomo.add(numAleat);
				if (numAleat == 1) {
					novoInd.peso += itens.get(contItens).getPeso();
					novoInd.valor += itens.get(contItens).getValor();
				}
			}
			individuos.add(novoInd);
			contInd++;
		} while (contInd < qtdIndividuos);
		return individuos;
	}

	public void mostraIndividuos(ArrayList<Individuo> individuos, int qtdItens) {
		int contInd = 0;
		do {
			for (int contCrom = 0; contCrom < qtdItens; contCrom++) {
				System.out.print(individuos.get(contInd).cromossomo.get(contCrom));
			}
			System.out.println();
			System.out.println("Peso: " + individuos.get(contInd).peso);
			System.out.println("Valor: " + individuos.get(contInd).valor);
			contInd++;
		} while (contInd < individuos.size());
	}

	public boolean individuoJaExiste(Individuo ind1, Individuo ind2) {
		for (int i = 0; i < ind1.cromossomo.size(); i++) {
			if (ind1.cromossomo.get(i) != ind2.cromossomo.get(i))
				return false;
		}
		return true;
	}

	public ArrayList<Integer> getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(ArrayList<Integer> cromossomo) {
		this.cromossomo = cromossomo;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
