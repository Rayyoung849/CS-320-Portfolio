# CS-320-Portfolio
CS-320 Portfolio
# CS-320 Software Test Automation & Quality Assurance Portfolio

## Overview

This repository contains selected work from my CS-320 Software Test Automation & Quality Assurance course at Southern New Hampshire University. The focus of this course was writing unit tests using JUnit, validating software requirements, and understanding how structured testing supports reliable backend services.

The artifacts included here represent my work from Project One and Project Two. Together, they demonstrate my ability to translate written requirements into code-based tests and evaluate testing strategies in a practical way.

---

## Repository Contents

### Project One – Contact Service

**Files included:**

- `Contact.java` – Defines the Contact object and enforces all field-level validation rules.
- `ContactService.java` – Manages adding, updating, and deleting contacts using in-memory data structures.
- `ContactTest.java` – Unit tests verifying Contact class requirements.
- `ContactServiceTest.java` – Unit tests verifying ContactService behavior and constraints.

This project demonstrates requirement-based unit testing, validation enforcement, immutability handling, duplicate ID prevention, and structured service-level testing.

---

### Project Two – Summary and Reflections Report

- **Summary and Reflections Report (Word Document)**

This report explains:

- My unit testing approach
- How I aligned tests directly to software requirements
- How I validated effectiveness using coverage results
- The testing strategies I used and why
- My mindset and discipline while testing

This document reflects my understanding of software testing principles and how I applied them during development.

---

## Reflection

### How can I ensure that my code, program, or software is functional and secure?

I ensure functionality by translating each requirement into a direct unit test. If a rule exists, there must be a test that verifies it. I test both valid input and invalid input to confirm that the system behaves correctly and fails safely when necessary. I also review coverage results to confirm that important logic paths are exercised. Preventing invalid states and rejecting improper input helps protect the integrity of the system.

---

### How do I interpret user needs and incorporate them into a program?

I start by carefully reviewing written requirements and breaking them down into clear, testable rules. Instead of assuming what the system should do, I focus on what the requirements explicitly state. From there, I design both the implementation and the tests around those rules. Writing tests forces me to think about edge cases and failure conditions, which helps ensure the final product reflects user expectations.

---

### How do I approach designing software?

I approach design by separating responsibilities. Model classes enforce validation and maintain data integrity. Service classes handle behavior and operations. Tests verify both layers independently. This separation keeps the code organized and easier to maintain. I also design with the mindset that input can be incorrect, so validation and defensive checks are built into the structure from the beginning.

---

## Conclusion

This portfolio submission represents my work in writing structured unit tests, validating requirements, and reflecting on testing strategies. These projects strengthened my understanding of how disciplined testing supports reliable and maintainable sof
