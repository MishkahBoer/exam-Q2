
    import org.junit.Test;
import static org.junit.Assert.*;

    public class ProductCalculationsTest {

        @Test
        public void GetVat_WithValidData_ReturnsCorrectValue() {
            double price = 3500;
            double expectedVat = 525.0;
            ProductCalculations calc = new ProductCalculations(price);
            double actualVat = calc.GetVat();
            assertEquals(expectedVat, actualVat, 0.001);
        }

        @Test
        public void GetTotal_WithValidData_ReturnsCorrectValue() {
            double price = 3500;
            double expectedTotal = 4200.0;
            ProductCalculations calc = new ProductCalculations(price);
            double actualTotal = calc.GetTotal();
            assertEquals(expectedTotal, actualTotal, 0.001);
        }
    }
}
