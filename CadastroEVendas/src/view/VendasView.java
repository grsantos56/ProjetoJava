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

    public VendasView() {
        produtoController = new ProdutoController();
        vendaController = new VendaController();
        clienteController = new ClienteController();
        itensVendidos = new ArrayList<>();
        totalVenda = 0.0;
        initComponents();
        atualizarListaItens();
    }

    private void initComponents() {
        setTitle("Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 600); // Aumentei a altura
        setMinimumSize(new Dimension(1360, 720)); // Definindo tamanho mínimo
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

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
        
        btnNewButton = new JButton("SAIR");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					TelaPrincipalView telaPrincipal = new TelaPrincipalView();
					telaPrincipal.setVisible(true);
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

        panelCompra = new JPanel();

        lblIdProduto = new JLabel("ID Produto:");
        txtIdProduto = new JTextField(10);
        btnAdicionarItem = new JButton("Adicionar Item");
        btnAdicionarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarItemVenda();
            }
        });

        modelListaItens = new DefaultListModel<>();
        listaItens = new JList<>(modelListaItens);
        JScrollPane scrollPaneItens = new JScrollPane(listaItens);
        scrollPaneItens.setPreferredSize(new Dimension(250, 150));
        scrollPaneItens.setBorder(BorderFactory.createTitledBorder("Itens da Venda"));

        lblTotalVenda = new JLabel("Total: R$ 0.00");
        lblTotalVenda.setFont(new Font("Arial", Font.BOLD, 16));

        lblCpfCliente = new JLabel("CPF Cliente:");
        txtCpfCliente = new JTextField(15);
        btnBuscarCliente = new JButton("Buscar Cliente");
        btnBuscarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
        
                btnFinalizarVenda = new JButton("Finalizar Venda");
                btnFinalizarVenda.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        finalizarVenda();
                    }
                });
        
        btnNewButton_1 = new JButton("Cancelar Venda");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente cancelar a venda?", "Confirmação", JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION) {
					itensVendidos.clear();
					atualizarListaItens();
					atualizarTotalVenda();
					tabelaVendas.setVisible(false);
					clienteAtual = null;
					txtCpfCliente.setText("");
					txtDataInicio.setText(""); // Limpa os campos de data
					txtDataFim.setText("");
				}				
        	}
        });

        panelVendasRealizadas = new JPanel(new BorderLayout());
        panelVendasRealizadas.setBorder(BorderFactory.createTitledBorder("Vendas Realizadas"));

        modelTabelaVendas = new DefaultTableModel(new Object[]{"ID", "Cliente", "Data", "Total"}, 0);
        tabelaVendas = new JTable(modelTabelaVendas);
        scrollPaneVendas = new JScrollPane(tabelaVendas);
        panelVendasRealizadas.add(scrollPaneVendas, BorderLayout.CENTER);
        tabelaVendas.setVisible(false); // Inicialmente a tabela fica invisível

        JPanel panelFiltroDatas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblDataInicio = new JLabel("Data Início (dd/MM/yyyy):");
        txtDataInicio = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        txtDataInicio.setText("");
        txtDataInicio.setPreferredSize(new Dimension(100, 25));
        lblDataFim = new JLabel("Data Fim (dd/MM/yyyy):");
        txtDataFim = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        txtDataFim.setText("");
        txtDataFim.setPreferredSize(new Dimension(100, 25));
        btnBuscarVendasPeriodo = new JButton("Buscar por Período");
        btnBuscarVendasPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buscarVendasPorPeriodo();
            }
        });
        panelFiltroDatas.add(lblDataInicio);
        panelFiltroDatas.add(txtDataInicio);
        panelFiltroDatas.add(lblDataFim);
        panelFiltroDatas.add(txtDataFim);
        panelFiltroDatas.add(btnBuscarVendasPeriodo);
        panelVendasRealizadas.add(panelFiltroDatas, BorderLayout.NORTH);
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
        
        horizontalStrut_30 = Box.createHorizontalStrut(20);
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
        panelCompra.setLayout(gl_panelCompra);
        contentPane.setLayout(gl_contentPane);

        setVisible(true);
    }

    private void buscarVendasPorPeriodo() {
        String dataInicioStr = txtDataInicio.getText();
        String dataFimStr = txtDataFim.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (dataInicioStr.trim().isEmpty() || dataFimStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha as datas de início e fim.", "Aviso", JOptionPane.WARNING_MESSAGE);
            tabelaVendas.setVisible(false); // Esconde a tabela se não houver filtro
            return;
        }

        try {
            LocalDate dataInicio = LocalDate.parse(dataInicioStr, formatter);
            LocalDate dataFim = LocalDate.parse(dataFimStr, formatter);

            List<Venda> vendasFiltradas = new ArrayList<>();
            List<Venda> todasVendas = vendaController.listarVendas();

            for (Venda venda : todasVendas) {
                if (!venda.getDataVenda().isBefore(dataInicio) && !venda.getDataVenda().isAfter(dataFim)) {
                    vendasFiltradas.add(venda);
                }
            }

            atualizarTabelaVendas(vendasFiltradas);
            tabelaVendas.setVisible(true); // Torna a tabela visível após a filtragem

        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            tabelaVendas.setVisible(false); // Esconde a tabela em caso de erro
        }
    }

    private void buscarCliente() {
        String cpf = txtCpfCliente.getText();
        if (!cpf.trim().isEmpty()) {
            clienteAtual = clienteController.buscarCliente(cpf);
            if (clienteAtual != null) {
                JOptionPane.showMessageDialog(this, "Cliente encontrado: " + clienteAtual.getNome(), "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado com o CPF informado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                clienteAtual = null;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, digite o CPF do cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void adicionarItemVenda() {
        try {
            int idProduto = Integer.parseInt(txtIdProduto.getText());
            Produto produto = produtoController.buscarProduto(idProduto);
            if (produto != null) {
                itensVendidos.add(produto);
                atualizarListaItens();
                txtIdProduto.setText("");
                atualizarTotalVenda();
            } else {
                JOptionPane.showMessageDialog(this, "Produto não encontrado com o ID informado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um ID de produto válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarListaItens() {
        modelListaItens.clear();
        for (Produto produto : itensVendidos) {
            modelListaItens.addElement(produto);
        }
    }

    private void atualizarTotalVenda() {
        totalVenda = 0.0;
        for (Produto produto : itensVendidos) {
            totalVenda += produto.getPrecoVenda();
        }
        lblTotalVenda.setText(String.format("Total: R$ %.2f", totalVenda));
    }

    private void finalizarVenda() {
        if (!itensVendidos.isEmpty()) {
            if (clienteAtual != null) {
                try {
                    vendaController.realizarVenda(clienteAtual, new ArrayList<>(itensVendidos));
                    JOptionPane.showMessageDialog(this, "Venda finalizada com sucesso para o cliente: " + clienteAtual.getNome() + "!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    itensVendidos.clear();
                    atualizarListaItens();
                    atualizarTotalVenda();
                    atualizarTabelaVendas(new ArrayList<>()); // Limpa a tabela após a venda
                    tabelaVendas.setVisible(false);
                    clienteAtual = null;
                    txtCpfCliente.setText("");
                    txtDataInicio.setText(""); // Limpa os campos de data
                    txtDataFim.setText("");
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(this, "Erro ao finalizar venda: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, informe e busque o cliente antes de finalizar a venda.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Adicione itens à venda antes de finalizar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarTabelaVendas(List<Venda> vendas) {
        modelTabelaVendas.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Venda venda : vendas) {
            String clienteNome = (venda.getCliente() != null) ? venda.getCliente().getNome() : "Não Informado";
            modelTabelaVendas.addRow(new Object[]{
                    venda.getId(),
                    clienteNome,
                    venda.getDataVenda().format(formatter),
                    venda.getProdutos().stream().mapToDouble(Produto::getPrecoVenda).sum()
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