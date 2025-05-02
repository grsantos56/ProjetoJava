package app;

import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import model.Cliente;
import model.Produto;

import java.time.LocalDate;
import java.util.*;

public class Main{

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteController clienteController = new ClienteController();
        ProdutoController produtoController = new ProdutoController();
        VendaController vendaController = new VendaController();

        while (true) {
            System.out.println("\n==== MENU PRINCIPAL ====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Atualizar Cliente");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Cadastrar Produto");
            System.out.println("6. Listar Produtos");
            System.out.println("7. Atualizar Produto");
            System.out.println("8. Remover Produto");
            System.out.println("9. Realizar Venda");
            System.out.println("10. Listar Vendas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = Integer.parseInt(sc.nextLine());

            try {
                switch (opcao) {
                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();
                        System.out.print("CPF (11 dígitos): ");
                        String cpf = sc.nextLine();
                        System.out.print("Telefone (11 dígitos): ");
                        String telefone = sc.nextLine();
                        System.out.print("Endereço (mín. 6 caracteres): ");
                        String endereco = sc.nextLine();
                        clienteController.cadastrarCliente(nome, cpf, telefone, endereco);
                        System.out.println("Cliente cadastrado com sucesso.");
                    }
                    case 2 -> {
                        List<Cliente> clientes = clienteController.listarClientes();
                        System.out.println("\n--- CLIENTES ---");
                        clientes.forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.print("CPF do cliente: ");
                        String cpf = sc.nextLine();
                        Cliente cliente = clienteController.buscarCliente(cpf);
                        if (cliente != null) {
                            System.out.print("Novo nome (" + cliente.getNome() + "): ");
                            cliente.setNome(sc.nextLine());
                            System.out.print("Novo telefone (" + cliente.getTelefone() + "): ");
                            cliente.setTelefone(sc.nextLine());
                            System.out.print("Novo endereço (" + cliente.getEndereco() + "): ");
                            cliente.setEndereco(sc.nextLine());
                            clienteController.atualizarCliente(cliente);
                            System.out.println("Cliente atualizado.");
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                    }
                    case 4 -> {
                        System.out.print("CPF do cliente a remover: ");
                        String cpf = sc.nextLine();
                        clienteController.excluirCliente(cpf);
                        System.out.println("Cliente removido.");
                    }
                    case 5 -> {
                        System.out.print("Nome do produto: ");
                        String nome = sc.nextLine();
                        System.out.print("Preço: ");
                        double preco = Double.parseDouble(sc.nextLine());
                        System.out.print("Estoque: ");
                        int estoque = Integer.parseInt(sc.nextLine());
                        produtoController.cadastrarProduto(nome, preco, estoque);
                        System.out.println("Produto cadastrado.");
                    }
                    case 6 -> {
                        List<Produto> produtos = produtoController.listarProdutos();
                        System.out.println("\n--- PRODUTOS ---");
                        produtos.forEach(System.out::println);
                    }
                    case 7 -> {
                        System.out.print("ID do produto: ");
                        int id = Integer.parseInt(sc.nextLine());
                        Produto produto = produtoController.buscarProduto(id);
                        if (produto != null) {
                            System.out.print("Novo nome (" + produto.getNome() + "): ");
                            produto.setNome(sc.nextLine());
                            System.out.print("Novo preço (" + produto.getPreco() + "): ");
                            produto.setPreco(Double.parseDouble(sc.nextLine()));
                            System.out.print("Novo estoque (" + produto.getEstoque() + "): ");
                            produto.setEstoque(Integer.parseInt(sc.nextLine()));
                            produtoController.atualizarProduto(produto);
                            System.out.println("Produto atualizado.");
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    }
                    case 8 -> {
                        System.out.print("ID do produto a remover: ");
                        int id = Integer.parseInt(sc.nextLine());
                        produtoController.excluirProduto(id);
                        System.out.println("Produto removido.");
                    }
                    case 9 -> {
                        System.out.print("CPF do cliente: ");
                        String cpf = sc.nextLine();
                        Cliente cliente = clienteController.buscarCliente(cpf);
                        if (cliente == null) {
                            System.out.println("Cliente não encontrado.");
                            break;
                        }

                        List<Produto> todosProdutos = produtoController.listarProdutos();
                        List<Produto> carrinho = new ArrayList<>();

                        System.out.println("\n--- PRODUTOS DISPONÍVEIS ---");
                        for (Produto p : todosProdutos) {
                            System.out.println("ID: " + p.getId() + " | " + p.getNome() + " | Estoque: " + p.getEstoque());
                        }

                        System.out.print("Digite IDs dos produtos (separados por vírgula): ");
                        String[] ids = sc.nextLine().split(",");
                        for (String idStr : ids) {
                            int id = Integer.parseInt(idStr.trim());
                            Produto p = produtoController.buscarProduto(id);
                            if (p != null && p.getEstoque() > 0) {
                                carrinho.add(p);
                            }
                        }

                        if (!carrinho.isEmpty()) {
                            vendaController.realizarVenda(cliente, carrinho);
                            System.out.println("Venda registrada com sucesso.");
                        } else {
                            System.out.println("Nenhum produto válido selecionado.");
                        }
                    }
                    case 10 -> {
                        System.out.println("\n--- VENDAS ---");
                        vendaController.listarVendas().forEach(v -> {
                            System.out.println(v);
                            System.out.println("--------------------------------------------------");
                        });
                    }
                    case 0 -> {
                        System.out.println("Saindo...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
