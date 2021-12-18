Feature: HU-001 Buscador Ebay
  Yo como usuario de Ebay
  Quiero buscar un producto en la plataforma
  Para ver el nombre del producto en pantalla

  Scenario: Busqueda exitosa de un producto
    Given que me encuentro en la pagina de Ebay
    When Hallar un producto
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|
    Then Habilitar en pantalla
      |Auriculares Bluetooth para iPhone Samsung Android Inalámbrico Auriculares IPX7 Impermeable|



  Scenario: Busqueda fallida de un producto
    Given que me encuentro en la pagina de Ebay
    When Hallar un producto
      |Google Pixel 2 XL 64GB Unlocked|
    Then Habilitar en pantalla
      |Google Pixel 2 XL 64GB Unlocked0|