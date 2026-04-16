# Quick Commerce Grocery Delivery Application

A fully object-oriented Java project modelled from the UML class diagram.

---

## Project Structure

```
QuickCommerceApp/
└── src/main/java/com/quickcommerce/
    ├── Main.java                          ← Entry point / demo runner
    ├── SpringApp.java                      ← Spring Boot wiring & app listener
    ├── enums/                              ← small value enums (OrderStatus, PaymentMethod, ...)
    ├── exception/                          ← domain exceptions
    ├── interfaces/                         ← `Trackable`, `Processable`
    ├── model/                              ← domain model (User, Customer, Vendor, Product, Cart, Order, Payment, ...)
    ├── factory/                            ← Factory classes (CartFactory, OrderFactory, CartItemFactory)
    ├── facade/                             ← Facade implementations (ProductManagementFacade)
    ├── role/                               ← Strategy implementations for roles (RoleBehavior, CustomerBehavior, ...)
    ├── payment/handler/                    ← Chain-of-responsibility handlers (ValidationHandler, AuthorizationHandler, ...)
    ├── persistence/
    │   ├── entity/                         ← JPA entity classes (UserEntity, ProductEntity, OrderEntity, ...)
    │   └── repo/                           ← Spring Data repositories
    ├── controller/                         ← UI controllers wrapping services
    ├── service/                            ← domain services (IUserService, ProductService, OrderService, mappers)
    └── gui/                                ← Swing UI, frames, panels and components
```

---

## OOP Principles Applied

| Principle | Where |
|---|---|
| **Abstraction** | `User` is abstract; `Trackable` and `Processable` are interfaces |
| **Encapsulation** | All fields are private; accessed only via getters/setters |
| **Inheritance** | `Customer`, `Vendor`, `DeliveryPartner`, `Administrator` all extend `User` |
| **Polymorphism** | `getRole()` overridden in every subclass; `Trackable` and `Processable` used generically |
| **Single Responsibility (SRP)** | Each class has one reason to change; service layer handles orchestration |
| **Open/Closed (OCP)** | New user types extend `User` without modifying it |
| **Liskov Substitution (LSP)** | Every `User` subtype can be used wherever `User` is expected |
| **Interface Segregation (ISP)** | `Trackable` and `Processable` are small, focused interfaces |
| **Dependency Inversion (DIP)** | Services depend on abstractions (interfaces) not concrete classes |

---

## Design Pattern Mapping

Below are the four design patterns implemented and where to find their code.

