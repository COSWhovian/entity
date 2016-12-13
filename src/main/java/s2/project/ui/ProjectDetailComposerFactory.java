package s2.project.ui;

/**
 * Created by russl on 12/3/2016.
 */
public class ProjectDetailComposerFactory {
    public ProjectDetailComposer create(String projectTypeCd) {
        if (projectTypeCd.equals("TSA")) {
            return new TvSeriesProjectDetailComposer();

        } else {
            return new DefaultProjectDetailComposer();
        }
    }
}
