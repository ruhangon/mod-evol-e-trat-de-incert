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
			}
			individuos.add(novoInd);
			contInd++;
		} while (contInd < qtdIndividuos);
		return individuos;
	}

	public static boolean existeNaPopulacao(Individuo individuoAComparar, ArrayList<Individuo> todosOsIndividuos) {
		for (Individuo atual : todosOsIndividuos) {
			if (atual.cromossomo.equals(individuoAComparar.cromossomo))
				return true;
		}
		return false;
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
