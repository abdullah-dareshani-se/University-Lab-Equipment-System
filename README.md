# University Lab Booking System

A console-based Java application developed to simplify and manage university laboratory booking requests using custom Data Structures and MySQL database integration.

## Project Overview

The University Lab Booking System allows students to request laboratory bookings and enables an administrator to manage laboratories, review booking requests, and approve or reject them.

The project demonstrates the practical implementation of Data Structures and Algorithms (DSA) in a real-world university scenario.

## Problem Statement

Managing laboratory bookings manually can lead to scheduling conflicts, inefficient record management, and difficulties tracking booking requests.

This system provides a structured way to manage lab information, student records, booking requests, and booking history.

## Key Features

### Student
- Student registration and login.
- View available laboratories.
- Submit laboratory booking requests.
- Check booking status.
- View booking history.
- View rejection reasons when a request is rejected.

### Administrator
- Secure admin login.
- Add and manage laboratories.
- View student records.
- View pending booking requests.
- Approve or reject booking requests.
- Provide a reason when rejecting a booking.
- Manage and review booking history.

## Technologies Used

- **Programming Language:** Java
- **Database:** MySQL
- **IDE:** IntelliJ IDEA
- **Database Connectivity:** JDBC
- **Version Control:** Git and GitHub

## Data Structures Used

| Data Structure | Application |
|---|---|
| AVL Tree | Managing and searching laboratory records |
| Student AVL Tree | Managing and searching student records |
| Queue | Processing pending booking requests in FIFO order |
| Linked List | Maintaining processed booking history |

These data structures are implemented manually to demonstrate DSA concepts rather than relying on Java's built-in collection classes.

## Project Structure

```text
src/
└── universitylab/
    ├── Main.java
    │
    ├── model/
    │   ├── Student.java
    │   ├── Lab.java
    │   └── Booking.java
    │
    ├── datastructures/
    │   ├── AVLTree.java
    │   ├── StudentAVLTree.java
    │   ├── Queue.java
    │   └── BookingHistory.java
    │
    ├── services/
    │   ├── AuthenticationManager.java
    │   ├── AvailabilityManager.java
    │   ├── BookingManager.java
    │   ├── LabManager.java
    │   └── StudentManager.java
    │
    ├── dao/
    │   ├── StudentDAO.java
    │   ├── LabDAO.java
    │   └── BookingDAO.java
    │
    ├── database/
    │   ├── DB