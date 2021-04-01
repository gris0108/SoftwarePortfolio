/*
 * securityStudent.cpp
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */


#include<iostream>
#include "securityStudent.h"
using namespace std;

SecurityStudent::SecurityStudent(string studentID, string firstName, string lastName,	string emailAddress, int age, int courseDaysArray[],
		Degree degreeProgram):Student(studentID, firstName, lastName, emailAddress, age, courseDaysArray, degreeProgram){
	setDegreeProgram(SECURITY);
}

Degree SecurityStudent::getDegreeProgram(){
	return SECURITY;
}

void SecurityStudent::print(){
	Student::print();
	cout << "Degree Program: Security";
}
