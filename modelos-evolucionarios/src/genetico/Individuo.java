package genetico;

import java.util.ArrayList;
import java.util.Random;

public class Individuo {
	private ArrayList<Integer> cromossomo;
	private Integer peso;
	private Double valor;

	public Individuo(int qtdItens) {
		cromossomo = new ArrayList<>(qtdItens);
	}

	public ArrayList<Integer> criaIndividuos(int qtdItens, ArrayList<Item> itens) {
		int qtdIndividuos = qtdItens * 5;
		ArrayList<Integer> individuos = new ArrayList<>(qtdIndividuos);
		int contInd;
		do {
			for (int contItens = 0; contItens < qtdItens; contItens++) {
				Random random = new Random();
				numAleat = random.nextInt(1); // pega um número aleatório entre 0 e 1
				Individuo novoInd = new Individuo(qtdItens);

			}
			contInd++;
		} while (contInd < qtdIndividuos);
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
