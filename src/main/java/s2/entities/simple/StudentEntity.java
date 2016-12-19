package s2.entities.simple;

/**
 * Created by russl on 12/18/2016.
 */

import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

    @Entity
    @Table(name = "STUDENT")
    public class StudentEntity {

        private String studentId;
        private String studentName;
        private Set<CourseEntity> courses = new HashSet<CourseEntity>(0);

        public StudentEntity() {
        }

        public StudentEntity(String studentName) {
            this.studentName = studentName;
        }

        public StudentEntity(String studentName, Set<CourseEntity> courses) {
            this.studentName = studentName;
            this.courses = courses;
        }




        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
        @Id
        @Column(name = "STUDENT_ID", unique = true, nullable = false, length = 36)
        public String getStudentId() {
            return this.studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        @Column(name = "STUDENT_NAME", nullable = false, length = 100)
        public String getStudentName() {
            return this.studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        @ManyToMany(cascade = CascadeType.ALL)
        @JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "STUDENT_ID") }, inverseJoinColumns = { @JoinColumn(name = "COURSE_ID") })
        public Set<CourseEntity> getCourses() {
            return this.courses;
        }

        public void setCourses(Set<CourseEntity> courses) {
            this.courses = courses;
        }

    }
