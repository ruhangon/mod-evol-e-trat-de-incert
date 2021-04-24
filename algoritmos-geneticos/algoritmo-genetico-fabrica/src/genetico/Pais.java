package genetico;

import java.util.ArrayList;
import java.util.Random;

public class Pais {
	public static ArrayList<Individuo> reproduz(ArrayList<Individuo> paisSelecionados, int tamPop) {
		System.out.println("Crossover");
		int totalDeFilhos = tamPop - paisSelecionados.size();
		ArrayList<Individuo> filhos = new ArrayList<>(totalDeFilhos);
		Random random = new Random();
		/*
		 * passa sequencialmente gerando os filhos e adicionando eles a arraylist de
		 * indivíduos principal
		 */
		for (int contPais = 0; contPais < (paisSelecionados.size() - 1); contPais += 2) {
			Individuo filho1 = Pais.geraFilho(paisSelecionados.get(contPais), paisSelecionados.get(contPais + 1));
			filhos.add(filho1);
			System.out.println(paisSelecionados.get(contPais) + " - " + paisSelecionados.get(contPais + 1));
			System.out.println("Filho adicionado: " + filho1);
			Individuo filho2 = Pais.geraFilho(paisSelecionados.get(contPais + 1), paisSelecionados.get(contPais));
			filhos.add(filho2);
			System.out.println(paisSelecionados.get(contPais + 1) + " - " + paisSelecionados.get(contPais));
			System.out.println("Filho adicionado: " + filho2);
		}
		if (filhos.size() < totalDeFilhos) {
			Individuo filho = Pais.geraFilho(paisSelecionados.get(0),
					paisSelecionados.get(paisSelecionados.size() - 1));
			filhos.add(filho);
			System.out.println(paisSelecionados.get(0) + " - " + paisSelecionados.get(paisSelecionados.size() - 1));
			System.out.println("Filho adicionado: " + filho);
		}
		if (filhos.size() < totalDeFilhos) {
			Individuo filho = Pais.geraFilho(paisSelecionados.get(paisSelecionados.size() - 1),
					paisSelecionados.get(0));
			filhos.add(filho);
			System.out.println(paisSelecionados.get(paisSelecionados.size() - 1) + " - " + paisSelecionados.get(0));
			System.out.println("Filho adicionado: " + filho);
		}
		return filhos;
	}

	public static Individuo geraFilho(Individuo i1, Individuo i2) {
		Individuo filho = new Individuo();
		ArrayList<Integer> ordemI1 = Pais.descobreOrdemDoIndividuo(i1);
		ArrayList<Integer> ordemI2 = Pais.descobreOrdemDoIndividuo(i2);
		if (!ordemI1.equals(ordemI2)) {
			filho = Pais.descobreNovoFilho(filho, i1, ordemI2);
		} else {
			// caso a ordem dos pais seja a mesma apenas inverte as posições 1 a 3 para
			// gerar o novo filho
			filho.getCromossomo().set(0, i1.getCromossomo().get(0));
			filho.getCromossomo().set(3, i1.getCromossomo().get(1));
			filho.getCromossomo().set(2, i1.getCromossomo().get(2));
			filho.getCromossomo().set(1, i1.getCromossomo().get(3));
		}
		return filho;
	}

	public static ArrayList<Integer> descobreOrdemDoIndividuo(Individuo i) {
		ArrayList<Integer> ordem = new ArrayList<>();
		if ((i.getCromossomo().get(1) <= i.getCromossomo().get(2))
				&& (i.getCromossomo().get(1) <= i.getCromossomo().get(3))) {
			ordem.add(1);
			if (i.getCromossomo().get(2) <= i.getCromossomo().get(3)) {
				ordem.add(2);
				ordem.add(3);
				return ordem;
			} else {
				ordem.add(3);
				ordem.add(2);
				return ordem;
			}
		}
		if ((i.getCromossomo().get(2) <= i.getCromossomo().get(1))
				&& (i.getCromossomo().get(2) <= i.getCromossomo().get(3))) {
			ordem.add(2);
			if (i.getCromossomo().get(1) <= i.getCromossomo().get(3)) {
				ordem.add(1);
				ordem.add(3);
				return ordem;
			} else {
				ordem.add(3);
				ordem.add(1);
				return ordem;
			}
		}
		ordem.add(3);
		if (i.getCromossomo().get(1) <= i.getCromossomo().get(2)) {
			ordem.add(1);
			ordem.add(2);
			return ordem;
		}
		ordem.add(2);
		ordem.add(1);
		return ordem;
	}

	public static Individuo descobreNovoFilho(Individuo filho, Individuo pai, ArrayList<Integer> ordem) {
		filho.getCromossomo().set(0, pai.getCromossomo().get(0));
		filho.getCromossomo().set(ordem.get(0), pai.getCromossomo().get(1));
		filho.getCromossomo().set(ordem.get(1), pai.getCromossomo().get(2));
		filho.getCromossomo().set(ordem.get(2), pai.getCromossomo().get(3));
		return filho;
	}

}
