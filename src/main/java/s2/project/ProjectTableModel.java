package s2.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import s2.entities.NameGroupEntity;
import s2.entities.ProjectEntity;
import s2.service.impl.ProjectService;

import javax.annotation.PostConstruct;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by russl on 11/17/2016.
 */
@Component
public class ProjectTableModel extends AbstractTableModel {
    //    SessionFactory sessionFactory;
    private boolean DEBUG = false;



    @Autowired
    ProjectService projectService;

    public ProjectTableModel() {

 //


        columnNamesList.add("Project");
        columnNamesList.add("Description");
        columnNamesList.add("Type");
        columnNamesList.add("Created");
        columnNamesList.add("Created By");



    }

    @PostConstruct
    protected void postConstructMethod() {

//        ProjectService projectService = new ProjectService();
        List<ProjectEntity> list = projectService.findAllProjects();

        list.forEach(p -> {
            System.out.println(" project entity:  " + p.toString());
            Set<NameGroupEntity> nameGroupEntities = p.getNameGroupEntities();
            nameGroupEntities.forEach(pn -> System.out.println("      project entity:  " + pn.toString()));
            projectDataList.add(new ProjectData(p));

        });

        System.out.println("  --------------------------------------------------");

    }

    List<String> columnNamesList = new ArrayList<>();

    public List<ProjectData> getProjectDataList() {
        return projectDataList;
    }

    public void setProjectDataList(List<ProjectData> projectDataList) {
        this.projectDataList = projectDataList;
    }

    List<ProjectData> projectDataList = new ArrayList<>();


    public final Object[] longValues = {"Jane", "Kathy", "a", "b", "c"
//            "None of the above",
//            new Integer(20), Boolean.TRUE
    };

    public int getColumnCount() {
        return columnNamesList.size();
    }

    public int getRowCount() {
        return projectDataList.size();
    }

    public String getColumnName(int col) {
        if (col < columnNamesList.size()) {
            return columnNamesList.get(col);
        } else {
            return "";
        }


    }


    public Object getValueAt(int row, int col) {

        ProjectData pd = projectDataList.get(row);

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
        projectDataList.get(row).setValue(col, value);

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
                System.out.print("  " + projectDataList.get(i).getValue(j));
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}