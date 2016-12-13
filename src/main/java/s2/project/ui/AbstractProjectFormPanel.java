package s2.project.ui;

import javax.swing.*;

/**
 * Created by russl on 12/4/2016.
 */
abstract public class AbstractProjectFormPanel extends JPanel {
    JTextField projectNameText;
    JTextField projectDescText;

    JTextField createdByText;

    public AbstractProjectFormPanel() {

    }

    public AbstractProjectFormPanel(SpringLayout springLayout) {
        super(springLayout);
    }

    public String toString() {
        StringBuffer buffy = new StringBuffer();


        buffy.append("project:  ");

        buffy.append("name=").append(projectNameText.getText()).append("; ");
        buffy.append("desc=").append(projectDescText.getText()).append("; ");
        buffy.append("type=").append(projectTypes.getSelectedEntity().getProjectTypeCd()).append("; ");
        buffy.append("createdBy=").append(createdByText.getText()).append("; ");

        return buffy.toString();
    }

    public ProjectTypeComboBoxDemo getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(ProjectTypeComboBoxDemo projectTypes) {
        this.projectTypes = projectTypes;
    }

    ProjectTypeComboBoxDemo projectTypes;


    public JTextField getProjectNameText() {
        return projectNameText;
    }

    public void setProjectNameText(JTextField projectNameText) {
        this.projectNameText = projectNameText;
    }

    public JTextField getProjectDescText() {
        return projectDescText;
    }

    public void setProjectDescText(JTextField projectDescText) {
        this.projectDescText = projectDescText;
    }


    public JTextField getCreatedByText() {
        return createdByText;
    }

    public void setCreatedByText(JTextField createdByText) {
        this.createdByText = createdByText;
    }


}
