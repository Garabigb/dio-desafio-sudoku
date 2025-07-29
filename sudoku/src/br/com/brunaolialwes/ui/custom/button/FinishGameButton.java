package br.com.brunaolialwes.ui.custom.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class FinishGameButton extends JButton {

    public FinishGameButton (final ActionListener actionListener){
        this.setText("Terminar");
        this.addActionListener(actionListener);

    }
}
