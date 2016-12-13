/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package components;

/*
 * TableRenderDemo.java requires no other files.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import start.PersonEntity;
import start.PersonNameEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * TableRenderDemo is just like TableDemo, except that it
 * explicitly initializes column sizes and it uses a combo box
 * as an editor for the Sport column.
 */
public class TableRenderDemo extends JPanel implements ActionListener {
    private boolean DEBUG = false;
    JButton addButton;
    MyTableModel tableModel = new MyTableModel();
    JTable table;

    public TableRenderDemo() {
        super(new GridLayout(1, 0));

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                if (event.getValueIsAdjusting()) {
                    return;
                }
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                System.out.println("-->" + tableModel.getPersonDataList().get(table.getSelectedRow()).getPersonEntity().toString());
                tableModel.getPersonDataList().get(table.getSelectedRow()).getPersonEntity().getPersonNameEntities().forEach( pn -> System.out.println("   > " + pn.toString()));

            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        setUpSportColumn(table, table.getColumnModel().getColumn(2));

        //Add the scroll pane to this panel.
        add(scrollPane);


//        addButton = new JButton();
//
//
//        addButton = new JButton("add row");
//        //Use the default text position of CENTER, TRAILING (RIGHT).
//        addButton.setMnemonic(KeyEvent.VK_E);
//        addButton.setActionCommand("enable");
//        addButton.setEnabled(true);
//
//        addButton.addActionListener(this);
//
//        add(addButton);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("X");
//        PersonData personData = new PersonData("Dean", "Winchester", sportList.get(5), 3, true);
//        List<PersonData> personDataList = tableModel.getPersonDataList();
//        personDataList.add(personData);
//        tableModel.fireTableRowsInserted(personDataList.size() - 1, personDataList.size());
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//      model.addRow(  personData.getVector());


        if ("disable".equals(e.getActionCommand())) {
            System.out.println("disable");
//            addButton.setEnabled(false);
//            b1.setEnabled(false);
//            b3.setEnabled(true);
        } else {
//            addButton.setEnabled(true);

            System.out.println("enable");
        }
    }

    /*
     * This method picks good column sizes.
     * If all column heads are wider than the column's cells'
     * contents, then you can just use column.sizeWidthToFit().
     */
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel) table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(
                            table, longValues[i],
                            false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (DEBUG) {
                System.out.println("Initializing width of column "
                        + i + ". "
                        + "headerWidth = " + headerWidth
                        + "; cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    List<String> sportList;


    public void setUpSportColumn(JTable table,
                                 TableColumn sportColumn) {


//        sportList.add("Snowboarding");
//        sportList.add("Rowing");
//        sportList.add("Knitting");
//        sportList.add("Speed reading");
//        sportList.add("Pool");
//        sportList.add("Hunter");
//        sportList.add("None of the above");


        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        sportList.forEach(s -> comboBox.addItem(s));

        sportColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        sportColumn.setCellRenderer(renderer);
    }

    public class PersonData {
//        public Vector<Object> getVector() {
//            Vector<Object> vector = new Vector<>();
//            vector.add(first);
//            vector.add(last);
//            vector.add(sport);
//            vector.add(years);
//            vector.add(state);
//
//            return vector;
//        }
private PersonEntity personEntity ;

        public PersonEntity getPersonEntity() {
            return personEntity;
        }

        public void setPersonEntity(PersonEntity personEntity) {
            this.personEntity = personEntity;
        }

        @Override
        public String toString() {
            return "PersonData{" +
                    "first='" + first + '\'' +
                    ", last='" + last + '\'' +
                    ", sport='" + sport + '\'' +
                    ", years=" + years +
                    ", state=" + state +
                    '}';
        }

        protected String first = "";
        protected String last = "";
        protected String sport = "";
        protected Integer years = 0;
        protected Boolean state = false;

        public Object getValue(int idx) {
            Object ret;
            switch (idx) {
                case 0:
                    ret = first;
                    break;
                case 1:
                    ret = last;
                    break;
                case 2:
                    ret = sport;
                    break;
                case 3:
                    ret = years;
                    break;
                case 4:
                    ret = state;
                    break;
                default:
                    ret = "";

            }
            return ret;
        }

        public void setValue(int idx, Object val) {

            switch (idx) {
                case 0:
                    first = (String) val;
                    break;
                case 1:
                    last = (String) val;
                    break;
                case 2:
                    sport = (String) val;
                    break;
                case 3:
                    years = (Integer) val;
                    break;
                case 4:
                    state = (Boolean) val;
                    break;


            }

        }

//        public PersonData(String first, String last, String sport, Integer years, Boolean state) {
//            this.first = first;
//            this.last = last;
//            this.sport = sport;
//            this.years = years;
//            this.state = state;
//        }


        public PersonData(PersonEntity personEntity) {
            this.personEntity = personEntity;
            Set<PersonNameEntity> personNameEntities = personEntity.getPersonNameEntities();
            Optional<PersonNameEntity> pri = personNameEntities.stream().filter(p -> p.getNameCd().equals("PRI")).findFirst();
            if (pri.isPresent()) {
                this.first = pri.get().getFirst();
                this.last = pri.get().getLast();
                this.sport = "Hunting";
                this.years = 10;
                this.state = true;
            }
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getSport() {
            return sport;
        }

        public void setSport(String sport) {
            this.sport = sport;
        }

        public Integer getYears() {
            return years;
        }

        public void setYears(Integer years) {
            this.years = years;
        }

        public Boolean getState() {
            return state;
        }

        public void setState(Boolean state) {
            this.state = state;
        }


    }

    class MyTableModel extends AbstractTableModel {
        SessionFactory sessionFactory;

        public MyTableModel() {
            sportList = new ArrayList<>();

            sportList.add("Snowboarding");
            sportList.add("Rowing");
            sportList.add("Knitting");
            sportList.add("Speed reading");
            sportList.add("Pool");
            sportList.add("Hunter");
            sportList.add("None of the above");

            columnNamesList.add("First Name");
            columnNamesList.add("Last Name");
            columnNamesList.add("Sport");
            columnNamesList.add("Years");
            columnNamesList.add("Menu");

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
                "None of the above",
                new Integer(20), Boolean.TRUE};

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


//            return columnNames[col];
        }

        //        public Object getValueAt(int row, int col) {
//            return data[row][col];
//        }
        public Object getValueAt(int row, int col) {

            PersonData pd = personDataList.get(row);

            return pd.getValue(col);
//            return data[row][col];
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

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableRenderDemo newContentPane = new TableRenderDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
