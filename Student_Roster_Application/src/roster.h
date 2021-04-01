/*
 * roster.h
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */

#ifndef ROSTER_H_
#define ROSTER_H_
#include "student.h"
#include "networkStudent.h"
#include "securityStudent.h"
#include "softwareStudent.h"

class Roster {
public:
	// Default constructor
	Roster();
	// Max size constructor
	Roster(int rsize);

	//Destructor
	~Roster();

	//Parse function
	void parse(string r);
	//sets the instance variables from part D1 and updates the roster
	void add(string studentID, string firstName, string lastName, string emailAddress,
			int age, int daysInCourse1, int daysInCourse2, int daysInCourse3, Degree degreeType);
	/*removes students from the roster by student ID. If the student ID does not exist,
	the function prints an error message indicating that the student was not found.*/
	void remove(string studentID);
	//function should loop through all  the students in classRosterArray and call the print() function for each student.
	void printAll();
	//prints a student’s average number of days in the three courses. The student is identified by the studentID parameter.
	void printAverageDaysInCourse(string studentID);
	//verifies student email addresses and displays all invalid email addresses to the user
	void printInvalidEmails();
	//prints out student information for a degree program specified by an enumerated type
	void printByDegreeProgram(Degree degreeProgram);

	Student** classRosterArray;
	int currentIndex;
	int size;

};




#endif /* ROSTER_H_ */
