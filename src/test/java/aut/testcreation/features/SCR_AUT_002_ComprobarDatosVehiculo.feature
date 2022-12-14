Feature: Comprobar datos vehiculo
  Se comprobaran que los datos de los vehiculos sean los correctos en diferentes busquedas y tabs

  Background:
    Given El Usuario navega a la página de Login "pUrl"
    And El Usuario clickea el botón I Agree
    And El Usuario ingresa el nombre de Usuario "pUsername"
    And El Usuario ingresa la Contraseña "pPassword"
    And El Usuario clickea el botón Sign In

  @aut
  Scenario: SC_002_02_proximoMantenimientoCorrecto
    When El Usuario obtiene el valor del Próximo Mantenimiento del primer Vehiculo
    And El Usuario clickea en el primer VIN
    And El Usuario obtiene el valor del Próximo Mantenimiento en la página de información del Vehiculo
    Then El Usuario compara que ambos Valores sean iguales

  @aut
  Scenario: SC_002_05_verificarRedireccionVehiculo
    When El Usuario obtiene el Nombre del vehiculo
    And El Usuario clickea en el primer VIN
    And El Usuario obtiene el Nombre del vehiculo en la página de información del Vehiculo
    Then El Usuario compara que ambos Valores sean iguales

