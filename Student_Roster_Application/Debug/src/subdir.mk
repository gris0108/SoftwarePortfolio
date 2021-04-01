################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/networkStudent.cpp \
../src/roster.cpp \
../src/securityStudent.cpp \
../src/softwareStudent.cpp \
../src/student.cpp 

OBJS += \
./src/networkStudent.o \
./src/roster.o \
./src/securityStudent.o \
./src/softwareStudent.o \
./src/student.o 

CPP_DEPS += \
./src/networkStudent.d \
./src/roster.d \
./src/securityStudent.d \
./src/softwareStudent.d \
./src/student.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


