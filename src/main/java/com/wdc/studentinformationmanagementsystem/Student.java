package com.wdc.studentinformationmanagementsystem;

public class Student {
	public String studentNumber;
	public String name;
	public String gender;
	public String studentClass;

	/**
	 * @param studentNumber 学号
	 * @param name          姓名
	 * @param gender        性别
	 * @param studentClass  班级
	 */

	public Student(String studentNumber, String name, String gender, String studentClass) {
		this.studentNumber = studentNumber;
		this.name = name;
		this.gender = gender;
		this.studentClass = studentClass;
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

	@Override
	public String toString() {
		return name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}
}
