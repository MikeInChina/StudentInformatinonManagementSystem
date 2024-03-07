package com.wdc.studentinformationmanagementsystem;

public class StudentWithNumber extends Student{
	private Integer number;
	/**
	 * @param number        序号
	 * @param studentNumber 学号
	 * @param name          姓名
	 * @param gender        性别
	 * @param studentClass  班级
	 */
	public StudentWithNumber(Integer number, String studentNumber, String name, String gender, String studentClass) {
		super(studentNumber, name, gender, studentClass);
		this.number = number;
	}
	public StudentWithNumber(Integer number, Student student){
		super(student.studentNumber, student.name, student.gender, student.studentClass);
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
