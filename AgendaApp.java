import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe Telefone
class Telefone {
    private String numero;
    private String rotulo;

    public Telefone(String numero, String rotulo) {
        this.numero = numero;
        this.rotulo = rotulo;
    }

    @Override
    public String toString() {
        return rotulo + ": " + numero;
    }
}

// Classe Email
class Email {
    private String endereco;
    private String rotulo;

    public Email(String endereco, String rotulo) {
        this.endereco = endereco;
        this.rotulo = rotulo;
    }

    @Override
    public String toString() {
        return rotulo + ": " + endereco;
    }
}

// Classe Contato
class Contato {
    private String nome;
    private List<Telefone> telefones;
    private List<Email> emails;

    public Contato(String nome) {
        this.nome = nome;
        this.telefones = new ArrayList<>();
        this.emails = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarTelefone(String numero, String rotulo) {
        telefones.add(new Telefone(numero, rotulo));
    }

    public void adicionarEmail(String endereco, String rotulo) {
        emails.add(new Email(endereco, rotulo));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("  Telefones:\n");
        for (Telefone t : telefones) {
            sb.append("    ").append(t).append("\n");
        }
        sb.append("  Emails:\n");
        for (Email e : emails) {
            sb.append("    ").append(e).append("\n");
        }
        return sb.toString();
    }
}

// Classe Agenda
class Agenda {
    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
    }

    public void removerContato(String nome) {
        contatos.removeIf(c -> c.getNome().equalsIgnoreCase(nome));
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
        } else {
            for (Contato c : contatos) {
                System.out.println(c);
                System.out.println("--------------------------------");
            }
        }
    }
}

// Classe principal (programa interativo)
public class AgendaApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Agenda agenda = new Agenda();

        int opcao;
        do {
            System.out.println("\n===== MENU AGENDA =====");
            System.out.println("1 - Adicionar contato");
            System.out.println("2 - Listar contatos");
            System.out.println("3 - Remover contato");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do contato: ");
                    String nome = sc.nextLine();

                    Contato contato = new Contato(nome);

                    // Adicionar telefones
                    System.out.print("Quantos telefones deseja adicionar? ");
                    int qtdTel = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < qtdTel; i++) {
                        System.out.print("Número do telefone: ");
                        String numero = sc.nextLine();
                        System.out.print("Rótulo (Ex: Casa, Trabalho, Celular): ");
                        String rotulo = sc.nextLine();
                        contato.adicionarTelefone(numero, rotulo);
                    }

                    // Adicionar emails
                    System.out.print("Quantos emails deseja adicionar? ");
                    int qtdEmail = sc.nextInt();
                    sc.nextLine();
                    for (int i = 0; i < qtdEmail; i++) {
                        System.out.print("Endereço de email: ");
                        String endereco = sc.nextLine();
                        System.out.print("Rótulo (Ex: Pessoal, Trabalho): ");
                        String rotulo = sc.nextLine();
                        contato.adicionarEmail(endereco, rotulo);
                    }

                    agenda.adicionarContato(contato);
                    System.out.println("✅ Contato adicionado com sucesso!");
                }

                case 2 -> {
                    System.out.println("\n---- Lista de Contatos ----");
                    agenda.listarContatos();
                }

                case 3 -> {
                    System.out.print("Digite o nome do contato a remover: ");
                    String nome = sc.nextLine();
                    agenda.removerContato(nome);
                    System.out.println("❌ Contato removido (se existia).");
                }

                case 0 -> System.out.println("Encerrando o programa...");

                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);

        sc.close();
    }
}
