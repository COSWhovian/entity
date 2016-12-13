package s2.serial;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by russl on 12/7/2016.
 */
public class Some implements Serializable {
    int x = 0;

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Some some = (Some) o;

        return x == some.x;
    }

    @Override
    public String toString() {

//        Gson gson = new Gson();
//        String s = gson.toJson(this);
//        return s;

        return "Some{" + "x=" + x + '}';
    }

    @Override
    public int hashCode() {
        return x;
    }

    public void setX(int value) {
        this.x = value;
    }
}
