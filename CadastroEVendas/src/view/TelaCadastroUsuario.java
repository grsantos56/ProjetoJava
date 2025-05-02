package view;

import controller.VendedorController;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario extends JFrame {
    private VendedorController controller;

    public TelaCadastroUsuario() {
        controller = new VendedorController();
    }
}