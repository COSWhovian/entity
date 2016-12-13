package s2.project.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import s2.entities.ProjectEntity;
import s2.project.ProjectData;

import javax.swing.*;
import java.util.List;

/**
 * Created by russl on 11/23/2016.
 */
public class ProjectFrame extends JFrame {
    @Autowired
    private ProjectGridPanel projectGridPanel;

    private ProjectDetailGridPanel projectDetailGridPanel;
//    private String frameTitle;
//
//    public String getFrameTitle() {
//        return frameTitle;
//    }
//
//    public void setFrameTitle(String frameTitle) {
//        this.frameTitle = frameTitle;
//    }

    public ProjectFrame() {
        super("EnigmaWriter Projects");
//        super(this.getFrameTitle());
//        this.setTitle(this.getFrameTitle());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        ProjectGridPanel projectGridPanel = (ProjectGridPanel) context.getBean("projectGridPanelBean");

        // Controller
//            projectGridPanel = new ProjectGridPanel();
        projectGridPanel.setOpaque(true); //content panes must be opaque
        List<ProjectData> projectDataList = projectGridPanel.getTableModel().getProjectDataList();

        // Controlled
        projectDetailGridPanel = new ProjectDetailGridPanel(projectGridPanel);
//        if (projectDataList.isEmpty()) {
//            projectDetailGridPanel = new ProjectDetailGridPanel();
//        } else {
//            projectDetailGridPanel = new ProjectDetailGridPanel(projectDataList.get(0));
//        }
        projectGridPanel.addObserver(projectDetailGridPanel);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.add(projectGridPanel);
        topPanel.add(projectDetailGridPanel);


        setContentPane(topPanel);


        //Display the window.
        pack();
        setVisible(true);

        System.out.println( "-----------------------------");
     }

}
