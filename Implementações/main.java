import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MusicRepository repo = new MusicRepository();

        while (true) {
            System.out.println("\n1. Adicionar Música");
            System.out.println("2. Listar Músicas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // consume \n

            switch (opcao) {
                case 1:
                    System.out.print("Digite o caminho do arquivo .wav: ");
                    String path = scanner.nextLine();
                    repo.addMusic(path);
                    break;
                case 2:
                    repo.listMusics();
                    break;
                case 3:
                    System.out.println("Encerrando.");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
