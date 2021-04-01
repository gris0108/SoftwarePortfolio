/*
 * networkStudent.cpp
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */

#include<iostream>
#include "networkStudent.h"
using namespace std;

NetworkStudent::NetworkStudent(string studentID, string firstName, string lastName,	string emailAddress, int age, int courseDaysArray[],
		Degree degreeProgram):Student(studentID, firstName, lastName, emailAddress, age, courseDaysArray, degreeProgram){
	setDegreeProgram(NETWORK);
}

Degree NetworkStudent::getDegreeProgram(){
	return NETWORK;
}

void NetworkStudent::print(){
	Student::print();
	cout << "Degree Program: Network";
}



