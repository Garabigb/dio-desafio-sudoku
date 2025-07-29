package br.com.brunaolialwes.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CheckGameStatusButton extends JButton {

    public CheckGameStatusButton (final ActionListener actionListener){
        this.setText("Verificar");
        this.addActionListener(actionListener);

    }

}
