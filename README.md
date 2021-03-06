# BackEnd-Ordenes

Esta aplicación consiste en un sistema que permite realizar ordenes de productos para clientes. Cada cliente tiene asociados unos productos sobre los cuales puede hacer ordenes de compra de hasta maximo 5 productos. Incluye pruebas unitarias. Entre las funciones del servicio web REST estan:
	
	Productos: Listar, Buscar, Crear, Editar, Eliminar. (Al crear o editar un producto se le indica el nombre y precio).
	
	Clientes:  Listar, Buscar, Crear, Editar, Eliminar. (Al crear o editar un cliente se le indica el nombre, email y productos).
	
	Ordenes: Crear, Buscar ordenes de un cliente, Buscar ordenes del ultimo mes de un cliente, Buscar ordenes de un cliente por un rango de fechas. (Al crear una orden se le indica cliente, dirección de entrega, fecha de creación, cantidad de cada producto asociado con máximo de 5 unidades totales).

- Proyecto JAVA con uso de Spring Framework, pruebas unitarias y base de datos h2 configurada desde hibernate. El frontEnd esta disponible en: https://github.com/lmarcela/FrontEnd-Ordenes

- Para hacer uso efectivo de hibernate y la conexion a mysql se debe modificar la url, nombre de usuario, contraseña y driver en src/main/resources. Actualmente las lineas estan comentadas pues se esta haciendo uso de h2 pero si se desea hacer uso de otra base de datos se deben quitar los numerales (#). Tiene la siguiente configuracion:

	#spring.datasource.url=jdbc:mysql://localhost:3307/tienda
	
	#spring.datasource.username=root
	
	#spring.datasource.password=root
	
	#spring.datasource.driver-class-name=com.mysql.jdbc.Driver


- El proyecto es un spring boot. Para ejecutarlo en Spring Tools Suite se debe correr como Spring Boot App.

Secciones del README:
- <a href="https://github.com/lmarcela/BackEnd-Ordenes#algunas-vistas-prueba-del-funcionamiento-con-postman">Algunas Vistas (Prueba del funcionamiento con Postman)</a> 
- <a href="https://github.com/lmarcela/BackEnd-Ordenes#test-unitarios">Test Unitarios</a> 
- <a href="https://github.com/lmarcela/BackEnd-Ordenes#comandos-para-git">COMANDOS PARA GIT</a> 
- <a href="https://github.com/lmarcela/BackEnd-Ordenes#configuracion-en-jenkins">Configuracion en jenkins</a> 
- <a href="https://github.com/lmarcela/BackEnd-Ordenes#otros-archivos">Otros archivos</a> 

## Algunas Vistas (Prueba del funcionamiento con Postman)
- **Vista de getCustomers (localhost:8080/customers) - METODO GET**	
![Vista de getCustomers](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/1.png)

- **Vista de getCustomer (localhost:8080/customer/1) - METODO GET**
![Vista de getCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/2.png)

- **Vista de createCustomer (localhost:8080/customer/) - METODO POST**
![Vista de createCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/3.png)

- **Vista de updateCustomer (localhost:8080/customer/) - METODO PUT**
![Vista de updateCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/4.png)

- **Vista de deleteCustomer (localhost:8080/customer/6) - METODO DELETE**
![Vista de deleteCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/5.png)

- **Vista de getOrderCustomer (localhost:8080/order/customer/1) - METODO GET**
![Vista de getOrderCustomer](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/6.png)

- **Vista de getOrderCustomerDates (localhost:8080/order/customer/1/desde/2017-01-07/hasta/2018-01-02) - METODO GET**
![Vista de getOrderCustomerDates](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/7.png)

- **Vista de getOrderCustomerLastMonth (localhost:8080/order/customer/1/ultimoMes) - METODO GET**
![Vista de getOrderCustomerLastMonth](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/8.png)

## Test Unitarios
- **CustomerSpringOrdenesApplicationTests**
![Vista de CustomerSpringOrdenesApplicationTests](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/9.png)

  **SALIDA EN CONSOLA**:
	
	1. Exito en getCustomerNull
	2. Exito en getCustomer con los datos:  {customerId=1, name=Lina Marcela Malaver Gómez, email=marcela9409@gmail.com, products=[{"productId":2,"name":"Tablet HUAWEI T1-701W WiFi - G","price":300000.0},{"productId":1,"name":"Tablet LENOVO Yoga 3 8\"\"  N","price":430000.0}]}
	3. Exito en deleteCustomer con salida: true
	4. Unique index or primary key violation: "UK_CRKJMJK1OJ8GB6J6T5KT7GCXM_INDEX_5 ON PUBLIC.CUSTOMER(NAME) VALUES (STRINGDECODE('Lina Marcela Malaver G\u00f3mez'), 1)"; SQL statement: insert into customer (customer_id, email, name) values (null, ?, ?) [23505-193]. Exito en createCustomerNull con los datos: .
	5. Exito en deleteCustomerNull con salida: false
	6. Exito en updateCustomerNull con los salida: .
	7. Exito en updateCustomer con los datos: {customerId=1, name=Marce, email=marcecorreo@mail.com, products=[]}
	8. Exito en createCustomer con los datos:  {customerId=7, name=Yehynny, email=jas@mail.com, products=[]}
	9. Exito en getCustomers con los datos: 5.  [{"customerId":1,"name":"Marce","email":"marcecorreo@mail.com","products":[]},{"customerId":3,"name":"Jeisson Guerrero Quezada","email":"jeisson@gmail.com","products":[{"productId":2,"name":"Tablet HUAWEI T1-701W WiFi - G","price":300000.0},{"productId":1,"name":"Tablet LENOVO Yoga 3 8\"\"  N","price":430000.0},{"productId":4,"name":"Morral ASUS 16\"\" Argo Negro","price":70000.0},{"productId":3,"name":"iMac MNDY2E\/A 1TB 21.5\"\"","price":5000000.0}]},{"customerId":4,"name":"Jose Hernán Castañeda","email":"hernan@live.com","products":[{"productId":4,"name":"Morral ASUS 16\"\" Argo Negro","price":70000.0}]},{"customerId":5,"name":"Lina Maria Leon Blanco","email":"lina@yahoo.es","products":[{"productId":2,"name":"Tablet HUAWEI T1-701W WiFi - G","price":300000.0}]},{"customerId":7,"name":"Yehynny","email":"jas@mail.com","products":[]}]

- **OrderSpringOrdenesApplicationTests**
![Vista de OrderSpringOrdenesApplicationTests](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/10.png)

  **SALIDA EN CONSOLA**:
	
	1. Exito en getOrderCustomerLastMonth con los datos: [{"orderId":1,"deliveryAddress":"cra 56","creationDate":"2018-01-01","total":900000.0,"orderDetails":[{"orderDetailId":1,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":300000.0,"quantity":3}],"customerId":1,"customerName":"Lina Marcela Malaver Gómez"}]
	2. Exito en getOrdersCustomerDates con los datos:  [{"orderId":1,"deliveryAddress":"cra 56","creationDate":"2018-01-01","total":900000.0,"orderDetails":[{"orderDetailId":1,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":300000.0,"quantity":3}],"customerId":1,"customerName":"Lina Marcela Malaver Gómez"}]
	3. Exito en createOrder con los datos: {orderId=2, deliveryAddress=jgj 58686, creationDate=2018-01-09, total=1630000.0, orderDetails=[], customerId=1, customerName=Lina Marcela Malaver Gómez}
	4. Exito en getOrderNull:[]
	5. Exito en getOrders con los datos:  [{"orderId":1,"deliveryAddress":"cra 56","creationDate":"2018-01-01","total":900000.0,"orderDetails":[{"orderDetailId":1,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":300000.0,"quantity":3}],"customerId":1,"customerName":"Lina Marcela Malaver Gómez"},{"orderId":2,"deliveryAddress":"jgj 58686","creationDate":"2018-01-09","total":1630000.0,"orderDetails":[{"orderDetailId":3,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":430000.0,"quantity":1},{"orderDetailId":2,"productDescription":"Tablet HUAWEI T1-701W WiFi - G","price":300000.0,"quantity":4}],"customerId":1,"customerName":"Lina Marcela Malaver Gómez"}]
	6. Exito en getOrdersCustomer con los datos:  [{"orderId":1,"deliveryAddress":"cra 56","creationDate":"2018-01-01","total":900000.0,"orderDetails":[{"orderDetailId":1,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":300000.0,"quantity":3}],"customerId":1,"customerName":"Lina Marcela Malaver Gómez"},{"orderId":2,"deliveryAddress":"jgj 58686","creationDate":"2018-01-09","total":1630000.0,"orderDetails":[{"orderDetailId":2,"productDescription":"Tablet HUAWEI T1-701W WiFi - G","price":300000.0,"quantity":4},{"orderDetailId":3,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":430000.0,"quantity":1}],"customerId":1,"customerName":"Lina Marcela Malaver Gómez"}]
	7. Exito en getOrder (1) con los datos:  {orderId=1, deliveryAddress=cra 56, creationDate=2018-01-01, total=900000.0, orderDetails=[{"orderDetailId":1,"productDescription":"Tablet LENOVO Yoga 3 8\"\"  N","price":300000.0,"quantity":3}], customerId=1, customerName=Lina Marcela Malaver Gómez}
	
- **ProductSpringOrdenesApplicationTests**
![Vista de ProductSpringOrdenesApplicationTests](https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/README/11.png)

  **SALIDA EN CONSOLA**:
	
	1. Exito en getProductNull
	2. Exito en updateProduct con los datos: {productId=1, name=PC LENOVO Yoga 3 8""  N, price=430000.0}
	3. No class com.marcela.model.Product entity with id 200 exists!. Exito en deleteProductNull con salida:  false
	4. Unique index or primary key violation: "UK_JMIVYXK9RMGYSRMSQW15LQR5B_INDEX_1 ON PUBLIC.PRODUCT(NAME) VALUES ('Tablet HUAWEI T1-701W WiFi - G', 2)"; SQL statement: insert into product (product_id, name, price) values (null, ?, ?) [23505-193]. Exito en createProductNull con los datos: .
	5. Exito en deleteProduct con salida: true
	6. Exito en getProduct con los datos:  {productId=1, name=PC LENOVO Yoga 3 8""  N, price=430000.0}
	7. Exito en getProducts con los datos: 4.  [{"productId":1,"name":"PC LENOVO Yoga 3 8\"\"  N","price":430000.0},{"productId":2,"name":"Tablet HUAWEI T1-701W WiFi - G","price":300000.0},{"productId":3,"name":"iMac MNDY2E\/A 1TB 21.5\"\"","price":5000000.0},{"productId":4,"name":"Morral ASUS 16\"\" Argo Negro","price":70000.0}]
	8. Exito en updateProductNull con los salida: .
	9. Exito en createProduct con los datos: {productId=7, name=Tablet LENOVO Yoga 100 8""  N, price=430000.0}
	
## COMANDOS PARA GIT
	VERSION: git --version
	USUARIO: git config --global user.name "Marcela Malaver"
	EMAIL: git config --global user.email "marcela9409@gmail.com"
	ALIAS PARA LOGIN: git config --global alias.lg "log --oneline --decorate --all --graph"
	ALIAS PARA STATUS: git config --global alias.s "status -s"
	VER URL REPOSITORIO: git remote -v
	PONER URL REPOSITORIO: git remote add origin https://github.com/lmarcela/BackEnd-Ordenes.git
	SUBIR CAMBIOS: git push -u origin master
	
	VER ESTADO DE GIT: git s
	AÑADIR ARCHIVOS AL GIT: git add .
	CREAR COMMIT CON MENSAJE: git commit -m "ESTE ES MI MENSAJE"
	SUBIR AL REPOSITORIO (REVISAR PRIMERO URL DEL REPOSITORIO): git push -u origin master
	
	REVERSAR CAMBIOS EN EL GIT: git checkout -- .
	
## Configuracion en jenkins

Pipeline nombre: SpringOrdenes (BackEnd)
Pipeline
 
- Definition: pipeline script
- script:
 
node('master') {

    stage('checkout') {
    
        git 'https://github.com/lmarcela/BackEnd-Ordenes.git'
        
    }
    
    stage('build and test') {
    
       bat 'mvn test' 
       
    }
    
    stage('generate report') {
    
        archive "target/**/*"
        
        junit 'target/surefire-reports/*.xml'
        
    }
    
}

Plugins Recomendados: Blue Ocean, HTML Publisher, JUnit, Test Results Analyzer.

Instalaciones adicionales recomendadas: git, maven.
## Otros archivos
- SQL: https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/sql/
- Diagrama Entidad Relacion: https://github.com/lmarcela/BackEnd-Ordenes/blob/master/src/main/resources/static/img/Diagrama%20Entidad%20Relaci%C3%B3n.png
 
