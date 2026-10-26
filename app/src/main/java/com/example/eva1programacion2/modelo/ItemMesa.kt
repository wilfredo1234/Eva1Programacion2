package com.example.eva1programacion2.modelo

/**
 * Representa un ítem solicitado en la mesa con su respectiva cantidad.
 * @param itemMenu El producto del menú seleccionado.
 * @param cantidad Cantidad de unidades pedidas.
 */
data class ItemMesa(
    val itemMenu: ItemMenu,
    var cantidad: Int
) {
    /**
     * Calcula el subtotal multiplicando la cantidad por el precio del ítem.
     */
    fun calcularSubtotal(): Int {
        return itemMenu.precio * cantidad
    }
}
