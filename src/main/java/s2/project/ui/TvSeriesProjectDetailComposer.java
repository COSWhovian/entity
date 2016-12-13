package s2.project.ui;

import s2.entities.ProjectEntity;

import javax.swing.*;

/**
 * Created by russl on 12/3/2016.
 */

public class TvSeriesProjectDetailComposer implements ProjectDetailComposer {
    public void compose(JPanel panel, ProjectEntity projectEntity) {
        panel.removeAll();

        JLabel something = new JLabel("TvSeries:  " + projectEntity.toString());
        panel.add(something);

        JLabel projectNameLabel = new JLabel(projectEntity.getProjectName());
        panel.add(projectNameLabel);

        JLabel projectDescLabel = new JLabel(projectEntity.getProjectDesc());
        panel.add(projectDescLabel);


        panel.revalidate();
        panel.repaint();

        panel.setVisible(true);
    }
}
