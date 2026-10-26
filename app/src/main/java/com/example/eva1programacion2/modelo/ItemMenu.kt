package com.example.eva1programacion2.modelo

/**
 * Representa un plato o ítem disponible en el menú del restaurante.
 * @param nombre Nombre descriptivo del plato.
 * @param precio Precio unitario en pesos chilenos (CLP).
 */
data class ItemMenu(
    val nombre: String,
    val precio: Int
)
