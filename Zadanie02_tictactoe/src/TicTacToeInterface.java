/**
 * Interfejs gry w kĂłĹko i krzyĹźyk.
 *
 */
public interface TicTacToeInterface {
    /**
     * Metoda pozwala na ustalenie rozmiaru kwadratowej planszy. Dla rozmiaru size
     * dozwolone sÄ numery wierszy i kolumn o wartoĹciach od 0 do size -1 . Metoda
     * uruchamiana przed rozgrywkÄ.
     *
     * @param size rozmiar planszy.
     */
    public void setSize(int size);

    /**
     * Metoda pozwala na umimeszczenie w pozycji o wspĂłĹrzÄdnych row i col znaku
     * koĹa.
     *
     * @param col kolumna
     * @param row wiersz
     */
    public void setO(int col, int row);

    /**
     * Metoda pozwala na umimeszczenie w pozycji o wspĂłĹrzÄdnych row i col znaku
     * krzyĹźyka.
     *
     * @param col kolumna
     * @param row wiersz
     */
    public void setX(int col, int row);

    /**
     * Metoda zwraca znak znajdujÄcy siÄ na wskazanej pozycji. Wynikiem moĹźe byÄ
     * tylko "" (pusty ciÄg znakĂłw), "O" - duĹźe o lub "X" duĹźy znak x. JeĹli
     * wskazana za pomocÄ col i row pozycja nie zawiera znaku krzyĹźyka lub koĹa
     * metoda zwraca pusty ciÄg znakĂłw. JeĹli wskazana za pomocÄ col i row pozycja
     * nie istnieje na planszy zwracany jest pusty ciÄg znakĂłw.
     *
     * @param col kolumna
     * @param row wiersz
     * @return informacja o zawartoĹci danego poĹoĹźenia planszy.
     */
    public String get(int col, int row);
}