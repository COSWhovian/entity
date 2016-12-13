package s2.person;

import start.PersonNameEntity;

/**
 * Created by russl on 11/17/2016.
 */
public class PersonNameData {
    private PersonNameEntity personNameEntity;

    public PersonNameData(PersonNameEntity entity) {
        personNameEntity = entity;
    }
//    private String nameType;
//
//    private String prefix;
//    private String first;
//    private String middle;
//    private String last;
//    private String suffix;
//
//    private String alternate ;


    @Override
    public String toString() {
        return "PersonNameData{" +
                "personNameEntity=" + personNameEntity +
                '}';
    }

    public PersonNameEntity getPersonNameEntity() {
        return personNameEntity;
    }

    public void setPersonNameEntity(PersonNameEntity personNameEntity) {
        this.personNameEntity = personNameEntity;
    }

    protected String translateNameCd(String nameCd) {
        String ret = "";
        switch (nameCd) {
            case "PRI":
                ret = "Primary";
                break;
            case "BIR":
                ret = "Birth";
                break;
            case "AKA":
                ret = "Alias";
                break;
            case "NCK":
                ret = "Nickname";
                break;
        }
        return ret;
    }

    protected String buildNameString(String nameCd) {
        StringBuffer buffy = new StringBuffer();
        if ( personNameEntity == null) {
            return "";
        }
        switch (nameCd) {
            case "PRI":
            case "BIR":
            case "AKA":
                if (personNameEntity.getTitle() != null && !personNameEntity.getTitle().isEmpty()) {
                    buffy.append(personNameEntity.getTitle()).append(" ");
                }

                if (personNameEntity.getPrefix() != null && !personNameEntity.getPrefix().isEmpty()) {
                    buffy.append(personNameEntity.getPrefix()).append(" ");
                }

                if (personNameEntity.getFirst() != null && !personNameEntity.getFirst().isEmpty()) {
                    buffy.append(personNameEntity.getFirst()).append(" ");
                }
                if (personNameEntity.getMiddle() != null && !personNameEntity.getMiddle().isEmpty()) {
                    buffy.append(personNameEntity.getMiddle()).append(" ");
                }
                if (personNameEntity.getLast() != null && !personNameEntity.getLast().isEmpty()) {
                    buffy.append(personNameEntity.getLast()).append(" ");
                }
                if (personNameEntity.getSuffix() != null && !personNameEntity.getSuffix().isEmpty()) {
                    buffy.append(personNameEntity.getSuffix()).append(" ");
                }

                break;


            case "NCK":
                buffy.append(personNameEntity.getAlternate());

                break;
        }
        return buffy.toString();
    }

    public Object getValue(int idx) {
        Object ret;
        String nameCd = personNameEntity.getNameCd();

        switch (idx) {
            case 0:
                ret = translateNameCd(nameCd);
                break;
            case 1:

                ret = buildNameString(nameCd);
                break;

            default:
                ret = "";

        }
        return ret;
    }

    public void setValue(int idx, Object val) {

    }

}
