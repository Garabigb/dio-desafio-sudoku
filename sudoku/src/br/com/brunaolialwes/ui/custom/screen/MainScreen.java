package br.com.brunaolialwes.ui.custom.screen;

import br.com.brunaolialwes.model.Space;
import br.com.brunaolialwes.service.BoardService;
import br.com.brunaolialwes.service.NotifierService;
import br.com.brunaolialwes.ui.custom.button.CheckGameStatusButton;
import br.com.brunaolialwes.ui.custom.button.FinishGameButton;
import br.com.brunaolialwes.ui.custom.button.ResetGameButton;
import br.com.brunaolialwes.ui.custom.frame.MainFrame;
import br.com.brunaolialwes.ui.custom.input.NumberText;
import br.com.brunaolialwes.ui.custom.panel.MainPanel;
import br.com.brunaolialwes.ui.custom.panel.SudokuSector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static br.com.brunaolialwes.service.EventEnum.CLEAR_SPACE;
import static javax.swing.JOptionPane.*;

public class MainScreen {

    private JButton finishGameButton;
    private JButton checkGameStatusButton;
    private JButton resetGameButton;

    private final static Dimension dimension = new Dimension(600,600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    public MainScreen(final Map<String,String> gameConfig){
        this.boardService = new BoardService(gameConfig);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen(){
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);

        for(int r = 0; r < 9; r+=3){
            var endRow = r+2;
            for(int c = 0; c < 9; c+=3){
                var endCol=c+2;
                var spaces = getSpacesFromSector(boardService.getSpaces(), c, r, endCol, endRow);
                JPanel sector = generateSection(spaces);
                mainPanel.add(sector);
            }
        }

        addResetButton(mainPanel);
        addCheckGameStatusButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Space> getSpacesFromSector(final List<List<Space>> spaces, final int initColl, final int initRow, final int endCol, final int endRow){
        List<Space> spaceSector = new ArrayList<>();

        for(int r = initRow; r<=endRow; r++){
            for(int c = initColl; c<=endCol; c++){
                spaceSector.add(spaces.get(c).get(r));
            }
        }

        return spaceSector;
    }


    private JPanel generateSection(final List<Space> spaces){
        List<NumberText> fields = new ArrayList<>(spaces.stream().map(NumberText::new).toList());
        fields.forEach(t-> notifierService.subscriber(CLEAR_SPACE, t));
        return new SudokuSector(fields);
    }

    private void addFinishGameButton(JPanel mainPanel) {
         finishGameButton = new FinishGameButton(e -> {
            if(boardService.gameIsFinished()){
                showMessageDialog(null,"Você é o cara");
                resetGameButton.setEnabled(false);
                checkGameStatusButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            }else{
                showMessageDialog(null, "Meu mano tem algo errado, melhore");
            }

        });
        mainPanel.add(finishGameButton);
    }

    private void addCheckGameStatusButton(JPanel mainPanel) {
        checkGameStatusButton = new CheckGameStatusButton(e -> {
            var hasErrors = boardService.hasErrors();
            var gameStatus = boardService.getStatus();
            var message = switch (gameStatus){
                case NON_STARTED -> "O jogo nao foi iniciado";
                case INCOMPLETE -> "O jogo esta incompleto";
                case COMPLETE -> "Jogo completo \\o/";
            };
            message+= hasErrors ? " e contém erros" : "";
            showMessageDialog(null, message);

        });
        mainPanel.add(checkGameStatusButton);
    }

    private void addResetButton(JPanel mainPanel) {
        resetGameButton = new ResetGameButton(e-> {
             var dialogResult = showConfirmDialog(
                     null,
                     "Certeza?",
                     "Limpar o jogo",
                     YES_NO_OPTION,
                     QUESTION_MESSAGE
             );

             if(dialogResult == 0){
                 boardService.reset();
                 notifierService.notify(CLEAR_SPACE);
             }
        });
        mainPanel.add(resetGameButton);
    }
}
