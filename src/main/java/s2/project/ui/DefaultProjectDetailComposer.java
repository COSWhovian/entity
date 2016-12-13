package s2.project.ui;

import s2.entities.ProjectEntity;

import javax.swing.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by russl on 12/3/2016.
 */
public class DefaultProjectDetailComposer implements ProjectDetailComposer {
    public void compose(JPanel panel, ProjectEntity projectEntity) {
        panel.removeAll();

//        JLabel something = new JLabel("DEFAULT:  " + projectEntity.toString());
//        panel.add(something);
//
        JLabel caption = new JLabel("Project Detail");
        panel.add(caption);

        JLabel nameLabel = new JLabel(projectEntity.getProjectName());
        JLabel descLabel = new JLabel(projectEntity.getProjectDesc());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        JLabel creatdDtLabel = new JLabel(dateTimeFormatter.format(projectEntity.getCreateDt()));

        JLabel createdByLabel = new JLabel(projectEntity.getCreatedBy());

        panel.add(nameLabel);
        panel.add(descLabel);
        panel.add( creatdDtLabel);
        panel.add( createdByLabel);
        //
        panel.revalidate();
        panel.repaint();

        panel.setVisible(true);
    }
}
