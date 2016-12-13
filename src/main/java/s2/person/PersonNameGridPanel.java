package s2.person;

import s2.common.ChangeHandler;
import s2.common.ControlPanel;
import s2.common.ControlPanelActionListener;
import s2.common.Disposer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by russl on 11/17/2016.
 */
public class PersonNameGridPanel extends JPanel implements ActionListener {

    private boolean DEBUG = false;


    PersonNameTableModel tableModel = new PersonNameTableModel();
    JTable table;
    PersonData personData;

    public PersonNameGridPanel(PersonData personData) {
        update(personData);

        init();
    }

    protected void refresh() {

//        tableModel.getPersonDataList().clear();
        java.util.List<PersonNameData> personDataList = new ArrayList();
        personData.getPersonEntity().getPersonNameEntities().forEach(e -> personDataList.add(new PersonNameData(e)));
        tableModel.setPersonDataList(personDataList);


//        List<String> definedOrder = // define your custom order
//                Arrays.asList("PRI", "BIR", "NCK", "AKA");
//
//        Comparator<PersonNameData> comparator = new PersonNameDataComparator(definedOrder).invoke();


        Collections.sort(personDataList, new PersonNameDataComparator(Arrays.asList("PRI", "BIR", "NCK", "AKA"))
                .invoke());
    }

    protected void update(PersonData personData) {
        this.personData = personData;

        refresh();
    }

    public void change(PersonData personData) {
        update(personData);
//        List<PersonNameData> personDataList = tableModel.getPersonDataList();
//        int size = personDataList.size();
//        personDataList.clear();

//        tableModel.fireTableRowsDeleted(0, size);

//        personData.getPersonEntity().getPersonNameEntities().forEach(e -> personDataList.add(new PersonNameData(e)));
//        tableModel.setPersonDataList(personDataList);

//        updateProject(personData);
        tableModel.fireTableDataChanged();
//        tableModel.fireTableRowsInserted(0, personDataList.size());
//tableModel.fireTableRowsDeleted();

    }

    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

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
                if (table.getSelectedRow() >= 0) {
                    System.out.println("-->" + tableModel.getPersonDataList().get(table.getSelectedRow())
                            .getPersonNameEntity().toString());
                }

//                tableModel.getPersonDataList().get(table.getSelectedRow()).getPersonNameEntity()
// .getPersonNameEntities().forEach(pn -> System.out.println("   > " + pn.toString()));

            }
        });


        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Set up column sizes.
        initColumnSizes(table);


        //Add the scroll pane to this panel.
        add(scrollPane);


        ControlPanel controlPanel = new ControlPanel(new ControlPanelActionListener() {
            @Override
            public void handleAction(ActionEvent e) {
                String actionCommand = e.getActionCommand();

                System.out.println("actionCommand:  " + actionCommand);

                switch (actionCommand) {
                    case "add":
                        showAddDialog();
                        break;

                    case "edit":
                        break;

                    case "deleteProject":
                        break;
                }
            }
        });

        add(controlPanel);


//        JPanel controlPanel = new JPanel();
//        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));


//        ImageIcon addIcon = createImageIcon("add.png", 15, 15);
//        addButton = new JButton(addIcon);
//        addButton.setToolTipText("add");
//        addButton.setActionCommand("add");
//        addButton.addActionListener(this);

//        ImageIcon editIcon = createImageIcon("edit.png", 15, 15);
//        editButton = new JButton(editIcon);
//        editButton.setToolTipText("edit");
//        editButton.setActionCommand("edit");
//        editButton.addActionListener(this);
//
//
//        ImageIcon trashIcon = createImageIcon("trash.png", 15, 15);
//        deleteButton = new JButton(trashIcon);
//        deleteButton.setToolTipText("deleteProject");
//        deleteButton.setActionCommand("deleteProject");
//        deleteButton.addActionListener(this);
//
//        controlPanel.add(addButton);
//        controlPanel.add(editButton);
//        controlPanel.add(deleteButton);

