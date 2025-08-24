## Step 2: Ticket Submission Stack

This covers user-facing ticket creation, independent of login or dashboard.

### 2.1 Create Entities
- User (simplified: `id, name, email`)
- Ticket (`id, user, category, subject, description, status=NEW, createdAt`)

**References**
- JPA Entities: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.entities
- Hibernate Annotations: https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#annotations

---

### 2.2 Create Repositories
- `TicketRepository extends JpaRepository<Ticket, Long>`

**References**
- Spring Data JPA Repositories: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories

---

### 2.3 Create Service
- `TicketService` with methods:
  - `createTicket(Ticket ticket)`
  - `listTicketsByUser(User user)`
  - `removeTicketByUser(User user)`
  - Any other method you believe will be needed

**References**
- Service Layer in Spring: https://docs.spring.io/spring-framework/reference/core/beans/definition.html

---

### 2.4 Create Controller
- `TicketController`:
  - `GET /tickets/create/step1` → Show form for user details (name, email)
  - `POST /tickets/create/step1` → Save user details in session, go to step 2
  - `GET /tickets/create/step2` → Show form for ticket details (category, subject, description)
  - `POST /tickets/create/step2` → Save ticket draft, go to step 3
  - `GET /tickets/create/step3` → Show review/confirmation page
  - `POST /tickets/create/submit` → Persist final ticket in DB with status NEW
  - `GET /tickets/{id}` → Show submitted ticket details

- Notes:
  - You can storage user data between pages using: `@SessionAttributes`
  - Remember to use `redirect:/tickets/create/stepX` instead of direct return to avoid resubmission.

**References**
- Spring MVC Controllers: https://docs.spring.io/spring-framework/reference/web/webmvc.html
- Session Attributes in Spring MVC: https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/sessionattributes.html

---

### 2.5 Create Views
- `ticket_step1.html` → Form for user info
- `ticket_step2.html` → Form for ticket details
- `ticket_step3.html` → Review page with collected info
- `ticket_success.html` → Final confirmation

**References**
- Thymeleaf with Spring Boot: https://www.thymeleaf.org/doc/tutorials/3.1/thymeleafspring.html
- Thymeleaf Form Binding: https://www.thymeleaf.org/doc/tutorials/3.1/thymeleafspring.html#creating-forms

### 3 MVP
  - [ ] I can submit a form and it's storage in the DB
  - [ ] I can open a submission using a reference (Reference creation and main page change for this requirement)
  - [ ] I can see a confirmation page with a reference ( We can send emails in the future if you want)
