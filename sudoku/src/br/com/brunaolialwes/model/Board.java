package br.com.brunaolialwes.model;

import java.util.Collection;
import java.util.List;

import static br.com.brunaolialwes.model.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {
    private final List<List<Space>> SPACES;

    public Board(final List<List<Space>> spaces){
        this.SPACES = spaces;
    }

    public List<List<Space>> getSPACES() {
        return SPACES;
    }

    public GameStatusEnum getStatus(){
        if(SPACES.stream().flatMap(Collection::stream).noneMatch(s->!s.isFIXED()
                && nonNull(s.getActual()))){
            return NON_STARTED;

        }
        if(SPACES.stream().flatMap(Collection::stream).anyMatch(s->isNull(s.getActual()))){
            return INCOMPLETE;

        }
        return COMPLETE;
    }

    public boolean hasError(){
        if (getStatus() == NON_STARTED){
            return false;
        }
        return SPACES.stream().flatMap(Collection::stream)
                .anyMatch(s-> nonNull(s.getActual())
                        && !s.getActual().equals(s.getEXPECTED()));
    }

    public boolean changeValue(final int col, final int row, final int value){
        var space = SPACES.get(col).get(row);
        if(space.isFIXED()){
            return false;
        }

        space.setActual(value);
        return true;
    }

    public boolean cleanValue(final int col, final int row){
        var space = SPACES.get(col).get(row);
        if(space.isFIXED()){
            return false;
        }

        space.clearSpace();
        return true;
    }

    public void reset(){
        SPACES.stream().flatMap(Collection::stream).forEach(Space::clearSpace);
    }

    public boolean gameIsFinished(){
        return !hasError() && getStatus().equals(COMPLETE);
    }


}
