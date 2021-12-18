Feature: HU-001 Buscador Ebay
  Yo como usuario de Ebay
  Quiero buscar un producto en la plataforma
  Para ver el nombre del producto en pantalla

  Scenario Outline: Buscar producto
    Given que me encuentro en la pagina de Ebay
    When busque el producto <NombreProducto>
    Then podre ver <NombreProducto> en pantalla
    Examples:
      |NombreProducto|
      |Lenovo IdeaPad Gaming 3 15.6 120Hz Laptop para Juegos AMD Ryzen 5-5600H 8GB Ram 512G|
      |Controlador inalámbrico Xbox choque Azul-inalámbrico y conectividad Bluetooth-Nuevo|
      |Samsung Galaxy S9 G960 Verizon TracFone Straight Talk total liberado B Page Plus|
      |Cable para Samsung Galaxy S20 S21 Cable Usb-C A USB-Carga Tipo C Charger C|
      |Tcl 43P615K 43 pulgadas 4K Ultra HD Smart Android Tv|
