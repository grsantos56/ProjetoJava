package controller;

import dao.VendaDAO;
import model.Cliente;
import model.Produto;
import model.Venda;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador responsável por registrar vendas
 * e garantir que todos os produtos tenham estoque disponível.
 */
public class VendaController {

    private VendaDAO dao = new VendaDAO();

    public void realizarVenda(Cliente cliente, List<Produto> produtos) {
        // Verifica se os produtos possuem estoque suficiente
        for (Produto p : produtos) {
            if (p.getEstoque() < 1) {
                throw new RuntimeException("Produto sem estoque: " + p.getNome());
            }
        }

        Venda venda = new Venda(null, cliente, produtos, LocalDate.now());
        dao.registrarVenda(venda);
    }

    public List<Venda> listarVendas() {
        return dao.listarTodas();
    }
}

