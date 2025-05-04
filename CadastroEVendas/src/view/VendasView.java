package view;

import controller.ClienteController; // Importe o ClienteController
import controller.ProdutoController;
import controller.VendaController;
import model.Cliente; // Importe a classe Cliente
import model.Produto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendasView extends JFrame {

    private JPanel contentPane;
    private JLabel lblNomeLoja;
    private JLabel lblVendedorAtual;
    private JList<Produto> listaItens;
    private DefaultListModel<Produto> modelListaItens;
    private JTextField txtIdProduto;
    private JButton btnAdicionarItem;
    private JButton btnFinalizarVenda;
    private JLabel lblTotalVenda;
    private JScrollPane scrollPaneVendas;
    private JTable tabelaVendas;
    private DefaultTableModel modelTabelaVendas;
    private JButton btnBuscarVendasPeriodo;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;
    private JLabel lblDataInicio;
    private JLabel lblDataFim;
    private JLabel lblCpfCliente; // Novo label para o CPF
    private JTextField txtCpfCliente; // Novo campo de texto para o CPF
    private JButton btnBuscarCliente; // Novo botão para buscar cliente

    private ProdutoController produtoController;
    private VendaController vendaController;
    private ClienteController clienteController; // Novo ClienteController
    private String nomeLoja = "Nome da Loja";
    private String vendedorAtual = "Vendedor";
    private List<Produto> itensVendidos;
    private double totalVenda;
    private Cliente clienteAtual = null; // Para armazenar o cliente selecionado para a venda

    public VendasView() {
        produtoController = new ProdutoController();
        vendaController = new VendaController();
        clienteController = new ClienteController(); // Inicializa o ClienteController
        itensVendidos = new ArrayList<>();
        totalVenda = 0.0;
        initComponents();
        atualizarListaItens();
        atualizarTabelaVendas(vendaController.listarVendas());
    }

    private void initComponents() {
        setTitle("Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 550); // Aumentei um pouco a largura e altura
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(5, 5));

        JPanel panelCabecalho = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblNomeLoja = new JLabel(nomeLoja);
        lblNomeLoja.setFont(new Font("Arial", Font.BOLD, 20));
        lblVendedorAtual = new JLabel("Vendedor: " + vendedorAtual);
        panelCabecalho.add(lblNomeLoja);
        panelCabecalho.add(Box.createHorizontalStrut(20));
        panelCabecalho.add(lblVendedorAtual);
        contentPane.add(panelCabecalho, BorderLayout.NORTH);

        JPanel panelVenda = new JPanel(new FlowLayout(FlowLayout.LEFT));
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

        panelVenda.add(txtIdProduto);
        panelVenda.add(btnAdicionarItem);
        panelVenda.add(scrollPaneItens);
        panelVenda.add(lblTotalVenda);
        panelVenda.add(lblCpfCliente);
        panelVenda.add(txtCpfCliente);
        panelVenda.add(btnBuscarCliente);
        panelVenda.add(btnFinalizarVenda);

        contentPane.add(panelVenda, BorderLayout.WEST);

        JPanel panelVendasRealizadas = new JPanel(new BorderLayout());
        panelVendasRealizadas.setBorder(BorderFactory.createTitledBorder("Vendas Realizadas"));

        modelTabelaVendas = new DefaultTableModel(new Object[]{"ID", "Cliente", "Data", "Total"}, 0);
        tabelaVendas = new JTable(modelTabelaVendas);
        scrollPaneVendas = new JScrollPane(tabelaVendas);
        panelVendasRealizadas.add(scrollPaneVendas, BorderLayout.CENTER);

        JPanel panelFiltroDatas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblDataInicio = new JLabel("Data Início (dd/MM/yyyy):");
        txtDataInicio = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        txtDataInicio.setPreferredSize(new Dimension(100, 25));
        lblDataFim = new JLabel("Data Fim (dd/MM/yyyy):");
        txtDataFim = new JFormattedTextField(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        txtDataFim.setPreferredSize(new Dimension(100, 25));
        btnBuscarVendasPeriodo = new JButton("Buscar por Período");
        btnBuscarVendasPeriodo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implementar a lógica de busca por período aqui
                JOptionPane.showMessageDialog(VendasView.this, "Funcionalidade de busca por período ainda não implementada.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelFiltroDatas.add(lblDataInicio);
        panelFiltroDatas.add(txtDataInicio);
        panelFiltroDatas.add(lblDataFim);
        panelFiltroDatas.add(txtDataFim);
        panelFiltroDatas.add(btnBuscarVendasPeriodo);
        panelVendasRealizadas.add(panelFiltroDatas, BorderLayout.NORTH);

        contentPane.add(panelVendasRealizadas, BorderLayout.CENTER);

        setVisible(true);
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
                    atualizarTabelaVendas(vendaController.listarVendas());
                    clienteAtual = null; // Limpa o cliente atual após a venda
                    txtCpfCliente.setText(""); // Limpa o campo de CPF
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

    private void atualizarTabelaVendas(List<model.Venda> vendas) {
        modelTabelaVendas.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (model.Venda venda : vendas) {
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