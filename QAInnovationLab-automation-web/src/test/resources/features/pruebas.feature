#language: es
@testPrueba
Característica:Cuestionario

  @prueba
  Escenario: Validación del precio de un producto con usuario y clave válidos
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario: "joselin.raymundorimachi@gmail.com" y contraseña: "#1295055AA"
    Cuando navego a la categoria "Clothes" y subcategoria "Men"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

  Escenario: Validación de la autenticación con usuario y clave inválidos
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario: "joselin.raymundorimachi@gmail.com" y contraseña: "#129555AA"
    Entonces valido que debería aparecer un mensaje de error de autenticación