| Assigned Person | Pattern | Category | Files (implementation) | Note |
|---|---|---:|---|---|
| Person 1 — Cart / Order / CartItem | Factory (static factories) | Creational | [src/main/java/com/quickcommerce/factory/CartFactory.java](src/main/java/com/quickcommerce/factory/CartFactory.java), [src/main/java/com/quickcommerce/factory/OrderFactory.java](src/main/java/com/quickcommerce/factory/OrderFactory.java), [src/main/java/com/quickcommerce/factory/CartItemFactory.java](src/main/java/com/quickcommerce/factory/CartItemFactory.java) | Centralises object creation; production code now uses these factories (e.g., `Customer` and `Cart`). |
| Person 2 — Admin / Vendor / Product | Facade (`ProductManagementFacade`) | Structural | [src/main/java/com/quickcommerce/facade/ProductManagementFacade.java](src/main/java/com/quickcommerce/facade/ProductManagementFacade.java) | Simplifies product/vendor/admin workflows; used from `Main`, `AppContext`, and `VendorPanel`. |
| Person 3 — User / Customer / DeliveryPartner | Strategy (`RoleBehavior`) | Behavioral | [src/main/java/com/quickcommerce/role/RoleBehavior.java](src/main/java/com/quickcommerce/role/RoleBehavior.java), [src/main/java/com/quickcommerce/role/CustomerBehavior.java](src/main/java/com/quickcommerce/role/CustomerBehavior.java), [src/main/java/com/quickcommerce/role/VendorBehavior.java](src/main/java/com/quickcommerce/role/VendorBehavior.java), [src/main/java/com/quickcommerce/role/DeliveryPartnerBehavior.java](src/main/java/com/quickcommerce/role/DeliveryPartnerBehavior.java), [src/main/java/com/quickcommerce/role/RoleBehaviorFactory.java](src/main/java/com/quickcommerce/role/RoleBehaviorFactory.java) | Role-specific runtime behaviour invoked from `UserService.login()` / `logout()`. |
| Person 4 — Payment / Order Items | Chain of Responsibility (payment pipeline) | Behavioral | [src/main/java/com/quickcommerce/payment/handler/PaymentHandler.java](src/main/java/com/quickcommerce/payment/handler/PaymentHandler.java), [src/main/java/com/quickcommerce/payment/handler/ValidationHandler.java](src/main/java/com/quickcommerce/payment/handler/ValidationHandler.java), [src/main/java/com/quickcommerce/payment/handler/AuthorizationHandler.java](src/main/java/com/quickcommerce/payment/handler/AuthorizationHandler.java), [src/main/java/com/quickcommerce/payment/handler/CaptureHandler.java](src/main/java/com/quickcommerce/payment/handler/CaptureHandler.java), [src/main/java/com/quickcommerce/payment/handler/InventoryUpdateHandler.java](src/main/java/com/quickcommerce/payment/handler/InventoryUpdateHandler.java), [src/main/java/com/quickcommerce/payment/handler/PaymentProcessingChain.java](src/main/java/com/quickcommerce/payment/handler/PaymentProcessingChain.java) | Payment processing pipeline executed from `Order.checkout()`. |

---

## UML Relationships Implemented

| Relationship | Classes | Multiplicity |
|---|---|---|
| Inheritance | User ← Customer / Vendor / DeliveryPartner / Administrator | — |
| Composition | Cart → CartItem | 1 to 1..* |
| Composition | Customer → Cart | 1 to 1 |
| Composition | Order → OrderItem | 1 to 1..* |
| Composition | Order → Payment | 1 to 1 |
| Association | Customer → Order | 1 to * |
| Association | DeliveryPartner → Order | 1 to 0..* |
| Association | Vendor → Product | 1 to * |
| Association | OrderItem → Product | * to 1 |
| Association | CartItem → Product | * to 1 |

---

## How to Build & Run

This project is a Spring Boot application that launches a Swing UI. The build uses Maven and targets Java 17.

### Requirements
- Java 17 (JDK 17)
- Maven 3.6+

> The app is launched by Spring Boot (`com.quickcommerce.SpringApp`). Spring is used to wire services; the GUI runs on the AWT Event Dispatch Thread (EDT). The Spring Boot plugin repackages the app as an executable JAR.

### Build (PowerShell)
```powershell
# From the project root (where pom.xml is)
mvn -DskipTests clean package
```

### Run (PowerShell)
```powershell
# Run via Spring Boot (runs on the EDT and opens the Swing UI)
mvn spring-boot:run

# Or run the packaged jar after building
java -jar target\QuickCommerceApp-1.0.0.jar
```

### Run from an IDE
1. Open the project in **IntelliJ IDEA** or **Eclipse**
2. Ensure the project SDK is set to **Java 17** and Maven import completes
3. Run the Spring Boot application `com.quickcommerce.SpringApp` (Run as Java Application / Spring Boot)

### Troubleshooting
- If the UI does not appear, ensure the JVM is not running in headless mode. The app sets headless=false in `SpringApp`.
- If Maven fails to download Spring artifacts, check your network/proxy settings or run `mvn -U clean package`.

---

## Demo Flow (Main.java)

When you run the application, it simulates a complete grocery delivery workflow:

1. A **Vendor** registers and lists 5 products (Milk, Bread, Eggs, Rice, Chocolate)
2. A **Customer** logs in and adds items to their cart
3. The customer places an **Order** and pays via **UPI**
4. The **Vendor** confirms the order
5. A **Delivery Partner** is auto-assigned by the `OrderService`
6. The delivery partner marks the order as **Delivered**
7. The customer **tracks** the order
8. A **second order** is placed and then **cancelled** (demonstrating stock restoration)
9. The **Admin** generates a full report and monitors active orders

