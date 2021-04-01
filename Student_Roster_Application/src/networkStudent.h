/*
 * networkStudent.h
 *
 *  Created on: April 10, 2020
 *      Author: Natan Garcia
 */

#ifndef NETWORKSTUDENT_H_
#define NETWORKSTUDENT_H_

#include<string>
#include "student.h"

class NetworkStudent : public Student{

public:
	//Constructors
	NetworkStudent(
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
	//~NetworkStudent();
};



#endif /* NETWORKSTUDENT_H_ */
