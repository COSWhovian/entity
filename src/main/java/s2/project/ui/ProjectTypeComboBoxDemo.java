package s2.project.ui;

/**
 * Created by russl on 11/28/2016.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import s2.entities.ProjectTypeEntity;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/*

 */
@org.springframework.stereotype.Component("projectTypeComboBox")
@Lazy
public class ProjectTypeComboBoxDemo extends JPanel {
    List<ProjectTypeEntity> entityList = new ArrayList<>();

    protected List<ProjectTypeEntity> retrieveProjectTypesViaRest() {
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://localhost:8080/project/types", String.class);
        System.out.println("-->" + forObject);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProjectTypeEntity>>() {
        }.getType();

        List<ProjectTypeEntity> list = gson.fromJson(forObject, listType);

        return list;
    }

    public void setSelectedItem(String cd) {

    }

    /*
     * Despite its use of EmptyBorder, this panel makes a fine content
     * pane because the empty border just increases the panel's size
     * and is "painted" on top of the panel's normal background.  In
     * other words, the JPanel fills its entire background if it's
     * opaque (which it is by default); adding a border doesn't change
     * that.
     */
    public ProjectTypeComboBoxDemo() {
        super(new BorderLayout());

    }

    public ProjectTypeComboBoxDemo(String projectTypeCd) {
        super(new BorderLayout());
        this.selectedProjectTypeCd = projectTypeCd;
    }

    @PostConstruct
    protected void postConstructMethod() {
        try {
            entityList = retrieveProjectTypesViaRest();

            Integer[] intArray = new Integer[entityList.size()];
            IntStream.range(0, entityList.size()).forEach(i -> intArray[i] = new Integer(i));
            int selectedIndex = 0;
            if (selectedProjectTypeCd != null && !selectedProjectTypeCd.isEmpty()) {
                Optional<ProjectTypeEntity> projectTypeEntity = entityList.stream().filter(e -> e.getProjectTypeCd().equals

                        (selectedProjectTypeCd)).findFirst();
                if (projectTypeEntity.isPresent()) {
                    selectedEntity = projectTypeEntity.get();
                    selectedIndex = entityList.indexOf(selectedEntity);
                    System.out.println(" * entity found at index=" + selectedIndex + "; " + selectedEntity);
                }
            } else if (entityList != null && entityList.size() > 0) {
                selectedEntity = entityList.get(0);
            }
            //Create the combo box.
            JComboBox petList = new JComboBox(intArray);
            ComboBoxRenderer renderer = new ComboBoxRenderer();
            petList.setSelectedIndex(selectedIndex);
            petList.setRenderer(renderer);


            //Lay out the demo.
            add(petList, BorderLayout.PAGE_START);
            setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        } catch ( ResourceAccessException e) {
            System.out.println("exception in post construct:  " + e.getLocalizedMessage());
        }
    }


    class ComboBoxRenderer extends JLabel
            implements ListCellRenderer {
        private Font uhOhFont;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            //Get the selected index. (The index param isn't
            //always valid, so just use the value.)
            int selectedIndex = ((Integer) value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            selectedEntity = entityList.get(selectedIndex);

            System.out.println(" -> idx=" + selectedIndex + "; cd=" + selectedEntity.getProjectTypeCd() + "; tip=" +
                    selectedEntity
                            .getTypeTip());
            setText(selectedEntity.getTypeDesc());
            setToolTipText(selectedEntity.getTypeTip());

            return this;
        }


    }

    String selectedProjectTypeCd;


    ProjectTypeEntity selectedEntity;

    public ProjectTypeEntity getSelectedEntity() {
        return selectedEntity;
    }

    public void setSelectedEntity(ProjectTypeEntity selectedEntity) {
        this.selectedEntity = selectedEntity;
    }
}

