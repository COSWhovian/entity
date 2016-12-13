package s2.project.ui;

import s2.entities.ProjectEntity;

import javax.swing.*;

/**
 * Created by russl on 12/3/2016.
 */
public interface ProjectDetailComposer {
    void compose(JPanel panel, ProjectEntity projectEntity);
}