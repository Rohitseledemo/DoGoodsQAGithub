package pages;
import java.util.*;

public class RandomGenerator extends BasePage {
    List<Integer> randomNumbersList;
    public RandomGenerator() {

   }
   public List<Integer> generateRandomIndexes(int count) {
       Random random = new Random();
       Set<Integer> uniqueMenus = new HashSet<>();
       while (uniqueMenus.size() < count) {
           uniqueMenus.add(random.nextInt(15));
       }
       List<Integer> randomNumbersList = new ArrayList<>(uniqueMenus);
       return randomNumbersList;
   }
   }

