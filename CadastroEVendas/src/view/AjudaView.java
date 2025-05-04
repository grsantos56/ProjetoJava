package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AjudaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AjudaView frame = new AjudaView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AjudaView() {
        setTitle("Ajuda - Sistema de Cadastro e Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 910, 487);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("COMO USAR O SISTEMA");
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 22));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblTitulo, BorderLayout.NORTH);

        // Texto fixo com as funcionalidades + sessão explicativa das telas
        JTextArea txtAjuda = new JTextArea();
        txtAjuda.setEditable(false);
        txtAjuda.setFont(new Font("Verdana", Font.PLAIN, 16));
        txtAjuda.setLineWrap(true);
        txtAjuda.setWrapStyleWord(true);
        txtAjuda.setText(
                "Este sistema permite realizar as seguintes ações:\n\n" +
                "- Cadastrar, editar, buscar e excluir clientes.\n" +
                "- Cadastrar produtos e livros (com campo adicional 'Autor').\n" +
                "- Gerenciar estoque de produtos e livros.\n" +
                "- Realizar vendas selecionando cliente e produtos.\n" +
                "- Buscar cliente por CPF.\n" +
                "- Exibir total da venda.\n" +
                "- Cancelar ou finalizar uma venda.\n" +
                "- Filtrar vendas realizadas por período.\n" +
                "- Confirmar ações críticas como salvar, excluir e sair.\n" +
                "- Interface de tamanho fixo com espaçamento adequado.\n\n" +

                "-----------------------------------------------------------------------------------------------------------------------\n" +
                "EXPLICAÇÃO DAS TELAS:\n\n" +
                "Tela de Produtos:\n\n" +
                "Aqui você pode cadastrar, editar, excluir e listar produtos.\n" +
                "Se o produto for um livro, o campo 'Autor' será exibido.\n" +
                "Para cadastrar se deve informar todos os campos, exceto autor caso o chekbox de livro não esteja ativado.\n" +
                "Para cadastrar se deve informar todos os campos, exceto autor caso o chekbox de livro não esteja ativado. e posteriormente clicar em atualizar, onde será pedido o id do produto que se deve atualizar.\n" +
                "Para excluir um produto basta apenas inserir o id do mesmo.\n" +
                "Ao clicar em visualizar Produtos serão exibidos todos os produtos em ordem Alfabética.\n\n" +

                "Tela de Clientes:\n\n" +
                "Permite o gerenciamento de clientes com busca por CPF.\n" +
                "Inclui validações de dados e confirmação antes de excluir.\n" +
                "Para cadastrar se deve informar todos os campos e clicar no botão de cadastrar.\n" +
                "Para atualizar digite em todos os modificando-os com exceção do cpf que deve ser o mesmo e clicar no botão de atualizar.\n" +
                "Para buscar um cliente basta digitar o cpf e clicar em buscar.\n" +
                "Para exlcuir basta digitar o cpf e clicar em excluir.\n" +
                "Para listar todos os clientes cadastrados clique no botão de listar todos.\n\n" +

                "Tela de Vendas:\n\n" +
                "Você pode iniciar uma nova venda, adicionar produtos,\n" +
                "selecionar o cliente, ver o total da venda em tempo real,\n" +
                "e finalizar ou cancelar a operação.\n" +
                "Coloque o id do produto e clique em adicionar item, faça isso para todos os produtos que deseja adicionar.\n" +
                "Vai aparecer o nome e o preço dos produtos que foram adicionados.\n" +
                "Digite o cpf do cliente que está realizando a compra e clique em finalizar venda para vender.\n" +
                "Caso queira cancelar a venda clique em cancelar venda.\n" +
                "Para visualizar as vendas feitas coloque a data no formato: dia/mês/ano e clique em buscar por período onde vai aparecer as vendas realizadas naquele período de tempo.\n"
        );

        JScrollPane scrollPane = new JScrollPane(txtAjuda);
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JButton btnFechar = new JButton("Voltar à Tela Principal");
        btnFechar.setFont(new Font("Verdana", Font.PLAIN, 14));
        btnFechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaPrincipalView telaPrincipal = new TelaPrincipalView();
                telaPrincipal.setVisible(true);
                dispose();
            }
        });

        JPanel panelBotao = new JPanel();
        panelBotao.add(btnFechar);
        contentPane.add(panelBotao, BorderLayout.SOUTH);
    }
}
