public interface StrangeWorldInterface {
    /**
     * Metoda ustawia rozmiar planszy do gry.
     *
     * @param size rozmiar planszy. Dozwolone indeksy od 0 do size-1.
     */
    public void setSize(int size);

    /**
     * Metoda prĂłbuje dodaÄ na pozycji row/col nowy blok. W zaleĹźnoĹci od aktualnego
     * stanu oraz wĹaĹciwoĹci bloku, blok jest lub nie jest dodawany. Dodanie bloku
     * powoduje zwrĂłcenie przez metodÄ prawdy. JeĹli blok zostanie dodany konieczne
     * jest uwzglÄdnienie jego wĹasnoĹci - moĹźe siÄ okazaÄ, Ĺźe dodany blok
     * natychmiast doĹwiadczy grawitacji i spadnie poniĹźej wiersza, w ktĂłrym zostaĹ
     * dodany, a nawet wypadnie poza planszÄ.
     *
     * @param row   wiersz, w ktĂłrym nastÄpuje prĂłba dodania bloku.
     * @param col   kolumna, w ktĂłrej nastÄpuje prĂłba dodania bloku.
     * @param block dodawany blok
     * @return true - blok zostaĹ dodany na planszÄ, wartoĹÄ zwracana nawet jeĹli
     *         blok w wyniku dziaĹania grawitacji wypadnie poza planszÄ, false -
     *         blok nie zostaĹ dodany.
     */
    public boolean tryAdd(int row, int col, PhysicsInterface block);

    /**
     * Metoda zleca usuniÄcie bloku na pozycji row/col. JeĹli blok zostanie usuniÄty
     * referencja do usuwanego bloku zostaje zwrĂłcona. Po usuniÄcie bloku naleĹźy
     * sprawdziÄ konsekwencje tego dziaĹania na pozostaĹe w pobliĹźu bloki (czyli np.
     * ich upadek).
     *
     * @param row wiersz, w ktĂłrym blok ma byÄ usuniÄty
     * @param col kolumna, w ktĂłrej blok ma byÄ usuniÄty
     * @return obiekt, ktĂłry znajdowaĹ siÄ na pozycji row/col. JeĹli na wskazanej
     *         pozycji nie byĹo obiektu metoda zwraca NULL.
     */
    public PhysicsInterface delAndGet(int row, int col);

    /**
     * Metoda zwraca obiekt znajdujÄcy siÄ na planszy na pozycji row/col.
     *
     * @param row wiersz
     * @param col kolumna
     * @return obiektu na pozycji row/col
     */
    public PhysicsInterface get(int row, int col);
}