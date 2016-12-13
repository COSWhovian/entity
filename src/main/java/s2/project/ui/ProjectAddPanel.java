package s2.project.ui;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s2.SpringUtilities;
import s2.common.ChangeHandler;
import s2.common.Disposer;
import s2.entities.ProjectEntity;
import s2.project.ProjectData;
import s2.service.impl.ProjectService;

import javax.swing.*;
import java.awt.*;

/**
 * Created by russl on 11/24/2016.
 */
public class ProjectAddPanel extends AbstractProjectAddEditPanel {


    public ProjectAddPanel(ProjectData projectData, Disposer disposer, ChangeHandler changeHandler) {

        this.projectData = projectData;
        this.disposer = disposer;
        this.changeHandler = changeHandler;

        setLayout(new BorderLayout());
        setSize(60, 600);

        JLabel l2 = new JLabel("Project: ");
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        add(l2, BorderLayout.NORTH);

        projectFormPanel = new ProjectFormPanel();
        add(projectFormPanel, BorderLayout.CENTER);

        JPanel controlPanel = constructControlPanel();


        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public ProjectEntity constructProjectEntity() {
        ProjectEntity projectEntity = new ProjectEntity(projectFormPanel.getProjectNameText().getText(),
                projectFormPanel.getProjectDescText().getText(),
                projectFormPanel.getProjectTypes()
                        .getSelectedEntity(), projectFormPanel.getCreatedByText().getText());
        System.out.println(" -->" + projectEntity.toString());

        return projectEntity;
    }

    public AbstractProjectFormPanel constructProjectFormPanel() {
        return new ProjectFormPanel();
    }

    public class ProjectFormPanel extends AbstractProjectFormPanel {


        public ProjectFormPanel() {
            super(new SpringLayout());

            ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
            projectTypes = (ProjectTypeComboBoxDemo) context.getBean("projectTypeComboBox");

            JLabel typeLabel = new JLabel("Project Type", JLabel.TRAILING);
            typeLabel.setLabelFor(projectTypes);


            JLabel nameLabel = new JLabel("Name", JLabel.TRAILING);
            projectNameText = new JTextField(20);
            nameLabel.setLabelFor(projectNameText);

            JLabel descLabel = new JLabel("Description", JLabel.TRAILING);
            projectDescText = new JTextField(20);
            descLabel.setLabelFor(projectDescText);

            JLabel createdByLabel = new JLabel("Created By", JLabel.TRAILING);
            createdByText = new JTextField(20);
            nameLabel.setLabelFor(createdByText);


            add(typeLabel);
            add(projectTypes);

            add(nameLabel);
            add(projectNameText);

            add(descLabel);
            add(projectDescText);

            add(createdByLabel);
            add(createdByText);


            SpringUtilities.makeCompactGrid(this, 4, 2, 6, 6, 6, 6);

        }


    }

    public void handlePersistence(ProjectEntity projectEntity) {
        ProjectService projectService = new ProjectService();
        projectService.createProject(projectEntity);
    }


}
