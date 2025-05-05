package view;

import controller.ClienteController;
import controller.ProdutoController;
import controller.VendaController;
import model.Cliente;
import model.Produto;
import model.Venda;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout.Alignment;

/**
 * Tela de Vendas da aplicação Navara's Store.
 * Permite que o vendedor adicione produtos a uma venda, busque clientes,
 * finalize a venda e visualize as vendas realizadas em um determinado período.
 */
public class VendasView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
    private JLabel lblNomeLoja;
    private JPanel panelCompra;
    private JLabel lblIdProduto;
    private JTextField txtIdProduto;
    private JButton btnAdicionarItem;
    private JList<Produto> listaItens;
    private DefaultListModel<Produto> modelListaItens;
    private JLabel lblTotalVenda;
    private JLabel lblCpfCliente;
    private JTextField txtCpfCliente;
    private JButton btnBuscarCliente;
    private JButton btnFinalizarVenda;
    private JPanel panelVendasRealizadas;
    private JLabel lblDataInicio;
    private JFormattedTextField txtDataInicio;
    private JLabel lblDataFim;
    private JFormattedTextField txtDataFim;
    private JButton btnBuscarVendasPeriodo;
    private JScrollPane scrollPaneVendas;
    private JTable tabelaVendas;
    private DefaultTableModel modelTabelaVendas;

    private ProdutoController produtoController;
    private VendaController vendaController;
    private ClienteController clienteController;
    private List<Produto> itensVendidos;
    private double totalVenda;
    private Cliente clienteAtual = null;
    private JButton btnNewButton;
    private Component horizontalStrut;
    private Component horizontalStrut_1;
    private Component horizontalStrut_2;
    private Component horizontalStrut_3;
    private Component horizontalStrut_4;
    private Component horizontalStrut_5;
    private Component horizontalStrut_6;
    private Component horizontalStrut_7;
    private Component horizontalStrut_8;
    private Component horizontalStrut_9;
    private Component horizontalStrut_10;
    private Component horizontalStrut_11;
    private Component horizontalStrut_12;
    private Component horizontalStrut_13;
    private Component horizontalStrut_14;
    private Component horizontalStrut_15;
    private Component horizontalStrut_16;
    private Component horizontalStrut_17;
    private Component horizontalStrut_18;
    private Component horizontalStrut_19;
    private Component horizontalStrut_20;
    private Component horizontalStrut_21;
    private Component horizontalStrut_22;
    private Component horizontalStrut_23;
    private Component horizontalStrut_24;
    private Component horizontalStrut_25;
    private Component horizontalStrut_26;
    private Component horizontalStrut_27;
    private Component horizontalStrut_28;
    private Component horizontalStrut_29;
    private Component horizontalStrut_31;
    private Component horizontalStrut_32;
    private Component horizontalStrut_33;
    private Component horizontalStrut_34;
    private Component horizontalStrut_35;
    private JButton btnNewButton_1;
    private Component horizontalStrut_30;
    private Component horizontalStrut_36;
    private Component horizontalStrut_37;
    private Component horizontalStrut_38;
    private Component horizontalStrut_39;
    private Component horizontalStrut_40;
    private Component horizontalStrut_41;

    /**
	 * Construtor da classe VendasView.
	 * Inicializa os componentes da interface gráfica, define o título da janela,
	 * configura o comportamento ao fechar, estabelece as dimensões, impede o
	 * redimensionamento, centraliza a janela na tela e adiciona os elementos visuais
	 * como o título, o painel de compra, a lista de itens vendidos e a tabela de vendas realizadas.
	 */
    public VendasView() {
        produtoController = new ProdutoController(); // Inicializa o controlador de produtos
        vendaController = new VendaController(); // Inicializa o controlador de vendas
        clienteController = new ClienteController(); // Inicializa o controlador de clientes
        itensVendidos = new ArrayList<>(); // Inicializa a lista de itens vendidos
        totalVenda = 0.0; // Inicializa o total da venda
        initComponents(); // Chama o método para inicializar os componentes da interface
        atualizarListaItens(); // Atualiza a lista de itens vendidos
    }

    /**
	 * Método para adicionar um item à venda.
	 * Busca o produto pelo ID informado, adiciona à lista de itens vendidos
	 * e atualiza o total da venda.
	 */
    private void initComponents() {
        setTitle("Vendas"); // Define o título da janela
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento ao fechar a janela (apenas fecha esta janela)
        setBounds(100, 100, 750, 600);  // Define as dimensões iniciais da janela
        setMinimumSize(new Dimension(1360, 720)); // Definindo tamanho mínimo
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false); // Impede que o usuário redimensione a janela
        contentPane = new JPanel(); // Cria um novo painel para conter os elementos da tela
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10)); // Define uma borda vazia ao redor do painel
        setContentPane(contentPane); // Define o painel como o contentPane da JFrame

        JPanel panelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        panelCabecalho.setBackground(new Color(0, 166, 41)); 
        panelCabecalho.add(Box.createHorizontalStrut(20)); 
        
        horizontalStrut = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut);
        
        horizontalStrut_1 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_1);
        
        horizontalStrut_2 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_2);
        
        horizontalStrut_3 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_3);
        
        horizontalStrut_4 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_4);
        
        horizontalStrut_5 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_5);
        
        horizontalStrut_6 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_6);
        
        horizontalStrut_7 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_7);
        
        horizontalStrut_8 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_8);
        
        horizontalStrut_9 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_9);
        
        horizontalStrut_10 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_10);
        
        horizontalStrut_11 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_11);
        
        horizontalStrut_12 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_12);
        
        horizontalStrut_13 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_13);
        
        horizontalStrut_14 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_14);
        
        horizontalStrut_15 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_15);
        
        horizontalStrut_16 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_16);
        
        horizontalStrut_17 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_17);
        
        horizontalStrut_18 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_18);
        
        horizontalStrut_19 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_19);
        
        horizontalStrut_20 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_20);
        lblNomeLoja = new JLabel("NAVARA'S STORE");
        lblNomeLoja.setForeground(new Color(255, 255, 255));
        lblNomeLoja.setFont(new Font("Arial", Font.BOLD, 20));
        panelCabecalho.add(lblNomeLoja);
        
        horizontalStrut_21 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_21);
        
        horizontalStrut_22 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_22);
        
        horizontalStrut_23 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_23);
        
        horizontalStrut_24 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_24);
        
        horizontalStrut_25 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_25);
        
        horizontalStrut_26 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_26);
        
        horizontalStrut_27 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_27);
        
        horizontalStrut_28 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_28);
        
        horizontalStrut_29 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_29);
        
        horizontalStrut_31 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_31);
        
        btnNewButton = new JButton("SAIR"); // Cria um botão para sair do sistema
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION); // Exibe uma janela de confirmação
				if (resposta == JOptionPane.YES_OPTION) { // Se o usuário confirmar a saída
					TelaPrincipalView telaPrincipal = new TelaPrincipalView(); // Cria uma nova instância da tela principal
					telaPrincipal.setVisible(true); // Torna a tela principal visível
					dispose(); // Fecha a janela atual
				}
        	}
        });
        
        horizontalStrut_32 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_32);
        
        horizontalStrut_33 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_33);
        
        horizontalStrut_34 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_34);
        
        horizontalStrut_35 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_35);
        
        horizontalStrut_36 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_36);
        
        horizontalStrut_37 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_37);
        
        horizontalStrut_38 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_38);
        
        horizontalStrut_39 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_39);
        
        horizontalStrut_40 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_40);
        
        horizontalStrut_41 = Box.createHorizontalStrut(20);
        panelCabecalho.add(horizontalStrut_41);
        panelCabecalho.add(btnNewButton);

        panelCompra = new JPanel(); // Cria um novo painel para a seção de compra

        lblIdProduto = new JLabel("ID Produto:"); // Rótulo para o campo de ID do produto
        txtIdProduto = new JTextField(10); // Campo de texto para inserir o ID do produto
        btnAdicionarItem = new JButton("Adicionar Item"); // Botão para adicionar o item à venda
        btnAdicionarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarItemVenda(); // Chama o método para adicionar o item à venda
            }
        });

        modelListaItens = new DefaultListModel<>(); // Modelo da lista de itens vendidos
        listaItens = new JList<>(modelListaItens); // Lista para exibir os itens vendidos
        JScrollPane scrollPaneItens = new JScrollPane(listaItens); // Adiciona a lista a um painel de rolagem
        scrollPaneItens.setPreferredSize(new Dimension(250, 150)); // Define o tamanho do painel de rolagem
        scrollPaneItens.setBorder(BorderFactory.createTitledBorder("Itens da Venda")); // Define o título do painel de rolagem

        lblTotalVenda = new JLabel("Total: R$ 0.00"); // Rótulo para exibir o total da venda
        lblTotalVenda.setFont(new Font("Arial", Font.BOLD, 16)); // Define a fonte do rótulo do total

        lblCpfCliente = new JLabel("CPF Cliente:"); // Rótulo para o campo de CPF do cliente
        txtCpfCliente = new JTextField(15); // Campo de texto para inserir o CPF do cliente
        btnBuscarCliente = new JButton("Buscar Cliente"); // Botão para buscar o cliente
        btnBuscarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCliente(); // Chama o método para buscar o cliente
            }
        });
        
                btnFinalizarVenda = new JButton("Finalizar Venda"); // Botão para finalizar a venda
                btnFinalizarVenda.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        finalizarVenda(); // Chama o método para finalizar a venda
                    }
                });
        
        btnNewButton_1 = new JButton("Cancelar Venda"); // Botão para cancelar a venda
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar a venda?", "Confirmação", JOptionPane.YES_NO_OPTION); // Exibe uma janela de confirmação
				if (resposta == JOptionPane.YES_OPTION) { // Se o usuário confirmar o cancelamento
					itensVendidos.clear(); // Limpa a lista de itens vendidos
					atualizarListaItens(); // Atualiza a lista de itens vendidos
					atualizarTotalVenda(); // Atualiza o total da venda
					tabelaVendas.setVisible(false); // Esconde a tabela de vendas
					clienteAtual = null; // Limpa o cliente atual
					txtCpfCliente.setText("");	 // Limpa o campo de CPF do cliente
					txtDataInicio.setText(""); // Limpa os campos de data
					txtDataFim.setText(""); // Limpa os campos de data
				}				
        	}
        });

        panelVendasRealizadas = new JPanel(new BorderLayout()); // Cria um novo painel para a seção de vendas realizadas
        panelVendasRealizadas.setBorder(BorderFactory.createTitledBorder("Vendas Realizadas")); // Define o título do painel

        modelTabelaVendas = new DefaultTableModel(new Object[]{"ID", "Cliente", "Data", "Total"}, 0); // Modelo da tabela de vendas
        tabelaVendas = new JTable(modelTabelaVendas); // Tabela para exibir as vendas realizadas
        scrollPaneVendas = new JScrollPane(tabelaVendas); // Adiciona a tabela a um painel de rolagem
        panelVendasRealizadas.add(scrollPaneVendas, BorderLayout.CENTER); // Adiciona o painel de rolagem ao painel de vendas realizadas
        tabelaVendas.setVisible(false); // Inicialmente a tabela fica invisível

        JPanel panelFiltroDatas = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Cria um novo painel para o filtro de datas
        lblDataInicio = new JLabel("Data Início (dd/MM/yyyy):"); // Rótulo para o campo de data de início
        txtDataInicio = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Campo de texto para inserir a data de início
        txtDataInicio.setText(""); // Limpa o campo de data de início
        txtDataInicio.setPreferredSize(new Dimension(100, 25)); // Define o tamanho do campo de data de início
        lblDataFim = new JLabel("Data Fim (dd/MM/yyyy):"); // Rótulo para o campo de data de fim
        txtDataFim = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // Campo de texto para inserir a data de fim
        txtDataFim.setText(""); // Limpa o campo de data de fim
        txtDataFim.setPreferredSize(new Dimension(100, 25)); // Define o tamanho do campo de data de fim
        btnBuscarVendasPeriodo = new JButton("Buscar por Período"); // Botão para buscar vendas por período
        btnBuscarVendasPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarVendasPorPeriodo(); // Chama o método para buscar vendas por período
                txtDataInicio.setText(""); // Limpa o campo de data de início
                txtDataFim.setText(""); // Limpa o campo de data de fim
            }
        });
        panelFiltroDatas.add(lblDataInicio); // Adiciona o rótulo de data de início ao painel
        panelFiltroDatas.add(txtDataInicio); // Adiciona o campo de data de início ao painel
        panelFiltroDatas.add(lblDataFim); // Adiciona o rótulo de data de fim ao painel
        panelFiltroDatas.add(txtDataFim); // Adiciona o campo de data de fim ao painel
        panelFiltroDatas.add(btnBuscarVendasPeriodo); // Adiciona o botão de buscar por período ao painel
        panelVendasRealizadas.add(panelFiltroDatas, BorderLayout.NORTH); // Adiciona o painel de filtro de datas ao painel de vendas realizadas
        GroupLayout gl_contentPane = new GroupLayout(contentPane); 
        gl_contentPane.setHorizontalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addComponent(panelCabecalho, GroupLayout.PREFERRED_SIZE, 1324, GroupLayout.PREFERRED_SIZE)
        		.addComponent(panelCompra, GroupLayout.PREFERRED_SIZE, 1324, GroupLayout.PREFERRED_SIZE)
        		.addComponent(panelVendasRealizadas, GroupLayout.PREFERRED_SIZE, 1324, GroupLayout.PREFERRED_SIZE)
        );
        gl_contentPane.setVerticalGroup(
        	gl_contentPane.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_contentPane.createSequentialGroup()
        			.addComponent(panelCabecalho, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(panelCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(panelVendasRealizadas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        horizontalStrut_30 = Box.createHorizontalStrut(20); // Adiciona um espaço horizontal entre os componentes
        panelCabecalho.add(horizontalStrut_30);
        GroupLayout gl_panelCompra = new GroupLayout(panelCompra);
        gl_panelCompra.setHorizontalGroup(
        	gl_panelCompra.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(5)
        			.addComponent(lblIdProduto)
        			.addGap(5)
        			.addComponent(txtIdProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(btnAdicionarItem)
        			.addGap(5)
        			.addComponent(scrollPaneItens, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(lblTotalVenda)
        			.addGap(5)
        			.addComponent(lblCpfCliente)
        			.addGap(5)
        			.addComponent(txtCpfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGap(5)
        			.addComponent(btnBuscarCliente)
        			.addGap(5)
        			.addComponent(btnFinalizarVenda)
        			.addGap(5)
        			.addComponent(btnNewButton_1))
        );
        gl_panelCompra.setVerticalGroup(
        	gl_panelCompra.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(73)
        			.addComponent(lblIdProduto))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(70)
        			.addComponent(txtIdProduto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(68)
        			.addComponent(btnAdicionarItem))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(5)
        			.addComponent(scrollPaneItens, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(70)
        			.addComponent(lblTotalVenda))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(73)
        			.addComponent(lblCpfCliente))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(70)
        			.addComponent(txtCpfCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(68)
        			.addComponent(btnBuscarCliente))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(68)
        			.addComponent(btnFinalizarVenda))
        		.addGroup(gl_panelCompra.createSequentialGroup()
        			.addGap(68)
        			.addComponent(btnNewButton_1))
        );
        panelCompra.setLayout(gl_panelCompra); // Define o layout do painel de compra
        contentPane.setLayout(gl_contentPane); // Define o layout do painel principal

        setVisible(true); // Torna a janela visível
    }

    private void buscarVendasPorPeriodo() {
    	// Obtém as datas de início e fim a partir dos campos de texto
        String dataInicioStr = txtDataInicio.getText(); // Data de início
        String dataFimStr = txtDataFim.getText(); // Data de fim
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato das datas

        if (dataInicioStr.trim().isEmpty() || dataFimStr.trim().isEmpty()) { // Verifica se os campos de data estão preenchidos
            JOptionPane.showMessageDialog(this, "Por favor, preencha as datas de início e fim.", "Aviso", JOptionPane.WARNING_MESSAGE); // Exibe mensagem de aviso
            tabelaVendas.setVisible(false); // Esconde a tabela se não houver filtro
            return; // Retorna se os campos de data não estiverem preenchidos
        }

        try { 
            LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter); // Converte a string da data de início para LocalDate
            LocalDate dataFim = LocalDate.parse(dataFimStr, formatter); // Converte a string da data de fim para LocalDate

            List<Venda> vendasFiltradas = new ArrayList<>(); // Lista para armazenar as vendas filtradas
            List<Venda> todasVendas = vendaController.listarVendas(); 	// Obtém todas as vendas realizadas

            for (Venda venda : todasVendas) { // Itera sobre todas as vendas
                if (!venda.getDataVenda().isBefore(dataInicio) && !venda.getDataVenda().isAfter(dataFim)) {  // Verifica se a data da venda está dentro do intervalo
                    vendasFiltradas.add(venda); // Adiciona a venda à lista filtrada se estiver dentro do intervalo de datas
                }
            }

            atualizarTabelaVendas(vendasFiltradas); // Atualiza a tabela com as vendas filtradas
            tabelaVendas.setVisible(true); // Torna a tabela visível após a filtragem

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro se o formato da data estiver incorreto
            tabelaVendas.setVisible(false); // Esconde a tabela em caso de erro
        }
    }

    private void buscarCliente() {
    			// Obtém o CPF do cliente a partir do campo de texto
        String cpf = txtCpfCliente.getText(); // CPF do cliente
        if (!cpf.trim().isEmpty()) { // Verifica se o campo de CPF não está vazio
            clienteAtual = clienteController.buscarCliente(cpf); // Busca o cliente pelo CPF
            if (clienteAtual != null) { // Se o cliente for encontrado
                JOptionPane.showMessageDialog(this, "Cliente encontrado: " + clienteAtual.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE); // Exibe mensagem de sucesso com o nome do cliente
            } else { // Se o cliente não for encontrado
                JOptionPane.showMessageDialog(this, "Cliente não encontrado com o CPF informado.", "Aviso", JOptionPane.WARNING_MESSAGE); // Exibe mensagem de aviso se o cliente não for encontrado
                clienteAtual = null; // Limpa o cliente atual se não for encontrado
            }
        } else { // Se o campo de CPF estiver vazio
            JOptionPane.showMessageDialog(this, "Por favor, digite o CPF do cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void adicionarItemVenda() {
			// Obtém o ID do produto a partir do campo de texto
        try {
            int idProduto = Integer.parseInt(txtIdProduto.getText()); // ID do produto
            Produto produto = produtoController.buscarProduto(idProduto); // Busca o produto pelo ID
            if (produto != null) { // Se o produto for encontrado
                itensVendidos.add(produto); // Adiciona o produto à lista de itens vendidos
                atualizarListaItens(); // Atualiza a lista de itens vendidos
                txtIdProduto.setText(""); // Limpa o campo de ID do produto
                atualizarTotalVenda(); // Atualiza o total da venda
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado com o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro se o produto não for encontrado
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um ID de produto válido.", "Erro", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro se o ID do produto não for válido
        }
    }

    private void atualizarListaItens() {
		// Atualiza a lista de itens vendidos na interface
        modelListaItens.clear(); // Limpa a lista atual
        for (Produto produto : itensVendidos) { // Itera sobre os itens vendidos
            modelListaItens.addElement(produto); // Adiciona o produto à lista
        }
    }

    private void atualizarTotalVenda() {
		// Atualiza o total da venda na interface
        totalVenda = 0.0; // Inicializa o total da venda
        for (Produto produto : itensVendidos) { // Itera sobre os itens vendidos
            totalVenda += produto.getPrecoVenda(); // Soma o preço de venda do produto ao total
        }
        lblTotalVenda.setText(String.format("Total: R$ %.2f", totalVenda)); // Atualiza o rótulo do total da venda
    }

    private void finalizarVenda() {
		// Finaliza a venda e registra no sistema
        if (!itensVendidos.isEmpty()) { // Verifica se há itens vendidos
            if (clienteAtual != null) { // Verifica se o cliente foi encontrado
                try {
                    vendaController.realizarVenda(clienteAtual, new ArrayList<>(itensVendidos)); // Realiza a venda com os itens vendidos
                    JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso para o cliente: " + clienteAtual.getNome() + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE); // Exibe mensagem de sucesso
                    itensVendidos.clear(); // Limpa a lista de itens vendidos após a venda
                    atualizarListaItens(); // Atualiza a lista de itens vendidos
                    atualizarTotalVenda(); // Atualiza o total da venda
                    atualizarTabelaVendas(new ArrayList<>()); // Limpa a tabela após a venda
                    tabelaVendas.setVisible(false); // Esconde a tabela após a venda
                    clienteAtual = null; // Limpa o cliente atual
                    txtCpfCliente.setText(""); // Limpa o campo de CPF do cliente
                    txtDataInicio.setText(""); // Limpa os campos de data
                    txtDataFim.setText(""); // Limpa os campos de data
                } catch (RuntimeException e) { // Captura exceções em tempo de execução
                    JOptionPane.showMessageDialog(this, "Erro ao finalizar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE); // Exibe mensagem de erro se ocorrer um erro ao finalizar a venda
                }
            } else { // Se o cliente não foi encontrado
                JOptionPane.showMessageDialog(this, "Por favor, informe e busque o cliente antes de finalizar a venda.", "Aviso", JOptionPane.WARNING_MESSAGE); // Exibe mensagem de aviso se o cliente não for encontrado
            }
        } else { // Se não houver itens vendidos
            JOptionPane.showMessageDialog(this, "Adicione itens à venda antes de finalizar.", "Aviso", JOptionPane.WARNING_MESSAGE); // Exibe mensagem de aviso se não houver itens vendidos
        }
    }

    private void atualizarTabelaVendas(List<Venda> vendas) {
		// Atualiza a tabela de vendas realizadas na interface
        modelTabelaVendas.setRowCount(0); // Limpa a tabela atual
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato da data
        for (Venda venda : vendas) { // Itera sobre as vendas
            String clienteNome = (venda.getCliente() != null) ? venda.getCliente().getNome() : "Não Informado"; // Obtém o nome do cliente da venda
            modelTabelaVendas.addRow(new Object[]{ // Adiciona uma nova linha à tabela com os dados da venda
                    venda.getId(), // ID da venda
                    clienteNome, // Nome do cliente
                    venda.getDataVenda().format(formatter), 	// Data da venda formatada
                    venda.getProdutos().stream().mapToDouble(Produto::getPrecoVenda).sum() // Total da venda
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    VendasView frame = new VendasView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}