package s2.serial;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by russl on 12/7/2016.
 */
public class Pair<K, V> implements Serializable {
    /**
     * The key (left) part of the pair.
     */
    private K key;

    /**
     * The value (right) part of the pair.
     */
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return, the pair's key.
     */
    public K getKey() {
        return this.key;
    }

    /**
     * @return, the pair's value.
     */
    public V getValue() {
        return this.value;
    }

    /**
     * Tests if an instance of the Pair class is equal to a specified Object.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            return (this.key == pair.key && this.value == pair.value);
        } else
            return false;
    }

    /**
     * Creates a String representation of the Pair class.
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        String s = gson.toJson(this);
        return s;

//        return "Pair: (" + key + "," + value + ")";
    }
}