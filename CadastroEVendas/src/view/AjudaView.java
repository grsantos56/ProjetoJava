package view;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Tela de Ajuda do sistema de Cadastro e Vendas.
 * Exibe informações sobre as funcionalidades do sistema e explica o uso de cada tela.
 */
public class AjudaView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Método principal para iniciar a aplicação e exibir a tela de ajuda.
     * Executa a criação e exibição da janela em um thread separado para garantir
     * a responsividade da interface gráfica.
     *
     * @param args argumentos da linha de comando (não utilizados).
     */
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

    /**
     * Construtor da classe AjudaView.
     * Inicializa os componentes da interface gráfica, define o título da janela,
     * configura o comportamento ao fechar, estabelece as dimensões, impede o
     * redimensionamento, centraliza a janela na tela e adiciona os elementos visuais
     * como o título, o texto de ajuda em um JTextArea dentro de um JScrollPane
     * e um botão para voltar à tela principal.
     */
    public AjudaView() {
        setTitle("Ajuda - Sistema de Cadastro e Vendas"); // Define o título da janela.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento ao fechar a janela (apenas fecha esta janela).
        setBounds(100, 100, 910, 487); // Define as dimensões iniciais da janela (posição X, posição Y, largura, altura).
        setResizable(false); // Impede que o usuário redimensione a janela.
        setLocationRelativeTo(null); // Centraliza a janela na tela.

        contentPane = new JPanel(new BorderLayout(10, 10)); // Cria um painel principal com layout BorderLayout e espaçamento entre os componentes.
        contentPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Define uma borda vazia ao redor do painel para espaçamento interno.
        setContentPane(contentPane); // Define o painel principal como o contentPane da JFrame.

        JLabel lblTitulo = new JLabel("COMO USAR O SISTEMA"); // Cria um rótulo com o título da seção de ajuda.
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 22)); // Define a fonte do título (Verdana, negrito, tamanho 22).
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); // Centraliza o texto do título.
        contentPane.add(lblTitulo, BorderLayout.NORTH); // Adiciona o título ao painel principal na região NORTH.

        // Cria um JTextArea para exibir o texto de ajuda com as funcionalidades e explicações das telas.
        JTextArea txtAjuda = new JTextArea();
        txtAjuda.setEditable(false); // Impede que o usuário edite o texto de ajuda.
        txtAjuda.setFont(new Font("Verdana", Font.PLAIN, 16)); // Define a fonte do texto de ajuda (Verdana, normal, tamanho 16).
        txtAjuda.setLineWrap(true); // Permite que as linhas do texto sejam quebradas automaticamente.
        txtAjuda.setWrapStyleWord(true); // Quebra as linhas nas palavras, evitando cortar palavras no meio.
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

        JScrollPane scrollPane = new JScrollPane(txtAjuda); // Adiciona o JTextArea a um JScrollPane para permitir a rolagem do texto se necessário.
        contentPane.add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane (contendo o texto de ajuda) ao painel principal na região CENTER.

        JButton btnFechar = new JButton("Voltar à Tela Principal"); // Cria um botão para voltar à tela principal.
        btnFechar.setFont(new Font("Verdana", Font.PLAIN, 14)); // Define a fonte do botão.
        btnFechar.addActionListener(new ActionListener() { // Adiciona um ActionListener ao botão para lidar com o evento de clique.
            public void actionPerformed(ActionEvent e) {
                TelaPrincipalView telaPrincipal = new TelaPrincipalView(); // Cria uma nova instância da TelaPrincipalView.
                telaPrincipal.setVisible(true); // Exibe a tela principal.
                dispose(); // Fecha a tela de ajuda atual.
            }
        });

        JPanel panelBotao = new JPanel(); // Cria um painel para conter o botão.
        panelBotao.add(btnFechar); // Adiciona o botão ao painel.
        contentPane.add(panelBotao, BorderLayout.SOUTH); // Adiciona o painel do botão ao painel principal na região SOUTH.
    }
}