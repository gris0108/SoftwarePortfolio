/*
 * roster.cpp
 *
 *  Created on: Feb 13, 2020
 *      Author: Natan Garcia
 */

#include <iostream>
#include <string>
#include "roster.h"
using namespace std;


// Class Member Functions

Roster::Roster(){
	currentIndex = -1;
	size = 0;
	classRosterArray = nullptr;
}

Roster::Roster(int rsize){
	currentIndex = -1;
	size = rsize;
	classRosterArray = new Student* [rsize];

}

void Roster::parse(string r){

	if (currentIndex < size){
		currentIndex++;
		Degree degreeType;

		// Read Student ID
		int rs = r.find(",");
		string rID = r.substr(0,rs);

		// Read Fist Name
		int ls = rs + 1;
		rs = r.find(",", ls);
		string rFirstName = r.substr(ls,rs-ls);

		// Read Last Name
		ls = rs + 1;
		rs = r.find(",",ls);
		string rLastName = r.substr(ls,rs-ls);

		// Read Email
		ls = rs + 1;
		rs = r.find(",",ls);
		string rEmail = r.substr(ls,rs-ls);

		//Read age
		ls = rs + 1;
		rs = r.find(",",ls);
		int rAge = stoi(r.substr(ls,rs-ls));

		//Read Days In Course 1
		ls = rs + 1;
		rs = r.find(",",ls);
		int rDaysInCourse1 = stoi(r.substr(ls,rs-ls));

		//Read Days In Course 2
		ls = rs + 1;
		rs = r.find(",",ls);
		int rDaysInCourse2 = stoi(r.substr(ls,rs-ls));

		//Read Days In Course 1
		ls = rs + 1;
		rs = r.find(",",ls);
		int rDaysInCourse3 = stoi(r.substr(ls,rs-ls));


		// Read Degree Type
		ls = rs + 1;
		rs = r.find(",",ls);
		string rDegreeTypeString = r.substr(ls,rs-ls);
		if(rDegreeTypeString == "NETWORK"){
			degreeType = NETWORK;
		} else if(rDegreeTypeString == "SOFTWARE") {
			degreeType = SOFTWARE;
		} else if (rDegreeTypeString == "SECURITY"){
			degreeType = SECURITY;
		} else{
			cerr << "INVALID DEGREE TYPE, EXITING PROGRAM\n";
			exit(-1);
		}


		//Add to classRosterArray
		add(rID, rFirstName, rLastName, rEmail, rAge, rDaysInCourse1, rDaysInCourse2, rDaysInCourse3, degreeType);

	}
	else{
		cerr << "MAX CAPACITY OF LIST REACHED\n";
		exit(-1);

	}
}

void Roster::add(string studentID, string firstName, string lastName, string emailAddress,
		int age, int daysInCourse1, int daysInCourse2, int daysInCourse3, Degree degreeType){

	int daysInCourses[3];
	daysInCourses[0] = daysInCourse1;
	daysInCourses[1] = daysInCourse2;
	daysInCourses[2] = daysInCourse3;

	if(degreeType == NETWORK){
		classRosterArray[currentIndex] = new NetworkStudent(studentID, firstName, lastName, emailAddress,
				age, daysInCourses, degreeType);
	} else if(degreeType == SOFTWARE) {
		classRosterArray[currentIndex] = new SoftwareStudent(studentID, firstName, lastName, emailAddress,
						age, daysInCourses, degreeType);
	} else {
		classRosterArray[currentIndex] = new SecurityStudent(studentID, firstName, lastName, emailAddress,
								age, daysInCourses, degreeType);
	}

}

void Roster::remove(string studentID){

	for(int i = 0; i <= currentIndex; i++){

		if(this->classRosterArray[i]->getStudentID() == studentID){
			delete this->classRosterArray[i];
			this->classRosterArray[i] = this->classRosterArray[currentIndex];
			currentIndex--;
		}

		if(currentIndex == i && this->classRosterArray[i]->getStudentID() != studentID){
			cerr << "STUDENT WITH THAT ID DOES NOT EXIST";
		}
	}

}

void Roster::printAll(){

	cout << "I made it to printAll()  :)";
	for(int i = 0; i <= this->currentIndex; i++){
		(this->classRosterArray)[i]->print();
	}

}


void Roster::printAverageDaysInCourse(string studentID){

	for(int i = 0; i<=currentIndex; i++){
		if(classRosterArray[i]->getStudentID() == studentID){
			int* p = classRosterArray[i]->getCourseDaysArray();
			cout << "The average number of days in a course for student " << classRosterArray[i]->getStudentID() <<
					" is " << (p[0] + p[1] + p[2])/3 << "\n";
		}
	}
}

void Roster::printInvalidEmails(){
	cout << "Invalid email address:\n";
	bool invalid = false;
	for(int i = 0; i <= currentIndex; i++){
		invalid = false;
		string email = classRosterArray[i]->getEmailAddress();
		int sLength = email.length();

		for(int x = 0; x < sLength; x++){
			if(email.find(' ') != string::npos){
				cout << email;
				invalid = true;
				break;
			}
			if(email.find('@') == string::npos || email.find('.') == string::npos){
				cout << email;
				invalid = true;
				break;
			}
		}


		if(invalid) cout << "\n";
	}
	if(!invalid) cout << "NONE";
}

void Roster::printByDegreeProgram(Degree degreeProgram){
	cout << "Printing Students in " << degreeString[degreeProgram] << "\n";
	for(int i = 0; i <= currentIndex; i++){
		if(classRosterArray[i]->getDegreeProgram() == degreeProgram)
			classRosterArray[i]->print();
	}
}

Roster::~Roster(){

}

int main(){
	cout << "C867 C++ #000923983 Natan Garcia \n";
	Roster classRoster;
	const string studentData[] =
	 {"A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
	 "A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
	 "A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
	 "A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
	 "A5,Natan,Garcia,ngarc64@wgu.edu,20,30,30,15,SOFTWARE"};

	for(int i = 0; i < 5; i++){
		classRoster.parse(studentData[i]);
	}

	cout << "Current Students:\n";
	classRoster.printAll();

	//classRoster.printInvalidEmails();
	// TODO loop through classRosterArray and for each element:
	for (int i = 0; i<5; i++){
		classRoster.printAverageDaysInCourse(classRoster.classRosterArray[i]->getStudentID());
	}
	classRoster.printByDegreeProgram(SOFTWARE);
	classRoster.remove("A3");
	classRoster.remove("A3");
	return 0;
}




