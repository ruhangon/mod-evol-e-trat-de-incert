import java.util.ArrayList;
import java.util.Scanner;

public class Item {
	private String nome;
	private Integer peso;
	private Double valor;

	public Item() {
	}

	public Item(String nome, Integer peso, Double valor) {
		this.nome = nome;
		this.peso = peso;
		this.valor = valor;
	}

	public ArrayList<Item> cadastraItens(Scanner scan, int qtdItens) {
		System.out.println("Cadastro de itens");
		ArrayList<Item> itens = new ArrayList<>(qtdItens);
		int cont = 1;
		do {
			System.out.println("Item " + cont);
			System.out.println("Digite o nome do item?");
			System.out.print("Resposta: ");
			nome = scan.nextLine();
			System.out.println("Digite o peso do item?");
			System.out.print("Resposta: ");
			peso = scan.nextInt();
			scan.nextLine();
			System.out.println("Digite o valor do item?");
			System.out.print("Resposta: ");
			valor = scan.nextDouble();
			scan.nextLine();
			itens.add(new Item(nome, peso, valor));
			cont++;
		} while (cont <= qtdItens);
		return itens;
	}

	public String getNome() {
		return nome;
	}

	public Integer getPeso() {
		return peso;
	}

	public Double getValor() {
		return valor;
	}

}
