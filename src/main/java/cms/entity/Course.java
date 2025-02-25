package cms.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "courseID")
	private int courseID;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "year")
	private int year;
	@Column(name = "semester")
	private String semester;
	@Column(name = "instructor")
	private String instructor;
	@OneToMany(mappedBy = "courseID", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StudentRegistration> studentRegistrations;
	
	public Course() {}
	
	public Course(int courseID, String name, String description, int year, String semester, String instructor) {
		super();
		this.courseID = courseID;
		this.name = name;
		this.description = description;
		this.year = year;
		this.semester = semester;
		this.instructor = instructor;
	}
	
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

}











