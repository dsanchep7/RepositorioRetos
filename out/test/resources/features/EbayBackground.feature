Feature: HU-001 Buscador Ebay
  Yo como usuario de Ebay
  Quiero buscar un producto en la plataforma
  Para ver el nombre del producto en pantalla

  Background: Navegar en la pagina
    Given que me encuentro en la pagina de Ebay

  Scenario Outline: Busqueda de un producto
    When Encontrar un producto
      |<Producto>|
    Then voy a visualizar <Producto> en pantalla
    Examples:
      |Producto|
      |Google Pixel 3 XL 64GB Desbloqueado de fábrica pantalla de 6.3" 4G LTE Smartphone Nuevo|

  Scenario Outline: Busqueda de un producto
    When Encontrar un producto
      |<Producto>|
    Then voy a visualizar <Producto> en pantalla
    Examples:
      |Producto|
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|