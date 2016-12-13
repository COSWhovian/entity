/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package s2;

/*
 * TableRenderDemo.java requires no other files.
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import s2.entities.NameGroupEntity;
import s2.entities.ProjectEntity;
import s2.person.PersonData;
import s2.person.PersonGridPanel;
import s2.person.PersonNameGridPanel;
import start.PersonEntity;
import start.PersonGroupEntity;
import start.PersonNameEntity;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * TableRenderDemo is just like TableDemo, except that it
 * explicitly initializes column sizes and it uses a combo box
 * as an editor for the Sport column.
 */
public class PersonDemo {
    private boolean DEBUG = false;


    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TableRenderDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create and set up the content pane.
        PersonGridPanel personGridPanel = new PersonGridPanel();
        personGridPanel.setOpaque(true); //content panes must be opaque
        java.util.List<PersonData> personDataList = personGridPanel.getTableModel().getPersonDataList();

        PersonNameGridPanel personGridPanel2;
        if (personDataList.isEmpty()) {
            personGridPanel2 = new PersonNameGridPanel();
        } else {
            personGridPanel2 = new PersonNameGridPanel(personDataList.get(0));
        }
        personGridPanel.setControlledPanel(personGridPanel2);

        personGridPanel2.setOpaque(true); //content panes must be opaque


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        topPanel.add(personGridPanel);
        topPanel.add(personGridPanel2);


        frame.setContentPane(topPanel);


        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    protected static void createProject() {

        SessionFactory sessionFactory;


        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


            Session session = sessionFactory.openSession();
            session.beginTransaction();

            ProjectEntity projectEntity = new ProjectEntity();
            projectEntity.setProjectName("test1");
            projectEntity.setProjectDesc("first test");

            Set<NameGroupEntity> nameGroupEntities = new HashSet<>();


            NameGroupEntity nameGroupEntity = new NameGroupEntity();
            nameGroupEntity.setGroupName("a name group x");
            nameGroupEntity.setGroupDesc("a group desc x");


            nameGroupEntities.add(nameGroupEntity);
            projectEntity.setNameGroupEntities(nameGroupEntities);


            session.save(projectEntity);

            session.getTransaction().commit();


        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            System.out.println(" exception:  " + e.getLocalizedMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }


    }

    //public static void holder() {
//
//    SessionFactory sessionFactory;
//
//
//    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//            .configure() // configures settings from hibernate.cfg.xml
//            .build();
//    try {
//        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//
//
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//
//
//
//        List list = session.createQuery("from PersonGroupEntity").list();
//        for (PersonGroupEntity p : (List<PersonGroupEntity>) list) {
//            System.out.println(" person entity:  " + p.toString());
//////                Set<PersonNameEntity> personNameEntities = p.getPersonNameEntities();
//////                personNameEntities.forEach(pn -> System.out.println("      person name entity:  " + pn.toString
// ()));
//////                personDataList.add(new components.TableRenderDemo.PersonData(p));
////
//        }
//        PersonGroupEntity           personGroupEntity;
////            if ( list.size() > 0) {
////                                        personGroupEntity = ((List<PersonGroupEntity>) list).get(0);
////
////            } else {
//        personGroupEntity = new PersonGroupEntity();
//        personGroupEntity.setGroupDesc("Doctor Who Group");
//        personGroupEntity.setGroupName("DWG");
//        personGroupEntity.setGroupCd("FAM");
////            }
//        NameGroupEntity nameGroupEntity = new NameGroupEntity();
//        nameGroupEntity.setGroupName("a name group");
//        nameGroupEntity.setGroupDesc("a group desc");
//
//
//
//        Set<PersonGroupEntity> personGroupEntities = new HashSet<>();
//        personGroupEntities.add(personGroupEntity);
//
//        nameGroupEntity.setPersonGroupEntities(personGroupEntities);
//
//        session.save(nameGroupEntity);
//
//        session.getTransaction().commit();
//
//
//
//    } catch (Exception e) {
//        // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
//        // so destroy it manually.
//        StandardServiceRegistryBuilder.destroy(registry);
//    }
//
//}
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
//        PersonDemo.createProject();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
