package s2.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import s2.person.PersonData;
import start.PersonEntity;
import start.PersonNameEntity;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by russl on 11/17/2016.
 */
public class PersonTableModel extends AbstractTableModel {
    SessionFactory sessionFactory;
     private boolean DEBUG = false;

    public PersonTableModel() {


        columnNamesList.add("First Name");
        columnNamesList.add("Last Name");
//        columnNamesList.add("Sport");
//        columnNamesList.add("Years");
//        columnNamesList.add("Menu");

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }


        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List list = session.createQuery("from PersonEntity").list();
        for (PersonEntity p : (List<PersonEntity>) list) {
            System.out.println(" person entity:  " + p.toString());
            Set<PersonNameEntity> personNameEntities = p.getPersonNameEntities();
            personNameEntities.forEach(pn -> System.out.println("      person name entity:  " + pn.toString()));
            personDataList.add(new PersonData(p));

        }

        System.out.println("  --------------------------------------------------");

        List list2 = session.createQuery("from PersonNameEntity pne where pne.personId = '53c96aef-aafb-11e6-b27d-d017c218b3b1'").list();
        for (PersonNameEntity p : (List<PersonNameEntity>) list2) {
            System.out.println(" person name entity:  " + p.toString());
            System.out.println("      person entity:  " + p.getPersonId().toString());
        }


        session.getTransaction().commit();
        session.close();
    }

    List<String> columnNamesList = new ArrayList<>();

    public List<PersonData> getPersonDataList() {
        return personDataList;
    }

    public void setPersonDataList(List<PersonData> personDataList) {
        this.personDataList = personDataList;
    }

    List<PersonData> personDataList = new ArrayList<>();


    public final Object[] longValues = {"Jane", "Kathy",
//            "None of the above",
//            new Integer(20), Boolean.TRUE
    };

    public int getColumnCount() {
        return columnNamesList.size();
    }

    public int getRowCount() {
        return personDataList.size();
    }

    public String getColumnName(int col) {
        if (col < columnNamesList.size()) {
            return columnNamesList.get(col);
        } else {
            return "";
        }


    }


    public Object getValueAt(int row, int col) {

        PersonData pd = personDataList.get(row);

        return pd.getValue(col);
    }


    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (DEBUG) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }
        personDataList.get(row).setValue(col, value);

//            data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (DEBUG) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + personDataList.get(i).getValue(j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}