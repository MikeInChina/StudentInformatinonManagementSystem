package com.wdc.studentinformationmanagementsystem;

public class Student {
	public String studentNumber;
	public String name;
	public String gender;
	public String shift;

	/**
	 * @param studentNumber 学号
	 * @param name 姓名
	 * @param gender 性别
	 * @param shift 班级<br>
	 * (shift也有班级之意)
	 */

	public Student(String studentNumber, String name, String gender, String shift) {
		this.studentNumber = studentNumber;
		this.name = name;
		this.gender = gender;
		this.shift = shift;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
}
