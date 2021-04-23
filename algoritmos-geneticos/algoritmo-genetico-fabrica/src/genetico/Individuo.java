package genetico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Individuo {
	private ArrayList<Integer> cromossomo;
	private Double custo;
	private Integer tempo;
	private Double lucro;

	public Individuo() {
		this.cromossomo = new ArrayList<>();
		for (int i=0; i<4; i++)
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
				int numAleat = random.nextInt(4); // pega um número aleatório entre 0 e 3
				novoInd.cromossomo.set(numAleat, (novoInd.cromossomo.get(numAleat) + 1));
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