---

## Extending the Project

- **Add a database layer**: Replace the in-memory `List<>` stores in each service with JPA repositories
- **Add a REST API**: Wrap each service with a Spring Boot `@RestController`
- **Add Spring Security**: Integrate with the existing `login()` / `logout()` logic
- **Add a Notification system**: Implement an Observer pattern to email/SMS customers on status changes

---

## GRASP & SOLID Mapping

The table below maps main source files to applicable GRASP and SOLID principles. Click a file path to open it in your editor.

| File | GRASP Principles | SOLID Principles | Notes |
|---|---|---|---|
| [src/main/java/com/quickcommerce/controller/ProductController.java](src/main/java/com/quickcommerce/controller/ProductController.java) | Controller, Information Expert, Low Coupling | SRP, DIP | Thin controller; delegates to `IProductService`. |
| [src/main/java/com/quickcommerce/controller/UserController.java](src/main/java/com/quickcommerce/controller/UserController.java) | Controller, Information Expert | SRP, DIP | Delegates auth/registration to `IUserService`. |
| [src/main/java/com/quickcommerce/controller/OrderController.java](src/main/java/com/quickcommerce/controller/OrderController.java) | Controller | SRP, DIP | Routes UI actions to `IOrderService`. |
| [src/main/java/com/quickcommerce/service/ProductService.java](src/main/java/com/quickcommerce/service/ProductService.java) | Information Expert, Creator, High Cohesion | SRP (improved), OCP, DIP | Owns product catalogue; mapping/presentation moved out to mappers/presenters. |
| [src/main/java/com/quickcommerce/service/ProductMapper.java](src/main/java/com/quickcommerce/service/ProductMapper.java) | Pure Fabrication | SRP | Mapping logic extracted from `ProductService`. |
| [src/main/java/com/quickcommerce/service/ProductPresenter.java](src/main/java/com/quickcommerce/service/ProductPresenter.java) | Pure Fabrication | SRP | Presentation separated from business logic. |
| [src/main/java/com/quickcommerce/service/UserService.java](src/main/java/com/quickcommerce/service/UserService.java) | Information Expert, Creator (user objects) | SRP (partial), DIP | Handles registration/auth; consider extracting mapping to `UserMapper`. |
| [src/main/java/com/quickcommerce/service/OrderService.java](src/main/java/com/quickcommerce/service/OrderService.java) | Information Expert, Creator (orders) | SRP, DIP | Manages order lifecycle and delivery assignment. |
| [src/main/java/com/quickcommerce/gui/AppContext.java](src/main/java/com/quickcommerce/gui/AppContext.java) | Indirection, Low Coupling | DIP | Mediates between GUI and services; allows DI wiring. |
| [src/main/java/com/quickcommerce/SpringApp.java](src/main/java/com/quickcommerce/SpringApp.java) | Protected Variations, Indirection | DIP | Exposes service beans via interfaces to protect callers from implementation changes. |
| [src/main/java/com/quickcommerce/Main.java](src/main/java/com/quickcommerce/Main.java) | Creator, Controller (app runner) | SRP | Demo runner that assembles and exercises components. |
| [src/main/java/com/quickcommerce/service/IProductService.java](src/main/java/com/quickcommerce/service/IProductService.java) | Protected Variations | ISP, DIP | Service abstraction to decouple controllers from implementation. |
| [src/main/java/com/quickcommerce/service/IUserService.java](src/main/java/com/quickcommerce/service/IUserService.java) | Protected Variations | ISP, DIP | Abstraction for user operations. |
| [src/main/java/com/quickcommerce/service/IOrderService.java](src/main/java/com/quickcommerce/service/IOrderService.java) | Protected Variations | ISP, DIP | Abstraction for order operations. |

