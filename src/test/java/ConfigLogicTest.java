import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ConfigLogicTest {

    private CountingLogic countingLogic;
    private Purchase purchase;
    private Map<String, Integer> maxCategory = new HashMap<>();

    @BeforeEach
    public void init() {
        Map<String, String> map = new HashMap<>();
        map.put("булка", "еда");
        map.put("мыло", "быт");
        countingLogic = new CountingLogic(map);
        purchase = new Purchase();
        purchase.setTitle("булка");
        purchase.setDate("2022.02.08");
        purchase.setSum(30);
        maxCategory.put("еда", 30);
    }

    @Test
    public void shouldAddCategory() {
        countingLogic.counting(purchase);

        Assertions.assertEquals(maxCategory, countingLogic.getMaxCategory());
    }

    @Test
    public void shouldAddSum() {
        maxCategory.put("еда", 60);
        countingLogic.counting(purchase);
        countingLogic.counting(purchase);

        Assertions.assertEquals(maxCategory,countingLogic.getMaxCategory());
    }


    @Test
    public void shouldReturnSring() {
        Assertions.assertEquals(String.class,countingLogic.counting(purchase).getClass());
    }

}
