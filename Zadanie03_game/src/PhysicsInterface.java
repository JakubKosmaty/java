public interface PhysicsInterface {
    /**
     * Aby poĹoĹźyÄ na planszy obiekt musi on posiadaÄ w najbliĹźszym sÄsiedztwie
     * (prawo/lewo/gĂłra/dĂłĹ) inny blok. Uwaga zaraz po umieszczeniu blok moĹźe zaczÄÄ
     * spadaÄ a nawet wypaĹÄ poza plansze. Wynik metody odnosi siÄ wyĹÄcznie do
     * samej moĹźliwoĹci poĹoĹźenia bloku na planszy.
     *
     * @return true - blok moĹźe byÄ umieszczony tylko w najbliĹźszym sÄsiedztwie
     *         innego bloku, false - blok moĹźe byÄ umieszczony w dowolnym miejscu
     *         planszy.
     */
    public boolean mustBeSupportedFromAnySideToBePlaced();

    /**
     * Blok moĹźe unosiÄ siÄ w powietrzu nie majÄc kontaktu z innymi blokami. W tym
     * przypadku chodzi o istnienie dowolnego innego bloku sÄsiadujÄcego z danym z
     * dowolnej strony (prawo/lewo/gĂłra/dĂłĹ).
     *
     * @return true - blok, ktĂłry nie ma sÄsiada pozostaje na swoim miejscu. false -
     *         blok, ktĂłry nie ma sÄsiada spada.
     */
    public boolean canFloatInTheAirWithoutSupport();

    /**
     * Blok spada jeĹli poniĹźej niego nie znajduje siÄ inny blok.
     *
     * @return true - blok musi byÄ podparty od doĹu aby nie spadĹ. false - blok
     *         niepodparty z doĹu moĹźe unosiÄ siÄ w powietrzu.
     */
    public boolean willFallIfNotSupportedFromBelow();
}