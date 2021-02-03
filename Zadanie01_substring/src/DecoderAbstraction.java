public abstract class DecoderAbstraction {
    /**
     * Metoda pozwala na przekazanie ciÄgu znakĂłw, ktĂłry ma zostaÄ odkodowany.
     * SposĂłb odkodowywania znakĂłw przedstawiony jest w opisie zadania.
     *
     * @see <a href="https://zti.if.uj.edu.pl/Piotr.Oramus/dydaktyka/Java/Zadania/">Strona WWW kursu</a>
     * @param input ciÄg do odkodowania
     */
    public abstract void decode(String input);

    /**
     * Metoda umoĹźliwia pobranie ciÄgu znakĂłw znajdujÄcego siÄ w buforze. DziaĹanie
     * metody nigdy nie moĹźe doprowadziÄ do pojawienia siÄ wyniku null. Gdy brak
     * znakĂłw w buforze metoda zwraca pusty ciÄg ("").
     *
     * @return aktualny stan bufora
     */
    public abstract String getBuffer();

    /**
     * Metoda resetuje dekoder do stanu poczÄtkowego. Wszystkie znaki w buforze sÄ
     * kasowane. Informacje przekazane do chwili wykonania tej metody majÄ zostaÄ
     * zapomniane (np. informacja o tym, Ĺźe nadawany jest podciÄg).
     */
    public abstract void reset();
}