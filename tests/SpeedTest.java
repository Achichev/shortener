package shortener.tests;

import shortener.Helper;
import shortener.Shortener;
import shortener.strategy.HashBiMapStorageStrategy;
import shortener.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * The class is used for testing storage strategies for data access speed by Junit 4.12.
 */
public class SpeedTest {

    //returns time in Ms for returning all IDs for strings out of Set of strings
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Date date = new Date();
        for (String s: strings) {
            ids.add(shortener.getId(s));
        }
        return new Date().getTime() - date.getTime();
    }

    //returns time in Ms for returning all strings for IDs out of Set of IDs
    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Date date = new Date();
        for (Long l: ids) {
            strings.add(shortener.getString(l));
        }
        return new Date().getTime() - date.getTime();
    }

    //tests different Storage Strategies for data access speed
    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids = new HashSet<>();
        long timeToGetIds1 = getTimeToGetIds(shortener1, origStrings, ids);
        long timeToGetIds2 = getTimeToGetIds(shortener2, origStrings, ids);
        //HashMap is slower for key access speed than HashBiMap
        Assert.assertTrue(timeToGetIds1 > timeToGetIds2);

        Set<String> strings = new HashSet<>();
        long timeToGetStrings1 = getTimeToGetStrings(shortener1, ids, strings);
        long timeToGetStrings2 = getTimeToGetStrings(shortener2, ids, strings);
        Assert.assertEquals(timeToGetStrings1, timeToGetStrings2, 30);
    }
}
