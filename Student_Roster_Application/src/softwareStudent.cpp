/*
 * softwareStudent.cpp
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */

#include<iostream>
#include "softwareStudent.h"
using namespace std;

SoftwareStudent::SoftwareStudent(string studentID, string firstName, string lastName,	string emailAddress, int age, int courseDaysArray[],
		Degree degreeProgram):Student(studentID, firstName, lastName, emailAddress, age, courseDaysArray, degreeProgram){
	setDegreeProgram(SOFTWARE);
}

Degree SoftwareStudent::getDegreeProgram(){
	return SOFTWARE;
}

void SoftwareStudent::print(){
	Student::print();
	cout << "Degree Program: Software";
}



