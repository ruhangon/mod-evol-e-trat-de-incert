import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Teste de algoritmo evolucionário");
		System.out.println();

		// perguntar para usuário quantos itens tem disponível
		int qtdItens;
		do {
			try {
				System.out.println("Quantos itens há disponível?");
				System.out.print("Resposta: ");
				qtdItens = scan.nextInt();
				scan.nextLine();
				if (qtdItens <= 0)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				qtdItens = 0;
				scan.nextLine();
			}
		} while (qtdItens <= 0);

		System.out.println();

		/*
		 * cadastro de itens. Pergunta nome, valor e peso. O nome serve para facilitar a
		 * identificação do item
		 */
		Item item = new Item();
		List<Item> itens = item.cadastraItens(scan, qtdItens);

		// for (Item it : itens) {
		// System.out.println("Nome do item: " + it.getNome());
		// System.out.println("Peso: " + it.getPeso());
		// System.out.println("Valor: " + it.getValor());
		// }

		System.out.println();

		// pede para o usuário qual será capacidade da mochila
		int capMochila;
		do {
			try {
				System.out.println("Qual a capacidade da mochila, o peso máximo que ela suporta?");
				System.out.print("Resposta: ");
				capMochila = scan.nextInt();
				scan.nextLine();
				if (capMochila <= 0)
					System.out.println("Opção inválida");
			} catch (InputMismatchException e) {
				System.out.println("Opção inválida");
				capMochila = 0;
				scan.nextLine();
			}
		} while (capMochila <= 0);

		// aqui

	}
}
