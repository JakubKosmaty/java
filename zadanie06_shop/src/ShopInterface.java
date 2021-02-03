import java.util.Map;

public interface ShopInterface {
    /**
     * Typ wyliczeniowy reprezentujÄcy zachowanie klientĂłw sklepu.
     */
    public static enum Customer {
        PATIENT, IMPATIENT;
    }

    /**
     * Zlecenie zakupu produktu productName w iloĹci quantity. W przypadku, gdy
     * zakup zgĹasza niecierpliwy klient metoda wykonywana jest na podstawie
     * aktualnego stanu magazynu. JeĹli po zakup zgĹasza siÄ klient cierpliwy, to w
     * przypadku, gdy towar jest dostÄpny metoda koĹczy siÄ natychmiast, lecz, gdy
     * towaru brak, to wÄtek wykonujÄcy metodÄ zostaje wstrzymany do chwili
     * pojawienia siÄ w sklepie wystarczajÄcej iloĹci sztuk produktu. Metoda nigdy
     * nie zakoĹczy siÄ odpowiedziÄ false dla klienta cierpliwego. Klient moĹźe
     * zgĹosiÄ chÄÄ zakupu produktu, ktĂłrego jeszcze nie ma w magazynie sklepu.
     * IloĹÄ sztuk produktu wydana klientowi zmniejsza stan magazynu.
     * Po zakoĹczeniu metody purchase stan magazynu musi uwzglÄdniaÄ wykonanÄ
     * transakcjÄ.
     *
     * @param productName  nazwa produktu
     * @param quantity     iloĹÄ sztuk produktu
     * @param customerType typ klienta
     * @return true - towar zostaĹ klientowi sprzedany, false - brak towaru
     */
    public boolean purchase(String productName, int quantity, Customer customerType) throws InterruptedException;

    /**
     * Dostawa produktu do sklepu. Do sklepu dostarczany jest towar o podanej nazwie
     * i w podanej iloĹci. JeĹli na dostarczony wĹaĹnie towar oczekujÄ klienci
     * cierpliwi, to dostawa powoduje zakoĹczenie ich oczekiwania (oczywiĹcie, o ile
     * sztuk towaru wystarczy). Po zakoĹczeniu metody stan magazynu musi odzwierciedlaÄ
     * wykonanÄ dostawÄ.
     *
     * @param productName nazwa produktu
     * @param quantity    iloĹÄ sztuk produktu
     */
    public void deliveryOfGoods(String productName, int quantity);

    /**
     * Aktualny stan magazynu. Stan magazynu udostÄpniany jest jako mapa, ktĂłrej
     * kluczem jest nazwa produktu. WartoĹci wskazywane przez klucze to iloĹÄ sztuk
     * danego produktu znajdujÄca siÄ w danej chwili na magazynie.
     *
     * @return stan magazynu w postaci mapy.
     */
    public Map<String, Integer> stockStatus();
}