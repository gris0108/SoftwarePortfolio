/*
 * softwareStudent.h
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */

#ifndef SOFTWARESTUDENT_H_
#define SOFTWARESTUDENT_H_

#include<string>
#include "student.h"

class SoftwareStudent : public Student{

public:
	//Constructors
	SoftwareStudent(
		string studentID,
		string firstName,
		string lastName,
		string emailAddress,
		int age,
		int courseDaysArray[],
		Degree degreeProgram
		);
	virtual Degree getDegreeProgram();
	void print();

	//Destructor
	//~SoftwareStudent();
};



#endif /* SOFTWARESTUDENT_H_ */
