package s2.project.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.*;
import org.springframework.stereotype.Component;
import s2.common.ChangeHandler;
import s2.common.ControlPanel;
import s2.common.ControlPanelActionListener;
import s2.common.Disposer;
import s2.entities.ProjectEntity;
import s2.project.ObservableProjectData;
import s2.project.ProjectData;
import s2.project.ProjectTableModel;
import s2.service.impl.ProjectService;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
//import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by russl on 11/23/2016.
 */
@Component("projectGridPanelBean")
public class ProjectGridPanel extends JPanel implements ActionListener {


    private boolean DEBUG = false;

    @Autowired
    ProjectTableModel tableModel;

    @Autowired
    ProjectService projectService;

    JTable table;

    public ProjectTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(ProjectTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public void addObserver(Observer observer) {
        selectedProjectData.addObserver(observer);
    }
//    public ProjectDetailGridPanel getControlledPanel() {
//        return controlledPanel;
//    }

//    public void setControlledPanel(ProjectDetailGridPanel controlledPanel) {
//        this.controlledPanel = controlledPanel;
//    }

    public ObservableProjectData getSelectedProjectData() {
        return selectedProjectData;
    }

    public void setSelectedProjectData(ObservableProjectData selectedProjectData) {
        this.selectedProjectData = selectedProjectData;
    }

    ObservableProjectData selectedProjectData = new ObservableProjectData();

    public ProjectGridPanel() {

//        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    }


    //
    @PostConstruct
    protected void postConstructMethod() {
        table = new JTable(tableModel);
        if (tableModel.getProjectDataList().size() > 0) {
            table.setRowSelectionInterval(0, 0);
            selectedProjectData.setProjectData(tableModel.getProjectDataList().get(0));
            // TODO -
//controlledPanel.update(selectedProjectData);
        }
        table.setPreferredScrollableViewportSize(new Dimension(500, 170));
        table.setFillsViewportHeight(true);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                if (event.getValueIsAdjusting()) {
                    return;
                }
//                int idx = determineIndexOfSelectedProjectData();
//                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
                int selectedRow = table.getSelectedRow();
                if (selectedRow < 0) {
                    int idx = determineIndexOfSelectedProjectData();

                    selectedRow = idx;
                    if (selectedRow < 0) {
                        selectedRow = 0;
                    }


                    table.setRowSelectionInterval(selectedRow, selectedRow);

                }

                System.out.println("selected row:  " + selectedRow);
                selectedProjectData.setProjectData(tableModel.getProjectDataList().get(selectedRow));
                // TODO -
//                controlledPanel.update(selectedProjectData);

                System.out.println("-->" + selectedProjectData);

                tableModel.getProjectDataList().get(selectedRow).getProjectEntity().getNameGroupEntities()
                        .forEach
                                (pn -> System.out.println("   > " + pn.toString()));

//                if ( controlledPanel != null) {
//                    controlledPanel.change(projectData);
//                }

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

                System.out.println("ControlPanel:  actionCommand:  " + actionCommand);

                switch (actionCommand) {
                    case "add":
                        showAddDialog();
                        break;

                    case "edit":
                        showEditDialog();

                        break;

                    case "deleteProject":
                        showDeleteDialog();
                        break;
                }
            }
        });

        controlPanel.setAlignmentX(java.awt.Component.TOP_ALIGNMENT);
//        controlPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        controlPanel.setPreferredSize(new Dimension(50, 190));

