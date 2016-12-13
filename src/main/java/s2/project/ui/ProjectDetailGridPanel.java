package s2.project.ui;

import s2.project.ProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by russl on 11/23/2016.
 */
public class ProjectDetailGridPanel extends JPanel implements ActionListener, Observer {
    // name group
    public ProjectDetailGridPanel() {

    }

    public ProjectDetailGridPanel(ProjectData projectData) {

    }

    ProjectGridPanel projectGridPanel;

    public ProjectDetailGridPanel(ProjectGridPanel projectGridPanel) {
        this.projectGridPanel = projectGridPanel;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(300, 300));
    }


    public ProjectData getSelectedProjectData() {
        return selectedProjectData;
    }

    ProjectData selectedProjectData;

//    public void update(ProjectData projectData) {
//        this.setSelectedProjectData(projectData);
//        this.refresh();
//    }

//    protected void refresh() {
//        // TODO
//        // different project types will have different types of display
//    }

    public void setSelectedProjectData(ProjectData projectData) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO - refresh the panel
        System.out.println(" something changed");
        ProjectData projectData = projectGridPanel.getSelectedProjectData().getProjectData();
        System.out.println(" ----> " + projectData.toString());


        ProjectDetailComposerFactory factory = new ProjectDetailComposerFactory();
        ProjectDetailComposer projectDetailComposer = factory.create(projectData
                .getProjectEntity().getProjectType()
                .getProjectTypeCd());
        projectDetailComposer.compose(this, projectData.getProjectEntity());
    }


}
