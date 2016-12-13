package s2.project;

import java.util.Observable;

/**
 * Created by russl on 12/3/2016.
 */
public class ObservableProjectData extends Observable {
    protected ProjectData projectData;

    public ProjectData getProjectData() {
        return this.projectData;
    }

    public void setProjectData(ProjectData projectData) {
        this.projectData = projectData;
        setChanged();
        notifyObservers();
    }

    @Override
    public String toString() {
        return this.projectData.getProjectEntity().toString();
    }
}
