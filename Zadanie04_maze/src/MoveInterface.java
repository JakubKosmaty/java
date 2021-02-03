public interface MoveInterface {

    /**
     * Zlecenie ruchu w podanym kierunku. Wykonanie metody bez zgĹoszenia przez niÄ
     * wyjÄtku oznacza, Ĺźe ruch zostaĹ wykonany.
     *
     * @param dir kierunek ruchu
     * @throws RoomOnFireException      ostrzeĹźenie przed poĹźarem w pomieszczeniu,
     *                                  do ktĂłrego ruch zlecono.
     * @throws WallException            ruch nie moĹźe byÄ wykonany, bo na drodze
     *                                  jest Ĺciana.
     * @throws HereIsTheFinishException koniec poszukiwaĹ wyjĹcia - SUKCESS!
     */
    public void move(Direction dir) throws RoomOnFireException, WallException, HereIsTheFinishException;

    /**
     * UĹźycie gaĹnicy. JeĹli gaĹnica jest juĹź oprĂłĹźniona zgĹaszany jest wyjÄtek.
     *
     * @throws FireExtinguisherIsWornOutException prĂłba uĹźycia niesprawnej gaĹnicy.
     */
    public void useFireExtinguisher() throws FireExtinguisherIsWornOutException;
}