//        add(controlPanel);

    }

    protected static ImageIcon createImageIcon(String path, int width, int height) {
        URL systemResource = ClassLoader.getSystemResource(path);

//        java.net.URL imgURL = PersonNameGridPanel.class.getResource(path);
        if (systemResource != null) {
            ImageIcon imageIcon = new ImageIcon(systemResource);
            Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public PersonNameGridPanel() {
        super(new GridLayout(1, 0));


        init();
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        System.out.println("actionCommand:  " + actionCommand);

//        switch (actionCommand) {
//            case "add":
//                showAddDialog();
//                break;
//
//            case "edit":
//                break;
//
//            case "deleteProject":
//                break;
//        }

    }

    private void showAddDialog() {
//        JFrame f1 = new JFrame("add dialog");
        JPanel panel = (JPanel) this.getParent();
        JLayeredPane layeredPane = (JLayeredPane) panel.getParent();
        JRootPane rootPane = (JRootPane) layeredPane.getParent();

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        Insets ins = Toolkit.getDefaultToolkit().getScreenInsets(gc);
        int sw = gc.getBounds().width - ins.left - ins.right;
        int sh = gc.getBounds().height - ins.top - ins.bottom;

        JFrame parent = (JFrame) rootPane.getParent();
        JDialog d2 = new JDialog(parent, Dialog.ModalityType.DOCUMENT_MODAL);
        d2.setTitle("Add");
        d2.setLocationRelativeTo(parent);
        int x = (int) Math.floor(parent.getLocationOnScreen().getX());
        int y = (int) Math.floor(parent.getLocationOnScreen().getY());
//        d2.setBounds(x, y, 300, 200);
//        d2.setBounds(32, ins.top + 32, 300, 200);
        d2.setSize(new Dimension(300, 200));

        Container cp2 = d2.getContentPane();
//
        cp2.setLayout(new BorderLayout());
//        JLabel l2 = new JLabel("Enter your name: ");
//        l2.setHorizontalAlignment(SwingConstants.CENTER);
//        JTextField tf2 = new JTextField(12);
//        cp2.add(l2, BorderLayout.NORTH);
//        cp2.add(tf2, BorderLayout.CENTER);


        JPanel p2 = new PersonNameEditPanel(personData, new Disposer() {

            @Override
            public void perform() {
                d2.dispose();
                //
                // createProject ...

//
//                SessionFactory sessionFactory;
//
//
//                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() //
//                        // configures settings from hibernate.cfg.xml
//                        .build();
//                try {
//                    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//
//
//                    Session session = sessionFactory.openSession();
//                    session.beginTransaction();
//
//
//                    session.updateProject(personData.getPersonEntity());
//                    session.flush();
//
//                    session.getTransaction().commit();
//                    session.close();
//
//
//                } catch (Exception e) {
//                    // The registry would be destroyed by the SessionFactory, but we had trouble building the
//                    // SessionFactory
//                    // so destroy it manually.
//                    StandardServiceRegistryBuilder.destroy(registry);
//                }
                //
//                refresh();
//                tableModel.fireTableDataChanged();

            }
        }, new ChangeHandler() {
            @Override
            public void handle() {
                refresh();
                tableModel.fireTableDataChanged();
            }
        });

        cp2.add(p2, BorderLayout.NORTH);
        d2.setSize(new Dimension(400, 250));

        d2.setVisible(true);
//d2.setResizable(false);
//d2.pack();

    }




    private void initColumnSizes(JTable table) {
        PersonNameTableModel model = (PersonNameTableModel) table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer = table.getTableHeader().getDefaultRenderer();

        int columnCount = table.getColumnModel().getColumnCount();
        for (int i = 0; i < columnCount; i++) {

            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(null, column.getHeaderValue(), false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).
                    getTableCellRendererComponent(table, longValues[i], false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;

            if (DEBUG) {
                System.out.println("Initializing width of column " + i + ". " + "headerWidth = " + headerWidth + "; "
                        + "cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }


}
