package s2.person;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by russl on 11/17/2016.
 */
public class PersonGridPanel extends JPanel implements ActionListener {

    private boolean DEBUG = false;
    //    JButton addButton;
    PersonTableModel tableModel = new PersonTableModel();

    public PersonTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(PersonTableModel tableModel) {
        this.tableModel = tableModel;
    }

    JTable table;


    public PersonNameGridPanel getControlledPanel() {
        return controlledPanel;
    }

    public void setControlledPanel(PersonNameGridPanel controlledPanel) {
        this.controlledPanel = controlledPanel;
    }

    PersonNameGridPanel controlledPanel;


    public PersonGridPanel() {
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
//                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                PersonData personData = tableModel.getPersonDataList().get(table.getSelectedRow());
                System.out.println("-->" + personData.getPersonEntity().toString());
                tableModel.getPersonDataList().get(table.getSelectedRow()).getPersonEntity().getPersonNameEntities()
                        .forEach(pn -> System.out.println("   > " + pn.toString()));

                if (controlledPanel != null) {
                    controlledPanel.change(personData);
                }

            }
        });


        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);


        //Add the scroll pane to this panel.
        add(scrollPane);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("X");


        if ("disable".equals(e.getActionCommand())) {
            System.out.println("disable");
        } else {
            System.out.println("enable");
        }
    }

    private void initColumnSizes(JTable table) {
        PersonTableModel model = (PersonTableModel) table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        int columnCount = table.getColumnModel().getColumnCount();
        for (int i = 0; i < columnCount; i++) {

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


}
