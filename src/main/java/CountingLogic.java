import java.util.HashMap;
import java.util.Map;

public class CountingLogic {
    private Map<String, String> categories;
    private Map<String, Integer> maxCategory = new HashMap<>();

    public CountingLogic(Map<String, String> categories) {
        this.categories = categories;
    }

    public String counting(Purchase purchase) {
        String category = categories.get(purchase.getTitle());
        if (maxCategory.containsKey(category)) {
            int sum = maxCategory.get(category);
            int newSum = sum + purchase.getSum();
            maxCategory.put(category, newSum);
        } else if (category == null) {
            if (maxCategory.containsKey("другое")) {
                int sum = maxCategory.get("другое");
                int newSum = sum + purchase.getSum();
                maxCategory.put("другое", newSum);
            } else {
                maxCategory.put("другое", purchase.getSum());
            }
        } else {
            maxCategory.put(category, purchase.getSum());
        }
        String maxValueCategory = "";
        int maxSum = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> stringIntegerEntry : maxCategory.entrySet()) {
            if (stringIntegerEntry.getValue() > maxSum) {
                maxSum = stringIntegerEntry.getValue();
                maxValueCategory = stringIntegerEntry.getKey();
            }
        }
        return "{\"maxCategory\": {\"category\": \"" + maxValueCategory + "\",\"sum\": " + maxSum + "}}";
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    public Map<String, Integer> getMaxCategory() {
        return maxCategory;
    }
}
