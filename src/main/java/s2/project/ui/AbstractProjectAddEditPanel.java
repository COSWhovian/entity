package s2.project.ui;

import s2.common.ChangeHandler;
import s2.common.Disposer;
import s2.entities.ProjectEntity;
import s2.project.ProjectData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by russl on 12/4/2016.
 */
abstract public class AbstractProjectAddEditPanel extends JPanel implements ActionListener {

    ProjectData projectData;

    Disposer disposer;
    ChangeHandler changeHandler;
    AbstractProjectFormPanel projectFormPanel;

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action performed");
    }

    public AbstractProjectAddEditPanel() {
    }

    abstract public AbstractProjectFormPanel constructProjectFormPanel();


    public AbstractProjectAddEditPanel(ProjectData projectData, Disposer disposer, ChangeHandler changeHandler) {

        this.projectData = projectData;
        this.disposer = disposer;
        this.changeHandler = changeHandler;

        setLayout(new BorderLayout());
        setSize(60, 600);

        JLabel l2 = new JLabel("Project: ");
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        add(l2, BorderLayout.NORTH);

        projectFormPanel = constructProjectFormPanel();
        add(projectFormPanel, BorderLayout.CENTER);

        JPanel controlPanel = constructControlPanel();


        add(controlPanel, BorderLayout.SOUTH);
    }

    //    abstract public ProjectEntity constructProjectEntity(String id, AbstractProjectFormPanel formPanel);
    abstract public ProjectEntity constructProjectEntity();


    abstract public void handlePersistence(ProjectEntity projectEntity);

    protected JPanel constructControlPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.LINE_AXIS));

        JButton b2 = new JButton("OK");
        b2.setHorizontalAlignment(SwingConstants.CENTER);

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.out.println("OK Pressed");
                ProjectEntity projectEntity = constructProjectEntity();

                System.out.println(" -->" + projectEntity.toString());


                handlePersistence(projectEntity);

                projectData.setProjectEntity(projectEntity);
                changeHandler.handle();
                disposer.perform();
            }
        });
        jPanel.add(b2);

        JButton b3 = new JButton("Cancel");
        b3.setHorizontalAlignment(SwingConstants.CENTER);


        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cancel Pressed");
                disposer.perform();
            }
        });
        jPanel.add(b3);

        return jPanel;
    }
}
