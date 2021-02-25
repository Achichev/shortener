package shortener.strategy;

import com.google.common.collect.HashBiMap;

/*
 * This strategy uses HashBiMap by Google Guava for data storage
 * since HashMap is slower for key access speed than HashBiMap
 */
public class HashBiMapStorageStrategy implements StorageStrategy {
    HashBiMap<Long, String> data = HashBiMap.create();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return data.inverse().get(value);
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
