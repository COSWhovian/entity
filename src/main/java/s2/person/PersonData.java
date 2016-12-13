package s2.person;

import start.PersonEntity;
import start.PersonNameEntity;

import java.util.Optional;
import java.util.Set;

/**
 * Created by russl on 11/17/2016.
 */
public class PersonData {

    private PersonEntity personEntity;

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    @Override
    public String toString() {
        return "PersonData{" + "first='" + getFirst() + '\'' + ", last='" + getLast() + '\'' + '}';
    }

//    protected String first = "";
//    protected String last = "";

    public Object getValue(int idx) {
        Object ret;
        switch (idx) {
            case 0:
                ret = getFirst();
                break;
            case 1:
                ret = getLast();
                break;

            default:
                ret = "";

        }
        return ret;
    }

    public void setValue(int idx, Object val) {

//        switch (idx) {
//            case 0:
//                personNameEntity.setFirst((String) val);
//                break;
//            case 1:
//                personNameEntity.setLast((String) val);
//                 break;
//
//
//        }

    }

    private PersonNameEntity personNameEntity;

    public PersonData(PersonEntity personEntity) {
        this.personEntity = personEntity;
        Set<PersonNameEntity> personNameEntities = personEntity.getPersonNameEntities();
        Optional<PersonNameEntity> pri = personNameEntities.stream().filter(p -> p.getNameCd().equals("PRI")).findFirst();
        if (pri.isPresent()) {
            this.personNameEntity = pri.get();
//            this.first = pri.get().getFirst();
//            this.last = pri.get().getLast();
        }
    }

    public String getFirst() {
        return (this.personNameEntity == null) ? "" : personNameEntity.getFirst();
    }

    //    public void setFirst(String first) {
//        this.first = first;
//    }
    public String getLast() {
        return (this.personNameEntity == null) ? "" : personNameEntity.getLast();
    }

//    public void setLast(String last) {
//        this.last = last;
//    }


}