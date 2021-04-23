import java.util.ArrayList;

import calcado.Calcado;
import genetico.Individuo;
import genetico.Populacao;

public class Main {
	public static void main(String[] args) {
		System.out.println("Fábrica de calçados com algoritmo genético");

		Calcado calcado1 = new Calcado("Sandálias", 100.00, 20, 60.00);
		Calcado calcado2 = new Calcado("Sapatos masculinos", 120.00, 30, 80.00);
		Calcado calcado3 = new Calcado("Botas femininas", 150.00, 25, 90.00);
		Calcado calcado4 = new Calcado("Sapatos femininos", 120.00, 28, 50.00);
		ArrayList<Calcado> calcados = new ArrayList<>();
		calcados.add(calcado1);
		calcados.add(calcado2);
		calcados.add(calcado3);
		calcados.add(calcado4);

		System.out.println();

		Individuo individuo = new Individuo();
		ArrayList<Individuo> individuos = individuo.criaIndividuos(100);
		// System.out.println("Lista de indivíduos: ");
		// for (Individuo i : individuos)
		// System.out.println(i);

		Populacao populacao = new Populacao();
		populacao.criaPopulacao(individuos, 40);

		System.out.println("\n\nFim do programa");

	}
}
