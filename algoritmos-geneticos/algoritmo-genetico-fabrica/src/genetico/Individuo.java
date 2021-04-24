package genetico;

import java.util.ArrayList;
import java.util.Random;

public class Individuo {
	private ArrayList<Integer> cromossomo;
	private Double custo;
	private Integer tempo;
	private Double lucro;

	public Individuo() {
		this.cromossomo = new ArrayList<>();
		for (int i = 0; i < 4; i++)
			cromossomo.add(0);
		this.custo = 0.0;
		this.tempo = 0;
		this.lucro = 0.0;
	}

	public ArrayList<Individuo> criaIndividuos(int totalInd) {
		ArrayList<Individuo> individuos = new ArrayList<>();
		for (int contInd = 0; contInd < totalInd; contInd++) {
			Individuo novoInd = new Individuo();
			for (int totalCalcados = 0; totalCalcados < 400; totalCalcados++) {
				Random random = new Random();
				int numAleat = random.nextInt(8); // pega um número aleatório entre 0 e 7
				/*
				 * 0, 1, 2 = sandálias. 3 = sapatos masculinos. 4, 5 = botas femininas. 6, 7 =
				 * sapatos femininos. Dessa forma se consegue aumentar o número de sandálias em
				 * muitos indivíduos, esse é um calçado de menor custo e menor tempo para
				 * produção
				 */
				if ((numAleat == 0) || (numAleat == 1) || (numAleat == 2)) {
					novoInd.cromossomo.set(0, (novoInd.cromossomo.get(0) + 1));
				} else if (numAleat == 3) {
					novoInd.cromossomo.set(1, (novoInd.cromossomo.get(1) + 1));
				} else if ((numAleat == 4) || (numAleat == 5)) {
					novoInd.cromossomo.set(2, (novoInd.cromossomo.get(2) + 1));
				} else if ((numAleat == 6) || (numAleat == 7)) {
					novoInd.cromossomo.set(3, (novoInd.cromossomo.get(3) + 1));
				}
			}
			individuos.add(novoInd);
		}
		return individuos;
	}

	public static boolean existeNaPopulacao(Individuo individuoAComparar, ArrayList<Individuo> todosOsIndividuos) {
		for (Individuo atual : todosOsIndividuos) {
			if (atual.cromossomo.equals(individuoAComparar.cromossomo))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Individuo: " + cromossomo + "";
	}

	public ArrayList<Integer> getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(ArrayList<Integer> cromossomo) {
		this.cromossomo = cromossomo;
	}

	public Double getCusto() {
		return custo;
	}

	public void setCusto(Double custo) {
		this.custo = custo;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Double getLucro() {
		return lucro;
	}

	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}

}
