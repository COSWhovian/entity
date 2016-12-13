package s2.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import s2.SpringUtilities;
import s2.common.ChangeHandler;
import s2.common.Disposer;
import start.PersonNameEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by russl on 11/19/2016.
 */
public class PersonNameEditPanel extends JPanel implements ActionListener {

    RegularFormPanel regularPanel;

    PersonData personData;

    Disposer disposer;
    ChangeHandler changeHandler;

    public PersonNameEditPanel(PersonData personData, Disposer disposer, ChangeHandler changeHandler) {

        this.personData = personData;
        this.disposer = disposer;
        this.changeHandler = changeHandler;

//        setLayout(new FlowLayout());
        setLayout(new BorderLayout());
        setSize(60, 500);

        JLabel l2 = new JLabel("Enter person name: ");
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        add(l2, BorderLayout.NORTH);

        regularPanel = new RegularFormPanel();

        add(regularPanel, BorderLayout.CENTER);

        JPanel controlPanel = constructControlPanel();


        add(controlPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("OK Pressed");
    }


    public class RegularFormPanel extends JPanel {
        JTextField titleText;

        JTextField prefixText;
        JTextField firstText;
        JTextField middleText;
        JTextField lastText;

        public JTextField getTitleText() {
            return titleText;
        }

        public void setTitleText(JTextField titleText) {
            this.titleText = titleText;
        }

        public JTextField getNameDescText() {
            return nameDescText;
        }

        public void setNameDescText(JTextField nameDescText) {
            this.nameDescText = nameDescText;
        }

        JTextField nameDescText;


        public JTextField getPrefixText() {
            return prefixText;
        }

        public void setPrefixText(JTextField prefixText) {
            this.prefixText = prefixText;
        }

        public JTextField getFirstText() {
            return firstText;
        }

        public void setFirstText(JTextField firstText) {
            this.firstText = firstText;
        }

        public JTextField getMiddleText() {
            return middleText;
        }

        public void setMiddleText(JTextField middleText) {
            this.middleText = middleText;
        }

        public JTextField getLastText() {
            return lastText;
        }

        public void setLastText(JTextField lastText) {
            this.lastText = lastText;
        }

        public JTextField getSuffixText() {
            return suffixText;
        }

        public void setSuffixText(JTextField suffixText) {
            this.suffixText = suffixText;
        }

        JTextField suffixText;

        public RegularFormPanel() {
            super(new SpringLayout());



            JLabel titleLabel = new JLabel("Title", JLabel.TRAILING);
            titleText = new JTextField(20);
            titleLabel.setLabelFor(titleText);


            JLabel prefixLabel = new JLabel("Prefix", JLabel.TRAILING);
            prefixText = new JTextField(20);
            prefixLabel.setLabelFor(prefixText);


            JLabel firstLabel = new JLabel("First", JLabel.TRAILING);
            firstText = new JTextField(60);
            firstLabel.setLabelFor(firstText);

            JLabel middleLabel = new JLabel("Middle", JLabel.TRAILING);
            middleText = new JTextField(60);
            middleLabel.setLabelFor(middleText);

            JLabel lastLabel = new JLabel("Last", JLabel.TRAILING);
            lastText = new JTextField(60);
            lastLabel.setLabelFor(lastText);

            JLabel suffixLabel = new JLabel("Suffix", JLabel.TRAILING);
            suffixText = new JTextField(20);
            suffixLabel.setLabelFor(suffixText);

            JLabel nameDescLabel = new JLabel("Description", JLabel.TRAILING);
            nameDescText = new JTextField(20);
            nameDescLabel.setLabelFor(nameDescText);


            add(titleLabel);
            add(titleText);

            add(prefixLabel);
            add(prefixText);

            add(firstLabel);
            add(firstText);

            add(middleLabel);
            add(middleText);

            add(lastLabel);
            add(lastText);

            add(suffixLabel);
            add(suffixText);

            add(nameDescLabel);
            add(nameDescText);

            SpringUtilities.makeCompactGrid(this, 7, 2, 6, 6, 6, 6);
        }
    }


    protected JPanel constructControlPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.LINE_AXIS));

        JButton b2 = new JButton("OK");
        b2.setHorizontalAlignment(SwingConstants.CENTER);


        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //pass a name into the document modal dialog
//            l3.setText("by " + tf2.getText());
//            d3.setVisible(true);
                System.out.println("OK Pressed");

                PersonNameEntity personNameEntity = new PersonNameEntity(personData.getPersonEntity(), "AKA",
                        regularPanel.getTitleText().getText(), regularPanel.getPrefixText().getText(), regularPanel
                        .getFirstText().getText(), regularPanel.getMiddleText().getText(), regularPanel.getLastText()
                        .getText(), regularPanel.getSuffixText().getText(), "", regularPanel.getNameDescText()
                        .getText());


                SessionFactory sessionFactory;


                final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() //
                        // configures settings from hibernate.cfg.xml
                        .build();
                try {
                    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();


                    Session session = sessionFactory.openSession();
                    session.beginTransaction();


//                    session.updateProject(personData.getPersonEntity());
                    session.save(personNameEntity);


                    session.flush();

                    session.getTransaction().commit();
                    session.close();


                } catch (Exception ex) {
                    // The registry would be destroyed by the SessionFactory, but we had trouble building the
                    // SessionFactory
                    // so destroy it manually.
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                System.out.println(" --> " + personNameEntity.toString() + " <-- ");
                personData.getPersonEntity().getPersonNameEntities().add(personNameEntity);
                changeHandler.handle();
                disposer.perform();
            }
        });

        jPanel.add(b2);

        JButton b3 = new JButton("Cancel");
        b3.setHorizontalAlignment(SwingConstants.CENTER);


        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //pass a name into the document modal dialog
//            l3.setText("by " + tf2.getText());
//            d3.setVisible(true);
                System.out.println("Cancel Pressed");


            }
        });

        jPanel.add(b3);

        return jPanel;
    }
}
