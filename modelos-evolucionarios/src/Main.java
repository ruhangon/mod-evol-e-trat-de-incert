import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Teste de algoritmo evolucion�rio");
		System.out.println();

		// perguntar para usu�rio quantos itens tem dispon�vel
		int qtdItens;
		do {
			try {
				System.out.println("Quantos itens h� dispon�vel?");
				System.out.print("Resposta: ");
				qtdItens = scan.nextInt();
				scan.nextLine();
				if (qtdItens <= 0)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				qtdItens = 0;
				scan.nextLine();
			}
		} while (qtdItens <= 0);

		System.out.println();

		/*
		 * cadastro de itens. Pergunta nome, valor e peso. O nome serve para facilitar a
		 * identifica��o do item
		 */
		Item item = new Item();
		List<Item> itens = item.cadastraItens(scan, qtdItens);

		// for (Item it : itens) {
		// System.out.println("Nome do item: " + it.getNome());
		// System.out.println("Peso: " + it.getPeso());
		// System.out.println("Valor: " + it.getValor());
		// }

		System.out.println();

		// pede para o usu�rio qual ser� capacidade da mochila
		int capMochila;
		do {
			try {
				System.out.println("Qual a capacidade da mochila, o peso m�ximo que ela suporta?");
				System.out.print("Resposta: ");
				capMochila = scan.nextInt();
				scan.nextLine();
				if (capMochila <= 0)
					System.out.println("Op��o inv�lida");
			} catch (InputMismatchException e) {
				System.out.println("Op��o inv�lida");
				capMochila = 0;
				scan.nextLine();
			}
		} while (capMochila <= 0);

		// aqui

	}
}
