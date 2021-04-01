/*
 * securityStudent.h
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */

#ifndef SECURITYSTUDENT_H_
#define SECURITYSTUDENT_H_

#include<string>
#include "student.h"

class SecurityStudent : public Student{

public:
	//Constructors
	SecurityStudent(
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
	//~SecurityStudent();
};



#endif /* SECURITYSTUDENT_H_ */
