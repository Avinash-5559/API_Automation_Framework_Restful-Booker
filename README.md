# API Automation Framework - Restful Booker

![Java](https://img.shields.io/badge/Java-11%2B-blue)
![IntelliJ](https://img.shields.io/badge/IntelliJ-IDEA-0C4B33?logo=jetbrains)
![Maven](https://img.shields.io/badge/Maven-Build-orange)
![TestNG](https://img.shields.io/badge/TestNG-Framework-brightgreen)
![RestAssured](https://img.shields.io/badge/RestAssured-API--Testing-yellowgreen)
![GSON](https://img.shields.io/badge/GSON-Payload%20Mapper-lightgrey)
![Allure](https://img.shields.io/badge/Allure-Reports-ff69b4)


This project is an **API Automation Framework** built to test the [Restful-Booker](https://restful-booker.herokuapp.com) APIs.  
The framework is developed using **Java**, **Rest-Assured**, **TestNG**, **GSON** for payload management, **Allure** for reporting, and **IntelliJ IDEA** as the IDE.

---

## 🚀 Features
- API test automation for **CRUD operations** (Create, Read, Update, Delete) on bookings.
- Payload creation and serialisation using **GSON**.
- Modular design with separation of concerns:
  - **Endpoints** for constants
  - **Modules** for payload management
  - **POJOs** for request/response mapping
  - **Tests** organised by CRUD, Integration, and Sample flows
- Assertion layer for reusable validations.
- Integrated with **Allure Reports** for rich test reporting.
- Test execution controlled via **TestNG XMLs**.

---

## ⚙️ Tech Stack
- **Java 11+**
- **IntelliJ IDEA** (IDE for development)
- **Maven** (Build tool)
- **Rest-Assured** (HTTP client for API automation)
- **TestNG** (Testing framework)
- **AssertJ** (Advanced assertions)
- **GSON** (Payload serialization & deserialization)
- **Allure Reports** (Test reporting)
- **Full Folder Structure** (Hybrid Framework)

---

## 🏗️ Project Structure

### Root Level
- **API Automation Framework Restful Booker**
  - `.idea/` *# IntelliJ IDE-specific files* (git-ignored)
  - `allure-results/` *# Allure report results* (generated after tests)
  - `pom.xml` *# Maven dependencies*
  - `testng_*.xml` *# TestNG suite files*
  - `.gitignore`
  - `src/` *# Source folder*
    - (See below for detailed structure)

### Source (`src/main/java`)
- `com.avinashsinha.endpoints`
  - `APIConstants` *# Base URLs and endpoint constants*
- `com.avinashsinha.modules`
  - `PayloadManager` *# GSON-based payload builders*
- `com.avinashsinha.pojos`
  - `Auth`
  - `Booking`
  - `BookingDates`
  - `BookingResponse`
  - `TokenResponse` *# POJO models for request/response mapping*

### Tests (`src/test/java`)
- `com.avinashsinha.asserts`
  - `AssertActions` *# Custom assertion actions*
- `com.avinashsinha.base`
  - `BaseTest` *# Test setup and teardown*
- `com.avinashsinha.tests.crud`
  - `TestBookingCreate`
  - `TestBookingDateValidation`
  - `TestBookingDeletion`
  - `TestBookingFullUpdate`
  - `TestBookingPartialUpdate`
  - `TestBookingVerificationById`
  - `TestBookingVerificationByName`
  - `TestCheckHealth`
  - `TestTokenCreate`
- `com.avinashsinha.tests.integration`
  - `TestE2EFlow` *# End-to-End booking workflow tests*
- `com.avinashsinha.tests.sample`
  - `TestIntegrationSample`

---

## ▶️ Running Tests

### Integration Test (Create Token and Create Booking, Update and Delete Booking)
```bash
mvn clean test -DsuiteXmlFile=testng_Integration.xml
```
#### Available TestNG XMLs

- `testng_createBooking.xml`
- `testng_deleteBookingId.xml`
- `testng_fullUpdate.xml`
- `testng_partialUpdate.xml`
- `testng_sample.xml`
- `testng_verifyByDate.xml`
- `testng_verifyByID.xml`
- `testng_verifyByName.xml`

---
## 📊 Reporting
### Generate Allure Report
```bash
allure serve allure-results
```
  <img src="https://github.com/user-attachments/assets/4e746c4a-78d6-4c0d-9e67-492ff048c799" alt="Restful Booker Allure Report" width="1100">

This will launch an interactive report in your browser.

---
##  ✅ Example Payload with GSON

Example of booking payload creation using GSON:
```java
Booking booking = new Booking();
booking.setFirstname("John");
booking.setLastname("Doe");
booking.setTotalprice(120);
booking.setDepositpaid(true);

BookingDates bookingDates = new BookingDates();
bookingDates.setCheckin("2025-10-01");
bookingDates.setCheckout("2025-10-05");
booking.setBookingdates(bookingDates);
booking.setAdditionalneeds("Lunch");
```
---
## 📌 Author
👤 Avinash Sinha
