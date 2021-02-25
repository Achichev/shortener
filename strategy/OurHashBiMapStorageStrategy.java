package shortener.strategy;

import java.util.HashMap;

/*
 * This strategy uses 2 HashMaps for data storage in order to return keys (id) from Map faster,
 * since to return key (id) for value (string) takes a lot longer than getting value for key.
 * The first Map stores KEY-VALUE match, and the second one - VALUE-KEY match.
 */
public class OurHashBiMapStorageStrategy implements StorageStrategy {
    private HashMap<Long, String> k2v = new HashMap<>();
    private HashMap<String, Long> v2k = new HashMap<>();


    @Override
    public boolean containsKey(Long key) {
        return k2v.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return v2k.containsKey(value);
    }

    @Override
    public void put(Long key, String value) {
        k2v.put(key, value);
        v2k.put(value, key);
    }

    @Override
    public Long getKey(String value) {
        return v2k.get(value);
    }

    @Override
    public String getValue(Long key) {
        return k2v.get(key);
    }
}
