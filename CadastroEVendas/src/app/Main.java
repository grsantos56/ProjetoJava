package app;

import java.awt.EventQueue;

import view.LoginView;

/**
 * @author Gabriel Rodrigues
 * Classe principal da aplicação.
 * Responsável por iniciar a interface de login.
 */
public class Main {
    /**
     * Método principal que inicia a aplicação Java Swing.
     * @param args Argumentos de linha de comando (não utilizados neste caso).
     */
    public static void main(String[] args) {
        // Garante que a interface gráfica seja criada e atualizada na Thread de Despacho de Eventos (EDT).
        EventQueue.invokeLater(() -> {
            try {
                // Cria uma instância da tela de login.
                LoginView login = new LoginView();
                // Torna a tela de login visível.
                login.setVisible(true);
            } catch (Exception e) {
                // Imprime o rastreamento da pilha em caso de exceção durante a inicialização.
                e.printStackTrace();
            }
        });
    }
}