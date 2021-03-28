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
		// define em qual posição do cromossomo será feita a divisão
		int posDivisao = 0;
		do {
			posDivisao = random.nextInt(individuosComReproducao.get(0).getCromossomo().size());
		} while ((posDivisao <= 0) || (posDivisao >= (individuosComReproducao.get(0).getCromossomo().size() - 1)));
		// reprodução para gerar novos filhos
		int contPais = 0;
		/*
		 * primeiro passa sequencialmente gerando os filhos e adicionando eles a
		 * arraylist de indivíduos principal
		 */
		do {
			Individuo filho1 = Pais.geraFilho(paisSelecionados.get(contPais), paisSelecionados.get(contPais + 1),
					posDivisao);
			if (!Individuo.existeNaPopulacao(filho1, individuosComReproducao)) {
				individuosComReproducao.add(filho1);
				System.out.println(paisSelecionados.get(contPais) + " - " + paisSelecionados.get(contPais + 1));
				System.out.println("Filho adicionado: " + filho1);
			}
			if (individuosComReproducao.size() == totalDePaisEFilhos)
				break;
			Individuo filho2 = Pais.geraFilho(paisSelecionados.get(contPais + 1), paisSelecionados.get(contPais),
					posDivisao);
			if (!Individuo.existeNaPopulacao(filho2, individuosComReproducao)) {
				individuosComReproducao.add(filho2);
				System.out.println(paisSelecionados.get(contPais + 1) + " - " + paisSelecionados.get(contPais));
				System.out.println("Filho adicionado: " + filho2);
			}
			if (individuosComReproducao.size() == totalDePaisEFilhos)
				break;
			contPais++;
		} while (contPais < (paisSelecionados.size() - 1));
		/*
		 * para o caso de não ter preenchido toda a arraylist principal, encontra pais
		 * aleatoriamente para gerar novos filhos para ela
		 */
		int indAleat1 = 0;
		int indAleat2 = 0;
		do {
			indAleat1 = random.nextInt(paisSelecionados.size());
			indAleat2 = random.nextInt(paisSelecionados.size());
			if (indAleat1 == indAleat2) {
				do {
					indAleat2 = random.nextInt(paisSelecionados.size());
				} while (indAleat1 == indAleat2);
			}
			Individuo filho1 = Pais.geraFilho(paisSelecionados.get(indAleat1), paisSelecionados.get(indAleat2),
					posDivisao);
			if (!Individuo.existeNaPopulacao(filho1, individuosComReproducao)) {
				individuosComReproducao.add(filho1);
				System.out.println(paisSelecionados.get(indAleat1) + " - " + paisSelecionados.get(indAleat2));
				System.out.println("Filho adicionado: " + filho1);
			}
			if (individuosComReproducao.size() == totalDePaisEFilhos)
				break;
			Individuo filho2 = Pais.geraFilho(paisSelecionados.get(indAleat2), paisSelecionados.get(indAleat1),
					posDivisao);
			if (!Individuo.existeNaPopulacao(filho2, individuosComReproducao)) {
				individuosComReproducao.add(filho2);
				System.out.println(paisSelecionados.get(indAleat2) + " - " + paisSelecionados.get(indAleat1));
				System.out.println("Filho adicionado: " + filho2);
			}
			if (individuosComReproducao.size() == totalDePaisEFilhos)
				break;
		} while (true);
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
