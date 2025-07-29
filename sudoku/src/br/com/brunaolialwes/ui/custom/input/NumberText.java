package br.com.brunaolialwes.ui.custom.input;

import br.com.brunaolialwes.model.Space;
import br.com.brunaolialwes.service.EventEnum;
import br.com.brunaolialwes.service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

import static br.com.brunaolialwes.service.EventEnum.CLEAR_SPACE;

public class NumberText extends JTextField implements EventListener {
    private final Space space;

    public NumberText(final Space space){
        this.space = space;
        var dimension = new Dimension(50,50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Times news roman", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!space.isFIXED());

        if(space.isFIXED()){
            this.setText(space.getActual().toString());
        }

        this.getDocument().addDocumentListener(new DocumentListener() {

            private void changeSpace(){
                if (getText().isEmpty()){
                    space.clearSpace();
                    return;
                }
                space.setActual(Integer.parseInt(getText()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSpace();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSpace();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSpace();

            }
        });

    }

    @Override
    public void update(EventEnum eventType) {
        if(eventType.equals(CLEAR_SPACE) && (this.isEnabled())){
            this.setText("");
        }
    }
}
