package br.com.brunaolialwes.model;

public class Space {
    private Integer actual;
    private final int EXPECTED;
    private final boolean FIXED;

    public Space(final int EXPECTED, final boolean FIXED){
        this.EXPECTED = EXPECTED;
        this.FIXED = FIXED;

        if(FIXED) actual = EXPECTED;

    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        if(isFIXED()) return;
        this.actual = actual;
    }

    public int getEXPECTED() {
        return EXPECTED;
    }

    public boolean isFIXED() {
        return FIXED;
    }

    public void clearSpace(){
        setActual(null);
    }
}
