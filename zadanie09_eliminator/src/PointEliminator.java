import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interfejs narzÄdzia do eliminacji punktĂłw.
 *
 */
public interface PointEliminator extends Supplier<Point>, Consumer<Supplier<Point>> {
    /**
     * Metoda do ustawienia funkcji pozwalajÄcej na policzenie odlegĹoĹci pomiÄdzy
     * dowoma punktami.
     *
     * @param distance odlegĹoĹÄ
     */
    public void setDistanceCalculator(BiFunction<Point, Point, Double> distance);
}