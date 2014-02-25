package com.asn.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
@Entity
@Table(name="personalDetails")
public class PersonalDetails implements Serializable{
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="user_id", nullable=false, unique=true)
	private Long userId;	
	@Column(length=50)
	private String firstName;
	@Column(length=50)
	private String lastName;
	private String imageName;
	@NotNull(message = "Date of birth required..")
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	@NotEmpty(message="Email required!")
	@Email
	@Column(length=100)
	private String email;
	
	private String mobile;
	@Column(length=100)
	private String city;
	private String state;
	private String country;	
	private String gender;
	private String studentId;
	private String admissionNum;
	private Long courseId;
	private String section;
	private String fatherName;
	@Transient
	private User user;
	@Transient
	private MultipartFile profilePhoto;
	@Transient
	private Attendence attendence;
	@Transient
	private Courses courses;
	@Transient
	private StudentCourseFees studentCourseFees;
	@Transient
	private List<Complaints> complaints;
	@Transient
	private List<ExamDetails> examDetails;	
		
	public PersonalDetails(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MultipartFile getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(MultipartFile profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getAdmissionNum() {
		return admissionNum;
	}

	public void setAdmissionNum(String admissionNum) {
		this.admissionNum = admissionNum;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Attendence getAttendence() {
		return attendence;
	}

	public void setAttendence(Attendence attendence) {
		this.attendence = attendence;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public StudentCourseFees getStudentCourseFees() {
		return studentCourseFees;
	}

	public void setStudentCourseFees(StudentCourseFees studentCourseFees) {
		this.studentCourseFees = studentCourseFees;
	}
	
	public List<Complaints> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaints> complaints) {
		this.complaints = complaints;
	}

	public List<ExamDetails> getExamDetails() {
		return examDetails;
	}

	public void setExamDetails(List<ExamDetails> examDetails) {
		this.examDetails = examDetails;
	}
	
	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "PersonalDetails [id=" + id + ", userId=" + userId
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", imageName=" + imageName + ", dateOfBirth=" + dateOfBirth
				+ ", email=" + email + ", mobile=" + mobile + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", gender="
				+ gender + ", studentId=" + studentId + ", admissionNum="
				+ admissionNum + ", courseId=" + courseId + ", section="
				+ section + ", fatherName=" + fatherName + ", user=" + user
				+ ", profilePhoto=" + profilePhoto + ", attendence="
				+ attendence + "]";
	}
	
}