        add(controlPanel);
    }

    private void showDeleteDialog() {
        JDialog.setDefaultLookAndFeelDecorated(true);
        JPanel panel = (JPanel) this.getParent();
        JLayeredPane layeredPane = (JLayeredPane) panel.getParent();
        JRootPane rootPane = (JRootPane) layeredPane.getParent();

        JFrame parent = (JFrame) rootPane.getParent();
//        JDialog d2 = new JDialog(parent, Dialog.ModalityType.DOCUMENT_MODAL);
//        d2.setTitle("Add Project");
//        d2.setLocationRelativeTo(parent);


        int response = JOptionPane.showConfirmDialog(parent, "Delete selected project?", "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        switch (response) {
            case JOptionPane.NO_OPTION:
                System.out.println("No button clicked");

                break;

            case JOptionPane.CANCEL_OPTION:
                System.out.println("cancel button clicked");

                break;

            case JOptionPane.YES_OPTION:
                System.out.println("Yes button clicked");

                deleteSelectedProject();
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("JOptionPane closed");

                break;
        }

    }

    protected int determineIndexOfSelectedProjectData() {
        int selectedRow = table.getSelectedRow();

        int idx = tableModel.getProjectDataList().indexOf(selectedProjectData.getProjectData());
        return idx;
    }

    protected void deleteSelectedProject() {

        ProjectEntity projectEntity = selectedProjectData.getProjectData().getProjectEntity();
        String id = projectEntity.getId();
        System.out.println(" * delete project:  " + projectEntity.toString());

        projectService.deleteProject(id);
        // TODO - update the UI grid

        tableModel.getProjectDataList().remove(selectedProjectData.getProjectData());
        tableModel.fireTableDataChanged();
    }

    private void showAddDialog() {
something();

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
        d2.setTitle("Add Project");
        d2.setLocationRelativeTo(parent);
        int x = (int) Math.floor(parent.getLocationOnScreen().getX());
        int y = (int) Math.floor(parent.getLocationOnScreen().getY());

        d2.setSize(new Dimension(300, 200));

        Container cp2 = d2.getContentPane();
        cp2.setLayout(new BorderLayout());

        // create an empty instance of project data
        ProjectData projectData = new ProjectData();

// TODO
        // TODO
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

//        projectTypes = (ProjectTypeComboBoxDemo) context.getBean("projectTypeComboBox", projectEntity
//                .getProjectType().getProjectTypeCd());


        JPanel p2 = new ProjectAddPanel(projectData, new Disposer() {
            @Override
            public void perform() {
                d2.dispose();
            }
        }, new ChangeHandler() {
            @Override
            public void handle() {
                // add the projectData instance to the tableModel's list
                // and then let the model know there was a change
                tableModel.getProjectDataList().add(projectData);
                int selectedRow = tableModel.getProjectDataList().size() - 1;
                int idx = determineIndexOfSelectedProjectData();

                table.setRowSelectionInterval(selectedRow, selectedRow);


                tableModel.fireTableDataChanged();
            }
        });

        cp2.add(p2, BorderLayout.NORTH);
        d2.setSize(new Dimension(400, 250));

        d2.setVisible(true);
    }

    private void showEditDialog() {
        if (selectedProjectData.getProjectData() == null) {
            System.out.println("select a project to edit");
            return;
        }
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
        d2.setTitle("Edit Project");
        d2.setLocationRelativeTo(parent);
        int x = (int) Math.floor(parent.getLocationOnScreen().getX());
        int y = (int) Math.floor(parent.getLocationOnScreen().getY());

        d2.setSize(new Dimension(300, 200));

        Container cp2 = d2.getContentPane();
        cp2.setLayout(new BorderLayout());

        // create an empty instance of project data
//        ProjectData projectData = new ProjectData();
        JPanel p2 = new ProjectEditPanel(selectedProjectData.getProjectData(), new Disposer() {
            @Override
            public void perform() {
                d2.dispose();
            }
        }, new ChangeHandler() {
            @Override
            public void handle() {
                // add the projectData instance to the tableModel's list
                // and then let the model know there was a change
//                tableModel.getProjectDataList().add(projectData);
                tableModel.fireTableDataChanged();
            }
        });

        cp2.add(p2, BorderLayout.NORTH);
        d2.setSize(new Dimension(400, 250));

        d2.setVisible(true);
    }


    protected void refresh() {

//        java.util.List<ProjectData> projectDataList = tableModel.getProjectDataList();

//        projectData.getProjectEntity().getNameGroupEntities().forEach(e -> projectDataList.add(new ProjectData
//                (e)));
//        tableModel.setPersonDataList(personDataList);
//tableModel.setProjectDataList(projectDataList);

//        List<String> definedOrder = // define your custom order
//                Arrays.asList("PRI", "BIR", "NCK", "AKA");
//
//        Comparator<PersonNameData> comparator = new PersonNameDataComparator(definedOrder).invoke();


//        Collections.sort(personDataList, new PersonNameDataComparator(Arrays.asList("PRI", "BIR", "NCK", "AKA"))
//                .invoke());
    }

    //
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        System.out.println("actionCommand:  " + actionCommand);

    }

    private void initColumnSizes(JTable table) {
        ProjectTableModel model = (ProjectTableModel) table.getModel();
        TableColumn column = null;
        java.awt.Component comp = null;
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
            if (tableModel.getProjectDataList().size() > 0) {

                comp = table.getDefaultRenderer(model.getColumnClass(i)).
                        getTableCellRendererComponent(
                                table, longValues[i],
                                false, false, 0, i);
                cellWidth = comp.getPreferredSize().width;
            } else {
                cellWidth = 50;
            }
            if (DEBUG) {
                System.out.println("Initializing width of column "
                        + i + ". "
                        + "headerWidth = " + headerWidth
                        + "; cellWidth = " + cellWidth);
            }

            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void something() {
        jdbcTemplate.query(
                "SELECT id, project_type_id, project_name, project_desc, create_dt, created_by from enigmabase.project",
                (rs, rowNum) -> new ProjectEntity(rs.getString("id"),
                        rs.getString("project_name"),
                        rs.getString("project_desc"), "",
                        rs.getString("created_by"))).forEach(c -> System.out.println("->" + c.toString()));
// project_desc
        //

//                    "id=" + rs.getLong("id")
//                            + ", first=" + rs.getString("first_name")+"," +
//                    "last=" +                rs                    .getString                    ("last_name")));


    }

}