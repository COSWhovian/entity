package s2.serial;

/**
 * Created by russl on 12/7/2016.
 */
public class NotSerializableExceptionExample {

    public static void main(String[] args) {
        Some some = new Some();
        some.setX(1);
        Pair<String, Some> pair = new Pair("Key1", some);
//        Pair<String, Integer> pair = new Pair("Key1", 1);

        Serializer<Pair> serializer = new Serializer<>();

        System.out.println("Trying to serialize the following object: " + pair);
        byte[] serialize = serializer.serialize(pair);// This statement throws a NotSerializableException
        String s = new String(serialize);
        System.out.println( "serialized:  " + s );


        Pair deserialize = (Pair)serializer.deserialize(serialize);
        System.out.println( "deserialized:  " + deserialize );

    }
}