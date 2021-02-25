package shortener;

import shortener.strategy.StorageStrategy;

/*
* The class is a kind of Google URL Link shortener, such as https://goo.gl, but more functional.
* It can return a unique identifier of any string. Also it can return a string according to
* a previously received identifier. It uses different strategies of data storage which implement
* StorageStrategy interface that is built on Strategy Pattern.
*/
public class Shortener {
    private Long lastId = 0L;
    private StorageStrategy storageStrategy;

    public Shortener(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    public synchronized Long getId(String string) {
        if (storageStrategy.containsValue(string)) return storageStrategy.getKey(string);
        else {
            lastId++;
            storageStrategy.put(lastId, string);
            return lastId;
        }
    }

    public synchronized String getString(Long id) {
        return storageStrategy.getValue(id);
    }
}
