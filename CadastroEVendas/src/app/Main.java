package app;

import java.awt.EventQueue;

import view.LoginView;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginView login = new LoginView();
                login.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}