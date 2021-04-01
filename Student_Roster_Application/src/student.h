/*
 * student.h
 *
 *  Created on: April 10, 2020
 *      Author: Natan Garcia
 */

#ifndef STUDENT_H_
#define STUDENT_H_

#include <string>
#include "degree.h"
using namespace std;

class Student{
	public:

		const static int courseArraySize = 3;
		virtual ~Student();
		// Full Constructor with parameters
		//TODO include degree type?
		Student(string ID, string fName, string lName, string email, int studentAge, int courses[], Degree degree);

		// Accessors for class Student
		string getStudentID() const;
		string getFirstName() const;
		string getLastName() const;
		string getEmailAddress() const;
		int getAge() const;
		int *getCourseDaysArray();
		virtual Degree getDegreeProgram() = 0;

		// Mutators for class Student
		void setStudentID(string ID);
		void setFirstName(string fName);
		void setLastName(string lName);
		void setEmailAddress(string email);
		void setAge(int studentAge);
		void setCourseDaysArray(int courses[]);
		void setDegreeProgram(Degree degree);

		//Virtual print
		virtual void print();



	private:
		string studentID;
		string firstName;
		string lastName;
		string emailAddress;
		int age;
		int courseDaysArray[courseArraySize];
		Degree degreeProgram;


};



#endif /* STUDENT_H_ */
