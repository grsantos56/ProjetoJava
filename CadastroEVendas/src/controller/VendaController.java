package controller;

import dao.VendaDAO;
import model.Cliente;
import model.Produto;
import model.Venda;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador responsável por orquestrar o processo de registro de vendas,
 * aplicando regras de negócio como verificar a disponibilidade de estoque
 * antes de permitir a venda. Interage com a camada de acesso a dados (VendaDAO)
 * para persistir as informações da venda.
 */
public class VendaController {

    // Instância da classe VendaDAO para realizar as operações de acesso aos dados da venda.
    private VendaDAO dao = new VendaDAO();

    /**
     * Método para realizar uma nova venda no sistema.
     * Recebe o cliente, a lista de produtos a serem vendidos e a data da venda.
     * Antes de registrar a venda, verifica se todos os produtos possuem estoque suficiente.
     * Se algum produto não tiver estoque, uma exceção em tempo de execução é lançada.
     * Caso contrário, cria um objeto Venda e o envia para a camada de acesso a dados (DAO)
     * para ser persistido no banco de dados.
     *
     * @param cliente  o cliente que está realizando a compra.
     * @param produtos a lista de produtos que estão sendo vendidos.
     */
    public void realizarVenda(Cliente cliente, List<Produto> produtos) {
        // Verifica se os produtos possuem estoque suficiente para a venda.
        for (Produto p : produtos) {
            if (p.getEstoque() < 1) {
                // Se algum produto não tiver estoque (quantidade menor que 1), lança uma exceção.
                throw new RuntimeException("Produto sem estoque: " + p.getNome());
            }
        }

        // Cria um novo objeto Venda com o cliente, a lista de produtos e a data atual.
        Venda venda = new Venda(null, cliente, produtos, LocalDate.now());
        // Chama o método 'registrarVenda' do DAO para persistir a venda no banco de dados
        // e atualizar o estoque dos produtos vendidos.
        dao.registrarVenda(venda);
    }

    /**
     * Método para listar todas as vendas registradas no sistema.
     * Chama o método 'listarTodas' do DAO para obter uma lista de todas as vendas do banco de dados,
     * incluindo as informações do cliente e dos produtos de cada venda.
     *
     * @return uma lista de todos os objetos Venda registrados no sistema.
     */
    public List<Venda> listarVendas() {
        // Chama o método 'listarTodas' do DAO para obter a lista de todas as vendas com seus detalhes.
        return dao.listarTodas();
    }
}