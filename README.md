# API Automation Framework - Restful Booker

This project is an **API Automation Framework** built to test the [Restful-Booker](https://restful-booker.herokuapp.com) APIs.  
The framework is developed using **Java**, **Rest-Assured**, **TestNG**, **GSON** for payload management, and **Allure** for reporting.  

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

## 🏗️ Project Structure

### Root Level
API_Automation_Framework_Restful-Booker
│── .idea/ # IDE specific files
│── allure-results/ # Allure report results
│── src/ # Source folder
│── pom.xml # Maven dependencies
│── testng_*.xml # TestNG suite files
│── .gitignore


### Source (src/main/java)
com.avinashsinha.endpoints
└── APIConstants # Base URLs and endpoint constants

com.avinashsinha.modules
└── PayloadManager # GSON-based payload builders

com.avinashsinha.pojos
├── Auth
├── Booking
├── BookingDates
├── BookingResponse
└── TokenResponse # POJO models for request/response mapping


### Tests (src/test/java)
com.avinashsinha.asserts
└── AssertActions # Custom assertion actions

com.avinashsinha.base
└── BaseTest # Test setup and teardown

com.avinashsinha.tests.crud
├── TestBookingCreate
├── TestBookingDateValidation
├── TestBookingDeletion
├── TestBookingFullUpdate
├── TestBookingPartialUpdate
├── TestBookingVerificationById
├── TestBookingVerificationByName
├── TestCheckHealth
└── TestTokenCreate

com.avinashsinha.tests.integration
└── TestE2EFlow # End-to-End booking workflow tests

com.avinashsinha.tests.sample
└── TestIntegrationSample


---

## ⚙️ Tech Stack
- **Java 11+**
- **Maven** (Build tool)
- **Rest-Assured** (HTTP client for API automation)
- **TestNG** (Testing framework)
- **AssertJ** (Advanced assertions)
- **GSON** (Payload serialization & deserialization)
- **Allure Reports** (Test reporting)
- **Full Folder Structure** (Hybrid Framework)

---

## ▶️ Running Tests

Run specific TestNG suite:
mvn clean test -DsuiteXmlFile=testng_createBooking.xml

Available TestNG XMLs:

- testng_Integration.xml
- testng_createBooking.xml
- testng_deleteBookingId.xml
- testng_fullUpdate.xml
- testng_partialUpdate.xml
- testng_sample.xml
- testng_verifyByDate.xml
- testng_verifyById.xml
- testng_verifyByName.xml

---

## 📊 Reporting

Generate Allure Report

1. Execute tests:
   mvn clean test

3. Generate Allure Report:
   allure serve allure-results

![Restfull](https://github.com/user-attachments/assets/4e746c4a-78d6-4c0d-9e67-492ff048c799)

This will launch an interactive report in your browser.

---

## ✅ Example Payload with GSON

Example of booking payload creation using GSON:

Booking booking = new Booking();
booking.setFirstname("John");
booking.setLastname("Doe");
booking.setTotalprice(120);
booking.setDepositpaid(true);

BookingDates bookingDates = new BookingDates();
bookingDates.setCheckin("2025-10-01");
bookingDates.setCheckout("2025-10-05");
booking.setBookingdates(bookingDates);

String payload = new Gson().toJson(booking);
