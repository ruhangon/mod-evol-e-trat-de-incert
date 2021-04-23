package util;

import java.util.ArrayList;

import genetico.Individuo;

public class LimitesDoIndividuo {
	private Individuo individuo;
	private Integer inicio;
	private Integer fim;

	public LimitesDoIndividuo() {
	}

	public LimitesDoIndividuo(Individuo individuo, Integer inicio, Integer fim) {
		this.individuo = individuo;
		this.inicio = inicio;
		this.fim = fim;
	}

	/*
	 * preenche limites iniciais e finais de cada indivíduo para uso na roleta da
	 * população
	 */
	public ArrayList<LimitesDoIndividuo> preencheLimites(ArrayList<Individuo> individuosDaPopulacao) {
		ArrayList<LimitesDoIndividuo> limitesDosIndividuos = new ArrayList<>(individuosDaPopulacao.size());
		int valorAtual = 0;
		for (int contInd = 0; contInd < individuosDaPopulacao.size(); contInd++) {
			int limiteInicialInd = valorAtual;
			// soma valor atual a valor do indivíduo atual
			valorAtual += individuosDaPopulacao.get(contInd).getValor();
			// agora diminui 1 do valor atual para que seja o valor do limite final atual
			valorAtual--;
			int limiteFinalInd = valorAtual;
			// soma 1 a valor atual para ser o valor do limite inicial do próximo indivíduo
			valorAtual++;
			limitesDosIndividuos
					.add(new LimitesDoIndividuo(individuosDaPopulacao.get(contInd), limiteInicialInd, limiteFinalInd));
		}
		// mostraLimitesDosIndividuos(limitesDosIndividuos);
		return limitesDosIndividuos;
	}

	/*
	 * com base no numAleat retorna o indivíduo referente a ele
	 */
	public Individuo encontraIndividuo(ArrayList<LimitesDoIndividuo> limitesInd, int numAleat) {
		Individuo i = null;
		for (int contLim = 0; contLim < limitesInd.size(); contLim++) {
			if ((numAleat >= limitesInd.get(contLim).inicio) && (numAleat <= limitesInd.get(contLim).fim)) {
				i = limitesInd.get(contLim).individuo;
				break;
			}
		}
		return i;
	}

	public void mostraLimitesDosIndividuos(ArrayList<LimitesDoIndividuo> limitesInd) {
		for (int contLim = 0; contLim < limitesInd.size(); contLim++) {
			for (int contCrom = 0; contCrom < limitesInd.get(0).individuo.getCromossomo().size(); contCrom++) {
				System.out.print(limitesInd.get(contLim).individuo.getCromossomo().get(contCrom));
			}
			System.out.println();
			System.out.println("Limite inicial desse indivíduo: " + limitesInd.get(contLim).inicio);
			System.out.println("Limite final desse indivíduo: " + limitesInd.get(contLim).fim);
		}
		System.out.println();
	}

	public Individuo getIndividuo() {
		return individuo;
	}

	public Integer getInicio() {
		return inicio;
	}

	public Integer getFim() {
		return fim;
	}

}
