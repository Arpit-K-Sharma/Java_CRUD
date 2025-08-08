# Student CRUD Web Application

A simple CRUD application built with **Java EE 7** to manage student records. The project uses **JSF** for the view layer, **JPA** (EclipseLink) for persistence, and **MySQL** for data storage.

---

## Features

- Add, edit, delete, and list students.
- Track student information: name, address, class, and subject.
- Dynamic subject list changes based on the selected class.
- JSF front end backed by a managed bean for seamless data binding.
- Maven‐based build producing a deployable WAR file.

---

## Requirements

| Component                     | Version / Notes                                                       |
| ----------------------------- | --------------------------------------------------------------------- |
| Java Development Kit (JDK)   | 8                                                                     |
| Maven                         | 3.x                                                                  |
| MySQL                         | Any recent 8.x version                                                |
| Java EE Application Server    | GlassFish / Payara / WildFly / TomEE (Java EE 7 compliant)           |
| JDBC Data Source              | JNDI name `jdbc/studentdb` (configured on the application server)     |

---

## Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Java_CRUD/Task_2
   ```

2. **Create the MySQL database and table**
   ```sql
   CREATE DATABASE studentdb;
   USE studentdb;

   CREATE TABLE student (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(255),
       address VARCHAR(255),
       student_class VARCHAR(50),
       subject VARCHAR(255)
   );
   ```

3. **Configure the application server**

   - Create a JDBC connection pool pointing to your MySQL instance.
   - Register a data source with JNDI name `jdbc/studentdb`.

4. **Build the project**
   ```bash
   mvn clean package
   ```
   This generates `Task_2.war` in the `target/` directory.

5. **Deploy the WAR**

   - GlassFish / Payara: use the admin console or `asadmin deploy target/Task_2.war`.
   - WildFly / TomEE: similar deployment via console or CLI.

6. **Run the application**

   Open your browser and navigate to:
   ```
   http://localhost:8080/Task_2/
   ```
   *(URL may differ depending on server configuration.)*

---

## Project Structure

```
Task_2/
├── pom.xml                           # Maven build configuration
└── src
    ├── main
    │   ├── java
    │   │   └── com/studentcrudapp
    │   │       ├── controller
    │   │       │   └── StudentBean.java       # JSF backing bean
    │   │       ├── model
    │   │       │   └── Student.java           # JPA entity
    │   │       └── service
    │   │           └── StudentService.java    # CRUD operations
    │   ├── resources
    │   │   └── META-INF
    │   │       └── persistence.xml            # JPA configuration
    │   └── webapp
    │       ├── index.xhtml                    # JSF page
    │       └── WEB-INF
    │           ├── faces-config.xml           # JSF config
    │           └── web.xml                    # Servlet configuration
    └── test                                   # (no tests provided)
```

---

## Notes & Tips

- **PrimeFaces** is declared as a dependency, enabling the use of advanced UI components if desired.
- The `persistence.xml` property `eclipselink.ddl-generation` is set to `none`, so the database schema must already exist.
- When editing, the subject list is repopulated based on the selected class via JSF's `<f:ajax>`.

---

## IDE Usage

1. Import the project as a Maven project (IntelliJ IDEA, Eclipse, etc.).
2. Ensure the IDE uses JDK 8.
3. Configure an application server within the IDE and deploy the `Task_2` module.

---

## Potential Improvements

- Add form validation and user feedback messages.
- Implement authentication/authorization.
- Replace managed bean with CDI (`@Named` and `@ViewScoped`) for newer JSF versions.
- Add unit or integration tests.
- Enable schema auto-generation during development for easier setup.

---


