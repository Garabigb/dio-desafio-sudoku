package br.com.brunaolialwes.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetGameButton extends JButton {

    public ResetGameButton (final ActionListener actionListener){
        this.setText("Resetar");
        this.addActionListener(actionListener);

    }
}
