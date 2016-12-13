package s2.person;

import java.util.Comparator;
import java.util.List;

/**
 * Created by russl on 11/18/2016.
 */
public class PersonNameDataComparator {
    private final List<String> definedOrder;

    public PersonNameDataComparator(List<String> definedOrder) {
        this.definedOrder = definedOrder;
    }

    public Comparator<PersonNameData> invoke() {
        return new Comparator<PersonNameData>() {

            @Override
            public int compare(final PersonNameData o1, final PersonNameData o2) {
                // let your comparator look up your car's color in the custom order
                return Integer.valueOf(
                        definedOrder.indexOf(o1.getPersonNameEntity().getNameCd()))
                        .compareTo(
                                Integer.valueOf(
                                        definedOrder.indexOf(o2.getPersonNameEntity().getNameCd())));
            }
        };
    }
}