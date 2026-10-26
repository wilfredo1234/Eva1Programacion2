package com.example.eva1programacion2.modelo

/**
 * Representa la cuenta total de la mesa, gestionando los ítems pedidos y el cálculo de la propina.
 * @param items Lista de ítems pedidos en la mesa.
 * @param aceptaPropina Indica si el cliente desea incluir la propina opcional del 10%.
 */
class CuentaMesa(
    val items: MutableList<ItemMesa> = mutableListOf(),
    var aceptaPropina: Boolean = true
) {
    /**
     * Agrega o reemplaza un ítem en la lista de la cuenta.
     */
    fun agregarItem(item: ItemMesa) {
        items.add(item)
    }

    /**
     * Suma los subtotales de todos los alimentos/ítems pedidos.
     */
    fun calcularSubtotalSinPropina(): Int {
        return items.sumOf { it.calcularSubtotal() }
    }

    /**
     * Calcula el 10% del subtotal de alimentos si se aceptó la propina voluntaria.
     */
    fun calcularPropina(): Int {
        return if (aceptaPropina) {
            (calcularSubtotalSinPropina() * 0.10).toInt()
        } else {
            0
        }
    }

    /**
     * Calcula el monto total a pagar (subtotal alimentos + propina si aplica).
     */
    fun calcularTotalConPropina(): Int {
        return calcularSubtotalSinPropina() + calcularPropina()
    }
}
