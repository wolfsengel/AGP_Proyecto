# AGP_Proyecto
#### Hecho por Ángel Gómez Prol  2ºDAM
#### 2023-2024  
  
## Descripción
El proyecto consiste en una aplicación de escritorio que permite gestionar un carrito de la compra de una tienda online. La aplicación está desarrollada en Java, usando el framework JavaFX para crear la interfaz gráfica de usuario. La aplicación se conecta a una base de datos embebida H2, que almacena los datos de los productos, los clientes y los pedidos. Para acceder a la base de datos, la aplicación usa el estándar JPA (Java Persistence API) y la implementación Hibernate, que facilitan el mapeo entre las clases de entidad y las tablas de la base de datos. La aplicación usa el patrón DAO (Data Access Object) para encapsular las operaciones de acceso a datos, y el patrón DTO (Data Transfer Object) para transferir los datos entre las capas de la aplicación. La aplicación permite al usuario realizar las siguientes acciones:

- Ver el catálogo de productos disponibles, filtrados por categoría.
- Añadir productos al carrito de la compra, indicando la cantidad deseada.
- Ver el detalle del carrito de la compra, con el precio total y los descuentos aplicados.
- Eliminar productos del carrito de la compra.
- Seleccionar un cliente existente o crear uno nuevo para realizar el pedido.
- Confirmar el pedido y generar un comprobante de compra.
- Ver el historial de pedidos realizados por un cliente.
