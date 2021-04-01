/*
 * Test.cpp
 *
 *  Created on: April 10, 2020
 *      Author: Natan Garcia
 */

#include <iostream>
#include <string>
#include "student.h"
using namespace std;


Student::Student(string ID, string fName, string lName, string email, int studentAge, int courses[], Degree program){
	setStudentID(ID);
	setFirstName(fName);
	setLastName(lName);
	setEmailAddress(email);
	setAge(studentAge);
	setCourseDaysArray(courses);
	setDegreeProgram(program);

}

//Accessors
string Student::getStudentID() const {
	return studentID;
}

string Student::getFirstName() const {
	return firstName;
}

string Student::getLastName() const {
	return lastName;
}
string Student::getEmailAddress() const {
	return emailAddress;
}

int Student::getAge() const {
	return age;
}

int * Student::getCourseDaysArray(){
	return courseDaysArray;
}


// Mutators
void Student::setStudentID(string ID){
	studentID = ID;
}
void Student::setFirstName(string fName){
	firstName = fName;
}
void Student::setLastName(string lName){
	lastName = lName;
}
void Student::setEmailAddress(string email){
	emailAddress = email;
}
void Student::setAge(int studentAge){
	age = studentAge;
}
void Student::setCourseDaysArray(int courses[]){
	for(int i = 0; i<3; i++)
		courseDaysArray[i] = courses[i];
}

void Student::setDegreeProgram(Degree degree){
	degreeProgram = degree;
}

void Student::print(){
	//A1 [tab] First Name: John [tab] Last Name: Smith [tab] Age: 20 [tab]daysInCourse: {35, 40, 55} Degree Program: Security

	cout <<  getStudentID() << "\t" << "First Name: " << getFirstName() << "\t" << "Last Name: " << getLastName() <<
			"\t" << "Age: " << getAge() << "daysInCourse: " << getCourseDaysArray() << " ";
}

Student::~Student(){
	//Nothing
}



