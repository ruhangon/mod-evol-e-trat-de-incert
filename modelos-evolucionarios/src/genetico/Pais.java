package genetico;

import java.util.ArrayList;
import java.util.Random;

public class Pais {
	public static ArrayList<Individuo> reproduz(ArrayList<Individuo> paisSelecionados, int totalDePaisEFilhos) {
		System.out.println("Crossover");
		ArrayList<Individuo> individuosComReproducao = new ArrayList<>();
		for (Individuo individuosPais : paisSelecionados) {
			individuosComReproducao.add(individuosPais);
		}
		Random random = new Random();
		// define em qual posi��o do cromossomo ser� feita a divis�o
		int posDivisao = 0;
		do {
			posDivisao = random.nextInt(individuosComReproducao.get(0).getCromossomo().size());
		} while ((posDivisao <= 0) || (posDivisao >= (individuosComReproducao.get(0).getCromossomo().size() - 1)));
		/*
		 * passa sequencialmente gerando os filhos e adicionando eles a arraylist de
		 * indiv�duos principal
		 */
		for (int contPais = 0; contPais < (paisSelecionados.size()-1); contPais += 2) {
			Individuo filho1 = Pais.geraFilho(paisSelecionados.get(contPais), paisSelecionados.get(contPais + 1),
					posDivisao);
			individuosComReproducao.add(filho1);
			System.out.println(paisSelecionados.get(contPais) + " - " + paisSelecionados.get(contPais + 1));
			System.out.println("Filho adicionado: " + filho1);
			Individuo filho2 = Pais.geraFilho(paisSelecionados.get(contPais + 1), paisSelecionados.get(contPais),
					posDivisao);
			individuosComReproducao.add(filho2);
			System.out.println(paisSelecionados.get(contPais + 1) + " - " + paisSelecionados.get(contPais));
			System.out.println("Filho adicionado: " + filho2);
		}
		// caso tenha faltado um indiv�duo adiciona o abaixo
		if (individuosComReproducao.size() < totalDePaisEFilhos) {
			Individuo filho = Pais.geraFilho(paisSelecionados.get(0), paisSelecionados.get(paisSelecionados.size() - 1),
					posDivisao);
			individuosComReproducao.add(filho);
			System.out.println(paisSelecionados.get(0) + " - " + paisSelecionados.get(paisSelecionados.size() - 1));
			System.out.println("Filho adicionado: " + filho);
		}
		return individuosComReproducao;
	}

	public static Individuo geraFilho(Individuo i1, Individuo i2, int posDivisao) {
		Individuo filho = new Individuo(i1.getCromossomo().size());
		for (int contCrom = 0; contCrom < i1.getCromossomo().size(); contCrom++) {
			if (contCrom <= posDivisao) {
				filho.getCromossomo().add(i1.getCromossomo().get(contCrom));
			} else {
				filho.getCromossomo().add(i2.getCromossomo().get(contCrom));
			}
		}
		return filho;
	}

}
