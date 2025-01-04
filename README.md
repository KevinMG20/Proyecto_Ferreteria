# Point of Sale for Hardware Stores 🛠️🖥️

This is a desktop application developed with **Java Swing** and powered by a **MySQL** database. Specifically designed for hardware businesses, it enables the management of employees, products, suppliers, deliveries, and sales, providing a modern, efficient, and tailored experience for business needs.

---

## 📸 Screenshots

![Start](./Capturas/Login.png)
*Login*

![Game Modes](./Capturas/Empleados.png)
*Employees*

![Character Selector](./Capturas/Productos.png)
*Products*

![Game Board](./Capturas/Proveedores.png)
*Suppliers*

![Game Board](./Capturas/Entregas.png)
*Deliveries*

![Game Board](./Capturas/Ventas.png)
*Sells*

![Game Board](./Capturas/Reportes.png)
*Generated Reports*

---

## ✨ Main Features

### 🔐 **Login**
- Secure login with username and password.
- User privilege control:
  - **Regular Workers**: Limited access.
  - **Manager**: Access to advanced administrative tools.

---

## 🖥️ **Main Screen and Sections**

### 👥 **Employees**
- **Profile data**: Each user can view their personal information.
- **Coworkers list**: View all registered employees.
- **Manager-exclusive tools**:
  - Generate PDF reports for:
    - Employee list.
    - Products.
    - Suppliers.
    - Deliveries.
    - Sales.
  - Modify their own profile data.
  - Register, modify, or delete employees.

---

### 🛒 **Products**
- **Category paginator**: Easily navigate through different categories.
- **Product table**: Clear and organized view of available products.
- **Individual details**: Detailed information for each product.

---

### 📦 **Suppliers**
- **Supplier paginator**: Allows simple navigation between registered suppliers.
- **CRUD Operations**:
  - Create, read, update, and delete supplier information.
- **Advanced search**: Quickly find suppliers using various criteria.

---

### 🚛 **Merchandise Deliveries**
- **Register new deliveries**: Efficiently document every delivery.
- **Recent deliveries view**: Check recent deliveries in one place.
- **Delivery history**: Access detailed information about past deliveries.
- **Advanced search**: Quickly locate specific deliveries.
- **Manager tools**:
  - Modify deliveries.
  - Delete deliveries.

---

### 💵 **Sales**
- **Register new sales**: Fast and simple flow to register sales.
- **Recent sales view**: Check the most recent sales.
- **Expanded information**: Detailed view of a specific sale.
- **Advanced operations**:
  - Search, modify, and delete sales.
- **Customer ticket preview**: Generate and preview the ticket before printing.

---

## 🎨 User Interface
- Modern, intuitive, and user-friendly design.
- Efficient navigation with **database-connected paginators**, allowing users to view all information in one place.

---

## 🛠️ Technologies Used
- **Java Swing**: For the graphical user interface.
- **MySQL**: For data management and storage.
- **iText and JasperReports**: For generating reports in PDF format.
- **JDBC**: For database connection and manipulation.
- **Privilege control**: Differentiates access levels between users (regular workers and managers).

---

## 🚀 Installation and Execution

1. Clone the repository:
   ```bash
   git clone https://github.com/KevinMG20/Proyecto_Ferreteria.git
   
   
2. Set up the database:
Import the SQL file located in the root folder.
Ensure you configure the username and password in the program's connection file.
Compile and run the program:
```bash
java principal.java
