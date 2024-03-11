public class Deskovka {
    private String nazevDeskovky;
    private boolean mameKoupenou;
    private int oblibenost;

    public Deskovka(String nazevDeskovky, boolean mameKoupenou, int oblibenost) {
        this.nazevDeskovky = nazevDeskovky;
        this.mameKoupenou = mameKoupenou;
        this.oblibenost = oblibenost;
    }

    public String getNazevDeskovky() {
        return nazevDeskovky;
    }

    public void setNazevDeskovky(String nazevDeskovky) {
        this.nazevDeskovky = nazevDeskovky;
    }

    public boolean isMameKoupenou() {
        return mameKoupenou;
    }

    public void setMameKoupenou(boolean mameKoupenou) {
        this.mameKoupenou = mameKoupenou;
    }

    public int getOblibenost() {
        return oblibenost;
    }

    public void setOblibenost(int oblibenost) {
        this.oblibenost = oblibenost;
    }


}
