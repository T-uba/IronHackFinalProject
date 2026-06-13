# MY FINAL JAVA PROJECT
**Title:** Box Studio Booking System  
**Entities:** Trainees, Fighting Area, Booking, Slot 

The "Boxing Studio Booking System" is an application designed to efficiently manage training schedules in a boxing gym while optimizing facility utilization. 

The "Trainee" manages all relevant member data, including their contact details. The "Fighting Area" represents physical training zones, such as boxing rings, training zones along with their capacities. 

Trainees book the training area using the system. 
The booking can always be made as long as there is remaiing capacity in the selected fighting area. 

---

---

## Requirements

### Trainee Actions
- A trainee can register themselves.
- A trainee can login and receive an access token.
- A trainee can see all registered trainees.
- A trainee can delete his/her account.
- A trainee can create a booking for multiple fighting areas.
- A trainee can only book a fighting area if it is not already booked at that time.
- A trainee can see all bookings and his/her personal bookings.
- A trainee can update an existing booking.
- A trainee can delete his/her booking only if it is +24 hours before the appointment.

### Manager Actions
- A manager can add a fighting area with a name and capacity.
- A manager can delete a fighting area.

<br>

---
Presentation Link: https://docs.google.com/presentation/d/1mRjSQTPHxt3fGO0CcGLnhZnWMZVlHSld6ICUPnEd5P0/edit?usp=sharing

GitHub Link: https://github.com/T-uba/IronHackFinalProject.git

---

<br>


## Class Diagram (ERD)

```mermaid
classDiagram
    class TRAINEE {
        - Long id
        - String firstName
        - String lastName
        - String email
        - String password
        - String role
    }
    
    class BOOKING {
        - Long id
        - LocalDateTime startTime
        - LocalDateTime endTime
    }
    
    class FIGHTING_AREA {
        - Long id
        - String name
        - int capacity
    }
    
    TRAINEE "1" --> "n" BOOKING : has
    BOOKING "n" --> "m" FIGHTING_AREA : to
```


<br>
<br>

## API First Design

### 1. Trainee API

#### POST /api/register
Create new Trainee

| POST /api/register | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | None | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| {<br>&nbsp;&nbsp;"firstName",<br>&nbsp;&nbsp;"lastName",<br>&nbsp;&nbsp;"email",<br>&nbsp;&nbsp;"password"<br>} | **201 Created**<br>"Trainee registered successfully." | **400 Bad Request**<br>"Email is already taken."<br><br>**400 Bad Request**<br>"The provided name is too short." |

<br>

#### POST /api/register/manager
Create new Manager

| POST /api/register/manager | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | None | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| {<br>&nbsp;&nbsp;"firstName",<br>&nbsp;&nbsp;"lastName",<br>&nbsp;&nbsp;"email",<br>&nbsp;&nbsp;"password"<br>} | **201 Created**<br>"Manager registered successfully." | **400 Bad Request**<br>"Email is already taken."<br><br>**400 Bad Request**<br>"The provided name is too short." |

<br>

#### GET /api/trainees
Get all Trainees (Managers Only)

| GET /api/trainees | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token (ROLE_MANAGER) | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| None | **200 OK**<br>[<br>&nbsp;&nbsp;{ "firstName", "lastName", "email" }, ...<br>] | **403 Forbidden**<br>"Access denied. Managers only." |

<br>

#### DELETE /api/trainees/delete/{id}
Delete a Trainee Account

| DELETE /api/trainees/delete/{id} | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| PathVariable: id | **200 OK**<br>"Account deleted successfully." | **400 Bad Request**<br>"Trainee account does not exist." |

---
<br>


### 2. Login API

#### POST /api/login
Authenticate User and get Access Token

| POST /api/login | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Basic Auth (Email & Password in Header) | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| None | **200 OK**<br>{<br>&nbsp;&nbsp;"access_token": "JWT_TOKEN_STRING"<br>} | **401 Unauthorized**<br>Bad credentials |

---
<br>


### 3. Booking API

#### GET /api/bookings
Get Studio Bookings (Managers see all, Trainees see only their own)

| GET /api/bookings | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| None | **200 OK**<br>[<br>&nbsp;&nbsp;{ "id", "trainee", "fightingAreas": [], "startTime", "endTime" }, ...<br>] | **403 Forbidden**<br>Access Token missing or invalid |

<br>

#### GET /api/bookings/trainee/{traineeId}
Get Bookings for a Specific Trainee by ID

| GET /api/bookings/trainee/{traineeId} | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| PathVariable: traineeId | **200 OK**<br>[<br>&nbsp;&nbsp;{ "id", "trainee", "fightingAreas": [], "startTime", "endTime" }, ...<br>] | **403 Forbidden**<br>Access Token invalid |

<br>

#### POST /api/bookings
Create a New Booking

| POST /api/bookings | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| {<br>&nbsp;&nbsp;"traineeId",<br>&nbsp;&nbsp;"fightingAreaIds": [],<br>&nbsp;&nbsp;"startTime",<br>&nbsp;&nbsp;"endTime"<br>} | **201 Created**<br>"Booking created successfully." | **400 Bad Request**<br>"Trainee does not exist."<br><br>**400 Bad Request**<br>"Fighting area does not exist."<br><br>**400 Bad Request**<br>"This area has reached its maximum capacity." |

<br>

#### PUT /api/bookings
Update an Existing Booking

| PUT /api/bookings | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| {<br>&nbsp;&nbsp;"id",<br>&nbsp;&nbsp;"traineeId",<br>&nbsp;&nbsp;"fightingAreaIds": [],<br>&nbsp;&nbsp;"startTime",<br>&nbsp;&nbsp;"endTime"<br>} | **200 OK**<br>"Booking updated successfully." | **400 Bad Request**<br>"Booking does not exist."<br><br>**400 Bad Request**<br>"Fighting area does not exist."<br><br>**400 Bad Request**<br>"This area has reached its maximum capacity." |

<br>

#### DELETE /api/bookings/delete/{id}
Delete a Booking (Allowed up to 24h before)

| DELETE /api/bookings/delete/{id} | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| PathVariable: id | **200 OK**<br>"Booking deleted successfully." | **400 Bad Request**<br>"Booking does not exist."<br><br>**400 Bad Request**<br>"Cannot delete booking less than 24 hours before." |

---
<br>


### 4. Fighting Area API

#### POST /api/areas
Create a New Fighting Area (Managers Only)

| POST /api/areas | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token (ROLE_MANAGER) | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| {<br>&nbsp;&nbsp;"name",<br>&nbsp;&nbsp;"capacity"<br>} | **201 Created**<br>"Fighting area created successfully." | **400 Bad Request**<br>"Invalid area name."<br><br>**403 Forbidden**<br>Access denied |

<br>

#### GET /api/areas
Get All Fighting Areas

| GET /api/areas | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| None | **200 OK**<br>[<br>&nbsp;&nbsp;{ "id", "name", "capacity" }, ...<br>] | **403 Forbidden**<br>Access Token invalid |

<br>

#### DELETE /api/areas/delete/{id}
Delete a Fighting Area (Managers Only)

| DELETE /api/areas/delete/{id} | | |
| :--- | :--- | :--- |
| **AUTHORIZATION:** | Bearer Token (ROLE_MANAGER) | |
| **Data IN:** | **Data out (Success):** | **Data out (Error):** |
| PathVariable: id | **200 OK**<br>"Fighting area successfully deleted." | **403 Forbidden**<br>Access denied |

---

