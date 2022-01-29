import calc.Calculator;
import converter.PostfixConverter;
import org.junit.Test;
import validator.Validator;

import static org.junit.Assert.*;

public class ComponentsTest {
    @Test
    public void checkValidator() {
        Validator validator = new Validator();
        assertFalse(validator.check(" 1+1"));
        assertFalse(validator.check("(1+1) "));
        assertFalse(validator.check("1 +1"));
        assertFalse(validator.check("a(1+1)"));
        assertFalse(validator.check("1+1o"));
        assertFalse(validator.check("1++1"));
        assertFalse(validator.check("1^1"));
        assertFalse(validator.check("1+(--1)"));
        assertFalse(validator.check("(1+(-1)"));
        assertFalse(validator.check("1+(-1))"));

        assertTrue(validator.check("(36+15)*2"));
        assertTrue(validator.check("(67-34)*13"));
        assertTrue(validator.check("1200/2+(-36/6)"));
        assertTrue(validator.check("-812+252/2"));
        assertTrue(validator.check("42*2+(-12)"));
        assertTrue(validator.check("(59+84)*3"));
        assertTrue(validator.check("201/(-3)+539"));
        assertTrue(validator.check("34*(-87-46)"));
        assertTrue(validator.check("(243-168)+5*6"));
    }

    @Test
    public void checkConverter() {
        PostfixConverter converter = new PostfixConverter();
        assertEquals("36 15 + 2*", converter.convert("(36+15)*2"));
        assertEquals("67 34 - 13*", converter.convert("(67-34)*13"));
        assertEquals("1200 2 /0 36 6 /-+", converter.convert("1200/2+(-36/6)"));
        assertEquals("0 812 -252 2/+", converter.convert("-812+252/2"));
        assertEquals("42 2 *0 12 -+", converter.convert("42*2+(-12)"));
        assertEquals("59 84 + 3*", converter.convert("(59+84)*3"));
        assertEquals("201 0 3 - /539+", converter.convert("201/(-3)+539"));
        assertEquals("34 0 87 -46 -*", converter.convert("34*(-87-46)"));
        assertEquals("243 168 - 5 6*+", converter.convert("(243-168)+5*6"));
    }

    @Test
    public void checkCalculator() {
        Calculator calculator = new Calculator();
        assertEquals(102, calculator.calculate("36 15 + 2*"), 0.001);
        assertEquals(429, calculator.calculate("67 34 - 13*"), 0.001);
        assertEquals(594, calculator.calculate("1200 2 /0 36 6 /-+"), 0.001);
        assertEquals(-686, calculator.calculate("0 812 -252 2/+"), 0.001);
        assertEquals(72, calculator.calculate("42 2 *0 12 -+"), 0.001);
        assertEquals(429, calculator.calculate("59 84 + 3*"), 0.001);
        assertEquals(472, calculator.calculate("201 0 3 - /539+"), 0.001);
        assertEquals(-4522, calculator.calculate("34 0 87 -46 -*"), 0.001);
        assertEquals(105, calculator.calculate("243 168 - 5 6*+"), 0.001);
    }
}
