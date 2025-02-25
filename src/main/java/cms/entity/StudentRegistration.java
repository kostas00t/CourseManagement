package cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studentreg")
public class StudentRegistration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "studentID")
	private int studentID;
	@Column(name = "name")
	private String name;
	@Column(name = "yearofregistration")
	private int yearofregistration;
	@Column(name = "projectgrade")
	private int projectgrade;
	@Column(name = "examgrade")
	private int examgrade;
	@ManyToOne
    @JoinColumn(name = "courseID", referencedColumnName = "courseID")
    private Course courseID;
	
	public StudentRegistration() {}

	public StudentRegistration(int studentID, String name, int yearofregistration, int projectgrade, int examgrade, Course courseID) {
		super();
		this.studentID = studentID;
		this.name = name;
		this.yearofregistration = yearofregistration;
		this.projectgrade = projectgrade;
		this.examgrade = examgrade;
		this.courseID = courseID;
	}
	
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYearofregistration() {
		return yearofregistration;
	}
	public void setYearofregistration(int yearofregistration) {
		this.yearofregistration = yearofregistration;
	}
	public int getProjectgrade() {
		return projectgrade;
	}
	public void setProjectgrade(int projectgrade) {
		this.projectgrade = projectgrade;
	}
	public int getExamgrade() {
		return examgrade;
	}
	public void setExamgrade(int examgrade) {
		this.examgrade = examgrade;
	}
	public Course getCourseID() {
		return courseID;
	}
	public void setCourseID(Course courseID) {
		this.courseID = courseID;
	}

	@Override
	public String toString() {
		return "StudentRegistration [studentID=" + studentID + ", name=" + name + ", yearofregistration=" + yearofregistration
				+ ", Pgrade=" + projectgrade + ", Egrade=" + examgrade + ", courseID=" + courseID + "]\n";
	}
	
}
