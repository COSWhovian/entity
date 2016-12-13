package s2.hello;

/**
 * Created by russl on 11/28/2016.
 */

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.client.RestTemplate;
import s2.entities.ProjectTypeEntity;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
 * CustomComboBoxDemo.java uses the following files:
 *   images/Bird.gif
 *   images/Cat.gif
 *   images/Dog.gif
 *   images/Rabbit.gif
 *   images/Pig.gif
 */
public class ProjectTypeComboBoxDemo2 extends JPanel {
    //    ImageIcon[] images;
//    String[] petStrings = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};
    List<ProjectTypeEntity> entityList = new ArrayList<>();


    protected List<ProjectTypeEntity> retrieveProjectTypesViaRest() {
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://localhost:8080/project/types", String.class);
        System.out.println("-->" + forObject);
        Gson gson = new Gson();
        Type listType = new TypeToken<List<ProjectTypeEntity>>() {
        }.getType();

        List<ProjectTypeEntity> list = gson.fromJson(forObject, listType);
//        List<String> collect = list.stream().map(e -> e.getProjectTypeCd()).collect(Collectors.toList());


        return list;
    }

    /*
     * Despite its use of EmptyBorder, this panel makes a fine content
     * pane because the empty border just increases the panel's size
     * and is "painted" on top of the panel's normal background.  In
     * other words, the JPanel fills its entire background if it's
     * opaque (which it is by default); adding a border doesn't change
     * that.
     */
    public ProjectTypeComboBoxDemo2() {
        super(new BorderLayout());
entityList = retrieveProjectTypesViaRest();



        //Load the pet images and create an array of indexes.
//        images = new ImageIcon[petStrings.length];
        Integer[] intArray = new Integer[entityList.size()];

        IntStream.range(0, entityList.size()).forEach(i -> intArray[i] = new Integer(i));


//
//
//        for (int i = 0; i < petStrings.length; i++) {
//            intArray[i] = new Integer(i);
////            images[i] = createImageIcon("images/" + petStrings[i] + ".gif");
////            if (images[i] != null) {
////                images[i].setDescription(petStrings[i]);
////            }
//        }

        //Create the combo box.
        JComboBox petList = new JComboBox(intArray);
        ComboBoxRenderer renderer = new ComboBoxRenderer();
//        renderer.setPreferredSize(new Dimension(200, 130));
        petList.setRenderer(renderer);
        petList.setMaximumRowCount(3);

        //Lay out the demo.
        add(petList, BorderLayout.PAGE_START);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
//    protected static ImageIcon createImageIcon(String path) {
//        java.net.URL imgURL = ProjectTypeComboBoxDemo.class.getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("CustomComboBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ProjectTypeComboBoxDemo2();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
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

            //Set the icon and text.  If icon was null, say so.
//            ImageIcon icon = images[selectedIndex];
//            String pet = petStrings[selectedIndex];
            ProjectTypeEntity entity = entityList.get(selectedIndex);

//            setIcon(icon);
//            if (icon != null) {
            System.out.println(" -> idx=" + selectedIndex + "; cd=" + entity.getProjectTypeCd() + "; tip=" + entity
                    .getTypeTip());
                setText(entity.getTypeDesc());
//                setFont(list.getFont());
//            } else {
//                setUhOhText(pet + " (no image available)",
//                        list.getFont());
//            }

            return this;
        }

        //Set the font and text when no image was found.
//        protected void setUhOhText(String uhOhText, Font normalFont) {
//            if (uhOhFont == null) { //lazily create this font
//                uhOhFont = normalFont.deriveFont(Font.ITALIC);
//            }
//            setFont(uhOhFont);
//            setText(uhOhText);
//        }
    }
}

