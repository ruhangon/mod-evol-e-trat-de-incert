package calcado;

public class Calcado {
	private String nome;
	private Double custo;
	private Integer tempo;
	private Double lucro;

	public Calcado(String nome, Double custo, Integer tempo, Double lucro) {
		this.nome = nome;
		this.custo = custo;
		this.tempo = tempo;
		this.lucro = lucro;
	}

	public String getNome() {
		return nome;
	}

	public Double getCusto() {
		return custo;
	}

	public Integer getTempo() {
		return tempo;
	}

	public Double getLucro() {
		return lucro;
	}

}
