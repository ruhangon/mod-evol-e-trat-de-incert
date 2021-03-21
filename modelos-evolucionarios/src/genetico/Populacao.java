package genetico;

import java.util.ArrayList;

public class Populacao {
	ArrayList<Individuo> individuos;

	public Populacao(int tamPop) {
		individuos = new ArrayList<>(tamPop);
	}

}
