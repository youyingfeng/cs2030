import java.util.Map;
import java.util.HashMap;

class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T> {
    T key;
    Map<K, V> map;


    KeyableMap(T key) {
        this.key = key;
        this.map = new HashMap<>();
    }

    public T getKey() {
        return key;
    }

    public KeyableMap<T, K, V> put(V item) {
        map.put(item.getKey(),item);
        return this;
    }

    V get(K key) {
        return map.get(key);
    }
    
    @Override
    public String toString() {
        String output = "";
        for (Map.Entry<K, V> element: map.entrySet()) {
            V entryValue = element.getValue();
            
            if (output == "") {
                output = entryValue.toString();
            } else {
                output = output + ", " + entryValue;
            }
        }
        
        return key + ": {" + output + "}";
    }
}
