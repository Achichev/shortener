package shortener;

import shortener.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    //returns a set of keys (id) for a test
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> set = new HashSet<>();
        for (String s: strings) {
            set.add(shortener.getId(s));
        }
        return set;
    }

    //returns a set of values (strings) for a test
    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> set = new HashSet<>();
        for (Long l: keys) {
            set.add(shortener.getString(l));
        }
        return set;
    }

    //makes a test of different strategies that is based on measuring time that
    // takes to execute getIds() and getStrings() methods
    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> stringSet = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            stringSet.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        Date date = new Date();
        Set<Long> longHashSet = getIds(shortener, stringSet);
        long getIdTime = new Date().getTime() - date.getTime();
        Helper.printMessage("getIds runTime " + getIdTime);

        Date date1 = new Date();
        Set<String> stringHashSet = getStrings(shortener, longHashSet);
        long getStringTime = new Date().getTime() - date1.getTime();
        Helper.printMessage("getStrings runTime " + getStringTime);

        if (stringSet.size() == stringHashSet.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }

    }

    public static void main(String[] args) {
        
//        testStrategy(new HashMapStorageStrategy(), 10000);
//        testStrategy(new OurHashMapStorageStrategy(), 10000);
//        testStrategy(new FileStorageStrategy(), 100);
//        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
//        testStrategy(new HashBiMapStorageStrategy(), 10000);
//        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);

    }
}
