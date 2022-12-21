Feature: Busquedas Google
  Se realizaran una serie de busquedas desde la pagina principal de google

  @aut
  Scenario: SC_001_02_buscarTsoft
    Given que estoy en el Home de Google
    When busco la palabra "pBusqueda" en el navegador
    And presiono el boton buscar
    Then me lleva a la pagina de resultados "pEsperado"

  @aut
  Scenario: SC_001_03_buscarScotiaBank
    Given que estoy en el Home de Google
    When busco la palabra "pBusqueda" en el navegador
    And presiono el boton buscar
    Then me lleva a la pagina de resultados "pEsperado"
    Then linea de cucumber de mentira