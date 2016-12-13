package s2.hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import s2.entities.ProjectEntity;
import s2.project.NeoProjectDemo;
import s2.service.impl.ProjectService;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

/**
 * Created by russl on 11/30/2016.
 */
public class CousinSimple {

    private static void createAndShowGUI() {
        //Create and set up the window.

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");

        ProjectService projectService = (ProjectService) context.getBean("projectServiceBean");


        try {
            Handler fh = new FileHandler("C:/logs/wombat.log.xml");
            Handler ch = new ConsoleHandler();
            ch.setLevel(Level.FINEST);
            logger.addHandler(fh);
            logger.addHandler(ch);
            logger.setLevel(Level.FINEST);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.fine("some message");
        List<ProjectEntity> all = projectService.findAllProjects();
        all.forEach(e -> System.out.println("->" + e));
    }

    private static Logger logger = Logger.getLogger(NeoProjectDemo.class.getTypeName());

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        PersonDemo.createProject();


                createAndShowGUI();

    }
}



